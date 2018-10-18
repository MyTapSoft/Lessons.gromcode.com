package Core.Lesson15.Cast;

import java.util.Objects;

public class InternetProvider extends Provider {
    private int x, y;

    public InternetProvider(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InternetProvider that = (InternetProvider) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y);
    }
}
