package ftc.shift_europe.sample.repositories;//package ftc.shift.sample.repositories;

import ftc.shift_europe.sample.models.User;
import ftc.shift_europe.sample.models.Route;
import ftc.shift_europe.sample.models.Flag;

import ftc.shift_europe.sample.repositories.FlagExtractor;
import ftc.shift_europe.sample.repositories.FlagRepository;
import ftc.shift_europe.sample.repositories.RouteExtractor;
import ftc.shift_europe.sample.repositories.UserExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Repository
@ConditionalOnProperty(name = "use.database", havingValue = "true")
public class DatabaseFlagRepository implements FlagRepository, RouteRepository, UserRepository{
    private NamedParameterJdbcTemplate jdbcTemplate;
    private FlagExtractor flagExtractor;
    private RouteExtractor routeExtractor;
    private UserExtractor userExtractor;

    @Autowired
    public DatabaseFlagRepository(NamedParameterJdbcTemplate jdbcTemplate, FlagExtractor flagExtractor,
                                  RouteExtractor routeExtractor, UserExtractor userExtractor) {
        this.jdbcTemplate = jdbcTemplate;
        this.flagExtractor = flagExtractor;
        this.routeExtractor = routeExtractor;
        this.userExtractor = userExtractor;
    }

    @PostConstruct
    public void initialize() {
        String createGenerateFlagIdSequenceSql = "create sequence Flag_ID_GENERATOR";
        String createGenerateRouteIdSequenceSql = "create sequence Route_ID_GENERATOR";
        String createGenerateUserIdSequenceSql = "create sequence User_ID_GENERATOR";

        String createFlagTableSql = "create table FLAGS (" +
                "FLAG_ID   INTEGER default FLAG_ID_GENERATOR.nextval," +
                "ROUTE_ID  INTEGER," +
                "X         DOUBLE," +
                "Y         DOUBLE," +
                "PRICE     INTEGER," +
                "PREW      INTEGER" +
                ");";
        String createRouteTableSql = "create table ROUTES (" +
                "ROUTE_ID  INTEGER default ROUTE_ID_GENERATOR.nextval," +
                "USER_ID   INTEGER," +
                "PRICE      INTEGER" +
                ");";
        String createUserTableSql = "create table USERS (" +
                "USER_ID     INTEGER default USER_ID_GENERATOR.nextval," +
                "USER_LOGIN  VARCHAR(64)" +
                ");";

        jdbcTemplate.update(createGenerateFlagIdSequenceSql, new MapSqlParameterSource());
        jdbcTemplate.update(createGenerateRouteIdSequenceSql, new MapSqlParameterSource());
        jdbcTemplate.update(createGenerateUserIdSequenceSql, new MapSqlParameterSource());
        jdbcTemplate.update(createFlagTableSql, new MapSqlParameterSource());
        jdbcTemplate.update(createRouteTableSql, new MapSqlParameterSource());
        jdbcTemplate.update(createUserTableSql, new MapSqlParameterSource());
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        //-------------------------------------------------------------------------------------------
        //Заполним таблицы тестовыми данными
//        createBook("UserA", new Book("1", "Название 1", "Автор Авторович", 12,
//                Arrays.asList("Фантастика", "Драма", "Нуар")));
//        createBook("UserA", new Book("2", "Название 2", "Автор Писателевич", 48,
//                Collections.singletonList("Детектив")));
//        createBook("UserB", new Book("3", "Название 3", "Писатель Авторович", 24,
//                Collections.singletonList("Киберпанк")));
        //-------------------------------------------------------------------------------------------
    }


    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public Collection<Route> getAllRoutes(Integer userId) {
        String sql = "select USER_ID, ROUTE_ID, PRICE " +
                "from ROUTES " +
                "where USER_ID =: userId";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("userId", userId);
        return jdbcTemplate.query(sql, params, routeExtractor);
    }

    public Collection<User> getAllUsers() {
        String sql = "select USER_ID, USER_LOGIN " +
                "from USERS ";
        MapSqlParameterSource params = new MapSqlParameterSource();
        return jdbcTemplate.query(sql, params, userExtractor); //user
    }

    public Collection<Flag> getAllFlags(Integer routeId) {
        String sql = "select ROUTE_ID, FLAG_ID, X, Y, PRICE, PREW " +
                "from FLAGS " +
                "where ROUTE_ID =: routeId";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("routeId", routeId);
        return jdbcTemplate.query(sql, params, flagExtractor);
    }

    public Boolean checkUser(String userLogin) {
        String sql = "select USER_ID, USER_LOGIN " +
                "from USERS " +
                "where USER_LOGIN=:userLogin";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("userLogin", userLogin);
        List<User> users = jdbcTemplate.query(sql, params, userExtractor); // user
        return !users.isEmpty();
    }

    public Flag fetchFlag(Integer routeId, Integer flagId) {
        String sql = "select ROUTE_ID, FLAG_ID, X, Y, PRICE, PREW " +
                "from FLAGS " +
                "where FLAG_ID=:flagId and ROUTE_ID=:routeId";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("flagId", flagId)
                .addValue("routeId", routeId);
        List<Flag> flags = jdbcTemplate.query(sql, params, flagExtractor);
        if (flags.isEmpty()) {
            return null;
        }
        return flags.get(0);
    }

