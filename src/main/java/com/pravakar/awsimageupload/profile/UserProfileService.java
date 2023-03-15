package com.pravakar.awsimageupload.profile;

import com.pravakar.awsimageupload.buckets.BucketName;
import com.pravakar.awsimageupload.filestore.FileStore;
import org.apache.http.entity.ContentProducer;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class UserProfileService {

    private final UserProfileDataAccessService userProfileDataAccessService;
    private final FileStore fileStore;
    @Autowired
    public UserProfileService(UserProfileDataAccessService userProfileDataAccessService , FileStore fileStore) {
        this.userProfileDataAccessService = userProfileDataAccessService;
        this.fileStore = fileStore;
    }

    List<UserProfile> getUserProfiles(){
        return userProfileDataAccessService.getuserProfiles();
    }


    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) throws IOException {
        //1.check if image is not empty
        isFileEmpty(file);
        //2. if file is an image
        isImage(file);
        //3. user exists
        UserProfile user = getUserProfileIsNotFound(userProfileId);
        //4 . grab some metadata from file if any
        Map<String, String> metadata = getMetadata(file);

        //Store the image in s3 and update database with s3
        String path = String.format("&s/%s", BucketName.PROFILE_IMAGE.getBucketName(),user.getUserProfileId());
        String fileName = String.format("&s-%s",file.getName(),UUID.randomUUID());
        try {
            fileStore.save(path,fileName,Optional.of(metadata),file.getInputStream());
        } catch (IOException e){
            throw new IllegalStateException(e);
        }
        
    }

    private static Map<String, String> getMetadata(MultipartFile file) {
        Map<String,String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length",String.valueOf(file.getSize()));
        return metadata;
    }

    private UserProfile getUserProfileIsNotFound(UUID userProfileId) {
        return userProfileDataAccessService
                .getuserProfiles()
                .stream()
                .filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("User profile is not Found")));
    }

    private static void isFileEmpty(MultipartFile file) {
        if(file.isEmpty()){
            throw new IllegalStateException("Can not Upload File : [ " + file.getSize()  + "]");
        }
    }

    private static void isImage(MultipartFile file) {
        if(!Arrays.asList(
                ContentType.IMAGE_JPEG.getMimeType(),
                ContentType.IMAGE_PNG.getMimeType(),
                ContentType.IMAGE_GIF.getMimeType()).contains(file.getContentType())){
            throw new IllegalStateException("File must be an image [" + file.getContentType() + "]");
        }
    }
}
