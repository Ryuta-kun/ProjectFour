import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by Lanndon on 11/15/2015.
 */
public class UnMixTest {
    @Test
    public void testUndoProcessCommand() {
        // Creates the file for testing,
        // this code could be removed if the file already exist
        Mix message = new Mix();
        message.setInitialMessage("This is a secret message");
        String userMessage = null;
        try {
            userMessage = message.processCommand("b a 0");
        } catch (Exception e) {
            System.out.println("Command Unknown");
        }
        try {
            message.processCommand("s testIt");
        } catch (Exception e) {
            System.out.println("Command Unknown");
        }


        UnMix unMessage = new UnMix();
        String original = unMessage.UnMixUsingFile("testIt", userMessage);
        assertEquals(original, "This is a secret message");
    }
}
