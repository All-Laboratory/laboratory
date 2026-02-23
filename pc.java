import java.util.*;

public class pc {

    static char[][] matrix = new char[5][5];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Plain Text: ");
        String plaintext = sc.nextLine().toUpperCase().replace('J', 'I');

        System.out.print("Enter Key: ");
        String key = sc.nextLine().toUpperCase().replace('J', 'I');

        buildMatrix(key);
        String preparedText = prepareText(plaintext);
        String cipherText = encrypt(preparedText);

        System.out.println("Cipher Text: " + cipherText);
    }

    // Build 5x5 key matrix
    static void buildMatrix(String key) {
        StringBuilder sb = new StringBuilder();
        Set<Character> set = new LinkedHashSet<>();

        for (char c : key.toCharArray()) if (c != ' ') set.add(c);
        for (char c = 'A'; c <= 'Z'; c++) if (c != 'J') set.add(c);

        Iterator<Character> it = set.iterator();
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (it.hasNext()) matrix[i][j] = it.next();
    }

    // Prepare text: split into pairs, insert X if needed
    static String prepareText(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char a = text.charAt(i);
            sb.append(a);
            if (i + 1 < text.length()) {
                char b = text.charAt(i + 1);
                if (a == b) sb.append('X');
            }
        }
        if (sb.length() % 2 != 0) sb.append('X');
        return sb.toString();
    }

    // Encrypt pairs
    static String encrypt(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i);
            char b = text.charAt(i + 1);
            int[] posA = findPosition(a);
            int[] posB = findPosition(b);

            if (posA[0] == posB[0]) { // Same row
                sb.append(matrix[posA[0]][(posA[1]+1)%5]);
                sb.append(matrix[posB[0]][(posB[1]+1)%5]);
            } else if (posA[1] == posB[1]) { // Same column
                sb.append(matrix[(posA[0]+1)%5][posA[1]]);
                sb.append(matrix[(posB[0]+1)%5][posB[1]]);
            } else { // Rectangle
                sb.append(matrix[posA[0]][posB[1]]);
                sb.append(matrix[posB[0]][posA[1]]);
            }
        }
        return sb.toString();
    }

    static int[] findPosition(char c) {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (matrix[i][j] == c) return new int[]{i, j};
        return null;
    }
}
