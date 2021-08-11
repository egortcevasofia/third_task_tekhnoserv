import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

public class Main {
    static CyclicBarrier barrier;
    private static volatile int TOVAR = 1000;

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
        Store store = new Store();
        store.setGoods(TOVAR);

        barrier = new CyclicBarrier(numberOfCustomers);

        ArrayList<Customer> listOfCustomers = new ArrayList<>();

        for (int i = 1; i <= numberOfCustomers; i++) {
            Customer customer = new Customer(String.valueOf(i), store);
            listOfCustomers.add(customer);
            new Thread(customer).start();
        }


        Thread.currentThread().join();

        for (Customer listOfCustomer : listOfCustomers) {
            System.out.println("Покупатель совершил " + listOfCustomer.getQuantityOfPurchase()
                    + " покупок, на общую сумму " + listOfCustomer.getSumm());

        }
        System.out.println("Всего сделано покупок на сумму = " +
                listOfCustomers.stream().mapToInt(Customer::getSumm).sum());
        System.out.println("Остаток на складе = " + store.getGoods());


    }

}
