import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

public class Main {
    static CyclicBarrier BARRIER;

    public static void main(String[] args) throws InterruptedException {

        int numberOfCustomers = Integer.parseInt(args[0]);
        Store store = new Store();
        store.setGoods(1000);

        BARRIER = new CyclicBarrier(numberOfCustomers, store);

        ArrayList<Customer> listOfCustomers = new ArrayList<>();

        for (int i = 1; i <= numberOfCustomers; i++) {
            System.out.println(i);
            Customer customer = new Customer(String.valueOf(i), store);
            listOfCustomers.add(customer);
        }

        store.setListOfCustomers(listOfCustomers);

        for (int i = 0; i < listOfCustomers.size(); i++) {
            new Thread(listOfCustomers.get(i)).start();
        }

        Thread.sleep(3000);
        for (int i = 0; i < listOfCustomers.size(); i++) {
            System.out.println("Покупатель совершил " +  listOfCustomers.get(i).getQuantityOfPurchase()
                    + " покупок, на общую сумму " + listOfCustomers.get(i).getQuantityOfPurchase());
        }
    }

}
