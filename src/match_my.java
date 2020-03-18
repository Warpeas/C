
import java.io.*;
import java.util.*;

public class match_my {
    static Queue<man> singles;
    static HashMap<String, woman> women;
    static HashMap<String, man> men;
    static int n;
    
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;
        
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }
        
        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
        
        public int nextInt() {
            return Integer.parseInt(next());
        }
        
        public long nextLong() {
            return Long.parseLong(next());
        }
        
        public double nextDouble() {
            return Double.parseDouble(next());
        }
        
        public char[] nextCharArray() {
            return next().toCharArray();
        }
    }
    
    public static class person {
        static int id_cnt = 0;
        String name;
        int id;
        
        public person(String name) {
            this.name = name;
            id = id_cnt++;
        }
    }
    
    public static class man extends person {
        Queue<woman> lovers;
        woman partner;
        
        public man(String name) {
            super(name);
            lovers = new LinkedList<>();
            partner = null;
        }
    }
    
    public static class woman extends person {
        HashMap<String, Integer> lovers;
        int matched;
        man partner;
        
        public woman(String name) {
            super(name);
            lovers = new HashMap<>();
            matched = n + 1;
            partner = null;
        }
    }
    
    public static int invite(man man) {
        while (!man.lovers.isEmpty()) {
            woman woman = man.lovers.peek();
            if (woman == null) {
                return 1;
            }
            int preference = woman.lovers.get(man.name);
            if (woman.matched > preference) {
                if (woman.partner != null) {
                    woman.partner.lovers.remove();
                    woman.partner.partner = null;
                    singles.add(woman.partner);
                }
                man.partner = woman;
                woman.partner = man;
                woman.matched = preference;
                return 0;
            }
            man.lovers.poll();
        }
        return 1;
    }
    
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        men = new HashMap<>();
        women = new HashMap<>();
        singles = new LinkedList<>();
        n = in.nextInt();
        String[][] names = new String[2 * n][n + 1];
        for (int i = 0; i < 2 * n; i++) {
            for (int j = 0; j < n + 1; j++) {
                names[i][j] = in.next();
            }
        }
//        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            woman woman = new woman(names[n + i][0]);
            women.put(names[n + i][0], woman);
            for (int j = 0; j < n; j++) {
                woman.lovers.put(names[n + i][j + 1], j);
            }
        }
        man[]men1=new man[n];
        for (int i = 0; i < n; i++) {
            men1[i] = new man(names[i][0]);
            men.put(names[i][0], men1[i]);
            for (int j = 0; j < n; j++) {
                men1[i].lovers.add(women.get(names[i][j + 1]));
            }
            singles.add(men1[i]);
        }
        while (!singles.isEmpty()) {
            invite(singles.poll());
        }
        for (man m :
                men1) {
            out.println(m.name + " " + m.partner.name);
        }
//        long stop = System.currentTimeMillis();
//        out.println(stop - start);
        out.close();
    }
}
