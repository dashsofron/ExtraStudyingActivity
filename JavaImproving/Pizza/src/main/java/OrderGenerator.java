import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * class for order's simulations
 */
public class OrderGenerator {
    BlockingQueue<Order> orders;
    private int numOfOrders = 1;
    int maxTaskNum;//define number
    int period;//how often make orders
    Thread thread; //thread only for simulation

    OrderGenerator(BlockingQueue<Order> orders, int period, int maxTaskNum) {
        this.orders = orders;
        this.period = period;
        this.maxTaskNum = maxTaskNum;
    }


    public void createOrder() {
        try {
            while (numOfOrders <= maxTaskNum && !thread.isInterrupted()) {
                orders.add(new Order(numOfOrders++));
                thread.wait(period);
            }
        } catch (InterruptedException e) {
            try {
                thread.join();
            } catch (InterruptedException ex) {
                System.out.println("interrupted thread was interrupted again");
                assert false;
            }
        }

    }


    /**
     * create and start thread
     */
    public void startWork() {
        thread = new Thread(this::createOrder);
        thread.start();
    }

    /**
     * function that stop work at the end of working day
     */
    public void stopWork() {
        thread.interrupt();
        try {
            thread.join();
        } catch (InterruptedException e) {
            assert false;
        }
    }
}
