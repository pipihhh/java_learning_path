public class Module {
    public static void main(String[] args) {
        // 由于jar只是class文件的一个容器,它并不关心容器内部的class文件是否依赖了其他的jar内的class
        // 所以在java 8 以前人们运行项目通常要指定一大堆的jar java -cp a.jar:b.jar....
        // java 9开始引入了module的概念,模块,每一个模块都是一个.jmod后缀的文件
        // 编写模块,模块就像一个java工程一样,目录结构为
        // 最外层目录:bin,bulid.sh,src其中src依旧为源码文件,bin为编译后文件
        // 在src的根目录中有一个module-info.java的文件,内容如下面的module代码
        // 其中hello.world为模块名称,requires后为依赖,如果其他代码想要使用此模块的类,需要使用exports导出
        // 编译的语句为javac -d bin src/module-info.java src/com/itranswarp/sample/*.java
        // 下一步,将bin目录打包为一个jar文件,
        // jar --create --file hello.jar --main-class com.itranswarp.sample.Main -C bin .
        // 此时的jar文件和之前的没有什么不同,可以用java -jar hello.jar来运行它
        // 之后使用jmod命令创建模块,jmod create --class-path hello.jar hello.jmod
        // 要运行一个模块要使用java --module-path hello.jar --module hello.world
        // 制作的jmod文件有何用,可以用来打包jre,以前的jar是一个巨大的jre,现在核心库中的jre已经变为了一个一个的模块
        // 只需要将要用到的jmod文件打包为一个jre即可,使用jlink命令
        // jlink --module-path hello.jmod --add-modules java.base,java.xml,hello.world --output jre/
        // 之后jre/bin/java --module hello.world运行jmod
    }
}


// module hello.world {
//     requires java.base;  可不写,任何模块都会引入它
//     requires java.xml;
//     exports com.itranswarp.sample;
// }
