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
        USER_PROFILES.add(new UserProfile(UUID.fromString("b57a02f3-e7d4-4c52-92f5-1e1d3492bc20"), "MaryJane", null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("f1615253-ad52-4666-9ac6-71e97ca9ffa1"), "PeterParker", null));
    }

    public static List<UserProfile> getUserProfiles() {
        return USER_PROFILES;
    }
}
