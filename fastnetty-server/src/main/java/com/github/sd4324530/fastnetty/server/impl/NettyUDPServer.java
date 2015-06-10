package com.github.sd4324530.fastnetty.server.impl;

import io.netty.bootstrap.ServerBootstrap;

import java.net.InetSocketAddress;

/**
 * @author peiyu
 */
public class NettyUDPServer extends AbstractNettyServer {

    @Override
    public ServerType getServerType() {
        return null;
    }

    @Override
    public ServerBootstrap createServerBootstrap() {
        return null;
    }

    @Override
    public void startServer() throws Exception {

    }

    @Override
    public void startServer(int port) throws Exception {

    }

    @Override
    public void startServer(InetSocketAddress socketAddress) throws Exception {

    }
}
