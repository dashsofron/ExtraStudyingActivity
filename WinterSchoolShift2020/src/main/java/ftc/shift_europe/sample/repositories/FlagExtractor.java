package ftc.shift_europe.sample.repositories;
import ftc.shift_europe.sample.models.Flag;
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
public class FlagExtractor implements ResultSetExtractor<List<Flag>> {
    @Override
    public List<Flag> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<String, Flag> flags = new HashMap<>();

        while (rs.next()) {
            String flagId = rs.getString("FLAG_ID");

            Flag flag;
            if (flags.containsKey(flagId)) {
                flag = flags.get(flagId);
            } else {
                flag = new Flag();

                flag.setId(rs.getInt("FLAG_ID"));
                //flag.setRouteId(rs.getInt("ROUTE_ID"));
                //flag.setUserId(rs.getInt("USER_ID"));
                //flag.setName(rs.getString("NAME"));
                flag.set_x(rs.getDouble("X"));
                flag.set_y(rs.getDouble("Y"));
                flag.set_price(rs.getInt("PRICE"));
                //flag.setnext(rs.getInt("NEXT"));
                flag.set_prew_Flag_id(rs.getInt("PREW"));
                //flag.setGenre(new ArrayList<>());
                flags.put(flagId, flag);
            }
        }
        return new ArrayList<>(flags.values());
    }
}
