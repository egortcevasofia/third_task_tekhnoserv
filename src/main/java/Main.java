import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

public class Main {

    //Инициализируем барьер на три потока и таском, который будет выполняться, когда
    //у барьера соберется три потока. После этого, они будут освобождены.

    static CyclicBarrier BARRIER;

    public static void main(String[] args) throws InterruptedException {

        int numberOfCustomers = Integer.parseInt(args[0]);
        Store store = new Store();
        store.setTovar(1000);

        BARRIER = new CyclicBarrier(numberOfCustomers, store);

        ArrayList<Customer> listOfCustomers = new ArrayList<>();

        for (int i = 1; i <= numberOfCustomers; i++) {
            System.out.println(i);
            Customer customer = new Customer("Customer_№" + i, store);
            listOfCustomers.add(customer);
            new Thread(customer).start();
            //Thread.sleep(400);
        }

Thread.sleep(3000);
        for (int i = 0; i < listOfCustomers.size(); i++) {
            System.out.println(listOfCustomers.get(i).getQuantityOfPurchase());
        }
    }

}
