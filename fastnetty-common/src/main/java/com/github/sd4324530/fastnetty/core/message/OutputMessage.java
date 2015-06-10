package com.github.sd4324530.fastnetty.core.message;

/**
 * @author peiyu
 */
public abstract class OutputMessage implements FastNettyMessage {
    public abstract byte[] toBytes();
}
