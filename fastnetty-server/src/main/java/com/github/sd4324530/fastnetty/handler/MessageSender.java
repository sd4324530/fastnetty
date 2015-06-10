package com.github.sd4324530.fastnetty.handler;

import com.github.sd4324530.fastnetty.core.message.FastNettyMessage;

/**
 * @author peiyu
 */
public interface MessageSender {

    void send(FastNettyMessage message);
}
