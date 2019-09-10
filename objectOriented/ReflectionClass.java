import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Method;

public class ReflectionClass {
    public static void main(String[] args) {
        // 除了int等基本类型外,Java的其他类型全部都是class,包括interface,例如Object,Exception,Runnable等
        // 仔细思考,我们可以得出结论,class(包括interface)的本质是数据类型(Type),无继承关系的数据类型无法赋值
        // Number n = new Double(123.456);  可以
        // String s = new Double(123.456);  -----> compile error!
        // class是由JVM在执行过程中动态加载的,JVM在第一次读取到一种class类型时,将其加载进内存
        // 每加载一种class,JVM就为其创建一个Class类型的实例并关联起来,这里的Class是一个叫Class的class
        // 以String类为例,当JVM加载String类时,它首先读取String.class文件到内存,然后为String类创建一个Class实例并关联起来
        // CLass实例时JVM内部创建的,Class类的构造方法是private,只有JVM能创建Class实例
        // JVM持有每个Class实例都指向一个数据类型,class或interface
        // 一个Class实例包含了该class的所有完整的信息,name,package,super,interface,field,method等
        // 如果获取了某个class的实例,那么我们就获取到了该class 的所有的信息,这种通过Class实例获取class信息的方法成为反射
        // 上述为创建类而不是实例化
        // 获取Class实例方法1:直接通过一个class的静态变量class获取
        Class n = ReflectionClass.class;
        // 如果我们有一个实例变量,则通过getClass()来获取
        n.getClass();
        // 如果知道一个完整的类名,可以通过静态方法Class.forName()来获取
        n = Class.forName("java.lang.String");
        // 因为Class实例在JVM中是唯一的,所以可以通过==来比较
        // 注意Class实例和instanceof的区别,用instanceof不但匹配当前类型,还匹配当前类型的子类,用==判断Class实例可以精确地判断数据类型但不能作为子类
        // 因为反射的目的是为了获得某个实例的信息,因此当我们拿到Object实例时,我们可以通过反射获取该Object的class

        // 通过Class实例获取字段信息,只要我们获取了它的class,就可以获取一切信息,包括字段信息
        // Class类提供了一下几个方法来获取字段:
        // Field getField(name),根据字段名获取某个public的field(包括父类的)
        // Field getDeclaredField(name),根据字段名获取当前类的某个field(不包括父类)
        // Field[] getFields(), 获取所有的public的field(包括父类)
        // Field[] getDeclaredField()  获取当前类的所有field(不包括父类)
        // getDeclaredField方法可以获取private字段
        // 一个field字段包含了字段的所有信息,getName()返回字段名,getType()返回字段类型,getModifiers()返回字段的修饰符
        Person p = new Person();
        Class cls = p.getClass();
        System.out.println(cls.getField("age"));
        Field f = cls.getDeclaredField("name");
        f.getName();
        f.getType();
        int m = f.getModifiers();
        Modifier.isFinal(m);
        Modifier.isPublic(m);
        Modifier.isProtected(m);
        Modifier.isPrivate(m);
        Modifier.isStatic(m);
        // 关于获取字段的值
        f.setAccessible(true);  // 不管这个字段是不是public都可以访问
        Object value = f.get(p);  // 获取f字段在p对象中的值
        // 如果f在p中是private的,那么会报一个IllegalAccessException的错误,原因是此字段时private的
        // 此时需要这么做:在调用get前使用setAccessible,此方法可能会失败,如果JVM运行期存在SecurityManager,那么它会根据规则进行检查
        // 有可能阻止setAccessible(true)

        // 通过Field实例也可以设置字段的值
        f.set(p, "wangba");   // 第一个参数是指指定的实例,第二个参数是待修改的值,如果设置了Access同样可以修改private的字段

        // 调用方法,通过Class实例获取所有的Method信息,Class也提供了几个方法来获取Method
        // Method getMethod(name, Class...): 获取某个public的Method(包括父类)
        // Method getDeclareMethod(name, Class...): 获取当前类的某个Method(不包括父类)
        // Method[] getMethods() 获取所有的public的Method(包括父类)
        // Method[] getDeclareMethods()  获取当前类的所有Method(不包括父类)

        Class stdClass = Student.class;
        Method mt = stdClass.getMethod("Student", int.class);   // 获取一个参数为int的名字为Student的方法
        stdClass.getMethod("Student");   // 获取一个无参数的方法
        stdClass.getDeclaredMethods();   // 获取所有的方法,不包括父类
        stdClass.getMethods();     // 获取包括父类的所有的public的方法
        // 一个Method对象包含一个方法的所有信息
        // getName(),getReturnType(),getParameterTypes(),getModifiers()分别是,方法名,返回值类型,方法的参数类型(返回一个CLass数组),方法的修饰符
        int mod = mt.getModifiers();
        Modifier.isAbstract(mod);  // 等等等

        // 调用方法
        String s = "Hello world";
        // 我们要通过反射来调用substring方法
        Method mt2 = String.class.getMethod("substring", int.class);
        String r = (String) mt2.invoke(s, 6);  // 在s对象上调用该方法并获取结果
        System.out.println(r);

        // 调用静态方法
        Method mt3 = Integer.class.getMethod("parseInt", String.class);
        Integer ret = (Integer) mt3.invoke(null, "12345");  // 由于是静态方法,所以对象这填null
        System.out.println(ret);

        // 调用非public方法,和Field类似,也需要设置
        Person p2 = new Person("19970101");
        Method mt4 = p2.getClass().getDeclaredMethod("getBirth");
        mt4.setAccessible(true);
        String res = (String) mt4.invoke(p2);
        System.out.println(res);
        
        // 反射调用方法也遵循多态原则,假如通过Person的实例获取了Method对象A,用对象A来调用Student实例的方法时,会调用Student的方法

        // 刚才说到用newInstance的方法来创建新的实例是有局限性的
        // 为了调用任意的构造方法,Java的反射API提供了Constructor对象,它包含一个构造方法的所有信息,可以创建一个实例,Constructor对象和Method非常的类似
        // 不同之处仅在于它是一个构造方法,并且调用的结果总是返回实例
        // 获取Integer(int)
        Constructor cons1 = Integer.class.getConstructor(int.class);   // 与Method太类似了,int.class代表了获取参数时int的构造方法对象
        Integer n1 = (Integer) cons1.newInstance(123);

        // 如果想要获取private的构造方法,需要设置
        Constructor cons2 = Integer.class.getConstructor(String.class);
        cons2.setAccessible(true);
        Integer n2 = (Integer) cons2.newInstance("123");

        // 获取它的父类的Class
        Class c2 = Integer.class.getSuperclass();
        System.out.println(c2.getSuperclass());

        // 获取interface, getInterfaces只会返回当前类实现的接口类型,并不会返回父类的
        // 如果对一个interface调用getSuperclass,返回的是它的父interface或者null,interface并没有继承object
        // 如果一个类没有实现任何interface,则getInterfaces返回的是空数组
        for (Clsss i : c2.getInterfaces()) {
            System.out.println(i);
        }

        // 如果是两个class实例判断向上转型是否成立
        Integer.class.isAssignableFrom(Integer.class);   // true
        Number.class.isAssignableFrom(Integer.class);   // true
    }

