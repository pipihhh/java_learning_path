public class StringAndChar {
    public static void main(String[] args) {
        // char是基本数据类型,它是character的缩写,一个char保存一个Unicode字符
        char c1 = 'A';
        char c2 = '中';
        // java在内存中总是使用Unicode表示字符,所以一个英文字符和中文字符都用一个char来表示
        // 要显示一个Unicode编码只需要char类型直接赋值给int类型即可
        // 还可以直接转义字符\\u+Unicode编码来表示一个字符
        char c3 = '\u0041';
        char c4 = '\u4e2d';

        int n1 = 'A';  // 65
        int n2 = '中';  // 20013        

        // 字符串用双引号"..."表示,一个字符串可以存储0个到任意个字符
        String s = "";
        String s1 = "A";
        String s2 = "ABC";
        String s3 = "中文 ABC";

        // \代表了转译字符, \\u####代表了一个Unicode编码的字符
        String s4 = "abc\"xyz";
        // 字符串之间是可以做+的,如果用+连接不同的数据类型,则会先将其转为字符串再做+
        // 字符串是引用的,字符串不可变,虽然是引用但由于字符串不可变,所以下面代码t的值不变
        // null表示空值,表示该变量不指向任何对象
        System.out.println(s+s2+s1+s4);

        String t = s1;
        s1 = "B";
        System.out.println(t);
    }
}