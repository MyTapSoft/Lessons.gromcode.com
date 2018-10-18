package JDBC.Lesson4.Task2.Model;

import java.util.Arrays;

public class Storage {
   private long storageId;
   private String[] formatsSupported;
   private String storageCountry;
   private int storageSize;

    public Storage(long storageId, String[] formatsSupported, String storageCountry, int storageSize) {
        this.storageId = storageId;
        this.formatsSupported = formatsSupported;
        this.storageCountry = storageCountry;
        this.storageSize = storageSize;
    }

    public long getId() {
        return storageId;
    }

    public String[] getFormatsSupported() {
        return formatsSupported;
    }

    public int getStorageSize() {
        return storageSize;
    }

    public String getStorageCountry() {
        return storageCountry;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "storageId=" + storageId +
                ", formatsSupported=" + Arrays.toString(formatsSupported) +
                ", storageCountry='" + storageCountry + '\'' +
                ", storageSize=" + storageSize +
                '}';
    }
}
