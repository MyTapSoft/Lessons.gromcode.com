package JDBC.Lesson6.Task2.Exceptions;

public class BadRequestException extends Exception {
    public BadRequestException(String message) {
        super(message);
    }
}
