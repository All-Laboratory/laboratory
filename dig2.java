import java.util.*;

public class dig2 {


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

    static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++)
            if ((a * x) % m == 1)
                return x;
        return 1;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter prime number p : ");
        int p = sc.nextInt();

        System.out.print("Enter primitive root g : ");
        int g = sc.nextInt();

        System.out.print("Enter private key x : ");
        int x = sc.nextInt();

        System.out.print("Enter random number k : ");
        int k = sc.nextInt();

        System.out.print("Enter message : ");
        int m = sc.nextInt();

        // ❗ Ensure k is valid
        if (gcd(k, p - 1) != 1) {
            System.out.println("Invalid k! gcd(k, p-1) must be 1");
            return;
        }

        int y = modPow(g, x, p);

        int r = modPow(g, k, p);
        int kInv = modInverse(k, p - 1);
        int s = (kInv * (m - x * r)) % (p - 1);

        if (s < 0) s += (p - 1);

        System.out.println("\n--- Signature ---");
        System.out.println("r = " + r);
        System.out.println("s = " + s);

        int v1 = modPow(g, m, p);
        int v2 = (modPow(y, r, p) * modPow(r, s, p)) % p;

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
