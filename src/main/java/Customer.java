import java.util.Map;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

//Стороны, которые будут достигать барьера
public class Customer implements Runnable {
    private String name;
    CyclicBarrier cyclicBarrier;
    private int quantityOfPurchase;
    Store store;

    public Customer(String  name) {
        this.name = name;
    }

    public Customer(String name, Store store) {
        this.name = name;
        this.store = store;
    }

    @Override
    public void run() {
        try {
            while(store.getTovar() > 0) {
                System.out.printf("Покупатель №%s подошел к складу.\n", name);
                //Для указания потоку о том что он достиг барьера, нужно вызвать метод await()
                //После этого данный поток блокируется, и ждет пока остальные стороны достигнут барьера

                Main.BARRIER.await();
                quantityOfPurchase++;

            }

        } catch (Exception e) {
        }
    }

    public int getQuantityOfPurchase() {
        return quantityOfPurchase;
    }

    public void setQuantityOfPurchase(int quantityOfPurchase) {
        this.quantityOfPurchase = quantityOfPurchase;
    }
}

