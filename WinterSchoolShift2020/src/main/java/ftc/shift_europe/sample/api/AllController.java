package ftc.shift_europe.sample.api;


import ftc.shift_europe.sample.models.Flag;
import ftc.shift_europe.sample.models.Route;
import ftc.shift_europe.sample.models.User;
import ftc.shift_europe.sample.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

//controller
//сделать с флагом и другими классами вместо этого всего

@RestController //контроллер - запросы
public class AllController {
    private static final String  USER_PATH = "/api/{userId}";//вместо v001 айди юзера (хз что)
    private static final String FLAGS_PATH = "/api/{userId}/{routeId}";//вместо flags айди маршрута

    private Service service;


    @Autowired //пишем на конструкторе и полях, чтобы бут прописал зависимости сам (сам связал)
    public AllController(Service service) {
        this.service = service;
    }


    //flag считался с формы и добавляется в бд, возвращает тело полученного флага


    //FLAGCONTROLLER********************************************************************************************************************************************************************


    @PostMapping(FLAGS_PATH ) //отвечает на пост запрос по такому пути       путь до юзера+путь до маршрута
    public ResponseEntity<Flag> createFlag(
            @RequestHeader("userId") String userId,
            @RequestHeader("routeId") String routeId,
            @RequestBody Flag flag) {
        Flag result = service.createFlag(Integer.parseInt(userId),Integer.parseInt(routeId), flag);
        return ResponseEntity.ok(result);
    }

    //отвечает на запрос о получении инфы по флагу, возвращает тело флага
    @GetMapping(FLAGS_PATH + "/{flagId}")//отвечает на гет запрос
    public ResponseEntity<Flag> readFlag(
            @RequestHeader("userId") String userId,
            @RequestHeader("routeId") String routeId,
            @PathVariable String flagId) {
        Flag flag = service.provideFlag(Integer.parseInt(userId), Integer.parseInt(routeId), Integer.parseInt(flagId));
        return ResponseEntity.ok(flag);
    }


    //изменение флага -стоимость, имя, мб координаты или номер(если это будет нужно)
    @PatchMapping(FLAGS_PATH + "/{flagId}")//любой запрос
    public ResponseEntity<Flag> updateFlag(
            @RequestHeader("userId") String userId,
            @RequestHeader("routeId") String routeId,
            @PathVariable String flagId,
            @RequestBody Flag flag
    )
    {
        Flag updatedFlag = service.updateFlag(Integer.parseInt(userId), Integer.parseInt(routeId), Integer.parseInt(flagId), flag);
        return ResponseEntity.ok(updatedFlag);
    }

//    //изменение имени
//    @PatchMapping(FLAGS_PATH + "/changeName" + "/{flagId}")//любой запрос
//    public ResponseEntity<Flag> updateFlagName(
//            @RequestHeader("userId") String userId,
//            @RequestHeader("routeId") String routeId,
//            @PathVariable String flagId,
//            @RequestBody String flagName
//    )
//    {
//        Flag updatedFlag = service.updateFlagName(userId, routeId, flagId, flagName);
//        return ResponseEntity.ok(updatedFlag);
//    }
//    //измнение стоимости. ИЗ-ЗА ЭТОГО НАДО ДЕЛАТЬ ПЕРЕСЧЕТ СТОИМОСТИ ВО ВСЕМ МАРШРУТЕ ВОТ ТАК ВОТ
//    @PatchMapping(FLAGS_PATH + "/changeCost" + "/{flagId}")//любой запрос
//    public ResponseEntity<Flag> updateFlagCost(
//            @RequestHeader("userId") String userId,
//            @RequestHeader("routeId") String routeId,
//            @PathVariable String flagId,
//            @RequestBody String flagCost
//            )
//    {
//        Flag updatedFlag = service.updateFlagCost(userId, routeId, flagId, flagCoste);
//        return ResponseEntity.ok(updatedFlag);
//    }

//    @PatchMapping(FLAGS_PATH + "/goUp" + "/{flagId}")//любой запрос
//    public ResponseEntity<Flag> moveFlagUp(
//            @RequestHeader("userId") String userId,
//            @RequestHeader("route Id") String routeId,
//            @PathVariable String flagId
//            )
//    {
//        Flag updatedFlag = service.updateFlagPositionUp(userId, routeId, flagId);
//        return ResponseEntity.ok(updatedFlag);
//    }

//    @PatchMapping(FLAGS_PATH + "/goDown" + "/{flagId}")//любой запрос
//    public ResponseEntity<Flag> moveFlagDown(
//            @RequestHeader("userId") String userId,
//            @RequestHeader("routeId") String routeId,
//            @PathVariable String flagId
//            )
//    {
//        Flag updatedFlag = service.updateFlagPositionDown(userId, routeId, flagId);
//        return ResponseEntity.ok(updatedFlag);
//    }
    @DeleteMapping(FLAGS_PATH + "/{flagId}")
    public ResponseEntity<?> deleteFlag(
            @RequestHeader("userId") String userId,
            @RequestHeader("routeId") String routeId,
            @PathVariable String flagId) {
        service.deleteFlag(Integer.parseInt(userId),Integer.parseInt(routeId), Integer.parseInt(flagId));
        return ResponseEntity.ok().build();
    }

