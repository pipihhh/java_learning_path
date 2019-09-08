public class CustomException {
    public static void main(String[] args) throws UserNotFoundException{
        // 在一个大型项目中,我们可以自定义一些异常
        // 但是保持一个合理的异常继承体系是非常重要的,一般都要有一个BaseException作为根异常
        // BaseException一般从RuntimeException派生,
        // 自定义的异常应该提供多个构造方法
    }
}


final class BaseException extends RuntimeException {
    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }
}
