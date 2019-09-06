public class Polymorphism {
    public static void main(String[] args) {
        Person p = new Student();
        p.run();
        p.walk();
    }
}


abstract class Person {
    public void run() {
        System.out.println("running...");
    }

    public abstract void walk();
}


// 在继承的时候使用@Override可以让编译器检查是否进行了正确的重写,如果写错了会报错,@Override不是必须的
// 重写方法,如果返回值不同,参数不同则会产生错误
// 如果Person p = new Student();此时如果调用run方法执行的实际上是Student的run,这就是多态,方法的调用根据运行时的实际类型动态调用
// 重写Object方法,所有类都继承自Object,它提供了几个重要的方法
// equals()判断两个instance是否逻辑相等,toString()把类输出为String,hashCode()计算一个instance的哈希值
// final关键字,继承可以允许子类重写父类的方法,如果一个父类不希望子类重写某个方法,可以加上final标记
// 如果一个类 你不希望任何类继承它,同样可以用final修饰
// 对于一个类的字段,同样可以用final修饰,用final修饰的字段在初始化后不能被修改
// 抽象方法,本身不实现任何功能,仅仅为了让子类去重写它
// 如果一个类内部有抽象方法,则必须把这个类也声明为abstract,抽象类本身被设计为只能用不被继承,则子类没实现此方法则报错
final class Student extends Person {
    public final String name = "123mtr"; // 如果修改会报compile error
    
    @Override
    public void run() {
        System.out.println("student running...");
    }

    @Override
    public void walk() {
        System.out.println("student walking...");
    }

    public final void hello() {
        System.out.println("Hello...");
    }
}