    /**
     * Получение всех книг пользователя
     *
     * @param userId - Идентификатор пользователя
     */



//ROUTECONTROLLER*******************************************************************************************************************************************************************

//    @GetMapping(USER_PATH + "/{routeId}")
//    public ResponseEntity<Collection<Flag>> listFlags(
//            @RequestHeader("userId") String userId,
//            @PathVariable String routeId){
//        Collection<Flag> flags = service.provideFlags(userId,routeId);
//        return ResponseEntity.ok(flags);
//    }

    @PostMapping(USER_PATH) //отвечает на пост запрос по такому пути       путь до юзера+путь до маршрута
    public ResponseEntity<Route> createRoute(
            @RequestHeader("userId") String userId,
            @RequestBody Route route) {
        Route result = service.createRoute(Integer.parseInt(userId),route);
        return ResponseEntity.ok(result);
    }
    //отвечает на запрос о получении инфы по флагу, возвращает тело флага
    @GetMapping(USER_PATH + "/{routeId}")//отвечает на гет запрос
    public ResponseEntity<Route> readRoute(
            @RequestHeader("userId") String userId,
            @PathVariable String routeId) {
        Route route = service.provideRoute(Integer.parseInt(routeId));
        return ResponseEntity.ok(route);
    }

    //изменение флага -стоимость, имя, мб координаты или номер(если это будет нужно)
    @PatchMapping(USER_PATH + "/{routeId}")//любой запрос
    public ResponseEntity<Route> updateRoute(
            @RequestHeader("userId") String userId,
            @PathVariable String routeId,
            @RequestBody Route route)
    {
        Route updatedRoute = service.updateRoute(Integer.parseInt(userId), Integer.parseInt(routeId), route);
        return ResponseEntity.ok(updatedRoute);
    }

//    //изменение имени
//    @PatchMapping(USER_PATH + "/changeName" + "/{routeId}")//любой запрос
//    public ResponseEntity<Route> updateRouteName(
//            @RequestHeader("userId") String userId,
//            @PathVariable String routeId,
//            @RequestBody String routeName)
//    {
//        Route updatedRoute = service.updateRouteName(userId, routeId,routeName);
//        return ResponseEntity.ok(updatedRoute);
//    }
//
//    @PatchMapping(USER_PATH + "/changeCost" + "/{routeId}")//любой запрос
//    public ResponseEntity<Route> updateRouteCost(
//            @RequestHeader("userId") String userId,
//            @PathVariable String routeId,
//            @RequestBody String routeCost)
//    {
//        Route updatedRoute = service.updateRouteCost(userId, routeId,routeCost);
//        return ResponseEntity.ok(updatedRoute);
//    }

//    @PatchMapping(USER_PATH + "/goUp" + "/{routeId}")//любой запрос
//    public ResponseEntity<Route> moveRouteUp(
//            @RequestHeader("userId") String userId,
//            @PathVariable String routeId)
//    {
//        Route updatedRoute = service.updateRoutePositionUp(userId, routeId);
//        return ResponseEntity.ok(updatedRoute);
//    }
//
//    @PatchMapping(USER_PATH + "/goDown" + "/{routeId}")//любой запрос
//    public ResponseEntity<Route> moveRouteDown(
//            @RequestHeader("userId") String userId,
//            @PathVariable String routeId)
//    {
//        Route updatedRoute = service.updateRoutePositionDown(userId, routeId);
//        return ResponseEntity.ok(updatedRoute);
//    }

