import java.util.Arrays;

public class test    {
    public static void main(String[] args) {
        int[]a={2,1,3};
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
        System.out.println();
        fun(a.clone());
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }
    public static void fun(int[]a){
        a[0]=1;
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }
}
