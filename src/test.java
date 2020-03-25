import java.util.Arrays;
import java.util.HashMap;

public class test    {
    public static void main(String[] args) {
        int rest = 3;
        int[]a={1,2,3};
        int[]b={2,1,3};
        Arrays.sort(b);
        int[] key1 = {Arrays.hashCode(a),rest};
        int[] key2 = {Arrays.hashCode(b),rest};
        HashMap<Integer,Integer>hashMap=new HashMap<>();
        hashMap.put(Arrays.hashCode(key1),1);
        if (hashMap.containsKey(Arrays.hashCode(key2))){
            System.out.println(hashMap.get(Arrays.hashCode(key2)));
        }
    }
}
