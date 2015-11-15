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
        String userMessage = message.processCommand("b a 0");
        assertEquals("aThis is a secret message", userMessage);
    }

}
