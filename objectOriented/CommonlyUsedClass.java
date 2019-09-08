import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

public class CommonlyUsedClass {
    public static void main(String[] args) {
        // java的核心库提供了很多强大的工具类
        // Math提供了大量的静态方法来便于我们实现数学计算
        Math.abs(-123);  // 绝对值
        Math.max(1, 2);  // 返回较大的那一个值
        Math.min(3, 4);  // 返回较小的那一个值
        Math.pow(2, 10);  // 2的10次方
        Math.sqrt(2);    // 根号2
        Math.log(4);    // 以e为底4的对数
        Math.log10(4);   // 以10为底4的对数
        Math.sin(3.14);  // 三角函数
        Math.asin(3);
        // Math.PI;  // Math还提供了几个数字常量
        // Math.E;   
        Math.random();  // 随机数0<=x<1
        // java的标准库中还有一个函数StrictMath,提供了几乎一模一样的方法
        // 不同之处在于,StrictMath保证了在ARM和x86下计算结果一致,一般情况下使用Math即可

        // Random对象,伪随机,也就是只要给定了一个初始的种子,产生的随机数序列是完全一样的
        // 那么如何给种子呢,其实在实例化的时候,如果传入一个种子那么就使用这个种子
        // 如果没使用就采用当前时间戳为种子,Math.random实际上调用的是Random,我们无法指定种子,是伪随机
        Random r = new Random();
        r.nextInt(10);   // 生成一个0-10之间的随机数
        r.nextInt();   // 随机数 完全随机
        r.nextFloat();   // [0-1)随机
        r.nextDouble();  // 上同

        // 有伪随机就有真随机,实际上真随机只能通过量子力学原理来获取,在java中我们用SecureRandom用来创建一个安全的随机数
        SecureRandom sr = new SecureRandom();
        System.out.println(sr.nextInt(100)); // SecureRandom无法指定种子,它使用RNG算法(random number generator)
        // JDK的SecureRandom实际上有多种不同的底层实现,使用安全随机种子加上伪随机数算法产生安全的随机数
        // 有的使用真正的随机数生成器
        SecureRandom sr2 = null;
        try {
            sr2 = SecureRandom.getInstanceStrong();  // 获取高强度安全随机数生成器
        } catch (NoSuchAlgorithmException e) {
            sr2 = new SecureRandom(); // 获取普通的安全随机数生成器
        }
        byte[] buffer = new byte[16];
        sr2.nextBytes(buffer);  // 用安全的随机数填充buffer
        // 安全的随机数通过操作系统获取安全的种子,这些种子可能是CPU的热噪声,读写磁盘的字节,网络流量等随机事件产生的熵
        System.out.println(Arrays.toString(buffer));
    }
}