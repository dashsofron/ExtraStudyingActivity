import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * describe company work
 */
public class PizzaOfice {
    Baker[] bakers; //company bakers
    DeliveryMan[] deliveryMen;//company delivery men
    Storage storage;//company storage
    BlockingQueue<Order> orders;//company orders
    OrderGenerator generator;//orders simulator
    int workTime;//default time of company work

    /**
     * @param conf - JSON configuration with company parameters
     */
    PizzaOfice(PizzaConfigurator conf) {
        int workTime = conf.daytime;
        bakers = new Baker[conf.getBakersNum()];
        deliveryMen = new DeliveryMan[conf.getDeliversNum()];
        storage = new Storage(conf.getStorage());
        orders = new LinkedBlockingQueue<Order>();
        for (int i = 0; i < bakers.length; i++)
            bakers[i] = new Baker(conf.bakers.get(i).experience, orders, storage);
        for (int i = 0; i < deliveryMen.length; i++)
            deliveryMen[i] = new DeliveryMan(conf.delivers.get(i).backpacksize, storage);
        generator = new OrderGenerator(orders, 10, conf.maxpizzanum);
    }

    /**
     * show information about orders
     *
     * @param message - status
     */
    public void showProgress(String message) {
        System.out.println(message);
    }

    /**
     * start and company work - start work for each worker and simulation work. Stop it when time for work has ended
     */
    public void startWork() {
        showProgress("WorkDay was started \n");
        generator.startWork();
        for (Baker baker : bakers) baker.startWork();
        for (DeliveryMan deliveryMAN : deliveryMen) deliveryMAN.startWork();
        try {
            wait(workTime);
        } catch (InterruptedException ignore) {
        }
        showProgress("WorkDay was ended \n");
        generator.stopWork();
        for (Baker baker : bakers) baker.stopWork();
        for (DeliveryMan deliveryMAN : deliveryMen) deliveryMAN.stopWork();
    }


    public static void main(String[] args) {
        PizzaConfigurator conf = null;
        try {
            FileReader reader = new FileReader("info.json");
            Gson gson = new Gson();
            conf = gson.fromJson(reader, PizzaConfigurator.class);
        } catch (FileNotFoundException e) {
            System.out.println("create info file");
            System.exit(1);
        }
        PizzaOfice office = new PizzaOfice(conf);
        office.startWork();

    }
}

