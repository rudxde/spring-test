package com.mhp.pvh.michael.demo.demo.auth.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class SessionToken {
    private String UserId;
    @Getter
    private String Username;
    @Getter
    private String Group;
}
