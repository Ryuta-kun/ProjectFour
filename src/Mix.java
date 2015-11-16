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
        LinkList<Character> m = new LinkList<Character>();
        for (int i = 0; i < message.length(); i ++){
            m.addAtEnd(message.charAt(i));
        }
        this.message = m;
        this.message.displayCounter();
        System.out.println();
        this.message.display();
    }

    @Override
    public String processCommand(String command) {
        if (command.charAt(0) == 'b'){
            int num = Integer.parseInt(command.substring(4));
            String str = this.message.change(command.charAt(2), num);
            return str;
        }else if (command.charAt(0) == 'r'){

        }else if (command.charAt(0) == 'x'){

        }else if (command.charAt(0) == 'p'){

        }else if (command.charAt(0) == 'c') {

        }else if (command.charAt(0) == 's'){

        }
        return null;
    }

    public static void main(String[] args){
        Mix m = new Mix();

        //displays the set of commands
        System.out.println("Here is the initial set of commands:");
        System.out.println("Q \t\t\t" + "Quit");
        System.out.println("b c # \t\t" + "means insert char 'c' before position #");
        System.out.println("r # \t\t" + "means remove a char at position #");
        System.out.println("x & # \t\t" + "means cut to clipboard, starting at & to # (inclusive)");
        System.out.println("p & # \t\t" + "means paste from clipboard, start at #");
        System.out.println("c & # \t\t" + "means copy to clipboard, starting at & to # (inclusive)");
        System.out.print("s filename \t" + "means, to save off the set ");
        System.out.println("of undo commands into text file named \"filename\".");

        //asks the user to enter the secret message
        System.out.println("\nEnter in initial message to mix up");
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();
        System.out.println("\nMessage: \n");
        m.setInitialMessage(message);

        //Asking user to enter the command
        System.out.print("\nCommand: ");
        message = sc.nextLine();

        if(!message.equals("Q")) {
            String overall = m.processCommand(message);
            System.out.println("\nMessage: \n");
            m.setInitialMessage(overall);

            while (!message.equals("Q")) {
                System.out.print("\nCommand: ");
                message = sc.nextLine();
                if (!message.equals("Q")) {
                    overall = m.processCommand(message);
                    System.out.println("\nMessage: \n");
                    m.setInitialMessage(overall);
                }
            }
            System.out.println("\nFinal message: " + overall);
        }else{
            System.out.println("Final message: " + message);
        }
    }
}
