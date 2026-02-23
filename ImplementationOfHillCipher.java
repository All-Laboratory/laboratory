import java.util.Scanner;

public class ImplementationOfHillCipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input plaintext
        System.out.print("Enter 2-letter Plain Text: ");
        String text = sc.next().toUpperCase();
        int[] pt = {text.charAt(0) - 'A', text.charAt(1) - 'A'};

        // Input 2x2 key matrix
        int[][] key = new int[2][2];
        System.out.println("Enter 2x2 Key Matrix:");
        for(int i=0;i<2;i++)
            for(int j=0;j<2;j++)
                key[i][j] = sc.nextInt();

        // Encrypt
        int c1 = (key[0][0]*pt[0] + key[0][1]*pt[1]) % 26;
        int c2 = (key[1][0]*pt[0] + key[1][1]*pt[1]) % 26;
        System.out.println("Cipher Text: " + (char)(c1+'A') + (char)(c2+'A'));

        // Compute inverse key determinant
        int det = key[0][0]*key[1][1] - key[0][1]*key[1][0];
        det = ((det % 26) + 26) % 26;

        // Find modular inverse of determinant
        int invDet = 0;
        for(int i=1;i<26;i++){
            if((det*i)%26 == 1) { invDet=i; break; }
        }

        // Compute inverse key matrix
        int[][] invKey = new int[2][2];
        invKey[0][0] =  key[1][1]*invDet %26;
        invKey[0][1] = (-key[0][1]*invDet + 26)%26;
        invKey[1][0] = (-key[1][0]*invDet +26)%26;
        invKey[1][1] =  key[0][0]*invDet %26;

        // Decrypt
        int p1 = (invKey[0][0]*c1 + invKey[0][1]*c2) %26;
        int p2 = (invKey[1][0]*c1 + invKey[1][1]*c2) %26;

        System.out.println("Decrypted Text: " + (char)(p1+'A') + (char)(p2+'A'));
    }
}
