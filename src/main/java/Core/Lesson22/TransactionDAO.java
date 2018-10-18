package Core.Lesson22;

import Core.Lesson22.Exeptions.BadRequestException;
import Core.Lesson22.Exeptions.InternalServerException;

public class TransactionDAO {
    public static Transaction[] transactions = new Transaction[10];

    public Transaction save(Transaction transaction) throws Exception {
        if (transaction == null) {
            throw new BadRequestException("Transaction is null in method save");
        }
        Validator.checkCites(transaction);
        Validator.checkDaySimpleTransactionAmount(transaction);
        Validator.checkDayTransactionsAmount(transaction);
        Validator.checkDayTransactionsCount(transaction);
        Validator.checkDuplicate(transaction);
        for (int i = 0; i < transactions.length; i++) {
            if (transactions[i] == null) {
                transactions[i] = transaction;
                return transactions[i];
            }
        }
        throw new InternalServerException("No empty space for new transaction. Transactions array is full " + transactions.length + " from " + transactions.length + ". ID# " + transaction.getId());
    }

    public Transaction[] transactionList() {
        int count = 0;
        Transaction[] result;
        for (Transaction t :
                transactions) {
            if (t != null) {
                count++;
            }
        }
        result = new Transaction[count];
        count = 0;
        for (Transaction t :
                transactions) {
            if (t != null) {
                result[count] = t;
                count++;
            }
        }
        return result;
    }

    public Transaction[] transactionList(String city) {
        Transaction[] result;
        int count = 0;

        for (Transaction t : transactions) {
            if (t != null && t.getCity().equalsIgnoreCase(city)) {
                count++;//узнаем необходимую длину массива
            }
        }
        result = new Transaction[count];
        count = 0;
        for (Transaction t : transactions) {
            if (t != null && t.getCity().equalsIgnoreCase(city)) {
                result[count] = t; //загоняем данные в массив
            }
            count++;
        }
        return result;
    }

    public Transaction[] transactionList(int amount) {
        Transaction[] result;
        int count = 0;
        for (Transaction t : transactions) {
            if (t != null && t.getAmount() == amount) {
                count++;//узнаем необходимую длину массива
            }
        }
        result = new Transaction[count];
        count = 0;
        for (Transaction t : transactions) {
            if (t != null && t.getAmount() == amount) {
                result[count] = t;//загоняем данные в массив
                count++;
            }
        }
        return result;
    }


}

