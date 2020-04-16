import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class TheMaximumIncome {
    static InputStream inputStream = System.in;
    static OutputStream outputStream = System.out;
    static InputReader in = new InputReader(inputStream);
    static PrintWriter out = new PrintWriter(outputStream);
    static int[] time;
    static PriorityQueue<task> tasks;

    static class task implements Comparable<task> {
        int start, end;
        int pay, active;

        task(int s, int e, int p) {
            start = s;
            end = e;
            pay = p;
        }

        @Override
        public int compareTo(task task) {
//            if (end != task.end)
//                return end - task.end;
//            else return start - task.start;
            return pay - task.pay;
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
            mp = -1;
            mpv = Integer.MAX_VALUE;
            for (int i = s; i < e; i++) {
                mp = mpv <= time[i] ? mp : i;
                mpv = Math.min(mpv, time[i]);
                if (time[i] == 0) {
                    time[i] = p;
                    result += p;
                    break;
                } else if (i + 1 == e && mp >= 0 && time[mp] < p) {
                    result += p - time[mp];
                    time[mp] = p;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
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
        PriorityQueue<task> t = new PriorityQueue<>(tasks);
        for (int i = 0; i < n; i++) {
            task a = t.poll();
            for (int j = 0; j < 1000000000; j++) {
                j = Math.max(j, a.start);
                if (j > time.length - 1) {
                    break;
                } else if (time[j] == 0) {
                    tasks.remove(a);
                    a.active = j;
                    tasks.add(a);
                    time[j] = -1;
                    break;
                }
            }
        }
        out.println(getPayment());
        long end = System.currentTimeMillis();
        out.println(end - start);
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
