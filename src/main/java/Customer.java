import java.util.concurrent.CyclicBarrier;

//Стороны, которые будут достигать барьера
public class Customer implements Runnable {
    public String name;
    CyclicBarrier cyclicBarrier;
    public int quantityOfPurchase;
    public int summ;
    Store store;

    public Customer(String name) {
        this.name = name;
    }

    public Customer(String name, Store store) {
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

                Main.BARRIER.await();
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

    public int getSumm() {
        return summ;
    }

    public void setSumm(int summ) {
        this.summ = summ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

