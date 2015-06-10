package com.github.sd4324530.fastnetty.core.message;

import java.nio.ByteBuffer;

/**
 * @author peiyu
 */
public abstract class InputMessage implements FastNettyMessage {
    public abstract void fromBytes(ByteBuffer byteBuffer);
}
