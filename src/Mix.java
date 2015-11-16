import java.util.Scanner;

/**
 * Created by Ryo-chan on 11/11/15.
 */
public class Mix implements IMix {
    private LinkList<Character> message;
    private String commandos;

    public Mix(){
        message = new LinkList<Character>();
        commandos = "";

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
            int numr = Integer.parseInt(command.substring(2));
            String str2 = this.message.delete(numr);
            return str2;
        }else if (command.charAt(0) == 'x'){

        }else if (command.charAt(0) == 'p'){

        }else if (command.charAt(0) == 'c') {

        }else if (command.charAt(0) == 's'){
            String str3 = getCommandos();
            return str3;
        }
        return null;
    }

    public String getCommandos() {
        return commandos;
    }

    public void setCommandos(String commandos) {
        this.commandos += "\n" + commandos;
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
        String imessage = sc.nextLine();
        System.out.println("\nMessage: \n");
        m.setInitialMessage(imessage);

        //Asking user to enter the command
        System.out.print("\nCommand: ");
        String cmessage = sc.nextLine();

        if(!cmessage.equals("Q")) {
            m.setCommandos(cmessage);
            String overall = m.processCommand(cmessage);
            System.out.println("\nMessage: \n");
            m.setInitialMessage(overall);

            while (!cmessage.equals("Q")) {
                System.out.print("\nCommand: ");
                cmessage = sc.nextLine();
                if (cmessage.charAt(0) == 's'){
                    String saves = m.processCommand(cmessage);
                    System.out.println(saves);
                }else if (!cmessage.equals("Q")) {
                    m.setCommandos(cmessage);
                    overall = m.processCommand(cmessage);
                    System.out.println("\nMessage: \n");
                    m.setInitialMessage(overall);
                }
            }
            System.out.println("\nFinal message: " + overall);
        }else{
            System.out.println("Final message: " + imessage);
        }
    }
}
