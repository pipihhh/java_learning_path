## 动态代理

### 1，不写class的代码，直接在运行期创建一个interface的对象

```java
public interface Hello {
    void morning(String name);
} // 定义了一个接口

public class HelloWorld implements Hello {
    public void morning(String name) {
        System.out.println("Good morning..." + name);
    }
}
```

上述代码块为静态代码，也是最常见的一种编写代码的方式。

还有一种方式是动态代码，我们仍然先定义一个接口，但是我们不去编写类的类的具体实现，而是直接通过JDK提供的一个Proxy.newProxyInstance()创建了一个Hello接口的对象。这种没有实现类但是在运行期间动态创建了一个接口对象的方式，我们称之为动态代码。JDK提供的动态创建接口对象的方式，就叫做动态代理。

下面来实现一个最简单的动态代理

```java
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method);
                if (method.getName().equals("morning")) {
                    System.out.println("Good morning..." + args[0]);
                }
                return null;
            }
        };
        Hello hello = (Hello) Proxy.newProxyInstance(
        	Hello.class.getClassLoader(), // 传入classLoader
            new Class[] {Hello.class},  // 传入要实现的接口
            handler
        ); // 传入处理调用方法的InvocationHandler
        hello.morning("Bob");
    }
}
```

在运行期间创建一个代理的步骤如下：

1.定义一个InvocationHandler实例，他负责实现接口的方法调用

2.通过Proxy.newProxyInstance()创建interface实例，它需要3个参数：

​	①，使用ClassLoader，通常就是接口类的ClassLoader

​	②，需要实现的接口数组，至少需要传入一个接口

​	③，用来处理接口方法调用的InvocationHandler实例

3.将返回的Object强制转型为接口

动态代理实际上就是在运行期动态创建class字节码并加载的过程

## 小结：

Java标准库提供了动态代理的功能，允许在运行期动态创建一个接口的实例

动态代理是通过Proxy创建代理对象，然后将接口方法代理给InvocationHandler完成的