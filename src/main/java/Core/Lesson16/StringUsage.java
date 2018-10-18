package Core.Lesson16;

public class StringUsage {
    public static void main(String[] args) {
        none(10);
    }


    private static void none(int x){
        System.out.println(x);
        int y = x;
        none(y);
    }
}
