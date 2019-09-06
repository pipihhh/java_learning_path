import java.util.Arrays;

public class ObjectBase {
    public static void main(String[] args) {
        Person person = new Person();
        System.out.println(person.getClassmate());
        String[] ns = { "abc", "def", "fff" };
        person.setClassmates(ns);
        ns[0] = "ddd";
        System.out.println(person.getClassmate());

        Person p = new Student();
        System.out.println(p instanceof Person);
        System.out.println(p instanceof Student);
    }
}


class Person {
    // 一个class可以包含多个field,下面为此类的两个field,如果用public来修饰这些field,这些field就可以在外部通过.的方式访问到
    // 这么做就破坏了一个类的封装性
    // public String name;
    // public int age;
    // 此时应该用,private,私有的
    private String name;
    private int age;
    private String[] classmate;

    // 此为构造方法,构造方法在每次实例化的时候执行
    // 构造方法的方法名就是类名,构造方法没有返回值,也没有void,必须用new操作符
    // 没写构造方法的类默认有一个空的构造方法,此方法在编译时由编译器创建
    // 可以写多个构造方法,使用不同的参数,如果类中 有了构造方法,编译器不会帮忙创建
    // 如果没有在构造方法中初始化字段,引用类型默认为null,数值类型为0,boolean为false
    // 在构造方法内部调用其他构造方法
    public Person() {
        this("abc", 12);
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return calcAge(2019);
    }

    public void setName(String name) {
        this.name = name;
    }

    // 用这种方法来修改类内部的属性,可以在这时加一层校验来防止被设置成错误的数据
    // private方法,此方法为私有方法,只有在类的内部才可以调用
    public void setAge(int age) {
        if (age >= 10 && age <= 120) {
            this.age = age;
        }
        throw new IllegalArgumentException("invalid age value");
    }

    // 在类中this就指代当前类的实例,如果类属性与方法内变量不重名,可以不用写this
    private int calcAge(int currentYear) {
        return currentYear - this.age;
    }

    // 可变参数,相当于数组类型,当传入0个参数时,其为空数组,避免了接收到的参数为null的情况
    // 当传入的参数为引用类型时,如果修改了传入时的变量,则类中的变量值也会改变
    // 但是基础数据类型的传递,传的是值,会复制传递
    public void setClassmates(String... names) {
        this.classmate = names;
    }

    public String getClassmate() {
        return Arrays.toString(this.classmate);
    }

    // 方法的重载
    // 在一个类下面可以有多个同名不同参数的方法,此成为方法的重载Overload
    // 方法重载的返回值类型通常是相同的,方法重载的目的是,功能类似的方法使用同一名字,容易记住
    // 比如String的indexOf就提供了多种玩法

    public void hello() {
        System.out.println("123");
    }

    public void hello(String name) {
        System.out.println(name);
    }
}


// 继承,使用关键字extends
// 在java中任何类除了Object,都会继承自某个类,如果没写extends,则默认继承Object,所有类都是Object的基类
// 在java中,子类无法访问父类的private字段或者private方法,如果有一个属性或方法想被继承下来并且不想被外部访问,则需要使用关键字protected
// protected修饰的可以被子类以及子类的子类访问
// super关键字,表示引用父类的字段时,可以使用
// 任何class的构造方法的第一行语句必须是调用父类的构造方法,如果没有明确的调用父类的构造方法,编译器会帮我们自动加一句super()
// 子类不会继承父类的构造方法,子类默认构造方法是编译器生成的
// 向上转型,Person p = new Student();这种玩法是允许的,此时这个p对象是没有Student的新方法的,只具有一些Person的方法的重写方法和Person的方法
// 向下转型,Person p = new Student();Person p2 = new Person();则p = (student) p;是可以的,p2 = (student) p2会报RuntimeError ClassCastException
// 不能把父类转为子类,父类的功能少于子类,在上面p实际上指向的是Student实例
// 使用instanceof操作符来判断一个实例是否是一个类的实例
// 只有在两个对象是is的关系的时候才应该用继承,其他时候应该使用组合
class Student extends Person {
    public Student() {
        super();
    }

    public int getScore() {
        return 100;
    }
}
