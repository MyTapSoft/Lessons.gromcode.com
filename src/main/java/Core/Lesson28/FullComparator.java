package Core.Lesson28;

import java.util.Comparator;

public class FullComparator implements Comparator<Capability> {
    @Override
    public int compare(Capability o1, Capability o2) {
        return 0;// compareValidator(o1,o2);
    }


    private <T extends DataEntity> int compareValidator(T o1, T o2){
        final boolean f1, f2;
        return (f1 = o1 == null) ^ (f2 = o2 == null) ? f1 ? -1 : 1 : f1 && f2 ? 0 : o1.compareTo(o2);

    }


//    private int chanelNameCompare(Capability o1, Capability o2) {
//        final boolean f1, f2;
//        return (f1 = o1.getChannelName() == null) ^ (f2 = o2.getChannelName() == null) ? f1 ? -1 : 1 : f1 && f2 ? 0 : o1.getChannelName().compareTo(o2.getChannelName());
//    }
//
//    private int fingerPrintCompare(Capability o1, Capability o2) {
//        final boolean f1, f2;
//        return (f1 = o1.getFingerprint() == null) ^ (f2 = o2.getFingerprint() == null) ?
//                f1 ? -1 : 1 : f1 && f2 ? 0 : o1.getFingerprint().compareTo(o2.getFingerprint());
//
//    }
//
//    private int dateCompare(Capability o1, Capability o2){
//        return Long.compare(o1.getDateCreated().getTime(), o2.getDateCreated().getTime());
//    }
}
