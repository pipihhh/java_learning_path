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

----

### 2，定义注解

Java语言使用@interface语法来定义注解（Annotation）

```java
public @interface Report {
  int type() default 0;
  String level() default "info";
  String value() default "";
}
```

注解的参数类似无参数方法，可以用default设定一个默认值（强烈推荐）。最常用的参数应当命名为value。

### 元注解

有一些注解可以修饰其他注解，这些注解就称为源注解（meta annotation）。Java标准库已经定义了一些元注解，我们只需要使用元注解，通常不需要自己去编写元注解。

**@Target**

最常用的元注解是Target。使用Traget可以定义Annotation能够被应用于源码的哪些位置：

- 类或接口：ElementType.TYPE
- 字段：ElementType.FIELD
- 方法：ElementType.METHOD
- 构造方法：ElementType.CONSTRUCTOR
- 方法参数：ElementType.PARAMETER

例如，定义注解@Report可用在方法上，我们**必须**添加一个@Target(ElementType.METHOD)：

```java
// @Target(ElementType.METHOD)，此为只加入一个注解是这么玩的
@Target({ ElementType.METHOD, ElementType.FIELD })
public @interface Report {
  int type() default 0;
  String level() default "info";
  String value() default "";
}
```

Target注解内部接收的value参数是ElementType的数组 用了...的玩法？

**@Retention**

另一个重要的元注解@Retention定义了Annotation的声明周期：

- 仅编译期：RetentionPolicy.SOURCE
- 仅class文件：RetentionPolicy.CLASS
- 运行期：RetentionPolicy.RUNTIME

如果@Retention不存在，则该Annotation默认为CLASS。因为通常我们自定义的Annotation都是RUNTIME，所以，务必要加上@Retention(RetentionPolicy.RUNTIME)这个元注解：

```java
@Retention(RetentionPolicy.RUNTIME)
public @interface Report {
  int type() default 0;
  String level() default "info";
  String value() default "";
}
```

**@Repeatable**

使用@Repeatable这个元注解可以定义Annotation是否可以重复。应用并不广泛。

```java
@Repeatable
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Report {
  int type() default 0;
  String level() default "info";
  String value() default "";
}
```

在@Repeatable修饰后，在某个类型声明处，就可以添加多个@Report注解

```java
@Report(type=1, level="debug")
@Report(type=2, level="warning")
public class Hello {
  // ...
}
```

**@Inherited**

使用@Inherited定义子类是否可以继承父类定义的Annotation。@Inherited仅针对@Target(ElementType.TYPE)类型的annotation有效，并且仅针对class的继承，对interface的继承无效

```java
@Inherited
@Target(ElementType.TYPE)
public @interface Report {
  int type() default 0;
  String level() default "info";
  String value() default "";
}
```

在使用的时候，如果一个类用到了@Report

```java
@Report(type=1)
public class Person {
  // ...
}

public class Student extends Person {
  // ...
}
```

那么它的子类默认也定义了该注解

### 3，注解的使用

Java的注解本身对代码逻辑没有任何影响。根据@Retention的配置：

- SOURCE类型的注解在编译期就被丢掉
- CLASS类型的注解仅保存在class文件中，它们不会被加载进JVM
- RUNTIME类型的注解会被加载进JVM，并且在运行期可以被程序读取

如何使用注解完全由工具决定。SOURCE类型的注解只要由编译期使用，因此我们一般只使用，不编写。CLASS类型的注解主要由底层工具库使用，涉及到class的加载，一般我们很少使用。只有RUNTIME类型的注解不但要使用，还经常需要编写。

因为注解定义后也是一种class，所有的注解都继承自**java.lang.annotation.Annotation**，因此读取注解，需要使用反射API。

判断某个注解是否存在于Class，Field，Method或Constructor

- Class.isAnnotationPresent(Class)
- Field.isAnnotationPresent(Class)
- Method.isAnnotationPresent(Class)
- Constructor.isAnnotationPresent(Class)

例如：

```java
// 判断@Report是否存在于Person类
Person.class.isAnnotationPresent(Report.class);
```

使用反射API读取Annotation

```java
// 获取Person定义的@Report注解
// 如果Annotation不存在，将返回null
Report report = Person.class.getAnnotation(Report.class);
int type = report.type();
String level = report.level();

// 对于以下方法定义的注解
public void hello(@NotNull @Range(max=5) String name, @NotNull String prefix) {
  // ...
}
// 要读取方法参数的注解，先用反射获取Method实例，然后读取方法参数的所有注解
Method m = ...; // 获取方法对象的实例
Annotation[][] annos = m.getParameterAntations();
Annotation[] annosOfName = annos[0];
for (Annotaion anno : annosOfName) {
  if (anno instanceof Range) {
    Range r = (Range) anno;
  }
  if (anno instanceof NotNull) {
    NotNull n = (NotNull) anno;
  }
}
```

**使用注解**

注解的使用完全由程序决定。例如JUnit是一个测试框架，它会自动运行所有标记为@Test的方法。

我们来看一个@Range注解，我们希望用它来定义一个String字段的规则：字段长度满足@Range的参数定义：

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Range {
  int min() default 0;
  int max() default 255;
}

// 在某个JavaBean中我们可以使用该注解
public class Person {
  @Range(min=1, max=20)
  public String name;
  
  @Range(max=10)
  public String city;
}

// 虽然定义了注解，本身对程序没有任何的影响。我们必须自己编写代码来使用注解。这里我们编写一个Person实例的检查方法，他可以检查Person实例的String字段长度是否满足@Range的定义

void check(Person person) throws  IllegalArgumentException, ReflectiveOperationException {
  for (Field field : Person.getClass().getFields()) {
    Range range = field.getAnnotation(Range.class);
    if (range != null) {
      Object value = field.get(person);
      if (value instanceof String) {
        String s = (String) value;
        if (s.length() < range.min() || s.length() > range.max()) {
          throw new IllegalArgumentExcepion("Invalid field: " + field.getName());
        }
      }
    }
  }
}
```

这样一来，我们通过@Range注解配合chack()方法就可以完成Person实例的检查。注意检查逻辑完全是我们自己编写的，JVM不会自动给注解添加任何额外的逻辑。

```java
// 通过@Range注解配合check方法检查字段，如果是String就检查String的长度，如果是int就检查int的范围
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Report {
  int length() default 255;
  int max() default 255;
  int min() default 0;
}

public class Person {
  @Report(length=10, max=100, min=1)
  public int age;
  
  @Report(length=256)
  public String name;
}

void check(Person person) throws IllegalArgumentException, ReflectiveOperationException {
  for (Field field : person.getClass().getFields()) {
    try {
      String value =  (String) field.get(person);
    } catch (Exception e) {
      int value = (int) field.get(person);
    }
    Report report = field.getAnnotation(Report.class);
    if (report != null) {
      if (value instanceof int) {
      if (report.max < value || report.min > value) {
        throw new IllegalArgumentException("Invalid field: " + field.getName());
      }
    }
    if (value instanceof String) {
      if (report.length < value.length()) {
        throw new IllegalArgumentException("Invalid field: " + field.getName());
      }
    }
    }
  }
}
```





