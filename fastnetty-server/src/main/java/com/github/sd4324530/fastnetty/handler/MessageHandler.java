package com.github.sd4324530.fastnetty.handler;

import com.github.sd4324530.fastnetty.core.message.FastNettyMessage;

/**
 * @author peiyu
 */
public interface MessageHandler<M extends FastNettyMessage> {

    void handler(M message, MessageSender sender);
}
