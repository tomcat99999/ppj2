package cz.tul.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by The CAT
 */
public class ImtgDao {


    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @Transactional
    public boolean create(Imtg imtg) {

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("imid", imtg.getImg().getId());
        params.addValue("tgid", imtg.getTag().getId());


        return jdbc.update("insert into imtg (imid, tgid) values ( :imid, :tgid)", params) == 1;
    }


    public List<Imtg> getAllImtg() {
        return jdbc.query("select * from imtg", BeanPropertyRowMapper.newInstance(Imtg.class));
    }

    public List<Imtg> getAllByTag(MyTag tag) {
        return jdbc.query("select * from imtg where tgid=:tgid", new MapSqlParameterSource("tgid", tag.getId()), BeanPropertyRowMapper.newInstance(Imtg.class));
    }

    public List<Imtg> getAllByImg(Image img) {
        return jdbc.query("select * from imtg where imid=:imid", new MapSqlParameterSource("imid", img.getId()), BeanPropertyRowMapper.newInstance(Imtg.class));
    }

    public void deleteUsers() {
        jdbc.getJdbcOperations().execute("DELETE FROM imtg");

    }
}
