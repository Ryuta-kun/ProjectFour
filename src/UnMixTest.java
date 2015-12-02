import static org.junit.Assert.*;

import org.junit.Test;

/*************************************************************************
 * JUnit test for the unmix command. Although it has few test cases
 * we tried to enter bunch of random processcommands to see whether it
 * works or not.
 *
 * @author Ryuta Hirano
 * @author Lanndon Rose
 * @version November 2015
 ************************************************************************/
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
    public void testScrambleMessage() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        message.processCommand("b what 0");
        message.processCommand("x 4 8");
        message.processCommand("r 4");
        message.processCommand("r 4");
        message.processCommand("r 0");
        message.processCommand("b W 0");
        message.processCommand("b this_is 7");
        message.processCommand("c 6 13");
        message.processCommand("p 28");
        userMessage = message.processCommand("x 7 13");
        message.processCommand("s testIt2");

        UnMix unMessage = new UnMix();
        String original = unMessage.UnMixUsingFile("testIt2", userMessage);
        assertEquals(original, "This is a secret message");
    }

    @Test
    public void testScrambleMessage1() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        message.processCommand("w 1 21");
        message.processCommand("r 0");
        message.processCommand("b W 0");
        message.processCommand("w 21 2");
        message.processCommand("w 1 2");
        message.processCommand("w 3 15");
        message.processCommand("b n 6");
        message.processCommand("r 7");
        message.processCommand("b the 8");
        message.processCommand("c 0 0");
        message.processCommand("p 12");
        message.processCommand("r 11");
        message.processCommand("b _ 11");
        message.processCommand("b orld 13");
        message.processCommand("c 2 2");
        message.processCommand("p 18");
        message.processCommand("p 30");
        message.processCommand("r 31");
        message.processCommand("c 22 23");
        message.processCommand("p 19");
        message.processCommand("x 21 34");
        userMessage = message.processCommand("b _you_talking_about? 21");
        message.processCommand("s testIt2");

        UnMix unMessage = new UnMix();
        String original = unMessage.UnMixUsingFile("testIt2", userMessage);
        assertEquals(original, "This is a secret message");
    }

    @Test(expected = Exception.class)
    public void testException() {
       String userMessage = "This message";
        UnMix unMessage = new UnMix();
        String original = unMessage.UnMixUsingFile("test", userMessage);
    }

    @Test(expected = Exception.class)
    public void testException1() {
        String userMessage = "This message";
        UnMix unMessage = new UnMix();
        String original = unMessage.UnMixUsingFile("", userMessage);
    }

    @Test(expected = Exception.class)
    public void testException2() {
        String userMessage = "This message";
        UnMix unMessage = new UnMix();
        String original = unMessage.UnMixUsingFile("test.t", userMessage);
    }

    @Test(expected = Exception.class)
    public void testException3() {
        String userMessage = "";
        UnMix unMessage = new UnMix();
        String original = unMessage.UnMixUsingFile("testit", userMessage);
    }

    @Test(expected = Exception.class)
    public void testException4() {
        String userMessage = "       ";
        UnMix unMessage = new UnMix();
        String original = unMessage.UnMixUsingFile("testit", userMessage);
    }

    @Test
    public void superScramble() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        message.processCommand("w 1 21");
        message.processCommand("r 0");
        message.processCommand("b W 0");
        message.processCommand("w 21 2");
        message.processCommand("w 1 2");
        message.processCommand("w 3 15");
        message.processCommand("b n 6");
        message.processCommand("r 7");
        message.processCommand("b the 8");
        message.processCommand("c 0 0");
        message.processCommand("p 12");
        message.processCommand("r 11");
        message.processCommand("b _ 11");
        message.processCommand("b orld 13");
        message.processCommand("c 2 2");
        message.processCommand("p 18");
        message.processCommand("p 30");
        message.processCommand("r 31");
        message.processCommand("c 22 23");
        message.processCommand("p 19");
        message.processCommand("x 21 34");
        message.processCommand("b a 0");
        message.processCommand("c 0 20");
        message.processCommand("p 5");
        message.processCommand("x 10 14");
        message.processCommand("r 0");
        message.processCommand("r 0");
        message.processCommand("w 1 15");
        message.processCommand("w 10 8");
        message.processCommand("c 0 5");
        message.processCommand("c 10 15");
        message.processCommand("p 7");
        message.processCommand("p 0");
        message.processCommand("p 9");
        message.processCommand("x 0 15");
        message.processCommand("x 0 15");
        message.processCommand("b there 5");
        message.processCommand("b _hey_now 7");
        message.processCommand("r 1");
        message.processCommand("r 8");
        message.processCommand("w 1 15");
        message.processCommand("w 8 1");
        message.processCommand("b c 0");
        message.processCommand("b j 11");
        message.processCommand("x 5 8");
        message.processCommand("p 3");
        message.processCommand("p 9");
        message.processCommand("x 1 7");
        message.processCommand("w 9 11");
        message.processCommand("b c 0");
        message.processCommand("r 15");
        message.processCommand("c 10 15");
        message.processCommand("x 1 5");
        message.processCommand("p 1");
        message.processCommand("b there 0");
        message.processCommand("p 7");
        message.processCommand("b here 5");
        message.processCommand("p 4");
        userMessage = message.processCommand("b _you_talking_about? 21");
        message.processCommand("s testit3");

        UnMix unMessage = new UnMix();
        String original = unMessage.UnMixUsingFile("testit3", userMessage);
        assertEquals(original, "This is a secret message");
    }
}
