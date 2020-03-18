import java.util.Scanner;

public class Elites_In_the_Casino_Answer {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        char[][] chars = new char[n][];
        for (int i = 0; i < n; i++) {
            chars[i] = in.next().toCharArray();
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result = (result + Shuffle(chars[i], chars[j])) % 998244353;
            }
        }
        System.out.println(result);
    }
    
    public static int Shuffle(char[] a, char[] b) {
        StringBuilder stringBuilder = new StringBuilder();
        int p = a.length;
        int q = b.length;
        if (p >= q) {
            for (int i = 0; i < p - q; i++) {
                stringBuilder.append(a[i]);
            }
            for (int i = 0; i < q; i++) {
                stringBuilder.append(a[i + p - q]);
                stringBuilder.append(b[i]);
            }
        } else {
            for (int i = 0; i < q - p; i++) {
                stringBuilder.append(b[i]);
            }
            for (int i = 0; i < p; i++) {
                stringBuilder.append(a[i]);
                stringBuilder.append(b[i + q - p]);
            }
        }
        return Integer.parseInt(stringBuilder.toString()) % 998244353;
    }
}
