/**
 * Created by Lanndon on 11/15/2015.
 */
public interface IMix {
    /** set the original message into the linked list of characters */
    void setInitialMessage(String message);

    /** processes the given mix command and returns the current message after processing the mix command */
    String processCommand(String command) throws Exception;
}
