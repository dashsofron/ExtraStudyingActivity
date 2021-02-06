import com.google.gson.Gson;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * this class describe bakers in pizza production
 * every baker work independently -different threads
 * time of cooking depends of baker's experience
 */
public class Baker extends Worker {

    static int pizzaTime;//default time of cooking
    int experience;//more experience - less time
    Order currentOrder;//order that baker is doing
    String start = "start cook ";//for log
    String end = "end cook ";//for log
    BlockingQueue<Order> orders;//queue of orders where baker can get order if he is free (and it is not empty)
    Storage storage;//has queue with orders that are ready for delivery

    Baker(int experience, BlockingQueue<Order> orders, Storage storage) {
        this.orders = orders;
        this.experience = experience;
        this.storage = storage;
    }

    /**
     * function for baker's work. Every baker take order, making it for some time and send to the storage.
     * Also it gets some information about order status
     */
    public void work() {
        while (!thread.isInterrupted()) {// в чем разница интерраптед и неиз
            boolean sendPizza = makePizza();//make pizza and check if it is not end of work.
            sendToTheStorage();
            if (sendPizza) {//If it is -send pizza to the storage and stop working.
                return;
            }

        }
    }

    boolean makePizza() {
        try {
            //default work
            currentOrder = orders.take();
            showProgress(start + currentOrder.getOrderNum());
            wait(pizzaTime / experience);
            showProgress(end + currentOrder.getOrderNum());

        } catch (InterruptedException ex) {// end of work time - stop working. But he maid pizza..
            return true;
        }
        return false;
    }

    void sendToTheStorage() {
        try {
            storage.addPizza(currentOrder);
        } catch (InterruptedException e) {//maybe storage is full, wait and try again
            try {
                storage.addPizza(currentOrder);
            } catch (InterruptedException ex) {// if can't again, error. But maybe it is full again...
                System.out.println("interrupted thread was interrupted again");
                assert false;
            }

        }
    }
}