    public Route fetchRoute(Integer routeId) {
        String sql = "select ROUTE_ID, USER_ID, PRICE " +
                "from ROUTES " +
                "where ROUTE_ID=:routeId";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("routeId", routeId);
        List<Route> routes = jdbcTemplate.query(sql, params, routeExtractor);
        if (routes.isEmpty()) {
            return null;
        }
        return routes.get(0);
    }

    public void deleteRoute(Integer routeId) {
        String deleteRouteSql = "delete from ROUTES where ROUTE_ID=:routeId";
        String deleteFlagSql = "delete from FLAGS where ROUTE_ID=:routeId";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("routeId", routeId);
        jdbcTemplate.update(deleteRouteSql, params);
        jdbcTemplate.update(deleteFlagSql, params);
    }

    @Override
    public Flag fetchFlag(Integer userId, Integer routeId, Integer flag) {
        return null;
    }

    @Override
    public Flag updateFlag(Integer userId, Integer routeId, Integer flagId, Flag flag) {
        return null;
    }

    public void deleteFlag(Integer roateId, Integer flagId, Integer userId) {
        //price для route
        Flag flag = fetchFlag(roateId, flagId);
        Route route = fetchRoute(roateId);
        route.set_price(route.get_price() - flag.get_price());
        updateRoute(userId, roateId, route);
        //price для route

        String deleteFlagSql = "delete from FLAGS where FLAG_ID=:flagId";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("flagId", flagId);
        jdbcTemplate.update(deleteFlagSql, params);
    }


    @Override
    public Flag createFlag(Integer userId, Integer routeId, Flag flag) {
        String insertFlagSql = "insert into FLAGS (ROUTE_ID, USER_ID, X, Y, PRICE, PREW) values" +
                "(:routeId, :x, :y, :price, :prew)";
        MapSqlParameterSource flagParams = new MapSqlParameterSource()
                .addValue("routeId", routeId)
                .addValue("x", flag.get_x())
                .addValue("y", flag.get_y())
                .addValue("price", flag.get_price())
                .addValue("prew", flag.get_prew_Flag_id());
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(insertFlagSql, flagParams, generatedKeyHolder);
        Integer flagId = Integer.parseInt(generatedKeyHolder.getKeys().get("FLAG_ID").toString());
        flag.setId(flagId);

        //price для route
        Route route = fetchRoute(routeId);
        route.set_price(route.get_price() - flag.get_price());
        updateRoute(userId, routeId, route);
        //price для route

        return flag;
    }

    public Route createRoute(Integer user_Id, Route route) {
        String insertRouteSql = "insert into ROUTES (USER_ID, PRICE) values (:userId, :price)";
        MapSqlParameterSource routeParams = new MapSqlParameterSource()
                .addValue("userId", user_Id)
                .addValue("price", route.get_price());
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(insertRouteSql, routeParams, generatedKeyHolder);
        Integer routeId = Integer.parseInt(generatedKeyHolder.getKeys().get("ROUTE_ID").toString());
        route.set_id(routeId);
        return route;
    }

    public User createUser(User user) {
        String insertUserSql = "insert into USERS (USER_LOGIN) values (:userLogin)";
        MapSqlParameterSource userParams = new MapSqlParameterSource()
                .addValue("userLogin", user.getLogin());
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(insertUserSql, userParams, generatedKeyHolder);
        Integer userId = Integer.parseInt(generatedKeyHolder.getKeys().get("USER_ID").toString());
        user.setId(userId);
        return user;
    }

    public Flag updateFlag(Integer routeId, Integer flagId, Flag flag, Integer userId) {
        //price для route
        Flag flag_1 = fetchFlag(routeId, flagId);
        Route route = fetchRoute(routeId);
        route.set_price(route.get_price() - flag_1.get_price() + flag.get_price());
        updateRoute(userId, routeId, route);
        //price для route

        String updateFlagSql = "update FLAGS " +
                "set ROUTE_ID=:routeId, " +
                "X=:x, " +
                "Y=:y, " +
                "PRICE=:price, " +
                "PREW=:prew " +
                "where FLAG_ID=:flagId";
        MapSqlParameterSource flagParams = new MapSqlParameterSource()
                .addValue("flagId", flagId)
                .addValue("routeId", routeId)
                .addValue("x", flag.get_x())
                .addValue("y", flag.get_y())
                .addValue("price", flag.get_price())
                .addValue("prew", flag.get_prew_Flag_id());
        jdbcTemplate.update(updateFlagSql, flagParams);
        return flag;
    }

    public Route updateRoute(Integer userId, Integer routeId, Route route) {
        String updateRouteSql = "update ROUTES " +
                "set USER_ID=:userId, " +
                "PRICE=:price " +
                "where ROUTE_ID=:routeId";
        MapSqlParameterSource routeParams = new MapSqlParameterSource()
                .addValue("routeId", routeId)
                .addValue("userId", userId)
                .addValue("price", route.get_price());
        jdbcTemplate.update(updateRouteSql, routeParams);
        return route;
    }
}
//++++++++++++++++++++++++++++++++++++++++++++++++++
