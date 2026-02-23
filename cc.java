import java.util.Scanner;

public class cc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int key = 3; // default key
        System.out.print("Enter Plain Text: ");
        String text = sc.nextLine(); // allow spaces and keep lowercase

        String encrypted = "";
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                encrypted += (char)((Character.toUpperCase(c) - 'A' + key) % 26 + 'A');
            } else {
                encrypted += c; // keep spaces/punctuation as is
            }
        }
        System.out.println("Encrypted Text: " + encrypted);

        String decrypted = "";
        for (char c : encrypted.toCharArray()) {
            if (Character.isLetter(c)) {
                decrypted += (char)((c - 'A' - key + 26) % 26 + 'a'); // back to lowercase
            } else {
                decrypted += c;
            }
        }
        System.out.println("Decrypted Text: " + decrypted);
    }
}
