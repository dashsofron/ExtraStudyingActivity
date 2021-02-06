import java.util.LinkedList;
import java.util.List;

/**
 * this class describe delivery men in pizza production
 * time of cooking depends of baker's experience
 */
public class DeliveryMan extends Worker {
    static int DeliveryTime;//default time for delivery
    List<Order> orders;//current orders for delivery
    Storage storage;//where delivery man can get order
    String preStart = "get order";
    String start = "delivery :";
    String end = "end delivery :";
    int sizeOfBackBack;//max num of orders for this delivery man

    DeliveryMan(int size, Storage storage) {
        orders = new LinkedList<>();
        sizeOfBackBack = size;
        this.storage = storage;
    }


    @Override
    public void work() {
        while (!thread.isInterrupted()) {
            try {
                getOrders();//get orders
            } catch (InterruptedException ignore) {
            }
            if (!orders.isEmpty()) { //if get some orders - deliver it
                if (orders.size() > 1) {//give information about orders
                    showProgress(preStart + "s :");
                } else showProgress(preStart + " :");
                deliverLastOrders();
            }
        }
    }

    void getOrders() throws InterruptedException {
        for (int i = 0; i < sizeOfBackBack; i++)
            orders.add(storage.takePizza());
    }

    void deliverLastOrders() {
        for (int i = 0; i < orders.size(); i++) {
            showProgress(String.valueOf(orders.get(i).getOrderNum()));
            for (i = 0; i < orders.size(); i++) {
                showProgress(start + String.valueOf(orders.get(i).getOrderNum()));
                try {
                    wait(DeliveryTime);
                } catch (InterruptedException ex) {
                    if (!orders.isEmpty()) {
                        for (; i < orders.size(); i++) {
                            showProgress("some delay");
                            showProgress(start + String.valueOf(orders.get(i).getOrderNum()));

                            try {
                                wait(DeliveryTime);
                                showProgress(end + String.valueOf(orders.get(i).getOrderNum()));
                            } catch (InterruptedException exception) {
                                System.out.println("interrupted thread was interrupted again");
                                assert false;
                            }
                        }
                    }
                }
                showProgress(end + String.valueOf(orders.get(i).getOrderNum()));
            }
            orders.clear();
        }
    }
}

