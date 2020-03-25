import java.util.Arrays;

public class test    {
    public static void main(String[] args) {
        int[][] a={{2,1,3},{1}};
        System.out.println(a[1]);
        System.out.println();
        fun(a.clone());
        System.out.println(a);
        System.out.println();
    }
    public static void fun(int[][]a){
        System.out.println(a);
        System.out.println();
    }
}
