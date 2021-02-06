package ftc.shift_europe.sample.models;

public class Flag {
    private Integer flagId;
    private Double x;
    private Double y;
    private Integer price;
    private Integer prewFlagId;
    //МЕТОДЫ*******************************
    public Flag(Integer flagId/*,Integer routeId,Integer userId*/,Double x,Double y,Integer price,
                Integer prewFlagId){
        this.flagId = flagId;
        //this.routeId = routeId;
        //this.userId = userId;
        this.x = x;
        this.y = y;
        this.price = price;
        //this.nextFlagId = flagId; //значит, что он конечный, если надо в середину, то передвинем через goup и godown
        this.prewFlagId = prewFlagId; //если это первый, то пусть у него будет предыдущим его же id
    }

    //базовый конструктор (заполняет поля недопустимыми значениями), наверное пусть будет так, чем никак
    public Flag(){
        this.flagId = -1;
        this.x = 0.0;
        this.y = 0.0;
        this.price = 0;
        this.prewFlagId = -1;
    }

    //возвращаем номер флага
    public Integer getId() { return this.flagId; }
    //устанавливаем номер флага (не представляю где это может пригодиться)
    public void setId(Integer newFlagId) { this.flagId = newFlagId;}

    public Double get_x () { return this.x; }
    public void set_x (Double newX) { this.x = newX; }
    public Double get_y () { return this.y; }
    public void set_y (Double newY) { this.y = newY; }

    //возвращаем стоимость посещения этого места
    public Integer get_price() { return this.price; }
    //уставливаем стоимость посещения этого места
    public void set_price(Integer newPrice) { this.price = newPrice; }

    public Integer get_prew_Flag_id() { return this.prewFlagId; }
    public void set_prew_Flag_id(Integer newPrew) { this.prewFlagId = newPrew; }

}