package nl.jft.network.nio;

import java.util.List;
import java.util.Objects;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import nl.jft.network.EndPoint;
import nl.jft.network.message.Message;

/**
 * A {@code MessageCodec} handles the encoding/decoding of a {@link Message} into a {@link ByteBuf}.
 *
 * @author Lesley
 */
final class MessageCodec extends ByteToMessageCodec<Message> {

    private final EndPoint endPoint;

    /**
     * Initializes this {@code MessageCodec} with the given {@link EndPoint}.
     *
     * @param endPoint The {@code EndPoint} that this {@code MessageCodec} uses
     *                 to serialize {@code Messages}, should not be {@code null}.
     * @throws NullPointerException If the given {@code EndPoint} is {@code null}.
     */
    MessageCodec(EndPoint endPoint) {
        this.endPoint = Objects.requireNonNull(endPoint);
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Message message, ByteBuf buf) throws Exception {
        byte[] data = endPoint.getFstConfiguration().asByteArray(message);
        int length = data.length;

        buf.writeInt(length);
        buf.writeBytes(data, 0, length);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> list) throws Exception {
        if (buf.readableBytes() < 4) {
            return;
        }

        buf.markReaderIndex();
        int length = buf.readInt();

        if (buf.readableBytes() < length) {
            buf.resetReaderIndex();
            return;
        }

        byte[] data = new byte[length];
        buf.readBytes(data, 0, length);

        Message message = (Message) endPoint.getFstConfiguration().asObject(data);
        list.add(message);
    }

}
