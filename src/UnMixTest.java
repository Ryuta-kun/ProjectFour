import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by Lanndon on 11/15/2015.
 */
public class UnMixTest {
    @Test
    public void testUndoProcessCommand0() {
        // Creates the file for testing,
        // this code could be removed if the file already exist
        Mix message = new Mix();
        message.setInitialMessage("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("b a 0");
        message.processCommand("s testIt");


        UnMix unMessage = new UnMix();
        String original = unMessage.UnMixUsingFile("testIt", userMessage);
        assertEquals(original, "This is a secret message");
    }
    @Test
    public void testUndoProcessCommand1() {
        Mix message = new Mix();
        message.setInitialMessage("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("b a 0");
        userMessage = message.processCommand("b a 0");
        userMessage = message.processCommand("b a 0");
        userMessage = message.processCommand("b a 0");
        message.processCommand("s testIt1");


        UnMix unMessage = new UnMix();
        String original = unMessage.UnMixUsingFile("testIt1", userMessage);
        assertEquals(original, "This is a secret message");
    }

    @Test
    public void testUndoProcessCommand3() {
        Mix message = new Mix();
        message.setInitialMessage("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("b a 4");
        userMessage = message.processCommand("b f 8");
        userMessage = message.processCommand("r 0");
        userMessage = message.processCommand("b B 9");
        message.processCommand("s testIt2");


        UnMix unMessage = new UnMix();
        String original = unMessage.UnMixUsingFile("testIt2", userMessage);
        assertEquals(original, "This is a secret message");
    }

    @Test
            (expected = Exception.class)
    public void testException() {
       String userMessage = "";
        UnMix unMessage = new UnMix();
        String original = unMessage.UnMixUsingFile("test", userMessage);

    }
}
