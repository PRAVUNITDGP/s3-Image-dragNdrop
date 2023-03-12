package com.pravakar.awsimageupload.buckets;

public enum BucketName {
    PROFILE_IMAGE("pravakar-image-upload");

    private final String bucketName ;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
