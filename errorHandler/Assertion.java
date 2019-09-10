public class Assertion {
    public static void main(String[] args) throws AssertionError{
        // java中也有断言
        // 下为断言的语法,第二个断言代表了如果出现错误的话报的错误信息,如果断言失败,表达式为false,则会报AssertionError
        // 断言多用于(只应该)开发和测试阶段
        // JVM默认是关闭断言指令的,遇到断言不执行,如果想让它执行,必须java -ea(-enableassertions)
        // java -ea:com.itranswarp.sample.Main,表示这个类启用断言
        // java -ea:com.iteranswarp.sample...  注意三个.  表示对这个包启用断言
        int x = -1;
        assert x >= 0;
        assert x <= 0 : "x must <= 0";
    }
}