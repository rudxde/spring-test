package com.mhp.pvh.michael.demo.demo.auth.logic;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mhp.pvh.michael.demo.demo.auth.dto.EncodedSessionToken;
import com.mhp.pvh.michael.demo.demo.auth.entities.SessionToken;
import com.mhp.pvh.michael.demo.demo.auth.inbound.TokenServiceProvider;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService implements TokenServiceProvider {

    public static final String TOKEN_SUBJECT = "Session Token";
    public static final String TOKEN_ISSUER = "MHP";
    public static final String TOKEN_CLAIM_ID = "userId";
    public static final String TOKEN_CLAIM_USERNAME = "userName";
    public static final String TOKEN_CLAIM_GROUP = "group";
    // TODO: load over config
    private final String secret = "secret";

    public SessionToken readAndCheckToken(EncodedSessionToken encodedToken) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject(TOKEN_SUBJECT)
                .withIssuer(TOKEN_ISSUER)
                .build();
        DecodedJWT jwt = verifier.verify(encodedToken.getToken());
        return new SessionToken(
                jwt.getClaim(TOKEN_CLAIM_ID).asString(),
                jwt.getClaim(TOKEN_CLAIM_USERNAME).asString(),
                jwt.getClaim(TOKEN_CLAIM_GROUP).asString()
        );
    }

    public EncodedSessionToken encodeToken(SessionToken sessionToken) {
        String token = JWT.create()
                .withSubject(TOKEN_SUBJECT)
                .withClaim(TOKEN_CLAIM_ID, sessionToken.getUserId())
                .withClaim(TOKEN_CLAIM_USERNAME, sessionToken.getUsername())
                .withClaim(TOKEN_CLAIM_GROUP, sessionToken.getGroup())
                .withIssuedAt(new Date())
                .withIssuer(TOKEN_ISSUER)
                .sign(Algorithm.HMAC256(secret));
        return new EncodedSessionToken(token);
    }
}
