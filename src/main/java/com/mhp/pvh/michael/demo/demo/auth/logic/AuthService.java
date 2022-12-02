package com.mhp.pvh.michael.demo.demo.auth.logic;

import com.mhp.pvh.michael.demo.demo.auth.dto.ChangePasswordRequest;
import com.mhp.pvh.michael.demo.demo.auth.dto.CreateUserRequest;
import com.mhp.pvh.michael.demo.demo.auth.dto.LoginRequest;
import com.mhp.pvh.michael.demo.demo.auth.dto.EncodedSessionToken;
import com.mhp.pvh.michael.demo.demo.auth.entities.SessionToken;
import com.mhp.pvh.michael.demo.demo.auth.entities.UserAccount;
import com.mhp.pvh.michael.demo.demo.auth.inbound.AuthServiceProvider;
import com.mhp.pvh.michael.demo.demo.auth.inbound.TokenServiceProvider;
import com.mhp.pvh.michael.demo.demo.auth.outbound.UserStorageProvider;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService implements AuthServiceProvider {

    @Autowired
    private PasswordHasher passwordHasher;

    @Autowired
    private UserStorageProvider userStorageProvider;

    @Autowired
    private TokenServiceProvider tokenServiceProvider;

    @PostConstruct
    public void init() {
        if (this.userStorageProvider.getAllUsers().length == 0) {
            this.createInitialUser();
        }
    }

    public EncodedSessionToken login(LoginRequest loginRequest) {
        boolean isValidLogin = false;
        UserAccount user = this.userStorageProvider.getUserByName(loginRequest.getUsername());
        if (user != null) {
            isValidLogin = this.passwordHasher.verifyPassword(loginRequest.getPassword(), user.getPasswordHash());
        }
        if (!isValidLogin) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password wrong");
        }
        return this.tokenServiceProvider
                .encodeToken(new SessionToken(user.getUserId(), user.getUsername(), user.getGroup()));
    }

    public void logout(EncodedSessionToken sessionToken) {
        return;
    }

    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        boolean isValidLogin = false;
        UserAccount user = this.userStorageProvider.getUserByName(changePasswordRequest.getUsername());
        if (user != null) {
            isValidLogin = this.passwordHasher.verifyPassword(changePasswordRequest.getOldPassword(),
                    user.getPasswordHash());
        }
        if (!isValidLogin) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password wrong");
        }
        String newPasswordHash = this.passwordHasher.hashPassword(changePasswordRequest.getNewPassword());
        user.setPasswordHash(newPasswordHash);
        this.userStorageProvider.updateUserAccount(user.getUserId(), user);
    }

    public UserAccount getAccountFromToken(EncodedSessionToken sessionToken) {
        SessionToken token = this.tokenServiceProvider.readAndCheckToken(sessionToken);
        UserAccount userAccount = this.userStorageProvider.getUserById(token.getUserId());
        if (userAccount == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password wrong");
        }
        return userAccount;
    }

    public String createUser(CreateUserRequest createUserRequest) {
        String id = UUID.randomUUID().toString();
        String passwordHash = this.passwordHasher.hashPassword(createUserRequest.getPassword());
        this.userStorageProvider.createUserAccount(
                new UserAccount(id, createUserRequest.getUsername(), passwordHash, createUserRequest.getGroup()));
        return id;
    }

    private void createInitialUser() {
        this.createUser(new CreateUserRequest("admin", "password", "admin"));
    }
}
