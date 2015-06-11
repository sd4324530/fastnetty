package com.github.sd4324530.fastnetty.server.impl;

import com.github.sd4324530.fastnetty.handler.MessageHandler;
import com.github.sd4324530.fastnetty.handler.MessageSender;
import com.github.sd4324530.fastnetty.handler.SimpleMessageSender;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.Set;

/**
 * @author peiyu
 */
class NettyMessageHandler extends SimpleChannelInboundHandler<ByteBuffer> {

    private static final Logger LOG = LoggerFactory.getLogger(NettyMessageHandler.class);

    private Set<MessageHandler> handlers;

    public void setHandlers(Set<MessageHandler> handlers) {
        this.handlers = handlers;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuffer byteBuffer) throws Exception {
        if (null != this.handlers && !this.handlers.isEmpty()) {
            MessageSender sender = new SimpleMessageSender(ctx.channel());
            for (MessageHandler handler : this.handlers) {
                try {
                    handler.handler(byteBuffer, sender);
                } catch (Exception e) {
                    LOG.warn("handler exception..", e);
                } finally {
                    byteBuffer.rewind();
                }
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOG.error("channel error..", cause);
    }
}
