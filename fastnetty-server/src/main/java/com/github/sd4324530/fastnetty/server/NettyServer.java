package com.github.sd4324530.fastnetty.server;

import io.netty.bootstrap.ServerBootstrap;

import java.net.InetSocketAddress;

/**
 * @author peiyu
 */
public interface NettyServer {

    enum ServerType {
        TCP,UDP
    }

    ServerType getServerType();

    ServerBootstrap createServerBootstrap();

    void startServer() throws Exception;

    void startServer(int port) throws Exception;

    void startServer(InetSocketAddress socketAddress) throws Exception;

    void stopServer() throws Exception;

    InetSocketAddress getSocketAddress();
}
