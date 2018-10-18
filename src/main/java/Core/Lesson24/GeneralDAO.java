package Core.Lesson24;

public class GeneralDAO<T> {
    @SuppressWarnings("unchecked")
    private T[] array = (T[]) new Object[5];

    public T save(T t) {
        int index = 0;
        for (T tp : array) {
            if (tp == null) {
                array[index] = t;
                return array[index];
            }
            index++;
        }
        return null;
    }

    public T[] getAll(){
        return array;
    }
}
