package com.github.sd4324530.fastnetty.handler;

import java.nio.ByteBuffer;

/**
 * @author peiyu
 */
public interface MessageHandler {

    void handler(ByteBuffer byteBuffer, MessageSender sender);
}
