package home_server;

import home_server.structure.database.HomeServerDao;
import home_server.structure.database.entities.Host;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
        HomeServerDao homeServerDao = new HomeServerDao(jdbcTemplate);
        //Example
        insertFooData(homeServerDao);

    }

    private void insertFooData(HomeServerDao homeServerDao){
        String ip="12345";
        String name="'Nombre XD'";
        homeServerDao.persistsHost(ip,name);
        List<Host> hostList = homeServerDao.listAllHost();
        log.info(hostList.toString());
    }

}
