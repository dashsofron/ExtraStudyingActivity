import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * class describe company storage. Size - max number of orders there, queue-orders
 */
public class Storage {
    BlockingQueue<Order> pizzas;

    Storage(int size) {
        pizzas = new ArrayBlockingQueue<>(size);
    }

    /**
     * function for putting orders to the storage
     * @param pizza - the order that we put
     * @throws InterruptedException if it is full А КАК БЛОКИН КУ РАБОТАЕТ
     */
    public void addPizza(Order pizza) throws InterruptedException {
        pizzas.put(pizza);
    }

    /**
     * function for extract pizza from storage
     * @throws InterruptedException if it is free ----
     */
    public Order takePizza() throws InterruptedException {
        return pizzas.take();
    }

}
