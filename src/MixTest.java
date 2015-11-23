/**
 * Created by Lanndon on 11/15/2015.
 */
import static org.junit.Assert.*;

import org.junit.Test;

public class MixTest {

    @Test
    public void testProcessCommand() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("b a 0");
        assertEquals("aThis is a secret message", userMessage);
    }

    @Test
    public void testProcessCommand2() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("r 8");
        assertEquals("This is  secret message", userMessage);
    }

    @Test
    public void testProcessCommand3() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        message.processCommand("c 0 6");
        String userMessage = null;
        userMessage = message.processCommand("p 0");
        assertEquals("This isThis is a secret message", userMessage);
    }

    @Test
    public void testProcessCommand5() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("x 0 6");
        assertEquals(" a secret message", userMessage);
    }
}
