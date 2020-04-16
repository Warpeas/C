
public class generator {
    public static void main(String[] args) {
//        int n = (int)(Math.random() * 5);
        int n = (int) (5000 * Math.random());
        n = 50;
        System.out.println(n);
        for (int i = 0; i < n; i++) {
            int s, e, w;
            s = (int) (Math.random() * 100);
            e = (int) (Math.random() * 100);
            while (e < s) {
                e = (int) (Math.random() * 100000000);
            }
            w = (int) (Math.random() * 10);
            while (w == 0) {
                w = (int) (Math.random() * 10);
            }
            System.out.printf("%d %d %d", s, e, w);
            System.out.println();
        }
    }
}

