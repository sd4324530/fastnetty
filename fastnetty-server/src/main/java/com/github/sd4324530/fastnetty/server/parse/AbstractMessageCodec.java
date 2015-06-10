package com.github.sd4324530.fastnetty.server.parse;

import com.github.sd4324530.fastnetty.core.message.FastNettyMessage;
import com.github.sd4324530.fastnetty.core.message.OutputMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.List;

/**
 * @author peiyu
 */
public abstract class AbstractMessageCodec<M extends FastNettyMessage> extends ByteToMessageCodec<M> {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractMessageCodec.class);

    @Override
    protected void encode(ChannelHandlerContext ctx, M msg, ByteBuf out) throws Exception {
        if(msg instanceof OutputMessage) {
            OutputMessage message = (OutputMessage) msg;
            ctx.channel().writeAndFlush(message.toBytes());
        } else {
            LOG.warn("output message's type error:{}", msg.getClass());
        }
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        out.add(in(in.nioBuffer()));
        in.clear();
    }

    protected abstract M in(ByteBuffer byteBuffer);


}
