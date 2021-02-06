package ftc.shift_europe.sample.models;

import java.util.Collection;
import java.util.List;

public class Route {
    private Integer routeId;
    private Integer userId;
    private Integer price;

    //МЕТОДЫ****************************************************
    public Route(){};

    public Route(Integer routeId, Integer userId, Integer price){
        this.routeId = routeId;
        this.userId = userId;
        this.price = price;
    }

    public Integer get_id(){
        return this.routeId;
    }

    public void set_id(Integer routeId){
        this.routeId = routeId;
    }

    public Integer get_user_id(){
        return this.userId;
    }

    public void set_user_id(Integer userId){
        this.userId = userId;
    }

    public Integer get_price(){
        return this.price;
    }

    public void set_price(Integer price){
        this.price = price;
    }
    //что нужно вызывать при созданиее - метод у маршрута или метод у флага? если у флага, как обновлять маршрут? если у маршрута, что и как возвращать?
    //и что возвращать при изменении стимости? ведь тогда обновляется и у маршрута,как вернуть и флаг и маршрут?
    //наверное будет вызываться у флага и просто обновлять потом маршрут


}


