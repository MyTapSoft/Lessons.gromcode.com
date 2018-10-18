package Core.Lesson22;

import java.util.Arrays;
import java.util.Date;

public class Demo {
    public static void main(String[] args) throws Exception {
        Transaction transaction = new Transaction(1, "l", 20, "Wire transfer", TransactionType.INCOME, new Date());
        Transaction transaction1 = new Transaction(0, "x", 20, "Wire transfer", TransactionType.INCOME, new Date());
        Transaction transaction2 = new Transaction(3, "x", 20, "Wire transfer", TransactionType.INCOME, new Date());
        Transaction transaction3 = new Transaction(4, "l", 1, "Wire transfer", TransactionType.INCOME, new Date());
        Transaction transaction4 = new Transaction(5, "l", 1, "Wire transfer", TransactionType.INCOME, new Date());
        Transaction transaction5 = new Transaction(6, "l", 20, "Wire transfer", TransactionType.INCOME, new Date());

        Controller controller = new Controller();

        controller.save(transaction);
        controller.save(transaction4);
        controller.save(transaction2);
        controller.save(transaction3);
        controller.save(transaction1);


        System.out.println(Arrays.toString(controller.transactionList()));


    }
}
