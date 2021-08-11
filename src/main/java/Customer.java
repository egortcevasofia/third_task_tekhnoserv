import java.util.Random;
import java.util.concurrent.BrokenBarrierException;

public class Customer implements Runnable {
    private String name;
    private int quantityOfPurchase;
    private int summ;
    private Store store;
    private Random random = new Random();


    Customer(String name, Store store) {
        this.name = name;
        this.store = store;
    }

    @Override
    public void run() {
        try {
            while (store.getGoods() > 0) {
                System.out.printf("Покупатель №%s подошел к складу.\n", name);
                //Для указания потоку о том что он достиг барьера, нужно вызвать метод await()
                //После этого данный поток блокируется, и ждет пока остальные стороны достигнут барьера
                Main.barrier.await();
                System.out.println("всего осталось товара" + store.getGoods());
                int randomQuantity = random.nextInt(9) + 1;
                if (store.getGoods() <= randomQuantity) {
                    randomQuantity = store.getGoods();
                    quantityOfPurchase++;
                    summ+=randomQuantity;
                    store.byu(randomQuantity);
                    break;
                }

                quantityOfPurchase++;
                summ+=randomQuantity;
                store.byu(randomQuantity);

            }
            Thread.currentThread().interrupt();
        } catch (BrokenBarrierException | InterruptedException e) {
            System.out.println("Произошла ошибка при синхронизации потоков");
        }
    }

    public int getQuantityOfPurchase() {
        return quantityOfPurchase;
    }


    public int getSumm() {
        return summ;
    }

}

