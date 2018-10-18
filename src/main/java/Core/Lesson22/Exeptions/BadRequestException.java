package Core.Lesson22.Exeptions;

public class BadRequestException extends Exception {
    public BadRequestException(String s) {
        super(s);
    }
}
