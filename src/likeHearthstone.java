
import java.io.*;
import java.util.StringTokenizer;

public class likeHearthstone {
    static class card {
        int cost, value;
        
        card(int c, int v) {
            cost = c;
            value = v;
        }
    }
    
    public static int pack(card[] cards, int c) {
        int n = cards.length;
        int[][] bag = new int[c + 1][30];
//        int[][] num = new int[2][c + 1];
        int get;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= c; j++) {
                for (int k = 1; k < 31; k++) {
                    bag[j][k] = Math.max(bag[j][k], cards[i].value + bag[j - cards[i].cost][k - 1]);
                }
//                bag[i % 2][j] = bag[(i - 1) % 2][j];
//                num[i % 2][j] = num[(i - 1) % 2][j];
//                if (j >= cards[i].cost) {
//                    get = cards[i].value + bag[(i - 1) % 2][j - cards[i].cost];
//                    if (bag[i % 2][j] < get&&num[(i - 1) % 2][j - cards[i].cost]<30) {
//                        bag[i % 2][j] = get;
//                        num[i % 2][j] = num[(i - 1) % 2][j - cards[i].cost] + 1;
//                    }
//                }
            }
        }
        return bag[0][c];
    }
    
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        int c = in.nextInt();
        card[] cards = new card[91];
        int cost, a, h;
        for (int i = 1; i < 91; i++) {
            cost = in.nextInt();
            a = in.nextInt();
            h = in.nextInt();
            cards[i] = new card(cost, a + h);
        }
        out.println(pack(cards, c));
        out.close();
    }
    
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
}
