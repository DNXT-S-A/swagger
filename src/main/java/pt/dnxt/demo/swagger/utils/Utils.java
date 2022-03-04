package pt.dnxt.demo.swagger.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class Utils {

    private final static String ADMIN_ROLE = "ADMIN";

    private final static String ALGORITHM = "SHA-256";

    public static HttpHeaders prepareResponseHeader(UUID uuid){
        HttpHeaders responseHeaders = new HttpHeaders();
        StringBuilder responseToken = new StringBuilder();
        responseToken.append("Bearer ");
        responseToken.append(uuid);
        responseHeaders.set("Authorization", responseToken.toString());

        return responseHeaders;
    }

    public static UUID getTokenFromAuthorization(String authorization){
        return UUID.fromString(authorization.split(" ")[1]);
    }

    public static String getTokenFromAuthorizationAdmin(String authorization){
        return authorization.split(" ")[1];
    }

    public static String adminHash() throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
        byte[] hashValue = digest.digest(ADMIN_ROLE.getBytes(StandardCharsets.UTF_8));

        return Base64.encodeBase64String(hashValue);
    }

    public static HttpHeaders prepareAdminHeader() throws NoSuchAlgorithmException {
        HttpHeaders responseHeaders = new HttpHeaders();
        StringBuilder responseToken = new StringBuilder();
        responseToken.append("Bearer ");
        responseToken.append(adminHash());
        responseHeaders.set("Authorization", responseToken.toString());

        return responseHeaders;
    }

    public static Boolean compareHashes(String hashParam) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
        byte[] hashValue = digest.digest(ADMIN_ROLE.getBytes(StandardCharsets.UTF_8));


        return hashParam.equals(Base64.encodeBase64String(hashValue));
    }
}
