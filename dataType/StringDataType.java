import java.nio.charset.StandardCharsets;
import java.util.StringJoiner;

public class StringDataType {
    public static void main(String[] args) {
        // 字符串本质上内部是一个char类型的数组
        // 字符串是不可变的,内部是一个private final char[]
        // 判断字符串之间是否相等需要使用equals()而不可以使用==
        String s1 = "Hello";
        String s2 = new String(new char[] { 'H', 'e', 'l', 'l', 'o' });
        System.out.println(s1.equalsIgnoreCase(s2)); // 忽略大小写的比较
        System.out.println(s1.contains(s2)); // 判断s1中是否包含s2,contains接收的是string的父类CharSequence
        s1.indexOf("l");   // 2
        s1.lastIndexOf("l");   // 3
        s1.startsWith("He");   // true
        s1.endsWith("lo");   // true
        s1.substring(2);    // "llo"
        s1.substring(2, 4);  // ll 从0开始 顾头不顾尾
        s1.trim();      // 去除首尾的英文空格
        s1.strip();     //去除首尾的任何空格包括中文
        s1.stripLeading(); // 去除首空格
        s1.stripTrailing();  //去除尾空格
        s1.isEmpty();      // 是否为空 false
        s1.isBlank();      // 是否为空格false
        s1.replace("ll", "~~");   //替换 he~~o
        s1.replaceAll("[a-z]", "~");   //正则表达式的替换
        s1.split("ll");    // ["he", "ow"],split接收的也是正则
        String.join("..", new String[] { "abc", "def" }); // abc..def,用...连接字符串数组,此为静态方法
        String.valueOf(123); //将其他数据转为字符串
        Integer.parseInt("123");  // 将字符串转为数字
        Integer.parseInt("ff", 16); // 16进制转换
        Boolean.parseBoolean("true");  //将字符串转为boolean类型,true
        Boolean.parseBoolean("FALSE");  // false
        Integer.getInteger("java.version");  //获取版本号 12  把对应的系统变量转为int类型
        s1.toCharArray();   //将字符串转为char数组
        new String(s1.toCharArray());  //实例化的时候传入一个char数组创建string
        // 通过上述方法创建string对象的时候,即使修改了char数组字符串也是不会改变的,他会复制一份


        // 编码问题,unicode称为万国码,包含了任何一个字符的编码,但是unicode非常的占用空间
        // 而此时utf8编码应运而生,utf8为变长的编码,utf8的优点是容错能力强,
        // 如果传输过程中某些字符出错了也不影响后续字符,utf8依靠高字节位编码判断一个字符究竟几个字节
        // 在java中char类型实际上就是2字节的unicode编码
        byte[] b1 = s1.getBytes();    //不传参数默认按照ISO8859-1编码转换,不推荐
        b1 = s1.getBytes("UTF-8");
        b1 = s1.getBytes("GBK");
        b1 = s1.getBytes(StandardCharsets.UTF_8);  //按照utf8来转换
        // 转码后就不再是char类型而是byte类型的数组了
        new String(b1, "UTF-8");   // 使用utf-8编码创建字符串,需要接收一个byte类型的数组
        // 始终牢记,java中的char和string在内存中始终是unicode字符
        // 在较新的jdk版本中,string内部使用byte数组存储字符串


        // StringBuffer相关
        StringBuilder sb = new StringBuilder(1024);
        // 字符串虽然实现的+的操作但是每次+都会申请新的空间来存储字符串,这样会影响gc的效率
        // StringBuilder是一个可变对象,在append的时候不会创建新的对象
        // StringBuilder还可以进行链式操作,是因为append返回了this
        sb.append("bbb").append("ccc");
        sb.toString();
        // 对于普通的+操作,我们并不需要改写为StringBuilder,
        // 编译器在编译的时候会将普通的+优化为StringConcatFactory,
        // 运行时StringConcatFactory会自动 把字符串连接优化为数组的复制或StringBuilder
        // StringBuffer是StringBuilder的线程安全版本,用的很少,同步会造成性能的下降

        // 对于循环拼接字符串,内部有一个更加方便使用的类StringJoiner
        // 其构造方法的3个参数作用为,第一个为add时候增加的间隔,字符串的开头,字符串的结尾
        // StringJoiner的内部实际上使用的是StringBuilder,所以效率上相差无几
        String[] names = { "Bob", "Alice", "Anna" };
        StringJoiner sj = new StringJoiner(", ", "Hello ", "!");
        for (String name : names) {
            sj.add(name);
        }
        sj.toString(); // Hello Bob, Alice, Anna!
    }

    static String buildInsertSql(String table, String... fields) {
        StringBuilder tmp = new StringBuilder(1024);
        tmp.append("INSERT INTO ")
        .append(table)
        .append(" (")
        .append(String.join(", ", fields))
        .append(") VALUES (?, ?, ?)");
        return tmp.toString();
    }
}