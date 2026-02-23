import java.util.*;

class RailFenceCipher {

    // Encryption Method
    static String encrypt(String text, int key) {

        if (key <= 1 || key >= text.length())
            return text;

        char[][] rail = new char[key][text.length()];
        for (char[] row : rail)
            Arrays.fill(row, '\n');

        boolean down = false;
        int row = 0, col = 0;

        for (int i = 0; i < text.length(); i++) {
            if (row == 0 || row == key - 1)
                down = !down;

            rail[row][col++] = text.charAt(i);
            row += down ? 1 : -1;
        }

        String cipher = "";
        for (int i = 0; i < key; i++)
            for (int j = 0; j < text.length(); j++)
                if (rail[i][j] != '\n')
                    cipher += rail[i][j];

        return cipher;
    }

    // Decryption Method
    static String decrypt(String cipher, int key) {

        if (key <= 1 || key >= cipher.length())
            return cipher;

        char[][] rail = new char[key][cipher.length()];
        for (char[] row : rail)
            Arrays.fill(row, '\n');

        boolean down = false;
        int row = 0, col = 0;

        // Step 1: Mark zig-zag positions
        for (int i = 0; i < cipher.length(); i++) {
            if (row == 0 || row == key - 1)
                down = !down;

            rail[row][col++] = '*';
            row += down ? 1 : -1;
        }

        // Step 2: Fill cipher text row-wise
        int index = 0;
        for (int i = 0; i < key; i++)
            for (int j = 0; j < cipher.length(); j++)
                if (rail[i][j] == '*')
                    rail[i][j] = cipher.charAt(index++);

        // Step 3: Read zig-zag to get plaintext
        String text = "";
        down = false;
        row = col = 0;

        for (int i = 0; i < cipher.length(); i++) {
            if (row == 0 || row == key - 1)
                down = !down;

            text += rail[row][col++];
            row += down ? 1 : -1;
        }

        return text;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Plain Text: ");
        String text = sc.nextLine();

        System.out.print("Enter Number of Rails: ");
        int key = sc.nextInt();

        String cipher = encrypt(text, key);
        System.out.println("Encrypted Text : " + cipher);

        String decrypted = decrypt(cipher, key);
        System.out.println("Decrypted Text : " + decrypted);

        sc.close();
    }
}
