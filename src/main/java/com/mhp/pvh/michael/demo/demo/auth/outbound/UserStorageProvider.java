package com.mhp.pvh.michael.demo.demo.auth.outbound;

import com.mhp.pvh.michael.demo.demo.auth.entities.UserAccount;

public interface UserStorageProvider {
    UserAccount getUserById(String id);
    UserAccount getUserByName(String name);
    void updateUserAccount(String id, UserAccount updated);
    void createUserAccount(UserAccount insert);
    UserAccount[] getAllUsers();
}
