package com.github.sd4324530.fastnetty.server.impl;

import io.netty.bootstrap.AbstractBootstrap;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.logging.LoggingHandler;

/**
 * udp server
 *
 * @author peiyu
 */
public class NettyUDPServer extends AbstractNettyServer {

    @Override
    public ServerType getServerType() {
        return ServerType.UDP;
    }

    @Override
    public AbstractBootstrap createServerBootstrap() {
        this.serverBootstrap = new Bootstrap();
        this.serverBootstrap.group(new NioEventLoopGroup(this.workThreadSize));
        this.serverBootstrap.channel(NioDatagramChannel.class);
        this.serverBootstrap.handler(new ChannelInitializer<DatagramChannel>() {
            @Override
            protected void initChannel(DatagramChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                NettyMessageHandler messageHandler = new NettyMessageHandler();
                messageHandler.setHandlers(messageHandlers);
                pipeline.addLast(new LoggingHandler(), messageHandler, messageCodec);
                ALL_CHANNELS.add(ch);
            }
        });
        return this.serverBootstrap;
    }
}
