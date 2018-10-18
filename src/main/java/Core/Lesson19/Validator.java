package Core.Lesson19;

public class Validator {
    public boolean validateAll(Storage storage, File file) throws Exception {
        return idValidator(storage, file) && extensionValidator(storage, file) && maxSizeValidator(storage, file);
    }

    public boolean validateAll(Storage storageFrom, Storage storageTo) throws Exception {
        return maxSizeValidator(storageFrom, storageTo) && extensionValidator(storageFrom, storageTo) && idValidator(storageFrom, storageTo);
    }

    private boolean extensionValidator(Storage storage, File file) {
        for (String s : storage.getFormatsSupported()) {
            if (s.equalsIgnoreCase(file.getFormat())) {
                return true;
            }
        }
        return false;
    }

    private boolean extensionValidator(Storage storageFrom, Storage storageTo) {
        int count = 0;
        for (String from : storageFrom.getFormatsSupported()) {
            for (String to : storageTo.getFormatsSupported()) {
                if (from.equalsIgnoreCase(to)) {
                    count++;
                    break;
                }
            }
        }
        return count == storageFrom.getFormatsSupported().length;
    }

    private boolean idValidator(Storage storage, File file) {
        int count = 0;
        for (File f : storage.getFiles()) {
            if (storage.getFiles()[count] != null && file.getId() == f.getId()) {
                return false;
            }
            count++;
        }
        return true;
    }

    private boolean idValidator(Storage storageFrom, Storage storageTo) {
        for (File from :
                storageFrom.getFiles()) {
            for (File to :
                    storageTo.getFiles()) {
                if (from != null && to != null && from.getId() == to.getId()) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean maxSizeValidator(Storage storageFrom, Storage storageTo) {
        int sumFrom = 0;
        int sumTo = 0;
        for (File from :
                storageFrom.getFiles()) {
            if (from != null) {
                sumFrom += from.getSize();
            }
        }
        for (File to :
                storageTo.getFiles()) {
            if (to != null) {
                sumTo += to.getSize();
            }

        }

        return sumTo + sumFrom <= storageTo.getStorageSize();
    }

    private boolean maxSizeValidator(Storage storage, File file) {
        int sum = 0;
        for (File files :
                storage.getFiles()) {
            if (files != null) {
                sum += files.getSize();
            }
        }
        return storage.getStorageSize() >= sum + file.getSize();
    }
}
