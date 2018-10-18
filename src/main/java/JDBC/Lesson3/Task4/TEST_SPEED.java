package JDBC.Lesson3.Task4;

public class TEST_SPEED {
    private long ID;
    private String SOME_STRING;
    private int SOME_NUMBER;

    public TEST_SPEED(long ID, String SOME_STRING, int SOME_NUMBER) {
        this.ID = ID;
        this.SOME_STRING = SOME_STRING;
        this.SOME_NUMBER = SOME_NUMBER;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getSOME_STRING() {
        return SOME_STRING;
    }

    public void setSOME_STRING(String SOME_STRING) {
        this.SOME_STRING = SOME_STRING;
    }

    public int getSOME_NUMBER() {
        return SOME_NUMBER;
    }

    public void setSOME_NUMBER(int SOME_NUMBER) {
        this.SOME_NUMBER = SOME_NUMBER;
    }

    @Override
    public String toString() {
        return "TEST_SPEED{" +
                "ID=" + ID +
                ", SOME_STRING='" + SOME_STRING + '\'' +
                ", SOME_NUMBER=" + SOME_NUMBER +
                '}';
    }
}
