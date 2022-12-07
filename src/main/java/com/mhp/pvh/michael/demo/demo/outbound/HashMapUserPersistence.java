package com.mhp.pvh.michael.demo.demo.outbound;

import com.mhp.pvh.michael.demo.demo.auth.entities.UserAccount;
import com.mhp.pvh.michael.demo.demo.auth.outbound.UserStorageProvider;
import org.springframework.stereotype.Service;

import java.util.HashMap;

public class HashMapUserPersistence implements UserStorageProvider {

    private HashMap<String, UserAccount> idDb = new HashMap<>();
    private HashMap<String, String> nameIdDb = new HashMap<>();

    public UserAccount getUserById(String id) {
        return this.idDb.get(id);
    }

    public UserAccount getUserByName(String name) {
        return this.getUserById(this.nameIdDb.get(name));
    }

    public void updateUserAccount(String id, UserAccount updated) {
        if (!id.equals(updated.getUserId())) {
            throw new RuntimeException("User Id cannot be changed!");
        }
        UserAccount oldEntry = this.idDb.get(id);
        this.nameIdDb.remove(oldEntry.getUsername());
        this.idDb.put(id, updated);
        this.nameIdDb.put(updated.getUsername(), id);
    }

    public void createUserAccount(UserAccount insert) {
        this.idDb.put(insert.getUserId(), insert);
        this.nameIdDb.put(insert.getUsername(), insert.getUserId());
    }

    public UserAccount[] getAllUsers() {
        return this.idDb.values().toArray(new UserAccount[this.idDb.values().size()]);
    }
}
