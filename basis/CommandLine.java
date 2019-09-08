public class CommandLine {
    public static void main(String[] args) {
        // 命令行相关,由jvm接收用户的输入并传给main方法
        for (String str : args) {
            System.out.println(str);
        }
    }
}