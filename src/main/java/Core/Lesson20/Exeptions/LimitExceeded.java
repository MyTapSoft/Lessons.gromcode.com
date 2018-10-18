package Core.Lesson20.Exeptions;

public class LimitExceeded extends BadRequestException {
    public LimitExceeded(String s) {
        super(s);
    }
}
