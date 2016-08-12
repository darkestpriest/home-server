package home_server;

import home_server.structure.database.HomeServerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Manuel Perez P. (darkpriestrelative@gmail.com) on 11/08/16.
 */
@SpringBootApplication
public class Application {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }

    private void insertFooData(HomeServerDao homeServerDao){
        String ip="foo";
        String name="test-name";
        homeServerDao.persistsHost(ip,name);
    }

}
