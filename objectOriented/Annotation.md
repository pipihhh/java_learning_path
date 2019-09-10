## 注解

### 1，注解是放在源码的类，方法，字段，参数前的一种特殊的注释

```java
@Resource("hello")
public class Hello {
    @Inject
    int n;
    
    @PostConstruct
    public void hello(@Param String name) {
        System.out.println(name);
    }
    
    @Override
    public String toString() {
        return "Hello"
    }
}
```

注释会被编译器忽略，注解会被打包进class文件，因此注解是一种用作标注的“元数据”

### 2，注解的作用

从JVM的角度来讲，注解本身对代码逻辑没有任何的影响，如何使用注解完全由工具决定

Java的注解可分为3类：

第一类是由编译器使用的注解：

​	@Override：检查方法是否正确的覆写

​	@SuppressWarnings：告诉编译器忽略此处代码产生的警告

这类注解不会被编译进入class文件，它们在编译后就被扔掉了。

第二类是由工具处理.class文件使用的注解，比如有些工具会在加载class的时候，对class做动态修改，实现一些特殊的功能。这类注解会被编译进入.class文件，但加载结束后并不会存在于内存中。这类注解只被一些底层库使用，一般我们不自己处理。

第三类是在程序运行期能够读取的注解，它们在加载后一直存在于JVM中，这也是最常用的注解。例如，一个配置了@PostConstruct的方法会在调用构造方法后自动被调用（这是java代码读取该注解实现的功能，JVM并不会识别该注解）

定义一个注解时，还可以自定义配置参数。配置参数可以包括：

​	所有基本类型

​	String

​	枚举类型

​	基本类型、String以及枚举类型的数组

因为配置参数必须是常量，所以上述限制保证了注解在定义时就已经确定了每个参数的值。

注解的配置参数可以有默认值，缺少某个配置参数时将使用默认值。

此外大部分注解会有一个名为value的配置参数，对此配置参数复制，可以只写常量，相当于省略了value的参数

如果只写注解，相当于全部使用默认值。

例如：

```java
public class Hello {
    @Check(min=0, max=100, value=55)
    public int n;
    
    @Check(value=99)
    public int p;
    
    @Check(99)
    public int x;
    
    @Check
    public int y;
}
```

@Check就是一个注解。第一个明确了3个参数，第二个只定义了value参数，实际上和@Check(99)是完全一样的，最后一个表示所有都使用默认参数