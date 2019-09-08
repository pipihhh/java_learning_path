package dataType;
import java.math.BigInteger;

public class BigInt {
    public static void main(String[] args) {
        // java的long类型64位的整数类型在CPU指令进行计算,速度非常快
        // 但是如果数的范围超过了long的长度怎么办呢,此时只能用一个软件来模拟大整数,BigInteger
        // BigInteger内部是一个int[] 的数组来模拟大整数的
        // BigInteger做运算的时候只能使用实例方法
        BigInteger bi = new BigInteger("1234567890");
        BigInteger b2 = new BigInteger("12345678901234567890");
        System.out.println(bi.pow(5));   // 幂运算
        System.out.println(bi.add(b2));  // 加法
        // 和long类型的整数运算比起来,BigInteger不会有范围的限制,但是缺点是速度奇慢无比
        // 可以把BigInteger转换成long类型
        System.out.println(bi.longValue());
        // System.out.println(b2.multiply(b2).longValueExact()); // 此方法如果超过long的长度会报错ArithmeticException
        // BigInteger也集成了Number类,因此也有几个方法byteValue等
        // BigInteger因此也可以转化为基本类型,但是超过长度的部分会被剔除,因此结果并不准确
        // intValueExact,longValueExact等方法在不准确时会抛出ArithmeticException异常

        System.out.println(new BigInteger("999999").pow(99).floatValue());  // 结果为Infinity无限大
    }
}