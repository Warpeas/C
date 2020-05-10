import Util.ProxoolUtil;
import dao.DataFactory;
import dao.DataManipulation;

public class Client {

    public static void main(String[] args) {
        try {
            dbRequestArrived(6, args[0]);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            dbRequestArrived(9, args[0]);

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void dbRequestArrived(int count, String arg) {
        System.out.println(ProxoolUtil.getConnectState());

        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                DataManipulation dm = new DataFactory().createDataManipulation(arg);
                dm.getConnection();
//                System.out.print(dm.findMovieById((int) (Math.random() * 9000)));
//                System.out.println(dm.FullInformationOfMoviesRuntime(90,91));
                System.out.print(ProxoolUtil.getConnectState());
                dm.closeConnection();
            }).start();
        }
    }
}

