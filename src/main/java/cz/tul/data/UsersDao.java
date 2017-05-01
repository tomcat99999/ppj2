package cz.tul.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public class UsersDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @Transactional
    public boolean create(User user) {

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("name", user.getName());
        params.addValue("id", user.getId());
        params.addValue("registrationDate", new Date());



        return jdbc.update("insert into users (name, id, registrationDate) values (:name, :id, :registrationDate)", params) == 1;
    }

    public boolean exists(String name) {
        return jdbc.queryForObject("select count(*) from users where name=:name",
                new MapSqlParameterSource("name", name), Integer.class) > 0;
    }

    public  List<User> getUserByName(String name) {

       // params.addValue("tgid", imtg.getTag().getId());
        return jdbc.query("select * from users where name=:name",new MapSqlParameterSource("name", name), BeanPropertyRowMapper.newInstance(User.class)) ;

      //  return jdbc.update("insert into imtg (imid, tgid) values ( :imid, :tgid)", params) == 1;
     //   return jdbc.query("select * from users where name=:name", BeanPropertyRowMapper.newInstance(User.class));
    }
    public List<User> getAllUsers() {
        return jdbc.query("select * from users", BeanPropertyRowMapper.newInstance(User.class));
    }

    public void deleteUsers() {

        jdbc.getJdbcOperations().execute("DELETE FROM USERS");
    }
}
