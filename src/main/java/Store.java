import java.util.Random;

public class Store implements Runnable{
   private int tovar;
    Random random = new Random();


    @Override
    public void run() {
        try {
            Thread.sleep(50);
            for (int i = 1; i <= 5; i++) {

                int randomQuantity = random.nextInt(9) + 1;
                if (tovar <= randomQuantity){
                    randomQuantity = tovar;
                }

                tovar = tovar - randomQuantity;
            }


            System.out.println("всего осталось товара" + tovar);
        } catch (InterruptedException e) {
        }
    }

    public int getTovar() {
        return tovar;
    }

    public void setTovar(int tovar) {
        this.tovar = tovar;
    }
}
