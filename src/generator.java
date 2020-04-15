
public class generator {
    public static void main(String[] args) {
//        int n = (int)(Math.random() * 5);
        int n = (int) (5000 * Math.random());
        n = 5000;
        System.out.println(n);
        for (int i = 0; i < n; i++) {
            System.out.printf("%d %d %d", (int) (Math.random() * 100000000), (int) (Math.random() * 100000000), (int) (Math.random() * 100000000));
            System.out.println();
        }
    }
}

