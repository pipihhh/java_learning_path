import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class JavaError {
    public static void main(String[] args) throws Exception{
        // 写代码会遇到许许多多的异常
        // 用户希望输入一个年龄,但是用户输入的是abc
        // 还有一些错误永远无法避免,网络错误,内存耗尽
        // java中有一套异常处理机制,异常也是java中的一种class,因为本身携带有类型的信息,
        // 异常可以在任何地方抛出,但需要在上层进行捕获
        // java中的异常,Throwable是所有异常的根,它有两个体系,Error和Exception
        // Error表示严重的错误,程序一般对此无能为力,例如OutOfMemoryError内存耗尽
        // Exception表示运行时错误,他可以被捕获并处理,
        // 还有一些异常是程序本身的逻辑问题,需要修复编码的,NullPointerException等
        // Exception又分为两大类,RuntimeException和非RuntimeException(IOException等)
        // java规定必须捕获的异常,Exception及其子类但不包括RuntimeException
        byte[] b = toGBK("中文");
    }

    public static byte[] toGBK(String s) throws Exception{
        // 如果在这里不捕获这个异常会报编译错误
        // 因为String.getBytes的定义为 ....  throws UnsupportedEncodingException
        // 这样的check Exception必须被捕获
        // 在定义方法的时候可以指定可能抛出的异常类型,这样的调用的时候就必须捕获这个异常否则会报编译错误
        // 如果声明了可能抛出Exception表示可能抛出所有的Exception,那么就无需捕获异常了
        // 代价就是如果发生异常,程序会即可退出
        // JVM在捕获到异常后会从上到下匹配catch,匹配到某个catch后执行代码块,然后不再继续匹配
        // 存在多个catch时顺序是非常重要的,应该先将子类放在上面
        // finally 无论报没报错都会执行
        // 如果catch到多个错误的处理逻辑是相同的那么可以用|分割多个错误
        // throw还可以抛出新的异常,详细为下面的throw关键字,需要创建一个异常的实例并抛出
        // 如果在catch代码块中抛出了新的异常,那么在打印异常调用栈的时候会丢失原异常的信息
        // 为了能获取到完整的信息,通常在构造实例的时候将原来异常的实例放入构造方法中
        // 在catch中抛出异常后,JVM会先执行finally后再抛出异常
        // 如果在finally中抛出了一个异常,那么在catch中抛出的异常就消失了,此成为异常屏蔽
        // 如果想保存完整的异常,见下面玩法
        Exception e = null;
        try {
            return s.getBytes("GBK");
        } catch (UnsupportedEncodingException | IOException e) {
            e.printStackTrace();    // 打印异常栈  所有的异常都有这个方法
            System.out.println(e);  // 打印异常
            return s.getBytes();
        } catch (Exception e) {
            e.printStackTrace();
            origin = e;
            throw new Exception(e);
            return null;
        } finally {
            Exception exception = new Exception();
            if (origin != null) {
                exception.addSuppressed(origin);
            }
            System.out.println("finally...");
            throw exception;
        }
    }
}