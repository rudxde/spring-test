package com.mhp.pvh.michael.demo.demo.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class ChangePasswordRequest {

    ChangePasswordRequest() {
    }

    @Getter
    private String Username;
    @Getter
    private String oldPassword;
    @Getter
    private String newPassword;
}
