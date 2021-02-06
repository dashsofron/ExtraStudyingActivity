package ftc.shift_europe.sample.repositories;

import ftc.shift_europe.sample.models.Route;

import java.util.Collection;


public interface RouteRepository {

    Route fetchRoute(Integer routeId);

    Route updateRoute(Integer userId,Integer routeId, Route route);

    void deleteRoute(Integer routeId);

    Route createRoute(Integer userLog, Route route);

    Collection<Route> getAllRoutes(Integer userId);


}