import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class generator {
    public static long result1, result2;

    public static void main(String[] args) throws FileNotFoundException {
//        int n = (int)(Math.random() * 5);
        FileOutputStream fop;
        while (result2 == result1) {
            fop = new FileOutputStream("/home/hunter/Documents/C/ADAA/input.txt");
            PrintWriter out = new PrintWriter(fop);
            int n = (int) (5000 * Math.random());
            n = 3;
            out.println(n);
            int[][] outs = new int[n][3];
            int maximum = 0;
            for (int i = 0; i < n; i++) {
                int s, e, w;
                s = (int) (Math.random() * 2 + 1);
                e = s + (int) (Math.random() * 5);
                w = (int) (Math.random() * 3);
                while (w == 0) {
                    w = (int) (Math.random() * 6);
                }
                out.printf("%d %d %d", s, e, w);
                out.println();
                outs[i][0] = s;
                outs[i][1] = e;
                outs[i][2] = w;
                maximum += w;
            }
//            out.println(n);
//            for (int i = 0; i < n; i++) {
//                out.printf("%d %d %d\n", outs[i][0], outs[i][1], outs[i][2]);
//            }
//            out.println();
            out.println(maximum);
            out.close();
            String[] s = new String[2];
            TheMaximumIncome_ST.main(s);
            TheMaximumIncome.main(s);
        }
    }
}

