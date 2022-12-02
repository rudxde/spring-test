package com.mhp.pvh.michael.demo.demo.auth.inbound;

import com.mhp.pvh.michael.demo.demo.auth.dto.ChangePasswordRequest;
import com.mhp.pvh.michael.demo.demo.auth.dto.CreateUserRequest;
import com.mhp.pvh.michael.demo.demo.auth.dto.LoginRequest;
import com.mhp.pvh.michael.demo.demo.auth.dto.EncodedSessionToken;
import com.mhp.pvh.michael.demo.demo.auth.entities.UserAccount;

public interface AuthServiceProvider {
    EncodedSessionToken login(LoginRequest loginRequest);
    void logout(EncodedSessionToken sessionToken);
    void changePassword(ChangePasswordRequest changePasswordRequest);
    UserAccount getAccountFromToken(EncodedSessionToken sessionToken);
    String createUser(CreateUserRequest createUserRequest);
}
