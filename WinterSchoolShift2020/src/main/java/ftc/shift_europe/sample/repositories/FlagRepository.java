package ftc.shift_europe.sample.repositories;//import flag;
//import java.util.Collection;

import ftc.shift_europe.sample.models.Flag;

public interface FlagRepository {

    Flag fetchFlag(Integer userId, Integer routeId, Integer flag);

    Flag updateFlag(Integer userId,Integer routeId, Integer flagId, Flag flag);

    void deleteFlag(Integer userId,Integer routeId, Integer flagId);

    Flag createFlag(Integer userId,Integer routeId, Flag flag);
}