package cz.tul.data;

/**
 * Created by The CAT
 */

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;


public class ComentDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;
    @Autowired
    private SessionFactory sessionFactory;

    public Session session() { return sessionFactory.getCurrentSession();}
    @Transactional
    public void create(Coment coment) {
        session().save(coment);
       /* MapSqlParameterSource params = new MapSqlParameterSource();

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        params.addValue("id", coment.getId());
        params.addValue("coment", coment.getComent());
        params.addValue("created", sdf.format(coment.getCreatedDate()));
        params.addValue("changed", sdf.format(coment.getChangedDate()));
        params.addValue("likes", coment.getLikes());
        params.addValue("dislikes", coment.getDislikes());
        params.addValue("image_id", coment.getImage().getId());
        params.addValue("user_id", coment.getUser().getId());

      return jdbc.update("insert into coments (id, coment, created, changed, likes, dislikes, image_id, user_id) values (:id, :coment, :created, :changed, :likes, :dislikes, :image_id, :user_id)", params) == 1;
   */
    }

    public List<Coment> getAllComments() {

        Criteria criteria = session().createCriteria(Image.class);
        return criteria.list();
       // return jdbc.query("select * from comment", BeanPropertyRowMapper.newInstance(Coment.class));
    }

    public void dislikeComent(int id) {

        Criteria crit = session().createCriteria(Coment.class);
        crit.add(Restrictions.eq("id", id));
        Coment i=(Coment) crit.uniqueResult();
        i.setLikes(i.getLikes()+1);
        session().update(i);/*
        MapSqlParameterSource param = new MapSqlParameterSource();
        return jdbc.update("UPDATE `comment` SET likes = likes + 1 WHERE `id` = " + id, param) == 1;*/
    }

    public void likeComent(int id) {
        Criteria crit = session().createCriteria(Coment.class);
        crit.add(Restrictions.eq("id", id));
        Coment i=(Coment) crit.uniqueResult();
        i.setLikes(i.getLikes()+1);
        session().update(i);
        /*
        MapSqlParameterSource par = new MapSqlParameterSource();
        return jdbc.update("UPDATE `comment` SET dislikes = dislikes + 1 WHERE `idcomment` = " + id, par) == 1;
        */
    }

    public int getLikes(int id) {
        Criteria criteria = session().createCriteria(Coment.class);
        criteria.add(Restrictions.eq("id", id));
        Coment i=(Coment) criteria.uniqueResult();
        return i.getLikes();
     /*   MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("select likes from comment where id = :id", params, Integer.class);*/
    }

    public int getDislike(int id) {
       /* MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idcomment", id);

        return jdbc.queryForObject("select dislikes from comment where id = :id", params, Integer.class);*/

        Criteria criteria = session().createCriteria(Coment.class);
        criteria.add(Restrictions.eq("id", id));
        Coment i=(Coment) criteria.uniqueResult();
        return i.getDislikes();
    }

    public void deleteComents() {
        session().createQuery("delete from coments").executeUpdate();
       // jdbc.getJdbcOperations().execute("DELETE  FROM coments");
    }

    public void updateComent(int id, String text) {
        MapSqlParameterSource params = new MapSqlParameterSource();

      //  java.text.SimpleDateFormat sdf =  new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d= new Date();
        Criteria criteria = session().createCriteria(Coment.class);
        criteria.add(Restrictions.eq("id", id));
        Coment i=(Coment) criteria.uniqueResult();
        i.setChangedDate(d);
        i.setComent(text);

        session().update(i);
      /*  params.addValue("coment", text);

        params.addValue("changed", sdf.format(new Date()));
        params.addValue("id", id);
        return jdbc.update("UPDATE `comment` SET text = :coment, changed = :changed " +
                "WHERE `id` = :id", params) == 1;*/

    }
}