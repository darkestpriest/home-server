package home_server.structure.database;

import home_server.structure.database.entities.Host;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
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

    private JdbcTemplate jdbcTemplate;

    /**
     * Default constructor.
     */
    public HomeServerDao(){
        log.info("Init Dao");
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        this.jdbcTemplate = new JdbcTemplate(dataSourceConfig.dataSource());
        initDatabase();
    }

    /**
     * This method initializes the server database
     */
    public void initDatabase(){
        log.info("Creating tables");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS hosts(" +
                "id SERIAL, ip VARCHAR(255), name VARCHAR(255))");
        String ip="localhost";
        String name="Server";
        persistsHost(ip,name);
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
        //List<Object[]> record = new ArrayList<>();
        //record.add(new String[]{ip,name});
        jdbcTemplate.update("INSERT INTO hosts(ip, name) VALUES (?,?)", ip, name);
        //jdbcTemplate.batchUpdate("INSERT INTO hosts(ip, name) VALUES (?,?)", record);
        log.info(jdbcTemplate.getDataSource().toString());
    }

    public void persistHost(Host host){
        log.info(String.format(
                "Inserting Host['%s']",
                host));
        List<Object[]> record = new ArrayList<>();
        record.add(new String[]{host.getIp(), host.getName()});
        jdbcTemplate.batchUpdate("INSERT INTO hosts(ip, name) VALUES (?,?)", record);
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

    public Host getByIp(String ip){
        return getByParameter("ip",ip);
    }

    public Host getByName(String name){
        return getByParameter("name",name);
    }

    private Host getByParameter(String parameter, String value){
        Host host = jdbcTemplate.queryForObject(
                "SELECT id, ip, name FROM hosts WHERE "+parameter+" = ?", new Object[] { value },
                (rs, rowNum) -> new Host(rs.getLong("id"), rs.getString("ip"), rs.getString("name"))
        );
        return host;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
