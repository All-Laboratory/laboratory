import java.util.*;

public class dig3 {

    static int modPow(int base, int exp, int mod) {
        int result = 1;
        base = base % mod;

        while (exp > 0) {
            if (exp % 2 == 1)
                result = (result * base) % mod;

            base = (base * base) % mod;
            exp /= 2;
        }
        return result;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter prime p : ");
        int p = sc.nextInt();

        System.out.print("Enter prime q (q divides p-1) : ");
        int q = sc.nextInt();

        System.out.print("Enter generator g : ");
        int g = sc.nextInt();

        System.out.print("Enter private key x : ");
        int x = sc.nextInt();

        System.out.print("Enter message (integer) : ");
        int m = sc.nextInt();

        System.out.print("Enter random k : ");
        int k = sc.nextInt();

        int r = modPow(g, k, p);
        int e = (m + r) % q;   // simple hash (lab version)
        int s = (k + x * e) % q;

        System.out.println("\n--- Signature ---");
        System.out.println("e = " + e);
        System.out.println("s = " + s);

        int y = modPow(g, x, p);

        int v1 = modPow(g, s, p);
        int v2 = (r * modPow(y, e, p)) % p;

        System.out.println("\n--- Verification ---");
        System.out.println("V1 = " + v1);
        System.out.println("V2 = " + v2);

        if (v1 == v2)
            System.out.println("\nSignature is VALID");
        else
            System.out.println("\nSignature is INVALID");

        sc.close();
    }
}
