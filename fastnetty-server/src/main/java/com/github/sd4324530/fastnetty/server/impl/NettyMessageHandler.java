package com.github.sd4324530.fastnetty.server.impl;

import com.github.sd4324530.fastnetty.core.message.FastNettyMessage;
import com.github.sd4324530.fastnetty.handler.MessageHandler;
import com.github.sd4324530.fastnetty.handler.MessageSender;
import com.github.sd4324530.fastnetty.handler.SimpleMessageSender;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

/**
 * @author peiyu
 */
class NettyMessageHandler extends SimpleChannelInboundHandler<FastNettyMessage> {

    private List<MessageHandler> handlerList;

    public void setHandlerList(List<MessageHandler> handlerList) {
        this.handlerList = handlerList;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FastNettyMessage msg) throws Exception {
        if (null != this.handlerList && !this.handlerList.isEmpty()) {
            MessageSender sender = new SimpleMessageSender(ctx.channel());
            for (MessageHandler handler : this.handlerList) {
                try {
                    handler.handler(msg, sender);
                } catch (Exception ignored) {
                }
            }
        }
    }
}
