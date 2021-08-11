import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Customer implements Runnable {
    private String name;
    private int quantityOfPurchase;
    private int summ;
    private Store store;
    private Random random = new Random();
    private CyclicBarrier barrier;


    Customer(String name, Store store, CyclicBarrier barrier) {
        this.name = name;
        this.store = store;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            while (store.getGoods() > 0) {
                System.out.printf("Покупатель №%s подошел к складу.\n", name);
                barrier.await();
                byu();
                barrier.await();
            }
            printToConsole();
        } catch (BrokenBarrierException | InterruptedException e) {
            System.out.println("Произошла ошибка при синхронизации потоков");
        }
    }


    private void byu() {
        int randomQuantity = random.nextInt(10) + 1;
        int realQuantity = store.getRealQuantityAndByu(randomQuantity);

        if (realQuantity > 0) {
            quantityOfPurchase++;
            summ += realQuantity;
        }
    }


    private void printToConsole(){
        System.out.println("Покупатель_№" + name + " совершил " + quantityOfPurchase
                + " покупок, на общую сумму " + summ);
    }

}

