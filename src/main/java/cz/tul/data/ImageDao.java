package cz.tul.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * Created by The CAT
 */

public class ImageDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @Transactional
    public boolean create(Image image) {
        MapSqlParameterSource params = new MapSqlParameterSource();

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        params.addValue("id", image.getId());
        params.addValue("location", image.getLocation());
        params.addValue("name", image.getName());
        params.addValue("created", sdf.format(image.getCreatedDate()));
        params.addValue("changed", sdf.format(image.getChangedDate()));
        params.addValue("likes", image.getLikes());
        params.addValue("dislikes", image.getDislikes());
        params.addValue("user_id", image.getUser().getId());

        return jdbc.update("insert into images (id, location, name, changed, created, likes, dislikes, user_id) values (:id, :location, :name, :created, :changed, :likes, :dislikes, :user_id)", params) == 1;
    }

    public List<Image> getAllImages() {
        return jdbc.query("select * from image", BeanPropertyRowMapper.newInstance(Image.class));
    }

    public List<Image> getImageByName(String name) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);
        return jdbc.query("select * from image WHERE name = :name", params, BeanPropertyRowMapper.newInstance(Image.class));
    }
    public List<Image> getImageByTag(MyTag t) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", t.getId());
        return jdbc.query("select images.* from images,image_tags WHERE images.id=image_tags.image_id AND image_tags.tag_id = :id", params, BeanPropertyRowMapper.newInstance(Image.class));
    }
    public boolean exists(String name) {
        return jdbc.queryForObject("select count(*) from image where name=:name",
                new MapSqlParameterSource("name", name), Integer.class) > 0;
    }

    public boolean like(int id) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        return jdbc.update("UPDATE `image` SET likes = likes + 1 WHERE `id` = " + id, param) == 1;
    }

    public boolean dislike(int id) {
        MapSqlParameterSource par = new MapSqlParameterSource();
        return jdbc.update("UPDATE `image` SET dislikes = dislikes + 1 WHERE `id` = " + id, par) == 1;
    }

    public int getLikes(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("select likes from image where id = :id", params, Integer.class);
    }

    public int getDislikes(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("select dislikes from image where id = :id", params, Integer.class);
    }


    public boolean changeName(int id, String name) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        Date d = new Date();
        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        params.addValue("id", id);
        params.addValue("newName", name);
        params.addValue("datum", sdf.format(d));
        return jdbc.update("UPDATE `image` SET name = :newName, date_edit = :datum " +
                "WHERE `idimage` = :idimage", params) == 1;
    }

    public boolean editLocation(int id, String loc) {
        MapSqlParameterSource params = new MapSqlParameterSource();

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = new Date();
        params.addValue("id", id);
        params.addValue("loc", loc);
        params.addValue("datum", sdf.format(d));
        return jdbc.update("UPDATE `image` SET url = :loc, date_edit = :datum " +
                "WHERE `idimage` = :idimage", params) == 1;
    }

    public String getImgname(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("select name from image where idimage = :id", params, String.class);
    }

    public String getLocation(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idimage", id);

        return jdbc.queryForObject("select url from image where id = :id", params, String.class);
    }

    public String getLastChanged(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("select changed from image where id = :id", params, String.class);
    }

    public void deleteImages() {

        jdbc.getJdbcOperations().execute("DELETE  FROM images");
    }
    public boolean addTag(int idimg,int idtag){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("image_tags", idimg);
        params.addValue("tag_id", idtag);

        return jdbc.update("insert into image_tags (image_id,tag_id) values (:image_tags,:tag_id)", params) == 1;

    }



}

