import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

public class _2c {
    public static void main(String[] args) {
        Reader in = new Reader();
        n = in.nextInt();
        map = new HashMap(28707251);
        score = new int[n + 1];

        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += (score[i] = in.nextInt());
        }
        Arrays.sort(score);

        int time = n * (n - 1) / 2;
        type0 = 3 * time - sum;
        type1 = sum - 2 * time;

        cnt = 0;
        DFS(1, 2);

        System.out.println(cnt);
    }

    private static int n;
    private static HashMap map;

    private static int type0, type1;

    private static int[] score;
    private static long cnt;

    private static int getRestCases() {
        int p = 0;
        if (score[n - 3] >= 10) return 0;
        p += score[n - 3];
        p *= 10;
        if (score[n - 2] >= 10) return 0;
        p += score[n - 2];
        p *= 10;
        if (score[n - 1] >= 10) return 0;
        p += score[n - 1];
        p *= 10;
        if (score[n] >= 10) return 0;
        p += score[n];
        if (p >= list.length) return 0;
        return list[p];
    }

    private static void DFS(int i, int j) {
        if (type0 == 0 && type1 == 0) {
            cnt++;
            if (cnt == 998244353) {
                cnt = 0;
            }
            return;
        }
        if (j > n) return;
        if (n - i < 4) {
            cnt += getRestCases();
            if (cnt >= 998244353) {
                cnt %= 998244353;
            }
            return;
        }
        long n_cnt = map.search(type0, type1, getCode(i));
        if (n_cnt != -1) {
            cnt += n_cnt;
            if (cnt >= 998244353) {
                cnt %= 998244353;
            }
            return;
        }

        long cnt0 = cnt;

        int ni, nj;
        boolean notLast = (j != n);
        if (notLast) {
            ni = i;
            nj = j + 1;
        } else {
            ni = i + 1;
            nj = i + 2;
        }

        if (type0 > 0) {
            type0--;
            if (( (score[i] == 1) || (notLast && score[i] > 1) ) && score[j] >= 1) {//1:1
                score[i] -=  1;score[j] -=  1;
                DFS(ni, nj);
                map.add(type0, type1, cnt - cnt0, getCode(i));
                cnt0 = cnt;
                score[i] +=  1;score[j] +=  1;
            }
            type0++;
        }
        if (type1 > 0) {
            type1--;
            if ((score[i] == 3) || (notLast && score[i] > 3)/* && score[j] >= 0*/) {//3:0
                score[i] -= 3;
                DFS(ni, nj);
                map.add(type0, type1, cnt - cnt0, getCode(i));
                cnt0 = cnt;
                score[i] += 3;
            }
            if (( (score[i] == 2) || (notLast && score[i] > 2) ) && score[j] >= 1) {//2:1
                score[i] -= 2;score[j] -= 1;
                DFS(ni, nj);
                map.add(type0, type1, cnt - cnt0, getCode(i));
                cnt0 = cnt;
                score[i] += 2;score[j] += 1;
            }
            if (( (score[i] == 1) || (notLast && score[i] > 1) ) && score[j] >= 2) {//1:2
                score[i] -= 1;score[j] -= 2;
                DFS(ni, nj);
                map.add(type0, type1, cnt - cnt0, getCode(i));
                cnt0 = cnt;
                score[i] += 1;score[j] += 2;
            }
            if (( (score[i] == 0) || (notLast && score[i] > 0) ) && score[j] >= 3) {//0:3
                score[j] -= 3;
                DFS(ni, nj);
                map.add(type0, type1, cnt - cnt0, getCode(i));
                //cnt0 = cnt;
                score[j] += 3;
            }
            type1++;
        }

    }

    private static int getCode(int i) {
        int base = (n - 1) * 3;
        int code = 0;
        for (; i <= n; i++) {
            code = code * base + score[i];
        }
        return code >>> 1;
    }

    public static class HashMap {
        Node[] hash;
        int size;
        HashMap(int n) { hash = new Node[size = n]; }

        void add(int type0, int type1, long count, int code) {
            int index = code % size;
            if (hash[index] != null) {
                Node t = hash[index];
                while (t.next != null)
                    t = t.next;
                t.next = new Node(type0, type1, count, code);
            } else {
                hash[index] = new Node(type0, type1, count, code);
            }
        }

        long search(int type0, int type1, int code) {
            int index = code % size;
            if (hash[index] != null) {
                Node t = hash[index];
                while (t.next != null) {
                    if (t.type0 == type0 && t.type1 == type1
                            && t.code == code) return t.count;
                    t = t.next;
                }
            }
            return -1;
        }

        private static class Node {
            Node next;
            int type0, type1, code;
            long count;
            Node(int t0, int t1, long cnt, int cd) {
                type0 = t0;type1 = t1;count = cnt;code = cd;
            }
        }
    }



    static class Reader {
        final private int BUFFER_SIZE = 0x10000;
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        private byte read() {
            if (bufferPointer == bytesRead) {// fillBuffer();
                try { bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
                } catch (IOException e) { e.printStackTrace(); }
                if (bytesRead == -1) buffer[0] = -1;
            }
            return buffer[bufferPointer++];
        }

        public int nextInt() {
            int ret = 0;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg) c = read();
            do { ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg) return -ret;
            return ret;
        }
    }

    static int[] list = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,3,2,0,0,0,0,0,
            0,1,3,2,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,1,2,3,2,0,0,0,0,0,0,2,3,3,0,0,0,0,0,0,1,3,3,0,0,0,
            0,0,0,0,1,2,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,1,0,0,0,0,0,0,0,3,2,0,0,0,0,0,0,2,3,3,0,0,0,0,0,0,0,3,4,0,0,0,0,0,0,0,3,3,0,0,0,0,0,0,0,0,2,0,
            0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,
            0,0,0,0,0,0,1,3,2,0,0,0,0,0,0,1,3,3,0,0,0,0,0,0,0,3,3,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,
            1,2,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,
            0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,3,2,
            1,0,0,0,0,0,1,3,3,1,0,0,0,0,0,0,1,2,1,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,3,0,0,0,0,0,1,2,5,4,2,0,0,0,0,0,2,3,9,4,0,0,0,0,0,1,
            5,9,4,0,0,0,0,0,0,1,4,4,0,0,0,0,0,0,0,3,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,1,0,0,0,0,0,0,0,1,1,3,0,0,0,0,0,0,0,9,7,2,0,0,0,0,0,5,10,13,5,0,0,0,0,0,0,10,17,7,0,0,0,0,0,1,9,13,7,0,0,
            0,0,0,0,1,7,5,0,0,0,0,0,0,1,3,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,
            0,0,0,1,2,5,4,2,0,0,0,0,0,5,10,13,5,0,0,0,0,1,5,15,17,8,0,0,0,0,0,2,10,17,10,0,0,0,0,0,1,5,13,8,0,0,0,0,0,
            0,1,4,5,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,3,2,1,0,0,0,0,
            0,2,3,9,4,0,0,0,0,0,0,10,17,7,0,0,0,0,0,2,10,17,10,0,0,0,0,0,0,3,17,10,0,0,0,0,0,0,3,9,7,0,0,0,0,0,0,0,2,4,
            0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,3,3,1,0,0,0,0,0,1,5,9,
            4,0,0,0,0,0,1,9,13,7,0,0,0,0,0,1,5,13,8,0,0,0,0,0,0,3,9,7,0,0,0,0,0,0,1,3,4,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,2,1,0,0,0,0,0,0,1,4,4,0,0,0,0,0,
            0,1,7,5,0,0,0,0,0,0,1,4,5,0,0,0,0,0,0,0,2,4,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,3,2,0,0,0,0,0,0,1,3,2,0,0,0,
            0,0,0,0,1,2,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,1,1,
            0,0,0,0,0,0,0,3,2,1,0,0,0,0,0,1,3,3,1,0,0,0,0,0,0,1,2,1,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,1,2,0,0,0,0,0,0,0,5,4,3,0,0,0,0,0,2,5,9,5,2,0,0,0,0,0,5,
            10,10,4,0,0,0,0,1,5,9,10,4,0,0,0,0,0,1,4,5,4,0,0,0,0,0,1,2,3,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,1,1,2,0,0,0,0,0,1,2,5,4,3,0,0,0,0,0,4,7,15,9,3,0,0,0,1,4,14,20,18,7,0,0,0,0,2,7,20,22,10,
            0,0,0,0,1,5,15,18,10,0,0,0,0,0,1,4,9,7,0,0,0,0,0,0,2,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
            0,0,0,0,0,0,0,5,4,3,0,0,0,0,0,4,7,15,9,3,0,0,0,0,0,16,26,24,8,0,0,0,0,4,16,34,32,13,0,0,0,0,0,7,26,32,16,0,
            0,0,0,0,5,15,24,13,0,0,0,0,0,0,4,9,8,0,0,0,0,0,0,1,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,
            0,0,0,0,0,2,5,9,5,2,0,0,0,1,4,14,20,18,7,0,0,0,0,4,16,34,32,13,0,0,0,0,2,14,34,36,18,0,0,0,0,0,5,20,32,18,
            0,0,0,0,0,1,9,18,13,0,0,0,0,0,0,1,5,7,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,
            2,1,0,0,0,0,0,5,10,10,4,0,0,0,0,2,7,20,22,10,0,0,0,0,0,7,26,32,16,0,0,0,0,0,5,20,32,18,0,0,0,0,0,0,10,22,16,
            0,0,0,0,0,0,3,10,10,0,0,0,0,0,0,0,2,4,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,3,
            3,1,0,0,0,0,1,5,9,10,4,0,0,0,0,1,5,15,18,10,0,0,0,0,0,5,15,24,13,0,0,0,0,0,1,9,18,13,0,0,0,0,0,0,3,10,10,0,
            0,0,0,0,0,0,3,4,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,1,
            0,0,0,0,0,1,4,5,4,0,0,0,0,0,1,4,9,7,0,0,0,0,0,0,4,9,8,0,0,0,0,0,0,1,5,7,0,0,0,0,0,0,0,2,4,0,0,0,0,0,0,0,0,
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,1,2,
            3,2,0,0,0,0,0,0,2,3,3,0,0,0,0,0,0,1,3,3,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,1,0,0,0,0,
            0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,1,1,3,0,0,0,0,0,1,2,5,4,2,0,0,0,0,0,2,3,9,4,0,0,0,0,0,1,5,9,4,0,0,0,0,0,0,1,4,4,0,0,
            0,0,0,0,0,3,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,2,0,0,0,0,0,1,2,5,4,3,0,0,
            0,0,0,4,7,15,9,3,0,0,0,1,4,14,20,18,7,0,0,0,0,2,7,20,22,10,0,0,0,0,1,5,15,18,10,0,0,0,0,0,1,4,9,7,0,0,0,0,
            0,0,2,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,5,4,3,0,0,0,1,3,9,13,15,9,4,0,0,
            0,3,8,22,26,28,10,0,0,0,1,9,22,39,36,16,0,0,0,0,2,13,26,36,20,0,0,0,0,0,5,15,28,16,0,0,0,0,0,0,4,9,10,0,0,
            0,0,0,0,0,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,3,0,0,0,0,0,4,7,15,9,3,0,0,0,3,8,22,26,28,
            10,0,0,0,0,8,20,47,48,18,0,0,0,0,4,22,47,52,25,0,0,0,0,0,7,26,48,25,0,0,0,0,0,1,15,28,18,0,0,0,0,0,0,1,9,
            10,0,0,0,0,0,0,0,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,5,4,2,0,0,0,1,4,14,20,18,7,0,0,0,1,9,
            22,39,36,16,0,0,0,0,4,22,47,52,25,0,0,0,0,1,14,39,52,28,0,0,0,0,0,2,20,36,25,0,0,0,0,0,0,5,18,16,0,0,0,0,0,
            0,0,4,7,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,2,3,9,4,0,0,0,0,2,7,20,22,10,0,0,0,
            0,2,13,26,36,20,0,0,0,0,0,7,26,48,25,0,0,0,0,0,2,20,36,25,0,0,0,0,0,0,3,22,20,0,0,0,0,0,0,0,9,10,0,0,0,0,0,
            0,0,0,4,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,1,5,9,4,0,0,0,0,1,5,15,18,10,0,0,0,
            0,0,5,15,28,16,0,0,0,0,0,1,15,28,18,0,0,0,0,0,0,5,18,16,0,0,0,0,0,0,0,9,10,0,0,0,0,0,0,0,1,4,0,0,0,0,0,0,0,
            0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,4,4,0,0,0,0,0,1,4,9,7,0,0,0,0,0,0,
            4,9,10,0,0,0,0,0,0,1,9,10,0,0,0,0,0,0,0,4,7,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,3,2,0,0,0,0,0,0,2,3,3,0,0,0,0,0,0,0,3,4,0,0,0,
            0,0,0,0,3,3,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,3,2,0,0,0,0,0,0,1,3,2,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,1,1,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,1,3,0,0,0,0,0,0,0,9,7,
            2,0,0,0,0,0,5,10,13,5,0,0,0,0,0,0,10,17,7,0,0,0,0,0,1,9,13,7,0,0,0,0,0,0,1,7,5,0,0,0,0,0,0,1,3,2,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,5,4,3,0,0,0,0,0,4,7,15,9,3,0,0,0,0,0,16,26,24,8,0,0,0,
            0,4,16,34,32,13,0,0,0,0,0,7,26,32,16,0,0,0,0,0,5,15,24,13,0,0,0,0,0,0,4,9,8,0,0,0,0,0,0,1,3,3,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,3,0,0,0,0,0,4,7,15,9,3,0,0,0,3,8,22,26,28,10,0,0,0,0,8,20,47,48,18,0,
            0,0,0,4,22,47,52,25,0,0,0,0,0,7,26,48,25,0,0,0,0,0,1,15,28,18,0,0,0,0,0,0,1,9,10,0,0,0,0,0,0,0,3,3,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,9,7,2,0,0,0,0,0,16,26,24,8,0,0,0,0,8,20,47,48,18,0,0,0,0,0,20,60,66,
            28,0,0,0,0,0,16,47,66,32,0,0,0,0,0,0,26,48,28,0,0,0,0,0,0,9,24,18,0,0,0,0,0,0,0,7,8,0,0,0,0,0,0,0,1,2,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,5,10,13,5,0,0,0,0,4,16,34,32,13,0,0,0,0,4,22,47,52,25,0,0,0,0,0,16,47,66,32,0,0,0,0,0,5,34,52,32,0,0,0,0,0,0,10,32,25,0,0,0,0,0,0,1,13,13,0,0,0,0,0,0,0,1,5,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,2,0,0,0,0,0,0,10,17,7,0,0,0,0,0,7,26,32,16,0,0,0,0,0,7,26,48,25,0,0,0,0,0,0,26,48,28,0,0,0,0,0,0,10,32,25,0,0,0,0,0,0,0,17,16,0,0,0,0,0,0,0,3,7,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,3,2,0,0,0,0,0,1,9,13,7,0,0,0,0,0,5,15,24,13,0,0,0,0,0,1,15,28,18,0,0,0,0,0,0,9,24,18,0,0,0,0,0,0,1,13,13,0,0,0,0,0,0,0,3,7,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,1,7,5,0,0,0,0,0,0,4,9,8,0,0,0,0,0,0,1,9,10,0,0,0,0,0,0,0,7,8,0,0,0,0,0,0,0,1,5,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,3,2,0,0,0,0,0,0,1,3,3,0,0,0,0,0,0,0,3,3,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,1,2,3,2,0,0,0,0,0,0,2,3,3,0,0,0,0,0,0,1,3,3,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,1,2,5,4,2,0,0,0,0,0,5,10,13,5,0,0,0,0,1,5,15,17,8,0,0,0,0,0,2,10,17,10,0,0,0,0,0,1,5,13,8,0,0,0,0,0,0,1,4,5,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,2,5,9,5,2,0,0,0,1,4,14,20,18,7,0,0,0,0,4,16,34,32,13,0,0,0,0,2,14,34,36,18,0,0,0,0,0,5,20,32,18,0,0,0,0,0,1,9,18,13,0,0,0,0,0,0,1,5,7,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,5,4,2,0,0,0,1,4,14,20,18,7,0,0,0,1,9,22,39,36,16,0,0,0,0,4,22,47,52,25,0,0,0,0,1,14,39,52,28,0,0,0,0,0,2,20,36,25,0,0,0,0,0,0,5,18,16,0,0,0,0,0,0,0,4,7,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,5,10,13,5,0,0,0,0,4,16,34,32,13,0,0,0,0,4,22,47,52,25,0,0,0,0,0,16,47,66,32,0,0,0,0,0,5,34,52,32,0,0,0,0,0,0,10,32,25,0,0,0,0,0,0,1,13,13,0,0,0,0,0,0,0,1,5,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,3,2,0,0,0,0,1,5,15,17,8,0,0,0,0,2,14,34,36,18,0,0,0,0,1,14,39,52,28,0,0,0,0,0,5,34,52,32,0,0,0,0,0,1,15,36,28,0,0,0,0,0,0,2,17,18,0,0,0,0,0,0,0,3,8,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,3,3,0,0,0,0,0,2,10,17,10,0,0,0,0,0,5,20,32,18,0,0,0,0,0,2,20,36,25,0,0,0,0,0,0,10,32,25,0,0,0,0,0,0,2,17,18,0,0,0,0,0,0,0,3,10,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,3,3,0,0,0,0,0,1,5,13,8,0,0,0,0,0,1,9,18,13,0,0,0,0,0,0,5,18,16,0,0,0,0,0,0,1,13,13,0,0,0,0,0,0,0,3,8,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,1,4,5,0,0,0,0,0,0,1,5,7,0,0,0,0,0,0,0,4,7,0,0,0,0,0,0,0,1,5,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,3,2,0,0,0,0,0,0,2,3,3,0,0,0,0,0,0,0,3,4,0,0,0,0,0,0,0,3,3,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,3,2,1,0,0,0,0,0,2,3,9,4,0,0,0,0,0,0,10,17,7,0,0,0,0,0,2,10,17,10,0,0,0,0,0,0,3,17,10,0,0,0,0,0,0,3,9,7,0,0,0,0,0,0,0,2,4,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,2,1,0,0,0,0,0,5,10,10,4,0,0,0,0,2,7,20,22,10,0,0,0,0,0,7,26,32,16,0,0,0,0,0,5,20,32,18,0,0,0,0,0,0,10,22,16,0,0,0,0,0,0,3,10,10,0,0,0,0,0,0,0,2,4,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,2,3,9,4,0,0,0,0,2,7,20,22,10,0,0,0,0,2,13,26,36,20,0,0,0,0,0,7,26,48,25,0,0,0,0,0,2,20,36,25,0,0,0,0,0,0,3,22,20,0,0,0,0,0,0,0,9,10,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,2,0,0,0,0,0,0,10,17,7,0,0,0,0,0,7,26,32,16,0,0,0,0,0,7,26,48,25,0,0,0,0,0,0,26,48,28,0,0,0,0,0,0,10,32,25,0,0,0,0,0,0,0,17,16,0,0,0,0,0,0,0,3,7,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,3,3,0,0,0,0,0,2,10,17,10,0,0,0,0,0,5,20,32,18,0,0,0,0,0,2,20,36,25,0,0,0,0,0,0,10,32,25,0,0,0,0,0,0,2,17,18,0,0,0,0,0,0,0,3,10,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,4,0,0,0,0,0,0,3,17,10,0,0,0,0,0,0,10,22,16,0,0,0,0,0,0,3,22,20,0,0,0,0,0,0,0,17,16,0,0,0,0,0,0,0,3,10,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,0,0,0,0,0,0,3,9,7,0,0,0,0,0,0,3,10,10,0,0,0,0,0,0,0,9,10,0,0,0,0,0,0,0,3,7,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,2,4,0,0,0,0,0,0,0,2,4,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,3,2,0,0,0,0,0,0,1,3,3,0,0,0,0,0,0,0,3,3,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,3,3,1,0,0,0,0,0,1,5,9,4,0,0,0,0,0,1,9,13,7,0,0,0,0,0,1,5,13,8,0,0,0,0,0,0,3,9,7,0,0,0,0,0,0,1,3,4,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,3,3,1,0,0,0,0,1,5,9,10,4,0,0,0,0,1,5,15,18,10,0,0,0,0,0,5,15,24,13,0,0,0,0,0,1,9,18,13,0,0,0,0,0,0,3,10,10,0,0,0,0,0,0,0,3,4,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,1,5,9,4,0,0,0,0,1,5,15,18,10,0,0,0,0,0,5,15,28,16,0,0,0,0,0,1,15,28,18,0,0,0,0,0,0,5,18,16,0,0,0,0,0,0,0,9,10,0,0,0,0,0,0,0,1,4,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,3,2,0,0,0,0,0,1,9,13,7,0,0,0,0,0,5,15,24,13,0,0,0,0,0,1,15,28,18,0,0,0,0,0,0,9,24,18,0,0,0,0,0,0,1,13,13,0,0,0,0,0,0,0,3,7,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,3,3,0,0,0,0,0,1,5,13,8,0,0,0,0,0,1,9,18,13,0,0,0,0,0,0,5,18,16,0,0,0,0,0,0,1,13,13,0,0,0,0,0,0,0,3,8,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,0,0,0,0,0,0,3,9,7,0,0,0,0,0,0,3,10,10,0,0,0,0,0,0,0,9,10,0,0,0,0,0,0,0,3,7,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,1,3,4,0,0,0,0,0,0,0,3,4,0,0,0,0,0,0,0,1,4,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,2,1,0,0,0,0,0,0,1,4,4,0,0,0,0,0,0,1,7,5,0,0,0,0,0,0,1,4,5,0,0,0,0,0,0,0,2,4,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,1,0,0,0,0,0,1,4,5,4,0,0,0,0,0,1,4,9,7,0,0,0,0,0,0,4,9,8,0,0,0,0,0,0,1,5,7,0,0,0,0,0,0,0,2,4,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,4,4,0,0,0,0,0,1,4,9,7,0,0,0,0,0,0,4,9,10,0,0,0,0,0,0,1,9,10,0,0,0,0,0,0,0,4,7,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,1,7,5,0,0,0,0,0,0,4,9,8,0,0,0,0,0,0,1,9,10,0,0,0,0,0,0,0,7,8,0,0,0,0,0,0,0,1,5,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,1,4,5,0,0,0,0,0,0,1,5,7,0,0,0,0,0,0,0,4,7,0,0,0,0,0,0,0,1,5,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,2,4,0,0,0,0,0,0,0,2,4,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,3,2,0,0,0,0,0,0,1,3,2,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,1,2,3,2,0,0,0,0,0,0,2,3,3,0,0,0,0,0,0,1,3,3,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,3,2,0,0,0,0,0,0,2,3,3,0,0,0,0,0,0,0,3,4,0,0,0,0,0,0,0,3,3,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,3,2,0,0,0,0,0,0,1,3,3,0,0,0,0,0,0,0,3,3,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1};

}
