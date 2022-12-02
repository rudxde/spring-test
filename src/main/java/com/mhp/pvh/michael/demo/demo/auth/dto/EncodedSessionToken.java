package com.mhp.pvh.michael.demo.demo.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class EncodedSessionToken {

    EncodedSessionToken() {
    }

    @Getter
    private String Token;
}
