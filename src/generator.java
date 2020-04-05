
public class generator {
    public static void main(String[] args) {
        int n = (int)(Math.random() * 5);
        System.out.println(n);
        for (int i = 0; i < n; i++) {
            int start =(int)(Math.random()*10);
            System.out.print(start);
            System.out.print(" ");
            int end = (int)(Math.random()*10);
            while (end<start){
                end = (int)(Math.random()*10);
            }
            System.out.print(end);
            System.out.println();
        }
    }
}