    @DeleteMapping(USER_PATH + "/{routeId}")
    public ResponseEntity<?> deleteRoute(
            @RequestHeader("userId") String userId,
            @PathVariable String routeId) {
        service.deleteRoute(Integer.parseInt(routeId));
        return ResponseEntity.ok().build();
    }
//
//    @DeleteMapping(USER_PATH + "/deleteAllFlags" + "/{routeId}")
//    public ResponseEntity<?> deleteFlags(
//            @RequestHeader("userId") String userId,
//            @PathVariable String routeId) {
//        service.deleteFlags(Integer.parseInt(userId),Integer.parseInt(routeId));
//        return ResponseEntity.ok().build();
//    }





//USERCONTROLLER********************************************************************************************************************************************************************

 /*@PostMapping(USER_PATH) //отвечает на пост запрос по такому пути       путь до юзера+путь до маршрута
    public ResponseEntity<Route> createRoute(
            @RequestHeader("userId") String userId,
            @RequestBody Route route) {
        Route result = service.createFlag(userId,route);
        return ResponseEntity.ok(result);
    }*/
    //отвечает на запрос о получении инфы по флагу, возвращает тело флага

    /*@GetMapping(USER_PATH + "/{routeId}")//отвечает на гет запрос
    public ResponseEntity<Route> readRoute(
            @RequestHeader("userId") String userId,
            @PathVariable String routeId) {
        Route route = service.provideRoute(userId,routeId);
        return ResponseEntity.ok(route);
    }*/


//изменение имени
   /* @PatchMapping(USER_PATH + "/changeName" + "/{routeId}")//любой запрос
    public ResponseEntity<Route> updateRouteName(
            @RequestHeader("userId") String userId,
            @PathVariable String routeId,
            @RequestBody String routeName)
            {
        Route updatedRoute = service.updateRouteName(userId, routeId,routeName);
        return ResponseEntity.ok(updatedRoute);
    }*/
    //можно поменять на пароль
        /*@PatchMapping(USER_PATH + "/changeCost" + "/{routeId}")//любой запрос
    public ResponseEntity<Route> updateRouteCost(
            @RequestHeader("userId") String userId,
            @PathVariable String routeId,
            @RequestBody String routeCost)
            {
        Route updatedRoute = service.updateRouteCost(userId, routeId,routeCost);
        return ResponseEntity.ok(updatedRoute);
    }*/

   /*@PatchMapping(USER_PATH + "/goUp" + "/{routeId}")//любой запрос
    public ResponseEntity<Book> moveRouteUp(
            @RequestHeader("userId") String userId,
            @PathVariable String routeId)
            {
        Flag updatedFlag = service.updateFlagPositionUp(userId, routeId);
        return ResponseEntity.ok(updatedFlag);
    }

   @PatchMapping(USER_PATH + "/goDown" + "/{routeId}")//любой запрос
    public ResponseEntity<Book> moveRouteDown(
            @RequestHeader("userId") String userId,
            @PathVariable String routeId)
            {
        Flag updatedFlag = service.updateFlagPositionDown(userId, routeId);
        return ResponseEntity.ok(updatedFlag);
    }*/

    /*@DeleteMapping(USER_PATH + "/{routeId}")
    public ResponseEntity<?> deleteRoute(
            @RequestHeader("userId") String userId,
            @PathVariable String routeId) {
        service.deleteFlag(userId,routeId);
        return ResponseEntity.ok().build();
    }*/

    @PostMapping("/checkUser") //отвечает на пост запрос по такому пути       путь до юзера+путь до маршрута
    public ResponseEntity<Boolean> checkUser(
            @RequestBody User user) {
        boolean result = service.checkUser(user.getLogin());
        return ResponseEntity.ok(result);// а здесь нужно вернуть переход на новую страничку
    }
    @DeleteMapping(USER_PATH + "/deleteAllRoutes")
    public ResponseEntity<?> deleteRoutes(
            @RequestHeader("userId") String userId) {
//        service.deleteRoutes(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(USER_PATH)
    public ResponseEntity<Collection<Route>> listRoutes(
            @RequestHeader("userId") Integer userId){
        Collection<Route> routes = service.provideRoutes(userId);
        return ResponseEntity.ok(routes);
    }

    /*@RequestMapping("/")
    public String startPage(Model model) {
        return "index.html";
    }КАК ЗАПУСТИТЬ СТАРТОВУЮ СТРАНИЦУ
    */
}










