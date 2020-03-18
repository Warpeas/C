
public class generator {
    public static void main(String[] args) {
        int n = (int)(Math.random() * 500000);
        System.out.println(n);
        for (int i = 0; i < n; i++) {
            System.out.print((int)(Math.random()*100));
            System.out.print(" ");
        }
    }
}
