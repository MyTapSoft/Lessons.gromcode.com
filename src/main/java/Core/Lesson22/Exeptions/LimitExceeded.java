package Core.Lesson22.Exeptions;

public class LimitExceeded extends BadRequestException {
    public LimitExceeded(String s) {
        super(s);
    }
}
