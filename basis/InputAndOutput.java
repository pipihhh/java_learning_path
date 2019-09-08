import java.util.Scanner;

public class InputAndOutput {
    public static void main(String[] args) {
        // System.out.println表示输出并换行的意思,不想换行使用print
        // System.out.printf格式化输出
        double d = 3.1415926;
        System.out.printf("%.2f\n", d);   // 显示两位小数
        System.out.printf("%.4f\n", d);   // 显示4位小数

        // java的格式化功能提供了多种占位符,%d整数,  %x 输出16进制整数  ,%f 输出浮点数,   %e  格式化输出科学计数法表示的浮点数  ,  %s输出字符串
        // 由于%为占位符,%%为%本身
        // 占位符还有更加详细的参数,详见JDK文档的java.util.Formatter,当有2个占位符时,需要传2个参数

        // 和输入相比java的输出复杂的多
        // 创建scanner对象
        Scanner scanner = new Scanner(System.in);
        // System.in代表了标准输入流,通过Scanner对象可以简化代码
        String name = scanner.nextLine();  // 读取一行输入并获取字符串
        int age = scanner.nextInt();   // 读取一行输入并获取整数,与之队形的还有nextDouble()
        System.out.printf("%s, %d", name, age);
    }
}