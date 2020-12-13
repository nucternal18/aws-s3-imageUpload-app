package com.aolausoro.tech.dgitaloceanimageupload.bucket;

public enum BucketName {

    PROFILE_IMAGE("aolausoro.tech-image-upload-123");

    private final String bucketName;


    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
