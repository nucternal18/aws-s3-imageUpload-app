package com.aolausoro.tech.dgitaloceanimageupload.datastore;

import com.aolausoro.tech.dgitaloceanimageupload.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUerProfileDataStore {

    private static  final List<UserProfile> USER_PROFILES = new ArrayList<>();

    static {
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "MaryJane", null));
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "PeterParker", null));
    }

    public static List<UserProfile> getUserProfiles() {
        return USER_PROFILES;
    }
}
