
public class Store implements Runnable {
    private volatile int goods;

    Store(int goods) {
        this.goods = goods;
    }

    synchronized int getRealQuantityAndByu(int randomQuantity) {
        int realQuantity = randomQuantity;
        if (goods <= randomQuantity) {
            realQuantity = goods;
            goods = 0;
        }else{
            goods = goods - randomQuantity;
        }
        return realQuantity;
    }

    int getGoods() {
        return goods;
    }

    @Override
    public void run() {
        System.out.println("Остаток на складе = " + goods);
    }
}
