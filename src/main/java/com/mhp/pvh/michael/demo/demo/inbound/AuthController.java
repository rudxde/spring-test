package com.mhp.pvh.michael.demo.demo.inbound;

import com.mhp.pvh.michael.demo.demo.auth.dto.ChangePasswordRequest;
import com.mhp.pvh.michael.demo.demo.auth.dto.CreateUserRequest;
import com.mhp.pvh.michael.demo.demo.auth.dto.EncodedSessionToken;
import com.mhp.pvh.michael.demo.demo.auth.dto.LoginRequest;
import com.mhp.pvh.michael.demo.demo.auth.entities.UserAccount;
import com.mhp.pvh.michael.demo.demo.auth.inbound.AuthServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class AuthController {
    @Autowired
    private AuthServiceProvider authService;

    @PostMapping("login")
    public ResponseEntity<EncodedSessionToken> login(
            @RequestBody LoginRequest loginRequest) {
        EncodedSessionToken result = this.authService.login(loginRequest);
        return ResponseEntity.ok(result);
    }

    @PostMapping("user")
    public ResponseEntity createUser(
            @RequestHeader("authorization") String authHeader,
            @RequestBody CreateUserRequest createUserRequest) {
        UserAccount account = this.authService.getAccountFromToken(new EncodedSessionToken(authHeader));
        if (!account.getGroup().equals("admin")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Only the users from the admin group are allowed to create other users");
        }
        this.authService.createUser(createUserRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("password")
    public ResponseEntity changePassword(
            @RequestBody ChangePasswordRequest changePasswordRequest) {
        this.authService.changePassword(changePasswordRequest);
        return ResponseEntity.ok().build();
    }

}
