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
        try {
            userMessage = message.processCommand("b a 0");
        } catch (Exception e) {
            System.out.println("Command Unknown");
        }
        assertEquals("aThis is a secret message", userMessage);
    }

    @Test
    public void testProcessCommand2() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        try {
            userMessage = message.processCommand("r 8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("This is  secret message", userMessage);
    }

    @Test
    public void testProcessCommand3() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        try {
            message.processCommand("c 0 6");
        } catch (Exception e) {
            System.out.println("Command Unknown");
        }
        String userMessage = null;
        try {
            userMessage = message.processCommand("p 0");
        } catch (Exception e) {
            System.out.println("Command Unknown");

        }
        assertEquals("This isThis is a secret message", userMessage);
    }

    @Test
    public void testProcessCommand5() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        try {
            userMessage = message.processCommand("x 0 6");
        } catch (Exception e) {
            System.out.println("Command Unknown");
        }
        assertEquals(" a secret message", userMessage);
    }
}
