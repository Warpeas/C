
public class generator {
    public static void main(String[] args) {
//        int n = (int)(Math.random() * 5);
        int n = (int) (2000 * Math.random());
        n=200;
        System.out.println(n);
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                System.out.printf("%d ", (int) (Math.random() * 1000000000));
            }
            System.out.println();
        }
    }
}

