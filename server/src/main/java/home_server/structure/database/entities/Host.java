package home_server.structure.database.entities;

import home_server.structure.util.JsonParser;

/**
 * Created by Manuel Perez P. (darkpriestrelative@gmail.com) on 11/08/16.
 */
public class Host {

    /**
     * Represents the internal host id
     */
    private long id;

    /**
     * Represents the host ip
     */
    private String ip;

    /**
     * Represents the host name
     */
    private String name;

    /**
     * Default constructor with parameters
     * @param id
     * @param ip
     * @param name
     */
    public Host(
            long id,
            String ip,
            String name) {
        this.id = id;
        this.ip = ip;
        this.name = name;
    }

    public Host(){}

    public long getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public String getName() {
        return name;
    }

    public static Host fromJson(String jsonString){
        return new JsonParser<Host>(jsonString).parseToObject(Host.class);
    }

    @Override
    public String toString() {
        return String.format(
                "Host[id=%d, ip='%s', name='%s']",
                id, ip, name);
    }
}
