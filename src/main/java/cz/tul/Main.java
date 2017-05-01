package cz.tul;

import cz.tul.data.ComentDao;
import cz.tul.data.ImageDao;
import cz.tul.data.MyTagDao;
import cz.tul.data.UsersDao;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.Arrays;
@SpringBootApplication
@EnableTransactionManagement
@EntityScan("cz.tul.data")
public class Main {


    @Bean
    public ImageDao ImageDao() {
        return new ImageDao();
    }

    @Bean
    public UsersDao UsersDao() {
        return new UsersDao();
    }

    @Bean
    public MyTagDao MyTag() {
        return new MyTagDao();
    }

    @Bean
    public ComentDao ComentDao() {
        return new ComentDao();
    }


    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Bean
    public SessionFactory sessionFactory() {
        return entityManagerFactory.unwrap(SessionFactory.class);
    }

    @Bean
    public PlatformTransactionManager txManager() {
        return new HibernateTransactionManager(entityManagerFactory.unwrap(SessionFactory.class));
    }

    private static final Logger log = LoggerFactory.getLogger(Main.class);

        public static void main(String[] args) throws Exception {
            SpringApplication app = new SpringApplication(Main.class);
            ConfigurableApplicationContext context = app.run(args);

            log.debug(Arrays.toString(context.getEnvironment().getActiveProfiles()));
            log.warn("START");
        }


    }