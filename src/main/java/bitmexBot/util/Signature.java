package bitmexBot.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Signature {

    public String getSignature(String secretKey, String message) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac sha256_HMAC = null;
        try {
             sha256_HMAC = Mac.getInstance("HmacSHA256");
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }

        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8),"HmacSHA256" );
        try{
            sha256_HMAC.init(secretKeySpec);
        }catch (InvalidKeyException e){
            throw new RuntimeException();
        }

        byte[] haseBytes = sha256_HMAC.doFinal(message.getBytes(StandardCharsets.UTF_8));

        StringBuilder builder = new StringBuilder();

        for(byte b : haseBytes){
            builder.append(String.format("%02x",b));
        }
        return builder.toString();
    }
}
