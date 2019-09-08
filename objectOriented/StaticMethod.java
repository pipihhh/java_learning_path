public class StaticMethod {
    public static void main(String[] args) {
        
    }
}


// 对于静态字段,可以通过Person.name的方式来访问,也可以通过实例.name的方式来访问
// 无论上述那种访问方式,如果Person.name="1"这种方式修改的话,那么所有此类实例的name都将修改,所有实例都共享该字段
// 静态方法可以通过Person.setName的方式调用,静态方法的内部没有this关键字,静态方法类似于其他语言的函数
// 静态方法常用于工具类,比如Arrays.sort(),Math.random(),静态方法也经常用于辅助方法,java的入口函数main()也是静态方法
// 接口的静态字段,接口不可以有实例字段但是可以有静态字段,静态字段为必须是public static final类型,所以上述修饰符可以去掉

class Person {
    public static String name;
    private static int count = 0;

    public Person() {
        Person.count++;
    }

    public static getCount() {
        return Person.count;
    }

    public static setName(String name) {
        Person.name = name;
    }
}
