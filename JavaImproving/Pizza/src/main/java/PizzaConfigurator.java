import java.util.List;

public class PizzaConfigurator {
    int storage;
    int maxpizzanum;
    int daytime;
    List<BakerConf> bakers;
    List<DeliveryConf> delivers;
    int getBakersNum(){return bakers.size();}
    int getDeliversNum(){return delivers.size();}
    int getStorage(){return storage ;}
    int getMaxPizzaSize(){return maxpizzanum;}
}

class BakerConf{
    int experience;
}
class DeliveryConf{
    int backpacksize;
}