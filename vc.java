import java.util.Scanner;

public class vc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Plain Text: ");
        String text = sc.next().toUpperCase();
        System.out.print("Enter Key: ");
        String key = sc.next().toUpperCase();

        String encrypted = "";
        for (int i = 0; i < text.length(); i++) {
            encrypted += (char)((text.charAt(i) - 'A' 
                    + key.charAt(i % key.length()) - 'A') % 26 + 'A');
        }
        System.out.println("Encrypted Text: " + encrypted);

        String decrypted = "";
        for (int i = 0; i < encrypted.length(); i++) {
            decrypted += (char)((encrypted.charAt(i) 
                    - key.charAt(i % key.length()) + 26) % 26 + 'A');
        }
        System.out.println("Decrypted Text: " + decrypted);
    }
}
