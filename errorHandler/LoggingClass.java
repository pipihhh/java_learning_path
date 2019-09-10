import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoggingClass {
    public static void main(String[] args) {
        // 日志可以设置输出样式
        // 日志可以设置输出级别,只打印ERROR日志
        // 日志可以被重定向到文件
        // 日志可以按包名控制日志级别,只输出某些包打的日志
        // ....
        // java.util.logging
        Logger logger = Logger.getGlobal();  // 实例化一个日志对象
        logger.info("start process...");  // 打印info级别的日志
        logger.warning("memory is running out...");
        logger.fine("ignored.");
        logger.severe("process will be terminated...");
        // 上述代码会得到类似这样的输出:Mar 02, 2019 6:32:13 PM hello main....
        // 可见日志最大的好处是自动打印了时间,调用类,调用方法等很多有用的信息
        // 日志定义了7个日志级别,从严重到普通:SERVER,WARNING,INFO,CONFIG,FINE,FINER,FINEST
        // 默认级别是INFO,因此INFO级别以下的日志不会被打印出来,好处在于调整级别可以屏蔽很多无用的输出
        // java内置的Logging有局限性,Logging系统在JVM启动时读取配置文件并吃石化,一旦开始运行main就无法修改配置
        // 配置不方便,需要在JVM启动时传递参数-Djava.util.logging.config.file=<config-file-name>
        // 因此Java标准库的日志使用并不广泛

        // Commons Logging
        // 和Java。util。logging不同的是,此为Apache创建的日志模块
        // 它的特点是可以挂接不同的日志系统,并通过配置文件指定挂接的日志系统,默认情况下,Commons Logging自动搜索并使用Log4j
        // Log4j,另一个流行的日志系统,如果没有找到Log4j,再使用JDK Logging
        // Commons Logging只需要和两个类打交道,只有两步
        // 通过LogFactory获取Log类的实例,使用Log实例的方法打日志
        // apache是第三方包,需要先下载下来并放在与该类同级目录下,之后执行javac -cp commons-logging-1.2.jar LoggingClass.java
        // 在执行JVM也必须指定classpath,java -cp .:commons-logging-1.2.jar LoggingClass  注意classpath两部分,有一个是.
        // Commons Logging定义了6个日志级别,FATAL,ERROR,WARNING,INFO,DEBUG,TRACE,默认级别是INFO
        // 如果在静态方法中引用Log,通常直接定义一个静态类型的变量
        // 在实例方法中引用,通常定义一个实例变量,在调用LogFactory的时候使用getCLass()好处是,在被继承的类中的class是继承后的class而不是Person
        // 打印日志的方法除了标准的info(String msg)之外还有一个有用的重载方法info(String msg, Throwable)
        // 这使得记录异常更加的简单
        Log log = LogFactory.getLog(LoggingClass.class);
        log.info("start...");
        log.warn("end.");
        try {
            // do sth
        } catch (Exception e) {
            log.error("got exception!", e);
        }

        // Commons Logging可以作为日志接口来使用,真正的日志实现使用的是Log4j,Log4j是一个非常流行的日志框架,最新版本为2.x,为组件化设计
        // 依次穿过Appender->Filter->Layout再根据配置输出->Console或File或Socket或jdbc
        // 输出日志时,Log4j自动通过不同的Appender把同一条日志输出到不同的目的地
        // 输出过程中通过Filter来过滤哪些log需要被输出,哪些log不需要被输出,例如仅输出ERROR级别的日志
        // 通过Layout来格式化日志信息,如自动添加日期,时间,方法名称等信息
        // 虽然上述很复杂,但是在使用的时候无需关心上述问题,只需要做一个配置即可,XML配置
        // 在配置的时候,我们把一个log4j2.xml的文件放入classpath,就可以让Log4j读取配置文件并获取我们的配置来输出日志
        // 配置文件的例子:https://www.liaoxuefeng.com/wiki/1252599548343744/1264739436350112

        // 上述的Commons Logging和Log4j这一对好基友,一个负责充当日志的API,一个负责实现日志底层,搭配起来非常的方便
        // SLF4J和Logback,这两个东西看起来也像是日志,他们又是啥
        // 他们的功能也是类似的,有人对Commons Logging不满意,又搞了一个Logback
        // 那么Commons Loggin中我们要打印日志,有时候得这么写
        log.info("Set score " + "80" + " for Person " + "xxx" + " ok. ");
        // 字符串的拼接是一件非常麻烦的事情
        // 然后SLF4J的日志接口改为这样了
        int score = 99;
        log.info("Set score {} for Person {} ok.", score, "xxx");
        // SLF4J的日志接口传入的是一个带占位符的字符串,用后面的变量自动替换占位符,所以看起来更加的自然
        // 如何使用SLF4J呢,它的接口实际上和Commons Logging几乎一模一样
        final Logger logger2 = LoggerFactory.getLogger(LoggingClass.class);
        // 在使用上述实例化之前先要import org.slf4j.Logger;和import org.slf4j.LoggerFactory;
        // 和Commons的不同之处是,Log变成了Logger
        // 配置与下载相关,https://www.liaoxuefeng.com/wiki/1252599548343744/1264739155914176
        // 从目前的趋势来看,越来越多的日志使用了SLF4J和Logback
    }
}


class Person {
    protected final Log log = LogFactory.getLog(getClass());

    void foo() {
        log.info("foo");
    }
}


class Color {
    static final Log log = LogFactory.getLog(Color.class);

    static void foo() {
        log.info("foo");
    }
}
