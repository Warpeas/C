import java.util.Random;

public class generator {
    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
    
    public static void main(String[] args) {
//        int n = (int)(Math.random() * 5);
        int n = (int) (10 * Math.random());
        n = 1;
        System.out.println(n);
        int s;
        for (int i = 0; i < n; i++) {
            s = (int) (Math.random() * 10000);
            s = 8;
            System.out.println(getRandomString(s));
        }
    }
}

