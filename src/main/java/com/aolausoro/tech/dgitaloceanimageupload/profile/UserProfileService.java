package com.aolausoro.tech.dgitaloceanimageupload.profile;

import com.aolausoro.tech.dgitaloceanimageupload.bucket.BucketName;
import com.aolausoro.tech.dgitaloceanimageupload.filestore.FileStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
public class UserProfileService {

    private final UserProfileDataAccessService userProfileDataAccessService;
    private final FileStore fileStore;

    @Autowired
    public UserProfileService(UserProfileDataAccessService userProfileDataAccessService, FileStore fileStore) {
        this.userProfileDataAccessService = userProfileDataAccessService;
        this.fileStore = fileStore;
    }

    List<UserProfile> getUserProfiles() {
        return userProfileDataAccessService.getUserProfiles();
    }

    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
            // 1. check if image is not empty
        if(file.isEmpty()){
            throw new IllegalStateException("Cannot upload empty file [" + file.getSize() + "]");
        }
            // 2. check if file is an image
        if (!Arrays.asList(
                IMAGE_JPEG.getMimeType(),
                IMAGE_PNG.getMimeType(),
                IMAGE_GIF.getMimeType())
                .contains(file.getContentType())){
            throw new IllegalStateException("File must be an Image [" + file.getContentType() + "]");
        }
            // 3. check if the user exist in the database
        UserProfile user = getUserProfileOrThrow(userProfileId);
            // 4. grab some metadata from file in any
        Map<String, String> metadata =  new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
            // 5. store image in s3 and update database (userProfileImageLink) with s3 image link
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), user.getUserProfileId());
        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());
        try {
            fileStore.save(path, filename, Optional.of(metadata), file.getInputStream());
            user.setUserProfileImageLink(filename);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private UserProfile getUserProfileOrThrow(UUID userProfileId) {
        return userProfileDataAccessService
                .getUserProfiles()
                .stream()
                .filter(userProfile -> {
                    return userProfile.getUserProfileId().equals(userProfileId);
                })
                .findFirst()
                .orElseThrow(() -> {
                    return new IllegalStateException(String.format("User profile %s not found", userProfileId));
                });
    }

    byte[] downloadUserProfileImage(UUID userProfileId) {
        UserProfile user =  getUserProfileOrThrow(userProfileId);
        String path = String.format("%s/%s",
                BucketName.PROFILE_IMAGE.getBucketName(),
                user.getUserProfileId()
        );
       return user.getUserProfileImageLink().map(key -> fileStore.download(path, key)).orElse(new byte[0]);

    }
}
