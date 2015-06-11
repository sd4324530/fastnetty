package com.github.sd4324530.fastnetty.server.impl;

import io.netty.bootstrap.AbstractBootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;

/**
 * tcp server
 *
 * @author peiyu
 */
public class NettyTCPServer extends AbstractNettyServer {

    /**
     * 用于分配处理业务线程的线程组个数
     */
    private static final int            BIZ_GROUP_SIZE = Runtime.getRuntime().availableProcessors() * 2;    //默认
    private final        EventLoopGroup BOSS_GROUP     = new NioEventLoopGroup(BIZ_GROUP_SIZE);

    @Override
    public ServerType getServerType() {
        return ServerType.TCP;
    }

    @Override
    public AbstractBootstrap createServerBootstrap() {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(BOSS_GROUP, new NioEventLoopGroup(this.workThreadSize));
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                NettyMessageHandler messageHandler = new NettyMessageHandler();
                messageHandler.setHandlers(messageHandlers);
                pipeline.addLast(new LoggingHandler(), messageHandler, messageCodec);
                ALL_CHANNELS.add(ch);
            }
        });
        this.serverBootstrap = bootstrap;
        return this.serverBootstrap;
    }
}
