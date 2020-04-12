
public class generator {
    public static void main(String[] args) {
//        int n = (int)(Math.random() * 5);
        int n = (int)(1000*Math.random());
        int m = (int)(1000*Math.random());
        System.out.println(n + " " + m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.printf("%d ", (int) (Math.random() * 100));
            }
            System.out.println();
        }
    }
}
