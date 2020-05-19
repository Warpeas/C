public class card {
    public static void main(String[] args) {
        int mc = (int) (Math.random() * 300);
//        mc = 30;
        System.out.println(mc);
        int c, a, h;
        System.out.println("15 30 30");
        System.out.println("15 30 30");
        for (int i = 2; i < 90; i++) {
            c = (int) (Math.random() * 10);
            a = (int) (Math.random() * 10);
            h = (int) (Math.random() * 10);
            System.out.printf("%d %d %d\n", c, a, h);
        }
    }
}
