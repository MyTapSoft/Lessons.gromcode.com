package Core.Lesson20.Exeptions;

public class BadRequestException extends Exception {
    public BadRequestException(String s) {
        super(s);
    }
}
