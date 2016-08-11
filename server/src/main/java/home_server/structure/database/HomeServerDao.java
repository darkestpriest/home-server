package home_server.structure.database;

import home_server.structure.database.entities.Host;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Manuel Perez P. (darkpriestrelative@gmail.com) on 11/08/16.
 */
@SpringBootApplication
public class HomeServerDao {

    private static final Logger log = LoggerFactory.getLogger(HomeServerDao.class);

    JdbcTemplate jdbcTemplate;

    /**
     * Default constructor.
     */
    public HomeServerDao(JdbcTemplate jdbcTemplate){
        log.info("Init Dao");
        this.jdbcTemplate = jdbcTemplate;
        initDatabase();
    }

    /**
     * This method initializes the server database
     */
    private void initDatabase(){
        log.info("Creating tables");

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS hosts(" +
                "id SERIAL, ip VARCHAR(255), name VARCHAR(255))");
    }

    /**
     * This method creates a host and persists in database
     * @param ip
     * @param name
     */
    public void persistsHost(String ip, String name){
        log.info(String.format(
                "Inserting Host[ip='%s', name='%s']",
                 ip, name));
        List<Object[]> record = new ArrayList<>();
        record.add(new String[]{ip,name});
        //jdbcTemplate.update("INSERT INTO hosts(ip, name) VALUES (?,?)", ip, name);
        jdbcTemplate.batchUpdate("INSERT INTO hosts(ip, name) VALUES (?,?)", record);
        log.info(jdbcTemplate.getDataSource().toString());
    }

    /**
     * This method returns a list of host recorded in database
     * @return
     */
    public List<Host> listAllHost(){
        log.info("Listing all hosts");
        return jdbcTemplate.query(
                "SELECT id, ip, name FROM hosts ",
                (rs, rowNum) -> new Host(rs.getLong("id"), rs.getString("ip"), rs.getString("name"))
        );
    }

}
