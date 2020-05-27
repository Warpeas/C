import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import java.io.Console;

public class ClientCM {
    static PrintWriter out;
    static BufferedReader in;


    static int port = 8800;
    static String host = "localhost";

    static Scanner scanner;
    static Console console;

    static boolean signed;
    static String user;


    public static void main(String[] args) {
        try {
            Socket socket = new Socket(host, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            scanner = new Scanner(System.in);
            console = System.console();
            signed = false;
            System.out.println(in.readLine());
            System.out.println("就绪 如果需要帮助 请输入help");
            String input;
            System.out.print(">");
            while ((input = scanner.next())!=null){
                if (input.equals("help")){
                    AnswerHelp();
                }else if (input.equals("sign_in")){
                    AnswerSign_in();
                }else if (input.equals("sign_up")){
                    AnswerSign_up();
                }else if (input.equals("sign_out")){
                    AnswerSign_out();
                }else if (input.equals("cancel")){
                    AnswerCancel();
                }else if (input.equals("check")){
                    AnswerCheck();
                }else if (input.equals("reserve")){
                    AnswerReserve();
                }else if (input.equals("view")){
                    AnswerView();
                }else if (input.equals("quit")){
                    AnswerQuit();
                }else {
                    System.out.println("无法识别命令");
                }
                System.out.print(">");
            }
            in.close();
            out.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void AnswerHelp(){
        System.out.println("如果想登陆 请输入sign_in 并按照提示进行后续操作");
        System.out.println("如果想注册 请输入sign_up 并按照提示进行后续操作");
        System.out.println("如果想登出 请输入sign_out");
        System.out.println("如果想注销 请输入cancel");
        System.out.println("如果想查询城市中的车站 请输入check 并按照提示进行后续操作");
        System.out.println("如果想查询车次订购车票 请输入reserve 并按照提示进行后续操作");
        System.out.println("如果想查看订单信息 请输入view");
        System.out.println("如果想退出 请输入quit");
    }
    private static void AnswerSign_in(){
        try{
            out.println("sign_in");
            System.out.print("请输入用户名 #");
            String username = scanner.next();
            scanner.nextLine();
            out.println(username);
            System.out.print("请输入密码 *");
            String password = scanner.next();
            scanner.nextLine();
            out.println(password);
            String input = in.readLine();

            System.out.println(input);
            if (input.equals("登陆成功")){
                signed = true;
                user = username;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void AnswerSign_up(){
        try {
            out.println("sign_up");
            System.out.print("请输入用户名 #");
            String username = scanner.next();
            scanner.nextLine();
            out.println(username);
            System.out.print("请输入密码 *");
            String password = scanner.next();
            scanner.nextLine();
            out.println(password);
            String input = in.readLine();

            System.out.println(input);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void AnswerSign_out(){
        signed = false;
        user = null;
    }
    private static void AnswerCancel(){
        try {
            if (signed){
                out.println("cancel");
                out.println(user);
                String input = in.readLine();
                if (input.equals("success")){
                    System.out.println("注销成功");
                }else{
                    System.out.println("注销失败");
                }
            }else{
                System.out.println("请登陆");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void AnswerCheck(){
        try{
            if (!signed){
                System.out.println("请登陆");
            }else{
                out.println("check_city");
                System.out.print("请输入城市 @");
                String city = scanner.next();
                out.println(city);
                String input;
                while(!(input=in.readLine()).equals("over")){
                    System.out.println(input);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private static void AnswerReserve(){
        try{
            if (!signed){
                System.out.println("请登陆");
            }else{
                out.println("reserve");
                System.out.print("请输入始发站 @");
                String departure = scanner.next();
                out.println(departure);
                System.out.print("请输入终点站 @");
                String destination = scanner.next();
                out.println(destination);
                String input;

                while(!(input=in.readLine()).equals("over")){
                    System.out.println(input);
                }
                System.out.print("请选择车次 @");
                String train = scanner.next();
                out.println(train);
                System.out.print("请选择日期(格式 xxxx xx xx) @");
                String date = scanner.next()+"-"+scanner.next()+"-"+scanner.next();
                out.println(date);
                System.out.println("剩余座位如下");
                while(!(input=in.readLine()).equals("over")){
                    System.out.println(input);
                }
                System.out.print("请选择座位 @");
                String seat = scanner.next();
                out.println(seat);
                out.println(user);
                System.out.println("订单号："+in.readLine());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private static void AnswerView(){
        try {
            out.println("view");
            out.println(user);
            String input;
            while(!(input=in.readLine()).equals("over")){
                System.out.println(input);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private static void AnswerQuit(){
        try{
            in.close();
            out.close();
            System.exit(0);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
