package Core.Lesson22;

import Core.Lesson22.Exeptions.BadRequestException;
import Core.Lesson22.Exeptions.LimitExceeded;

import java.util.Calendar;
import java.util.Date;

public class Validator {
    public static void checkCites(Transaction transaction) throws BadRequestException {
        int count = 0;
        for (String cites : Utils.getCities()) {
            if (transaction.getCity().equalsIgnoreCase(cites)) {
                count++;
                break;
            }
        }
        if (count == 0) {
            throw new BadRequestException("City " + transaction.getCity() + " is't allowed. Transaction ID# " + transaction.getId());
        }
    }

    public static void checkDayTransactionsCount(Transaction transaction) throws BadRequestException {

        int dayTransactionsCount = allTransactionsByDay(transaction.getDateCreated()).length;

        if (dayTransactionsCount >= Utils.getLimitTransactionsPerDayCount()) {
            throw new LimitExceeded("The number of simple transaction per day <" + dayTransactionsCount + "> exceeded the allowed limit <" + Utils.getLimitTransactionsPerDayCount() + ">. ID #" + transaction.getId());
        }
    }

    public static void checkDaySimpleTransactionAmount(Transaction transaction) throws LimitExceeded {
        if (transaction.getAmount() > Utils.getLimitSimpleTransactionAmount()) {
            throw new LimitExceeded("The amount of single transaction per day <" + transaction.getAmount() + "> exceeded the allowed limit <" + Utils.getLimitSimpleTransactionAmount() + ">. ID #" + transaction.getId());
        }

    }

    public static void checkDayTransactionsAmount(Transaction transaction) throws Exception {

        int amount = transaction.getAmount();

        for (Transaction t : allTransactionsByDay(transaction.getDateCreated())) {
            if (t != null)
                amount += t.getAmount();
        }

        if (amount > Utils.getLimitTransactionsPerDayAmount()) {
            throw new LimitExceeded("The amount of all transactions per day <" + amount + "> exceeded the allowed limit <" + Utils.getLimitTransactionsPerDayAmount() + ">. ID #" + transaction.getId());
        }
    }

    public static void checkDuplicate(Transaction transaction) throws BadRequestException {
        for (Transaction t : TransactionDAO.transactions) {
            if (t != null && t.equals(transaction)) {
                throw new BadRequestException("Transaction already saved. ID #" + transaction.getId());
            }

        }
    }

    private static Transaction[] allTransactionsByDay(Date date) {

        Transaction[] result;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        int count = 0;

        for (Transaction t : TransactionDAO.transactions) {
            if (t != null) { // листаемся по массиву и сравниваем даты
                calendar.setTime(t.getDateCreated());

                int tDay = calendar.get(Calendar.DAY_OF_MONTH);
                int tMonth = calendar.get(Calendar.MONTH);
                int tYear = calendar.get(Calendar.YEAR);

                if (day == tDay && month == tMonth && year == tYear) {
                    count++; //узнаем какой длинны массив необходимо создать
                }
            }
        }

        result = new Transaction[count];
        count = 0;

        for (Transaction t : TransactionDAO.transactions) {
            if (t != null) {
                calendar.setTime(t.getDateCreated());

                int tDay = calendar.get(Calendar.DAY_OF_MONTH);
                int tMonth = calendar.get(Calendar.MONTH);
                int tYear = calendar.get(Calendar.YEAR);

                if (day == tDay && month == tMonth && year == tYear) {
                    result[count] = t; //заносим нужные данные в новый массив
                }
                count++;
            }
        }
        return result;
    }
}
