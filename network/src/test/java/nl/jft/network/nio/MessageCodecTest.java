package nl.jft.network.nio;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.nustaq.serialization.FSTConfiguration;

import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import nl.jft.network.EndPoint;
import nl.jft.network.util.mocks.FakeMessage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Lesley
 */
public class MessageCodecTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void construct_nullEndPoint_throwsException() {
        expectedException.expect(NullPointerException.class);

        MessageCodec codec = new MessageCodec(null);
    }

    @Test
    public void encode_whenCalled_encodesBuffer() throws Exception {
        // Step one, make a message to be encoded.
        FakeMessage stubMessage = new FakeMessage();
        stubMessage.content = "encoded string";

        ChannelHandlerContext stubCtx = mock(ChannelHandlerContext.class);
        ByteBuf stubBuffer = Unpooled.buffer();

        // Step two, encode the message.
        MessageCodec codec = makeMessageCodec();
        codec.encode(stubCtx, stubMessage, stubBuffer);

        // Step three, decode the message.
        byte[] data = new byte[stubBuffer.readInt()];
        stubBuffer.readBytes(data);
        FakeMessage decodedMessage = (FakeMessage) FSTConfiguration.getDefaultConfiguration().asObject(data);

        // Step four, see if the decoded message still contains the old data.
        String expected = "encoded string";
        String actual = decodedMessage.content;
        assertEquals(expected, actual);
    }

    @Test
    public void decode_notEnoughDataForLength_doesNotDecode() throws Exception {
        // Step one, write only one byte (4 bytes are expected).
        ChannelHandlerContext stubCtx = mock(ChannelHandlerContext.class);
        ByteBuf stubBuffer = Unpooled.buffer();
        stubBuffer.writeByte(2);

        // Step two, decode the message.
        List<Object> mockList = new ArrayList<>();
        MessageCodec codec = makeMessageCodec();
        codec.decode(stubCtx, stubBuffer, mockList);

        // Step three, see if the list is empty
        assertTrue(mockList.isEmpty());
    }

    @Test
    public void decode_notEnoughDataForContent_doesNotDecode() throws Exception {
        // Step one, write 4 bytes for the header, then write only one byte (2 bytes are expected).
        ChannelHandlerContext stubCtx = mock(ChannelHandlerContext.class);
        ByteBuf stubBuffer = Unpooled.buffer();
        stubBuffer.writeInt(2);
        stubBuffer.writeByte(1);

        // Step two, decode the message.
        List<Object> mockList = new ArrayList<>();
        MessageCodec codec = makeMessageCodec();
        codec.decode(stubCtx, stubBuffer, mockList);

        // Step three, see if the list is empty
        assertTrue(mockList.isEmpty());
    }

    @Test
    public void decode_whenCalled_decodesBuffer() throws Exception {
        // Step one, make a message to be decoded.
        FakeMessage stubMessage = new FakeMessage();
        stubMessage.content = "encoded string";
        byte[] data = FSTConfiguration.getDefaultConfiguration().asByteArray(stubMessage);

        // Step two, encode the message.
        ChannelHandlerContext stubCtx = mock(ChannelHandlerContext.class);
        ByteBuf stubBuffer = Unpooled.buffer();
        stubBuffer.writeInt(data.length);
        stubBuffer.writeBytes(data);

        // Step three, decode the message.
        List<Object> mockList = new ArrayList<>();
        MessageCodec codec = makeMessageCodec();
        codec.decode(stubCtx, stubBuffer, mockList);
        FakeMessage decodedMessage = (FakeMessage) mockList.get(0);

        // Step four, see if the decoded message still contains the old data.
        String expected = "encoded string";
        String actual = decodedMessage.content;
        assertEquals(expected, actual);
    }

    private MessageCodec makeMessageCodec() {
        return new MessageCodec(makeEndPoint());
    }

    private EndPoint makeEndPoint() {
        EndPoint endPoint = mock(EndPoint.class);
        when(endPoint.getFstConfiguration()).thenReturn(FSTConfiguration.createDefaultConfiguration());
        return endPoint;
    }

}
