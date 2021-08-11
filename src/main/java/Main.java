import java.util.concurrent.CyclicBarrier;

public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Неверно задано начальное количество покупателей," +
                    " в параметры необходимо передать одно целое число");
            return;
        }

        if (args[0].contains(".")) {
            System.out.println("В параметры необходимо передать одно целое число");
            return;
        }


        int numberOfCustomers = Integer.parseInt(args[0]);
        int GOODS = 1000;
        Store store = new Store(GOODS);

        CyclicBarrier barrier = new CyclicBarrier(numberOfCustomers, store);

        for (int i = 1; i <= numberOfCustomers; i++) {
            Customer customer = new Customer(String.valueOf(i), store, barrier);
            new Thread(customer).start();
        }
    }

}
