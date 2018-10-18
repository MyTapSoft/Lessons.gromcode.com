package Core.Lesson28;

import java.util.Date;

public abstract class DataEntity {
    public abstract String getChannelName();
    public abstract Date getDateCreated();
    public abstract String getFingerprint();
    public abstract <V> int compareTo(V anotherString);

}
