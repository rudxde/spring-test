package com.mhp.pvh.michael.demo.demo.auth.inbound;

import com.mhp.pvh.michael.demo.demo.auth.dto.EncodedSessionToken;
import com.mhp.pvh.michael.demo.demo.auth.entities.SessionToken;

public interface TokenServiceProvider {
    SessionToken readAndCheckToken(EncodedSessionToken encodedToken);
    EncodedSessionToken encodeToken(SessionToken sessionToken);
}
