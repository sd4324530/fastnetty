package com.github.sd4324530.fastnetty.server;

import java.net.InetSocketAddress;

/**
 * @author peiyu
 */
public interface NettyServer {

    enum ServerType {
        TCP,UDP
    }

    ServerType getServerType();

    void startServer() throws Exception;

    void startServer(int port) throws Exception;

    void startServer(InetSocketAddress socketAddress) throws Exception;

    void stopServer() throws Exception;

    InetSocketAddress getSocketAddress();

    void setSocketAddress(InetSocketAddress socketAddress);
}
