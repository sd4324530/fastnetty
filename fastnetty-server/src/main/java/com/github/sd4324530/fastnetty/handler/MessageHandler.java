package com.github.sd4324530.fastnetty.handler;

/**
 * @author peiyu
 */
public interface MessageHandler {

    void handler(byte[] bytes, MessageSender sender);
}
