package likelion.univ.utils;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import likelion.univ.exception.PublicKeyGenerationException;

public class PublicKeyGenerator {

    public static RSAPublicKey execute(String kty, String n, String e) {
        BigInteger modulus = new BigInteger(1, Base64.getUrlDecoder().decode(n));
        BigInteger exponent = new BigInteger(1, Base64.getUrlDecoder().decode(e));

        try {
            KeyFactory keyFactory = KeyFactory.getInstance(kty);
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus, exponent);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException exception) {
            throw new PublicKeyGenerationException();
        }
    }
}
