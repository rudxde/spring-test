package com.mhp.pvh.michael.demo.demo.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class LoginRequest {

    LoginRequest() {
    }

    @Getter
    private String Username;
    @Getter
    private String Password;
}
