package JDBC.Lesson4.Task2.Model;

import java.util.Random;

public class History {
    private long id;
    private OperationType operationType;
    private long timeProcessed;
    private Status status;

    public History(OperationType operationType, long timeProcessed, Status status) {
        id = new Random().nextLong();
        this.operationType = operationType;
        this.timeProcessed = timeProcessed;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public long getTimeProcessed() {
        return timeProcessed;
    }

    public Status getStatus() {
        return status;
    }

    public void setId(long id) {
        this.id = id;
    }
}
