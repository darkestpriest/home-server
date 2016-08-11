package home_server.structure.rest;

import home_server.structure.database.HomeServerDao;
import home_server.structure.database.entities.Host;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Manuel Perez P. (darkpriestrelative@gmail.com) on 11/08/16.
 */
@RestController
public class HostRestController {

    private static final HomeServerDao homeServerDao = new HomeServerDao();

    @RequestMapping("/host")
    public Host getHostByIp(@RequestParam(value="ip", defaultValue="localhost") String ip) {
        Host host = homeServerDao.getByIp(ip);
        return host;
    }

    @RequestMapping(value="/host/insert")
    public Host insert(@RequestParam(value="host", defaultValue = "{\"id\":1,\"ip\":\"localhost\",\"name\":\"Server\"}") String jsonString) {
        Host host = Host.fromJson(jsonString);
        homeServerDao.persistHost(host);
        return host;
    }

    @RequestMapping("/host/list")
    public List<Host> listAllHost() {
        List<Host> hosts = homeServerDao.listAllHost();
        return hosts;
    }

}
