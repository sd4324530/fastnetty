package com.github.sd4324530.fastnetty.server.impl;

import com.github.sd4324530.fastnetty.server.NettyServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.ChannelGroupFuture;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * @author peiyu
 */
public abstract class AbstractNettyServer implements NettyServer {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractNettyServer.class);

    protected ServerBootstrap serverBootstrap;

    protected InetSocketAddress socketAddress;

    public static final ChannelGroup ALL_CHANNELS = new DefaultChannelGroup("FASTNETTY-CHANNELS", GlobalEventExecutor.INSTANCE);

    @Override
    public void stopServer() throws Exception {
        LOG.debug("stop the server:{}", getClass().getName());
        ChannelGroupFuture future = ALL_CHANNELS.close();
        try {
            future.await();
        } catch (InterruptedException e) {
            LOG.error("error", e);
        }
    }

    @Override
    public InetSocketAddress getSocketAddress() {
        return this.socketAddress;
    }
}
