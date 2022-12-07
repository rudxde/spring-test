package com.mhp.pvh.michael.demo.demo.auth.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document( collection = "user")
public class UserAccount {
    @Getter
    private String UserId;
    @Getter
    private String Username;
    @Getter
    @Setter
    private String PasswordHash;
    @Getter
    private String Group;
}
