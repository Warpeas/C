import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class TheMaximumIncome {
    static InputStream inputStream = System.in;
    static FileInputStream fin;
    static OutputStream outputStream = System.out;
    //    static InputReader in = new InputReader(inputStream);
    static InputReader in;
    static PrintWriter out = new PrintWriter(outputStream);
    static int[] time;
    static PriorityQueue<task> tasks;

    static class task implements Comparable<task> {
        int start, end;
        int pay;

        task(int s, int e, int p) {
            start = s;
            end = e;
            pay = p;
        }

        @Override
        public int compareTo(task task) {
            return end - task.end;
        }
    }

    static long getPayment() {
        long result = 0;
        int s, e, p, mp, mpv;
        while (!tasks.isEmpty()) {
            task take = tasks.poll();
            s = take.start;
            e = take.end;
            p = take.pay;
            mp = 0;
            mpv = Integer.MAX_VALUE;
            for (int i = s; i < e; i++) {
                mp = mpv < time[i] ? mp : i;
                mpv = Math.min(mpv, time[i]);
                if (time[i] == 0) {
                    time[i] = p;
                    result += p;
                    break;
                } else if (i + 1 == e && time[mp] < p) {
                    result += p - time[mp];
                    time[mp] = p;
                }
            }
        }
        generator.result1 = result;
        return result;
    }

    public static void main(String[] args) {
        try {
            fin = new FileInputStream("/home/hunter/Documents/C/ADAA/input.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        in = new InputReader(fin);
        long start = System.currentTimeMillis();
        int maxEndTime = 0;
        int n = in.nextInt();
        tasks = new PriorityQueue<>();
        int s, e, p;
        for (int i = 0; i < n; i++) {
            s = in.nextInt();
            e = in.nextInt();
            p = in.nextInt();
            tasks.add(new task(s - 1, e, p));
            if (e > maxEndTime) {
                maxEndTime = e;
            }
        }
        time = new int[maxEndTime];
        out.println(getPayment());
//        getPayment();
        long end = System.currentTimeMillis();
//        out.println(end - start);
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
