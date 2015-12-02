/**
 * Created by Lanndon on 11/15/2015.
 */
import static org.junit.Assert.*;

import org.junit.Test;

/*************************************************************************
 * JUnit test for the mix command. We bunched up random codes and thought
 * about all the possible errors as we could.
 *
 * @author Ryuta Hirano
 * @author Lanndon Rose
 * @version November 2015
 ************************************************************************/
public class MixTest {

    @Test
    public void testBCommand() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("b a 0");
        assertEquals("aThis is a secret message", userMessage);
    }

    @Test
    public void testBCommand1() {
        Mix message = new Mix();
        message.setInitialMessage ("hello");
        String userMessage = null;
        userMessage = message.processCommand("b _ 5");
        assertEquals("hello ", userMessage);

        userMessage = message.processCommand("b there 6");
        assertEquals("hello there", userMessage);

        userMessage = message.processCommand("b _this 11");
        assertEquals("hello there this", userMessage);

        userMessage = message.processCommand("b _is_Mikagura 16");
        assertEquals("hello there this is Mikagura", userMessage);
    }

    @Test(expected = Exception.class)
    public void testBCommandError() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("b a -1");
    }

    @Test(expected = Exception.class)
    public void testBCommandError2() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("b 12 a");
    }
    @Test(expected = Exception.class)
    public void testBCommandError3() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("b -1 a");
    }
    @Test
    public void testAcceptableInputBCommand() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("b a 0  ");
        assertEquals("aThis is a secret message", userMessage);
    }
    @Test(expected = Exception.class)
    public void testBCommandError6() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("ba0");
    }

    @Test(expected = Exception.class)
    public void testBCommandError7() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("ba 0");
    }

    @Test(expected = Exception.class)
    public void testBCommandError8() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("b a0");
    }

    @Test(expected = Exception.class)
    public void testBCommandError9() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand(" b a 0");
        assertEquals("aThis is a secret message", userMessage);
    }

    @Test(expected = Exception.class)
    public void testBCommandError10() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand(" b  0");
        assertEquals("aThis is a secret message", userMessage);
    }

    @Test
    public void testRemove() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("r 8");
        assertEquals("This is  secret message", userMessage);
    }

    @Test
    public void testRemove2() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("r 0");
        assertEquals("his is a secret message", userMessage);

        userMessage = message.processCommand("r 0");
        assertEquals("is is a secret message", userMessage);

        userMessage = message.processCommand("r 0");
        assertEquals("s is a secret message", userMessage);

        userMessage = message.processCommand("r 0");
        assertEquals(" is a secret message", userMessage);

        userMessage = message.processCommand("r 0");
        assertEquals("is a secret message", userMessage);

        userMessage = message.processCommand("r 0");
        assertEquals("s a secret message", userMessage);

        userMessage = message.processCommand("r 0");
        assertEquals(" a secret message", userMessage);

        userMessage = message.processCommand("r 0");
        assertEquals("a secret message", userMessage);

        userMessage = message.processCommand("r 0");
        assertEquals(" secret message", userMessage);

        userMessage = message.processCommand("r 0");
        assertEquals("secret message", userMessage);
    }

    @Test(expected = Exception.class)
    public void testRemoveError() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("r 100");
    }
    @Test(expected = Exception.class)
    public void testRemoveError1() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("r -1");
    }

    @Test(expected = Exception.class)
    public void testRemoveError2() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("r8");
    }

    @Test(expected = Exception.class)
    public void testRemoveError3() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("r 8  ");
    }

    @Test
    public void testSwap() {
        Mix message = new Mix();
        message.setInitialMessage ("secret message");
        String userMessage = null;
        userMessage = message.processCommand("w 0 5");
        assertEquals("tecres message", userMessage);
    }

    @Test
    public void testSwap2() {
        Mix message = new Mix();
        message.setInitialMessage ("secret message");
        String userMessage = null;
        userMessage = message.processCommand("w 0 5");
        assertEquals("tecres message", userMessage);

        userMessage = message.processCommand("w 11 1");
        assertEquals("tacres messege", userMessage);
    }

    @Test(expected = Exception.class)
    public void testSwapError() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("w0 5");
    }

    @Test(expected = Exception.class)
    public void testSwapError1() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("w -1 5");
    }

    @Test(expected = Exception.class)
    public void testSwapError2() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("w 0 -1");
    }

    @Test(expected = Exception.class)
    public void testSwapError3() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("w 100 6");
    }

    @Test(expected = Exception.class)
    public void testSwapError5() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("w01");
    }

    @Test(expected = Exception.class)
    public void testSwapError6() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("w");
    }

    @Test(expected = Exception.class)
    public void testSwapError7() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand(" w 1100");
    }

    @Test
    public void testClipBoardCommands() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        message.processCommand("c 0 6");
        String userMessage = null;
        userMessage = message.processCommand("p 0");
        assertEquals("This isThis is a secret message", userMessage);
    }

    @Test
    public void testClipBoardCommands2() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("x 0 6");
        assertEquals(" a secret message", userMessage);

        userMessage = message.processCommand("p 17");
        assertEquals(" a secret messageThis is", userMessage);
    }

    @Test(expected = Exception.class)
    public void testClipBoardError() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("x 0 -6");
    }

    @Test(expected = Exception.class)
    public void testClipBoardError1() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("x 0 100");
    }

    @Test(expected = Exception.class)
    public void testClipBoardError2() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("x -1 6");
    }

    @Test(expected = Exception.class)
    public void testClipBoardError3() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("x -1 -2");
    }

    @Test(expected = Exception.class)
    public void testClipBoardError4() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("x 100 100");
    }

    @Test(expected = Exception.class)
    public void testClipBoardError5() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("x 6 0");
    }

    @Test(expected = Exception.class)
    public void testClipBoardError6() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("x 0 6");
        assertEquals(" a secret message", userMessage);

        userMessage = message.processCommand("p -1");
    }

    @Test(expected = Exception.class)
    public void testClipBoardError7() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("x 0 6");
        assertEquals(" a secret message", userMessage);

        userMessage = message.processCommand("p");
    }

    @Test(expected = Exception.class)
    public void testClipBoardError8() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("x 0 6");
        assertEquals(" a secret message", userMessage);

        userMessage = message.processCommand("p0");
        assertEquals(" a secret messageThis is", userMessage);
    }

    @Test(expected = Exception.class)
    public void testClipBoardError9() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("x 0 6");
        assertEquals(" a secret message", userMessage);

        userMessage = message.processCommand("p 100");
    }

    @Test(expected = Exception.class)
    public void nonExistingCommand() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("J 0 6");
    }


    @Test(expected = Exception.class)
    public void nonExistingCommand2() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("A 0 6");
    }

    @Test(expected = Exception.class)
    public void nonExistingCommand3() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = null;
        userMessage = message.processCommand("hello");
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

        assertEquals("What a secret message this is", userMessage);
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

        assertEquals("What in the World are you talking about?", userMessage);
    }
}
