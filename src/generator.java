
public class generator {
    public static void main(String[] args) {
//        int n = (int)(Math.random() * 5);
        int n = 10;
        System.out.println(n + " " + n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%d ", (int) (Math.random() * 1000));
            }
            System.out.println();
        }
    }
}
