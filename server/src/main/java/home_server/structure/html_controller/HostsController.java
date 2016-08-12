package home_server.structure.html_controller;

import home_server.structure.database.HomeServerDao;
import home_server.structure.database.entities.Host;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Manuel Perez P. (darkpriestrelative@gmail.com) on 12/08/16.
 */
@Controller
public class HostsController {

    private static final HomeServerDao homeServerDao = new HomeServerDao();

    @RequestMapping("/hosts/insert")
    public @ResponseBody String insert(@RequestParam(value="host", defaultValue = "{\"id\":1,\"ip\":\"localhost\",\"name\":\"Server\"}") String jsonString) {
        Host host = Host.fromJson(jsonString);
        homeServerDao.persistHost(host);
        List<Host> hostList = homeServerDao.listAllHost();
        return getHostsHTML(hostList);
    }

    @RequestMapping("/hosts")
    public @ResponseBody String hosts( ) {
        List<Host> hostList = homeServerDao.listAllHost();
        return getHostsHTML(hostList);
    }

    private String getHostsHTML(List<Host> hostList){
        StringBuilder listContent = new StringBuilder("<ul>");
        hostList.forEach(host -> listContent.append("<li>"+host.getName()+"</li>"));
        return "<!DOCTYPE HTML>\n" +
                "<html >\n" +
                "<head>\n" +
                "    <title>Getting Started: Serving Web Content</title>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "</head>\n" +
                "<body>\n" +
                listContent +
                "</body>\n" +
                "</html>";
    }

}
