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
public class MyTagDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @Transactional
    public boolean create(MyTag tag) {

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("id", tag.getId());
        params.addValue("value", tag.getValue());



        return jdbc.update("insert into mytag (id, value) values ( :id, :value)", params) == 1;
    }

    public boolean exists(String value) {
        return jdbc.queryForObject("select count(*) from mytag where value=:value",
                new MapSqlParameterSource("value", value), Integer.class) > 0;
    }

    public List<MyTag> getAllTags() {
        return jdbc.query("select * from mytag", BeanPropertyRowMapper.newInstance(MyTag.class));
    }
    public List<MyTag> getAllTagsofIMG(int id) {
        return jdbc.query("select mytag.* from tags,image_tags WHERE image_tags.tag_id= tags.id AND image_tags.image_id="+id, BeanPropertyRowMapper.newInstance(MyTag.class));
    }
    public void deleteTags() {
        jdbc.getJdbcOperations().execute("DELETE FROM mytag");

    }
}
