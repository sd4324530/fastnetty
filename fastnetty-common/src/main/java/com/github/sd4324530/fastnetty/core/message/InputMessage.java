package com.github.sd4324530.fastnetty.core.message;

import java.nio.ByteBuffer;

/**
 * @author peiyu
 */
public interface InputMessage extends FastNettyMessage {
    InputMessage fromBytes(ByteBuffer byteBuffer);
}
