import java.util.ArrayList;
import java.util.Random;
//Таск, который будет выполняться при достижении сторонами барьера

public class Store implements Runnable {
    private int goods;
    private ArrayList<Customer> listOfCustomers;
    private Random random = new Random();
    private ArrayList<Customer> finalListOfCustomers;



    @Override
    public void run() {
        try {
            Thread.sleep(50);
            for (int i = 0; i <= listOfCustomers.size(); i++) {

                int randomQuantity = random.nextInt(9) + 1;
                if (goods <= randomQuantity) {
                    randomQuantity = goods;
                }
//
//                Customer customer = listOfCustomers.get(i);
//                customer.quantityOfPurchase++;
//                customer.summ +=randomQuantity;
//
                goods = goods - randomQuantity;
            }

            System.out.println("всего осталось товара" + goods);
        } catch (InterruptedException e) {
        }
    }

    public int getGoods() {
        return goods;
    }

    public void setGoods(int goods) {
        this.goods = goods;
    }

    public ArrayList<Customer> getListOfCustomers() {
        return listOfCustomers;
    }

    public void setListOfCustomers(ArrayList<Customer> listOfCustomers) {
        this.listOfCustomers = listOfCustomers;
    }

    public ArrayList<Customer> getFinalListOfCustomers() {
        return finalListOfCustomers;
    }

    public void setFinalListOfCustomers(ArrayList<Customer> finalListOfCustomers) {
        this.finalListOfCustomers = finalListOfCustomers;
    }
}
