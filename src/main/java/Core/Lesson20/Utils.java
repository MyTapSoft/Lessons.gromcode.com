package Core.Lesson20;

public class Utils {
    private static int limitTransactionsPerDayCount = 2; // количество транзакций за день
    private static int limitTransactionsPerDayAmount = 100; //сумма  за день
    private static int limitSimpleTransactionAmount = 80; //сумма за раз
    private static String[] cities = {"l", "x", "v"};

    public static int getLimitTransactionsPerDayCount() {
        return limitTransactionsPerDayCount;
    }

    public static int getLimitTransactionsPerDayAmount() {
        return limitTransactionsPerDayAmount;
    }

    public static int getLimitSimpleTransactionAmount() {
        return limitSimpleTransactionAmount;
    }

    public static String[] getCities() {
        return cities;
    }
}
