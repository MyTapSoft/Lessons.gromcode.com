package Core.Lesson19;

public class Controller {

    public void put(Storage storage, File file) throws Exception {
        Validator chech = new Validator();
        if (!chech.validateAll(storage, file)) {
            throw new Exception("Can't put file id #" + file.getId() + " to storage id #" + storage.getId());
        }
        int count = 0;
        for (File f : storage.getFiles()) {
            if (f == null) {
                storage.getFiles()[count] = file;
                break;
            }
            count++;
        }
    }

    public void delete(Storage storage, File file) throws Exception {
        if (file == null || storage == null) {
            throw new Exception("Can't delete file id #" + file.getId() + " to storage id #" + storage.getId());
        }
        int count = 0;
        for (File f : storage.getFiles()) {
            if (f.getId() == file.getId()) {
                storage.getFiles()[count] = null;
                break;
            }
            count++;
        }
    }

    public void transferAll(Storage storageFrom, Storage storageTo) throws Exception {
        Validator chech = new Validator();
        if (chech.validateAll(storageFrom, storageTo)) {
            int count = 0;
            for (File from : storageFrom.getFiles()) {
                if (from != null) {
                    for (File to : storageTo.getFiles()) {
                        if (to == null) {
                            storageTo.getFiles()[count] = from;
                            break;
                        }
                        count++;
                    }
                }
                count = 0;
            }
        }
    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {
        if (storageFrom == null || storageTo == null) {
            throw new Exception("Can't transfer file id #" + id + " to storage id #" + storageTo.getId());
        }
        int count = 0;
        for (File from : storageFrom.getFiles()) {
            if (from.getId() == id) {
                for (File to : storageTo.getFiles()) {
                    if (to == null) {
                        storageTo.getFiles()[count] = from;
                        break;
                    }
                    count++;
                }
            }
        }
    }

}
