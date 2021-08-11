
public class Store {
    private volatile int goods;

    public void byu(int random) {

        goods = goods - random;
    }


    public int getGoods() {
        return goods;
    }

    public void setGoods(int goods) {
        this.goods = goods;
    }
}
