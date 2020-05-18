public class card {
    public static void main(String[] args) {
        int mc = (int) (Math.random() * 300);
//        mc = 30;
        System.out.println(mc);
        int c, a, h;
        for (int i = 0; i < 90; i++) {
            c = (int) (Math.random() * 10);
            a = (int) (Math.random() * 10);
            h = (int) (Math.random() * 10);
//            c = 1;
//            a = 1;
//            h = 1;
            System.out.printf("%d %d %d\n", c, a, h);
        }
    }
}
