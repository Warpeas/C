
public class generator {
    public static void main(String[] args) {
        int n = (int)(Math.random() * 2000000);
        System.out.println(n);
        for (int i = 0; i < n; i++) {
            System.out.print((int)(Math.random()*10000));
            System.out.print(" ");
        }
    }
}
