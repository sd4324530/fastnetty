package com.github.sd4324530.fastnetty.server.impl;

import com.github.sd4324530.fastnetty.handler.MessageHandler;
import com.github.sd4324530.fastnetty.server.parse.AbstractMessageCodec;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @author peiyu
 */
public class NettyTCPServer extends AbstractNettyServer {

    private static final Logger         LOG            = LoggerFactory.getLogger(NettyTCPServer.class);
    /**
     * 用于分配处理业务线程的线程组个数
     */
    private static final int            BIZGROUPSIZE   = Runtime.getRuntime().availableProcessors() * 2;    //默认
    private              int            workThreadSize = 4;
    private static final EventLoopGroup bossGroup      = new NioEventLoopGroup(BIZGROUPSIZE);

    private AbstractMessageCodec messageCodec;
    private List<MessageHandler> messageHandlerList;

    public void setMessageCodec(AbstractMessageCodec messageCodec) {
        this.messageCodec = messageCodec;
    }

    public void setMessageHandlerList(List<MessageHandler> messageHandlerList) {
        this.messageHandlerList = messageHandlerList;
    }

    public void setWorkThreadSize(int size) {
        this.workThreadSize = size;
    }

    @Override
    public ServerType getServerType() {
        return ServerType.TCP;
    }

    @Override
    public ServerBootstrap createServerBootstrap() {
        this.serverBootstrap = new ServerBootstrap();
        this.serverBootstrap.group(bossGroup, new NioEventLoopGroup(this.workThreadSize));
        this.serverBootstrap.channel(NioServerSocketChannel.class);
        this.serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                NettyMessageHandler messageHandler = new NettyMessageHandler();
                messageHandler.setHandlerList(messageHandlerList);
                pipeline.addLast(new LoggingHandler(), messageHandler, messageCodec);
            }
        });
        return this.serverBootstrap;
    }

    @Override
    public void startServer() throws Exception {
        startServer(49152);
    }

    @Override
    public void startServer(int port) throws Exception {
        this.socketAddress = new InetSocketAddress(port);
        startServer(this.socketAddress);
    }

    @Override
    public void startServer(InetSocketAddress socketAddress) throws Exception {
        this.socketAddress = socketAddress;
        LOG.debug("启动服务器.....监听端口:{}", socketAddress.getPort());
        this.serverBootstrap.bind(this.socketAddress.getPort()).sync();
    }
}
