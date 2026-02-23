import java.util.*;

class src {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Plain Text: ");
        String text = sc.nextLine();

        System.out.print("Ent er Number of Columns: ");
        int cols = sc.nextInt();

        int rows = (int) Math.ceil((double) text.length() / cols);
        char[][] mat = new char[rows][cols];

        int k = 0;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                mat[i][j] = (k < text.length()) ? text.charAt(k++) : 'X';

        String cipher = "";
        for (int j = 0; j < cols; j++)
            for (int i = 0; i < rows; i++)
                cipher += mat[i][j];

        System.out.println("Encrypted Text : " + cipher);

        /* --------- ADDED DECRYPTION LOGIC (NO CHANGES ABOVE) --------- */

        char[][] decMat = new char[rows][cols];
        k = 0;

        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < rows; i++) {
                decMat[i][j] = cipher.charAt(k++);
            }
        }

        String decrypted = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                decrypted += decMat[i][j];
            }
        }

        decrypted = decrypted.replaceAll("X+$", "");

        System.out.println("Decrypted Text : " + decrypted);

        /* ------------------------------------------------------------- */

        sc.close();
    }
}
