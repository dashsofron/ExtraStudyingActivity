package ftc.shift_europe.sample.repositories;
import ftc.shift_europe.sample.models.Flag;
import ftc.shift_europe.sample.models.Route;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class RouteExtractor implements ResultSetExtractor<List<Route>> {
    private List<Flag> flags;
    @Override
    public List<Route> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<String, Route> routes = new HashMap<>();

        while (rs.next()) {
            String routeId = rs.getString("ROUTE_ID");
            Route route;
            if (routes.containsKey(routeId)) {
                route = routes.get(routeId);
            } else {
                route = new Route();
                route.set_id(rs.getInt("ROUTE_ID"));
                route.set_user_id(rs.getInt("USER_ID"));
                //route.setName(rs.getString("NAME"));
                route.set_price(rs.getInt("COST"));
                //route.setNext(rs.getInt("NEXT"));
                //route.setPrew(rs.getInt("PREW"));
                //flag.setGenre(new ArrayList<>());
                routes.put(routeId, route);
            }
        }
        return new ArrayList<>(routes.values());
    }
}
