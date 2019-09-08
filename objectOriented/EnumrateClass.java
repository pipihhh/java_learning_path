public class EnumrateClass {
    public static final int SUN = 0;
    public static final int MON = 1;
    public static final int TUE = 2;
    public static final int WED = 3;
    public static final int THU = 4;
    public static final int FRI = 5;
    public static final int SAT = 6;

    public static void main(String[] args) {
        // 通常通过static final来定义一些常量,比如上述的周一到周日
        // 但是这种玩法存在安全隐患,编译器无法发现这种错误
        // 枚举类只能是这些类型中的一种,这样的话在判断的时候就不容易出错,编译器会检查错误
        // 就不可以使用类似1 == day这种判断,会报错
        // 并且不同类型的枚举类之间不可相互赋值,因为类型不同,使得编译器在编译期间可以找到错误
        // 在比较枚举类的时候需要用到equals
        Weekday day = Weekday.FRI;
        System.out.println(day.equals(Weekday.FRI));
        // enum本质上也是一个class,继承了内部的java.lang.Enum且无法被继承
        // 只能定义出enum实例无法通过new创建enum的实例
        // 定义的实例都是引用类型的唯一实例,且可以将enum用在switch
        // 由于是继承自eunm,所以有些特殊的方法
        day.name();  // 返回实例的名字的字符串
        day.ordinal();  // 返回常量的计数的顺序,从0开始,则FRI为5
        // 通过构造方法给每个常量添加值
    }
}


final class Color extends Enum {
    public static final Color RED = new Color();
    public static final Color GREEN = new Color();
    public static final Color BLUE = new Color();


    private Color() {} // 构造方法设为private后则无法通过new来创建对象
}


enum Weekday {
    SUN(0), MON(1), TUE(2), WED(3), THU(4), FRI(5), SAT(6);

    public final int dayValue;

    private Weekday(int dayValue) {
        this.dayValue = dayValue;
    }
}
