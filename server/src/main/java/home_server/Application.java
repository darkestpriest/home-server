package home_server;

import home_server.structure.database.HomeServerDao;
import home_server.structure.database.entities.Host;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by Manuel Perez P. (darkpriestrelative@gmail.com) on 11/08/16.
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        //Init database
        log.info("Init database");
        HomeServerDao homeServerDao = new HomeServerDao();
        //first Data
        //insertFooData(homeServerDao);
        //homeServerDao.persistsHost("1234","name");
        //log.info(homeServerDao.getByIp("localhost").toString());

    }

    private void insertFooData(HomeServerDao homeServerDao){
        String ip="foo";
        String name="test-name";
        homeServerDao.persistsHost(ip,name);
    }

}
