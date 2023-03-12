package com.pravakar.awsimageupload.filestore;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.Option;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

public class FileStore {
    public final AmazonS3 s3 ;

    @Autowired
    public FileStore(AmazonS3 s3){
        this.s3 = s3 ;
    }

    public void save(String path , String fileName , Optional<Map<String,String>> optionalMetadata, InputStream inpStream){
        ObjectMetadata metadata = new ObjectMetadata();
        optionalMetadata.ifPresent(map -> {
            if(!map.isEmpty()){
                map.forEach(metadata::addUserMetadata);
            }
        });
        try{
            s3.putObject(path,fileName,inpStream,metadata);
        } catch (AmazonServiceException e) {
            throw new IllegalStateException("Failed to Store file in s3",e);
        }
    }

}
