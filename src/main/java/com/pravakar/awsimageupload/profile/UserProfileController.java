package com.pravakar.awsimageupload.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/user-profile")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping
    public List<UserProfile> getUserProfiles(){
        return userProfileService.getUserProfiles();
    }

    @PostMapping(

            path ="{userProfileId}/image/upload",
            produces = {"*/*"},
            consumes = {"*/*"}
    )

    public void uploadUserProfileImage(@PathVariable("userProfileId") UUID userProfileId, @RequestParam("file")MultipartFile file) throws IOException {
        userProfileService.uploadUserProfileImage(userProfileId,file);
    }


}
