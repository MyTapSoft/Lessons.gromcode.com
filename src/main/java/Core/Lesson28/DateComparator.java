package Core.Lesson28;

import java.util.Comparator;

public class DateComparator implements Comparator<Capability> {

    @Override
    public int compare(Capability o1, Capability o2) {
            return Long.compare(o2.getDateCreated().getTime(), o1.getDateCreated().getTime());
        }
}
