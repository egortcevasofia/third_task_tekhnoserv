
public class Store implements Runnable {
    private volatile int goods;

    Store(int goods) {
        this.goods = goods;
    }


    public synchronized void byu(int randomQuantity) {
        if (goods <= randomQuantity) {
            randomQuantity = goods;
        }
        goods = goods - randomQuantity;
    }


    public synchronized int getGoods() {
        return goods;
    }


    @Override
    public void run() {
        System.out.println("Остаток на складе = " + goods);
    }
}
