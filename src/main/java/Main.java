import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

public class Main {
    static CyclicBarrier barrier;
    private static volatile int GOODS = 1000;

    public static void main(String[] args) throws InterruptedException {
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
        Store store = new Store(GOODS);

        barrier = new CyclicBarrier(numberOfCustomers, store);

        ArrayList<Customer> listOfCustomers = new ArrayList<>();

        for (int i = 1; i <= numberOfCustomers; i++) {
            Customer customer = new Customer(String.valueOf(i), store, barrier);
            listOfCustomers.add(customer);
            new Thread(customer).start();
        }


//        Thread.currentThread().join();
        Thread.sleep(3000);
        for (Customer listOfCustomer : listOfCustomers) {
            System.out.println("Покупатель совершил " + listOfCustomer.getQuantityOfPurchase()
                    + " покупок, на общую сумму " + listOfCustomer.getSumm());

        }
        System.out.println("Всего сделано покупок на сумму = " +
                listOfCustomers.stream().mapToInt(Customer::getSumm).sum());
        System.out.println("Остаток на складе = " + store.getGoods());
    }

}
