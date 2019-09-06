public class Char {
    public static void main(String[] args) {
        // char类型除了可表示标准的ASCII外,还可以表示Unicode字符
        // char类型表示的字符串只能用单引号引起来,与双引号的string类型区分开
        char a = 'A';
        char zh = '中';
        System.out.println(a);
        System.out.println(zh);

        // final成为修饰符,修饰后这个变量成为常量,常量在修饰后不可再次赋值,赋值后会报错
        // 常量只在定义时初始化
        final double PI = 3.14;

        // var关键字,有时候类型的名字太长写起来麻烦,此时可以用var,编译器会自动推断出变量sb的类型
        // 两句话意思相同
        var sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        {
            // 在不同代码块下,变量是不可以跨代码块使用的
            System.out.println(sb2);
            System.out.println(sb1);
        }
    }
}