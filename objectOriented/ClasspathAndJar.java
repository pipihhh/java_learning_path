public class ClasspathAndJar {
    public static void main(String[] args) {
        // 什么是classpath,就是一个配置,JVM用到的一个环境变量,用来指示JVM如何搜索class
        // JVM想要知道如果想要加载一个abc.def.Hello的类需要去哪找它的class文件
        // classpath就是一组目录的集合,在windows系统中用;分割,带空格的目录用""括起来
        // 可能长这样:C:\work\project1\bin;C:\shared;"D:\My Documents\project1\bin"
        // 在Linux上面,用:分割/usr/shared:/usr/local/bin:/home/liaoxuefeng/bin
        // 设置classpath的方式有2种,在环境变量种设置,和在JVM启动时设置,推荐后者
        // 其实就是在启动JVM时指定参数:
        // java -classpath .;C:\work\project1\bin;C:\shared abc.xyz.Hello
        // java -cp .;C:\work\project1\bin;C:\shared abc.xyz.Hello
        // 上述都可以,上述的.代表当前目录,如果没有classpath也没有-cp那么默认classpath为当前目录
        // 无需将java核心库的目录指定为classpath,它默认就回去那里面找的


        // jar包的出现时为了便利,如果有很多class文件散落在各层目录中,肯定不便管理
        // 此时将class文件打包成jar包变成一个文件就方便多了
        // jar包把package组织的目录层级,各目录下所有的文件,jar包相当于一个zip压缩文件
        // 如果要执行一个jar包的class,就可以把jar包放入classpath中java -cp ./hello.jar
        // jar包本质上是一个zip文件,所以只需要压缩为zip后修改后缀名为jar即可
        // jar包一定要严格按照package来压缩,如果没有bin目录则不可以将bin目录打包成jar
        // jar包内可以存在一个/META-INF/MANIFEST.MF纯文本文件,指定Main-Class和其他信息
        // jar包还可以包含其他jar包,在MANIFEST.MF文件里配置classpath即可
        // 大型项目中不可能手动编写此文件,可以用开源构建工具Maven
    }
}