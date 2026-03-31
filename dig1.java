import java.security.*;
import java.util.Base64;
import java.util.Scanner;

public class dig1 {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter message: ");
        String message = sc.nextLine();

        // Generate key pair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
        keyGen.initialize(1024);
        KeyPair pair = keyGen.generateKeyPair();

        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();

        // Signing
        Signature sign = Signature.getInstance("SHA256withDSA");
        sign.initSign(privateKey);
        sign.update(message.getBytes());

        byte[] signatureBytes = sign.sign();
        String signature = Base64.getEncoder().encodeToString(signatureBytes);

        System.out.println("\nDigital Signature:");
        System.out.println(signature);

        // Verification
        Signature verify = Signature.getInstance("SHA256withDSA");
        verify.initVerify(publicKey);
        verify.update(message.getBytes());

        boolean result = verify.verify(signatureBytes);

        System.out.println("\nSignature Verification Result: " + result);

        sc.close();
    }
}
