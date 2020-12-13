package com.aolausoro.tech.dgitaloceanimageupload.profile;

import com.aolausoro.tech.dgitaloceanimageupload.datastore.FakeUerProfileDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserProfileDataAccessService {

    private final FakeUerProfileDataStore fakeUerProfileDataStore;

    @Autowired
    public UserProfileDataAccessService(FakeUerProfileDataStore fakeUerProfileDataStore) {
        this.fakeUerProfileDataStore = fakeUerProfileDataStore;
    }

    List<UserProfile> getUserProfiles() {
        return fakeUerProfileDataStore.getUserProfiles();
    }

}
