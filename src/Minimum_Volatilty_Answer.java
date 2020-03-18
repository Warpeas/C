import java.util.Scanner;

public class Minimum_Volatilty_Answer {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int sum = a[0];
        for (int i = 1; i < n; i++) {
            int min = Math.abs(a[i] - a[0]);
            for (int j = i - 1; j >= 0; j--) {
                int result = Math.abs(a[i] - a[j]);
                if (result < min) {
                    min = result;
                }
            }
            sum += min;
        }
        System.out.println(sum);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
