import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

public class JavaBean {
    public static void main(String[] args) {
        // 在java中有很多class都符合这种规范
        // 若干个private的字段配合山若干个public getXXX和setXXX的方法
        // 如同下方这种写法的class成为JavaBean
        // 普通类型的字段有对应的get和set方法,boolean比较特殊,命名一般为isXXX
        // 我们通常把一组对应的读写方法成为属性,property,只有getter的方法成为只读属性
        // 与之对应的只有setter的成为只写属性,只读比只写更加的常见
        // 只读属性并不一定非要有对应的字段,比如下方的isChild
        // JavaBean方便IDE解析,生成代码减少开发,主要用在图形化可视开发中
        // 如果想要枚举一个JavaBean的所有属性,要用到一个自带的库函数
        // 注意,class属性是从Object继承而来的getClass()方法带来的
        BeanInfo info = Introspector.getBeanInfo(Person.class);
        for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
            System.out.println(pd.getName());
            System.out.println("   " + pd.getReadMethod());
            System.out.println("   " + pd.getWriteMethod());
        }
    }
}


class Person {
    private String name;
    private int age;

    public boolean isChild() {
        return this.age <= 6;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
