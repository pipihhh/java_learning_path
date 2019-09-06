public class Number {
    public static void main(String[] args) {
        // 基础数据类型,就是计算机可以直接计算的数据类型
        // byte  short  int  long  float  double  char  boolean
        // byte:-128~127   short:-32768~32768
        // int: -2147483648~2147483647    long:-9223372036854775808~9223372036854775807
        // long i = 9000000000000000L;  long类型的结尾需要加上L
        // 二进制表示  0b开头   十六进制表示  0x开头
        // 在定义int时可以加下划线  int i = 2_000_000_000;
        // float f1 = 3.14f;
        // float f2 = 3.14e38f;  代表科学计数法3.14 x 10^38 下同理 对于float类型 需要加上f后缀
        // double d = 1.79e308;
        // double d2 = -1.79e308;
        // double d3 = 4.9e-324;
        // boolean b1 = true;  在jvm中通常将boolean表示为4字节整数,但是boolean理论上只需要1字节即可表示
        int n = 100;
        System.out.println(n);

        int x = 200;
        System.out.println(x);

        x = n;
        x = x + 100;

        System.out.println(x);
        System.out.println(n);
    }
}