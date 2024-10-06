package com.example.authservice.util;

import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;

import java.text.ParseException;
import java.util.*;
import java.security.interfaces.*;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*;

import com.example.authservice.config.JwtSecrets;

public class JwtTokenProvider {

    RSAPrivateKey privateKey = JwtSecrets.getPrivateKey();
    RSAPublicKey publicKey = JwtSecrets.getPublicKey();

    // nimbusds's jwt claim default set's data
    String jwtissuer = "http://localhost:9000";
    List<String> jwtaudience = Arrays.asList("http://localhost:8080", "http://localhost:9000");

     //Hint of JWT token Encryption type
    // RSA-EAEP-256 + ASE/GCM 128
     JWEHeader header = new JWEHeader(
             JWEAlgorithm.RSA_OAEP_256,
             EncryptionMethod.A128GCM
     );

     public String issueRefresh(int requestedExpiration, String authJWT){

         Date now = new Date();
         String userUID = getClaimOfToken(authJWT, "subject");

         //Create JWT claims
         JWTClaimsSet jwtClaims = new JWTClaimsSet.Builder()
                 .issuer(jwtissuer)
                 .subject(userUID)
                 .audience(jwtaudience)
                 .notBeforeTime(now)
                 .issueTime(now)
                 .expirationTime(new Date(now.getTime() +
                         1000 * 86400 * requestedExpiration))
                 //Token is usable for user request days
                 .jwtID(UUID.randomUUID().toString())
                 .build();

         EncryptedJWT jwt = new EncryptedJWT(header, jwtClaims);

         RSAEncrypter encrypter = new RSAEncrypter(publicKey);

         try {
             jwt.encrypt(encrypter);
         } catch (JOSEException e) {
             throw new RuntimeException(e);
         }
         //return serialized jwt Token
         return jwt.serialize();
     }
    public String issueRefresh(String authJWT){

        Date now = new Date();
        String userUID = getClaimOfToken(authJWT, "subject");

        //Create JWT claims
        JWTClaimsSet jwtClaims = new JWTClaimsSet.Builder()
                .issuer(jwtissuer)
                .subject(userUID)
                .audience(jwtaudience)
                .notBeforeTime(now)
                .issueTime(now)
                .expirationTime(new Date(now.getTime() +
                        1000 * 86400))
                //Token is usable for user default refresh token life is 1 day
                .jwtID(UUID.randomUUID().toString())
                .build();

        EncryptedJWT jwt = new EncryptedJWT(header, jwtClaims);

        RSAEncrypter encrypter = new RSAEncrypter(publicKey);

        try {
            jwt.encrypt(encrypter);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
        //return serialized jwt Token
        return jwt.serialize();
    }

     public String refreshByToken(String refreshToken){
         try{
            String userUID = getClaimOfToken(refreshToken, "subject");
            return issueJwt(userUID, false);}
         catch(Exception e){throw new RuntimeException(e);}
     }

     public String issueJwt(String userUID, Boolean is2ndAuthed){

         Date now = new Date();


         //Create JWT claims
         JWTClaimsSet jwtClaims = new JWTClaimsSet.Builder()
                 .issuer(jwtissuer)
                 .subject(userUID)
                 .audience(jwtaudience)
                 .notBeforeTime(now)
                 .issueTime(now)
                 .expirationTime(new Date(now.getTime() + 1000 * 1800))
                 //Token is usable for 30 minutes
                 .jwtID(UUID.randomUUID().toString())
                 .claim("2nd-authed", is2ndAuthed)
                 .build();

         EncryptedJWT jwt = new EncryptedJWT(header, jwtClaims);

         RSAEncrypter encrypter = new RSAEncrypter(publicKey);

         try {
             jwt.encrypt(encrypter);
         } catch (JOSEException e) {
             throw new RuntimeException(e);
         }

         //return serialized jwt Token
         return jwt.serialize();
     }

     // parse serialized token value to token object
     private EncryptedJWT parseToken(String serializedJWT){
         EncryptedJWT candidateToken = null;
         try {
             candidateToken = EncryptedJWT.parse(serializedJWT);
         } catch (ParseException e) {throw new RuntimeException(e);}

         return candidateToken;
     }

     // decrypt token
     private EncryptedJWT decryptToken(EncryptedJWT token){

         RSADecrypter decrypter = new RSADecrypter(privateKey);

         try{
             token.decrypt(decrypter);
         } catch (JOSEException e) { throw new RuntimeException(e);}
        return token;
     }

     private boolean validateToken(JWTClaimsSet claims){
         boolean vaildation = false;

         String issuer = claims.getIssuer();
         Date expire = claims.getExpirationTime();
         Date now = new Date();
         List<String> audience = claims.getAudience();
         if (issuer == jwtissuer && audience.equals(jwtaudience) && expire.before(now)) {
             vaildation = true;
         }
         return vaildation;
     }

     //get values of token
     public String getClaimOfToken(String recievedToken, String typeOfClaim){
         try {
             EncryptedJWT targetToken = decryptToken(parseToken(recievedToken));
             JWTClaimsSet claimsSet = targetToken.getJWTClaimsSet();
             if (validateToken(claimsSet)){
                return claimsSet.getClaim(typeOfClaim).toString();
             }
             else{throw new RuntimeException("token expired");}
         }
         catch (Exception e){throw new RuntimeException(e);}
     }
}
