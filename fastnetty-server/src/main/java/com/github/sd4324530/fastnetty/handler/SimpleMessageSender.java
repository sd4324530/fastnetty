package com.github.sd4324530.fastnetty.handler;

import com.github.sd4324530.fastnetty.core.message.OutputMessage;
import io.netty.channel.Channel;

/**
 * @author peiyu
 */
public class SimpleMessageSender implements MessageSender {

    private final Channel channel;

    public SimpleMessageSender(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void send(OutputMessage message) {
        if(null != this.channel && this.channel.isOpen()) {
            this.channel.writeAndFlush(message);
        }
    }
}
