package Core.Lesson25;

public class GeneralDAO <T> {
    @SuppressWarnings("unchecked")
    private T[] array = (T[]) new Object[5];

    public T save(T t) throws Exception {
        if (t == null) {
            throw new NullPointerException("Object is null");
        }
        int index = 0;
        for (T arrayIndex : array) {
            if (arrayIndex == null) {
                array[index] = t;
                return array[index];
            }
            index++;
        }
        throw new Exception("Something wrong");
    }

    public T[] getAll() {
        return array;
    }
}
