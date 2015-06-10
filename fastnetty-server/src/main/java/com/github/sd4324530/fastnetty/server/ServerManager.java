package com.github.sd4324530.fastnetty.server;

import java.util.Set;

/**
 * @author peiyu
 */
public interface ServerManager {

    void addServer(NettyServer server);

    void setServers(Set<NettyServer> servers);

    void startServers(int tcpPort, int udpPort) throws Exception;

    void startServers() throws Exception;

    void stopServers() throws Exception;
}
