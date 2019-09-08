public class Interface {
    public static void main(String[] args) {
        Person p = new Student("name");
        p.run();
    }
}


// 接口相关,如果一个抽象类的所有方法均为抽象方法,那么他就是一个接口
// 一个接口只能有抽象方法,甚至连字段都不允许有,由于所有方法默认都是public abstract的,所以甚至连修饰符都有,写不写效果一样
// 当继承一个接口时,不再是extends而是implements,实现的意思,类是继承而接口是实现
// java中一个类只能继承一个类,但是可以实现多个接口
// 接口与抽象类的不同之处在于,抽象类可以定义实例字段,可以定义非抽象方法,而接口都不可以
// 一个interface可以继承另一个interface,接口之间的继承使用extends
// 多态无法调用public的方法但是如果内部内部调用了public方法是可以调用的
// interface有一个default方法,是可以不被重写的,default方法与抽象类的普通方法的区别是,抽象类的普通方法可以修改类的字段而接口不可以
interface Person {
    void run();
    String getName();
    default void see() {
        System.out.println("seeing...");
    }
}

interface Animal {
    void eat();
}

class Student implements Person, Animal {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public void talk() {
        System.out.println(name + " talking...");
    }

    @Override
    public void run() {
        setName("ppp");
        System.out.println(this.name + "running...");
        talk();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void eat() {
        System.out.println(this.name + "eatting...");
    }
}
