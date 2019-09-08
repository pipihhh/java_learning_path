public class WrapperClass {
    public static void main(String[] args) {
        // 由于只有引用类型可以设置为null,基本类型无法设置为null
        // 由于此原因,有许多包装类型应运而生
        Integer n = new Integer(10);
        n.intValue();   // 获取它的int类型的值
        n = null;
        // 实际上每一个基础类型都有对应的包装类型
        Integer n2 = Integer.valueOf(20); // 通过静态方法创建Integer实例
        n2.intValue();
        // 因为int与Integer可以相互转换,所以Java编译器帮助我们自动转换
        // 此操作成为自动装箱,与之对应的还有自动拆箱,这种操作只发生在编译的阶段
        // 自动拆箱会影响效率,编译后的class代码是严格区分引用和基本类型的,
        // 并且自动拆箱可能会引发NullPointerException
        int n3 = n2;
        // 对两个Integer比较时应该使用equals()
        // Integer.valueOf(),在数较小的时候会返回相同的实例
        // 编译器会将Integer n = 127;自动变为valueOf(127);valueOf是一个静态工厂方法
        // 创建实例的时候优先使用静态工厂方法而非new,valueOf有缓存优化
        // Integer还提供了大量的静态方法
        Integer.parseInt("100", 16);   // 16进制转换
        Integer.toHexString(100);   // 转为16进制的字符串
        Integer.toString(100);   // 10进制
        Integer.toString(100, 36);   // 36进制
        Integer.toOctalString(90);   // 8进制
        Integer.toBinaryString(1000); // 2进制
        // 在内存中都是2进制,int是4字节的2进制
        // Integer还有一些有用的静态变量
        int max = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        boolean t = Boolean.TRUE;
        t = Boolean.FALSE;
        int size = Long.SIZE;
        size = Long.BYTES;

        // 因为所有的整数类型都继承了Number,可以获取各种基本数据类型
        Number num = new Integer(999);
        num.doubleValue();
        num.floatValue();
        num.longValue();
        num.intValue();

        // 处理无符号整型,虽然java没提供无符号整型,但是由于C语言提供了CPU支持的全部数据类型
        // 使用无符号整型需要通过包装类的静态方法来实现
        byte x = -1;
        Byte.toUnsignedInt(x); // 将byte类型的x转化为无符号整型的int
        // 同理 我们可以把short类型的转为unsigned的int
        // 将int转为无符号的long
    }
}