import java.util.Scanner;

/**
 * Created by Ryo-chan on 11/11/15.
 */
public class Mix implements IMix {
    private LinkList<Character> message;

    public Mix(){
        message = new LinkList<Character>();
    }

    @Override
    public void setInitialMessage(String message) {
        for (int i = 0; i < message.length(); i ++){
            this.message.addAtEnd(message.charAt(i));
        }
        this.message.displayCounter();
        System.out.println();
        this.message.display();
    }

    @Override
    public String processCommand(String command) {
        return null;
    }

    public static void main(String[] args){
        Mix m = new Mix();
        System.out.println("Enter in initial message to mix up");
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();
        System.out.println("\nMessage: \n");
        m.setInitialMessage(message);

        //Asking user to enter the command
        System.out.print("\nCommand: ");
        Scanner com = new Scanner(System.in);
        String command = com.nextLine();
        m.processCommand(command);
    }
}
