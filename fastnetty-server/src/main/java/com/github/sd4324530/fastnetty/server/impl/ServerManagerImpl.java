package com.github.sd4324530.fastnetty.server.impl;

import com.github.sd4324530.fastnetty.server.NettyServer;
import com.github.sd4324530.fastnetty.server.ServerManager;

import java.util.HashSet;
import java.util.Set;

/**
 * @author peiyu
 */
public final class ServerManagerImpl implements ServerManager {

    private Set<NettyServer> servers;

    public ServerManagerImpl() {
        this.servers = new HashSet<NettyServer>();
    }

    @Override
    public void addServer(NettyServer server) {
        this.servers.add(server);
    }

    @Override
    public void setServers(Set<NettyServer> servers) {
        this.servers.addAll(servers);
    }

    @Override
    public void startServers(int tcpPort, int udpPort) throws Exception {
        if (!this.servers.isEmpty()) {
            for (NettyServer server : this.servers) {
                server.createServerBootstrap();
                if (server instanceof NettyTCPServer) {
                    server.startServer(tcpPort);
                } else if (server instanceof NettyUDPServer) {
                    server.startServer(udpPort);
                }
            }
        }
    }

    @Override
    public void startServers() throws Exception {
        if (!this.servers.isEmpty()) {
            for (NettyServer server : this.servers) {
                server.createServerBootstrap();
                server.startServer();
            }
        }
    }

    @Override
    public void stopServers() throws Exception {
        if (!this.servers.isEmpty()) {
            for (NettyServer server : this.servers) {
                server.stopServer();
            }
        }
    }
}
