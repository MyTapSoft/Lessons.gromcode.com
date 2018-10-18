package Core.Lesson28;

import java.util.Comparator;

public class IsActiveComparator implements Comparator<Capability> {
    @Override
    public int compare(Capability o1, Capability o2) {
        return Boolean.compare(o1.isActive(),o2.isActive());}
}
