package Core.Lesson25.Rewriting;

public class GeneralDAO {
    private void print(Order order) {
        System.out.println("Order is: " + order.toString());
    }

    public <T extends IdEntity> void validate(T t) throws Exception {
        if (t.getId() <= 0) {
            throw new Exception("id cant be negative");
        }

    }

    public <K> void validate(K k) {
        if (k.equals(100)) {
            System.out.println("true");
        } else System.out.println("false");
    }
}
