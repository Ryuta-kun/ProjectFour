import java.util.Scanner;

/**
 * Created by Ryo-chan on 11/11/15.
 */
public class Mix implements IMix {
    private LinkList message;

    public Mix(){
        message = new LinkList();
    }

    @Override
    public void setInitialMessage(String message) {

    }

    @Override
    public String processCommand(String command) {
        return null;
    }

    public static void main(String[] args){
        System.out.println("Enter in initial message to mix up");
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();
        System.out.println("\nMessage: \n");
        System.out.println("Command: ");
        sc = new Scanner(System.in);
    }
}
