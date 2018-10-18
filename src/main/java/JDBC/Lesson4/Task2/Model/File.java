package JDBC.Lesson4.Task2.Model;

public class File {
    private long id;
    private String name;
    private String format;
    private int fileSize;
    private Storage storage;

    public File(long id, String name, String format, int fileSize, Storage storage) {
        try {
            if (name.length() > 10) throw new Exception("File name is to long. You have to many characters: " + name.length());
        } catch (Exception e) {
            return;
        }
        this.id = id;
        this.name = name;
        this.format = format;
        this.fileSize = fileSize;
        this.storage = storage;
    }

    public long getId() {
        return id;
    }

    public String getFormat() {
        return format;
    }

    public int getFileSize() {
        return fileSize;
    }

    public Storage getStorage() {
        return storage;
    }

    public String getName() {
        return name;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", format='" + format + '\'' +
                ", fileSize=" + fileSize +
                ", storage=" + storage +
                '}';
    }
}
