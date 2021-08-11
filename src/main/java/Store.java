
public class Store implements Runnable{
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

//Метод который выводит на консоль остаток товара после каждого барьера
    @Override
    public void run() {
        System.out.println("Остаток на складе = " + goods);
    }
}
