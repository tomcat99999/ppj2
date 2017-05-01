package cz.tul;

import cz.tul.data.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by The CAT
 */
@SpringApplicationConfiguration(classes = {Main.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
public class ComentDaoTest {

    @Autowired
    private ComentDao comentDao;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private ImageDao imageDao;

    @Autowired
    private MyTagDao mytagDao;


    private User u1 =new User(1,"franta");
    private User u2 =new User(2,"honza");
    private Image i1=new Image(1,"img1","neni",u1);
    private Image i2=new Image(2,"img2","neni",u1);
   private MyTag t1 = new  MyTag(1, "t1");
    private MyTag t2 = new  MyTag(2, "t2");
    private MyTag t3 = new  MyTag(    3, "t3");
    private Coment c1=new Coment(1, "text1", u2, i1) ;
    private Coment c2=new Coment(2, "text1", u2, i2) ;
    @Test
    public void testExists() {
        usersDao.deleteUsers();
        imageDao.deleteImages();
        mytagDao.deleteTags();
        comentDao.deleteComents();


        usersDao.create(u1);
        usersDao.create(u2);
        imageDao.create(i1);
        imageDao.create(i2);
        mytagDao.create(t1);
        mytagDao.create(t3);
        mytagDao.create(t2);
        comentDao.create(c2);
        comentDao.create(c1);
        assertTrue( usersDao.exists("franta"));
        assertEquals("all tags", 3, mytagDao.getAllTags().size());
        imageDao.addTag(i1.getId(),t2.getId());
        imageDao.addTag(i2.getId(),t2.getId());
        assertEquals("images w/ t2", 2, imageDao.getImageByTag(t2).size());
        assertEquals("tags of im1", 1, mytagDao.getAllTagsofIMG(i1).size());

        usersDao.deleteUsers();
        imageDao.deleteImages();
        mytagDao.deleteTags();
        comentDao.deleteComents();
    }

}
