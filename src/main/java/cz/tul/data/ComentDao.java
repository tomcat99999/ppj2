package cz.tul.data;

/**
 * Created by The CAT
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


public class ComentDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @Transactional
    public boolean create(Coment coment) {

        MapSqlParameterSource params = new MapSqlParameterSource();

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        params.addValue("idcomment", coment.getId());
        params.addValue("coment", coment.getComent());
        params.addValue("created", sdf.format(coment.getCreatedDate()));
        params.addValue("changed", sdf.format(coment.getChangedDate()));
        params.addValue("likes", coment.getLikes());
        params.addValue("dislikes", coment.getDislikes());
        params.addValue("image_id", coment.getImage().getId());
        params.addValue("user_idr", coment.getUser().getId());

        return jdbc.update("insert into coments (id, coment, created, changed, likes, dislikes, image_id, user_id) values (NULL, :text, :created, :changed, :likes, :dislikes, :image_id, :user_id)", params) == 1;
    }

    public List<Coment> getAllComments() {
        return jdbc.query("select * from comment", BeanPropertyRowMapper.newInstance(Coment.class));
    }

    public boolean likeComent(int id) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        return jdbc.update("UPDATE `comment` SET likes = likes + 1 WHERE `id` = " + id, param) == 1;
    }

    public boolean like(int id) {
        MapSqlParameterSource par = new MapSqlParameterSource();
        return jdbc.update("UPDATE `comment` SET dislikes = dislikes + 1 WHERE `idcomment` = " + id, par) == 1;
    }

    public int getLikes(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("select likes from comment where id = :id", params, Integer.class);
    }

    public int getDislike(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idcomment", id);

        return jdbc.queryForObject("select dislikes from comment where id = :id", params, Integer.class);
    }


    public boolean updateComent(int id, String text) {
        MapSqlParameterSource params = new MapSqlParameterSource();

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d= new Date();

        params.addValue("coment", text);

        params.addValue("changed", sdf.format(new Date()));
        params.addValue("id", id);
        return jdbc.update("UPDATE `comment` SET text = :coment, changed = :changed " +
                "WHERE `id` = :id", params) == 1;
    }
}