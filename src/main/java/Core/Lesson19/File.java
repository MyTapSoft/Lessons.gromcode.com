package Core.Lesson19;

public class File {
    private long id;
    private String name;
    private String format;
    private long size;

    public File(long id, String name, String format, long size) throws Exception{
        if (nameSize(name)) {
            this.id = id;
            this.name = name;
            this.format = format;
            this.size = size;
        }
        else {
            throw new Exception("File name is to long...");
        }
    }

    public long getId() {
        return id;
    }

    public String getFormat() {
        return format;
    }

    public long getSize() {
        return size;
    }

    private boolean nameSize(String name){
        return name.length() <= 10;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", format='" + format + '\'' +
                ", size=" + size +
                '}';
    }
}
