package Core.Lesson22;

import Core.Lesson22.Exeptions.BadRequestException;

public class Controller {
    private TransactionDAO transactionDAO = new TransactionDAO();

    public Transaction save(Transaction transaction) throws Exception {
        return transactionDAO.save(transaction);
    }

    public Transaction[] transactionList() {
        return transactionDAO.transactionList();
    }

    public Transaction[] transactionList(String city) throws BadRequestException {
        return transactionDAO.transactionList(city);
    }

    public Transaction[] transactionList(int amount) throws BadRequestException {
        return transactionDAO.transactionList(amount);

    }


}
