public class Scope {
    public static void main(String[] args) {
        Inner inner = new Inner();
    }

    private static void hello() {
        System.out.println("Hello");
    }

    private void walk() {
        System.out.println("Im walking...");
    }

    // 定义在类内部的嵌套类,静态嵌套类,可以访问私有的hello方法
    static class Inner {
        Scope scope = new Scope();

        public Inner() {
            Scope.hello();
            this.scope.walk();
        }
    }
}


// 作用域相关
// public的class,interface,上面的Scope被public修饰,可以被其他包的类访问
// 被定义为public的field和method可以被其他类访问,前提是你的类拥有访问权限
// private,定义为private的field和method无法被其他类访问,private被限定在类内部,且无法被继承
// 但是有一个例外,那就是嵌套类,如果一个类嵌套在了method内,则他可以访问private
// 在同一个package中一个类可以访问,所有不带public,private修饰的类的方法
// 以及没有上述修饰符和protected修饰的方法和字段
// final修饰符与上述修饰符不同,他很特殊,作用为防止修饰的类被继承
// 防止修饰的字段被修改,防止修饰的方法被重写,在方法内部修饰,防止局部变量的值改变