    static void printObjectInfo(Object obj) {
        // JVM为每一种基本类型如int也创建了Class,通过int.class访问
        // 数组(String[])也是一种Class,而且不同于String.Class,它的类名是[Ljava.lang.String
        Class cls = obj.getClass();
        System.out.println(cls.getName());
        System.out.println(cls.getSimpleName());
        System.out.println(cls.getPackage().getName());
        System.out.println(cls.isInterface());
        System.out.println(cls.isEnum());
        System.out.println(cls.isArray());
        System.out.println(cls.isPrimitive());

        // 如果获取到了一个Class实例,我们就可以通过该Class实例来创建对应类型的实例
        // 下述这种创建方式局限性是只能调用public的无参的构造方法
        Class cls2 = String.class;
        String s = (String) cls2.newInstance();

        // 动态加载:只有JVM发现用到了这个类的时候,才会去加载,只有在第一次需要用到class时才加载
        // 这就是JVM动态加载class的特性
        // Commons Logging的总是优先使用Log4j,只有Log4j不存在时才使用logging就是利用JVM的动态加载特性
    }
}


class Person {
    public int age;
    public String name;
    private String birth;

    public Person(String birth) {
        this.birth = birth;
    }

    private String getBirth() {
        return this.birth;
    }
}


class Student extends Person {
    public int classNumber;

    public Student(int classNumber) {
        super("19960705");
        this.classNumber = classNumber;
    }
}
