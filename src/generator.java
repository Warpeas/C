
public class generator {
    public static void main(String[] args) {
//        int n = (int)(Math.random() * 5);
        int n = (int) (5000 * Math.random());
        n = 100;
        System.out.println(n);
        int[][] out = new int[n][3];
        for (int i = 0; i < n; i++) {
            int s, e, w;
            s = (int) (Math.random() * 5) + 1;
            e = s + (int) (Math.random() * 5);
            w = (int) (Math.random() * 10);
            while (w == 0) {
                w = (int) (Math.random() * 10);
            }
            System.out.printf("%d %d %d", s, e, w);
            System.out.println();
            out[i][0] = s;
            out[i][1] = e;
            out[i][2] = w;
        }
//        System.out.println(n);
//        for (int i = 0; i < n; i++) {
//            System.out.printf("%d %d %d\n", out[i][0], out[i][1], out[i][2]);
//        }
//        System.out.println();
//        String[] s = new String[2];
//        TheMaximumIncome_ST.main(s);
//        TheMaximumIncome.main(s);
    }
}

