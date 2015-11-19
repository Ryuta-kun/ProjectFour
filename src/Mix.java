import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by Ryo-chan on 11/11/15.
 */
public class Mix implements IMix {
    private LinkList<Character> message;
    private String commandos;
    private String clipBoard;

    public Mix(){
        message = new LinkList<Character>();
        commandos = "";
        clipBoard = "";
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
            setCommandos(command);
            return str;
        }else if (command.charAt(0) == 'r'){
            int numr = Integer.parseInt(command.substring(2));
            String str2 = this.message.delete(numr);
            setCommandos(command);
            return str2;
        }else if (command.charAt(0) == 'x'){
            String str = "";
            clipBoard = "";
            String [] values = command.split(" ");    //couldn't find another way to do it so
            int start = Integer.parseInt(values[1]);  //should work for now. need another way to split
            int end = Integer.parseInt(values[2]);    //these commands.
            for (int i = start; i <= end; i++){
                str = this.message.delete(start);
                clipBoard += this.message.getRemoved().getData().toString();
            }
            setCommandos(command);
            return str;
        }else if (command.charAt(0) == 'p'){
            String str = "";
            int start = Integer.parseInt(command.substring(2));
            if (!clipBoard.equals("")){
                System.out.println(clipBoard);
                for (int i = 0; i < clipBoard.length(); i ++){
                    str = this.message.change(clipBoard.charAt(i), start);
                    start++;
                }
                setCommandos(command);
                return str;
            }else {
                return this.message.toString();
            }
        }else if (command.charAt(0) == 'c') {
            clipBoard = "";
            String [] values = command.split(" ");    //couldn't find another way to do it so
            int start = Integer.parseInt(values[1]);  //should work for now. need another way to split
            int end = Integer.parseInt(values[2]);    //these commands.
            String str = this.message.copy(start, end);
            clipBoard = str;
            return clipBoard;
        }else if (command.charAt(0) == 's'){
            String str3 = getCommandos();
            save(str3, command.substring(2));
            return str3;
        }
        return null;
    }

    public void save (String str, String filename) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".txt")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        out.print(str);
        out.close();
    }


    public String getCommandos() {
        return commandos;
    }

    public void setCommandos(String commandos) {
        String str = "";
        if (commandos.charAt(0) == 'b'){
            str = "r " + commandos.substring(4);
        }else if (commandos.charAt(0) == 'r'){
            str = "b " + message.getRemoved().getData() + " " + commandos.substring(2);
        }else if (commandos.charAt(0) == 'x'){
            str = "p " + commandos.substring(2,3);
        }else if (commandos.charAt(0) == 'p'){
            int num = Integer.parseInt(commandos.substring(2)) + clipBoard.length() - 1;
            str = "x " + commandos.substring(2) + " " + num;
        }
        this.commandos += str + "\n";
    }

    public static void main(String[] args){
        Mix m = new Mix();
        String overall = "";

        //displays the set of commands
        System.out.println("Here is the initial set of commands:");
        System.out.println("Q \t\t\t" + "Quit");
        System.out.println("b c # \t\t" + "means insert char 'c' before position #");
        System.out.println("r # \t\t" + "means remove a char at position #");
        System.out.println("x & # \t\t" + "means cut to clipboard, starting at & to # (inclusive)");
        System.out.println("p # \t\t" + "means paste from clipboard, start at #");
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
            if (cmessage.charAt(0) == 'c'){
                m.processCommand(cmessage);
            }else {
                overall = m.processCommand(cmessage);
                System.out.println("\nMessage: \n");
                m.setInitialMessage(overall);
            }
            while (!cmessage.equals("Q")) {
                System.out.print("\nCommand: ");
                cmessage = sc.nextLine();
                if (cmessage.charAt(0) == 's'){
                    m.processCommand(cmessage);
                    //String saves = m.processCommand(cmessage);
                    //System.out.println(saves);
                }else if (cmessage.charAt(0) == 'c'){
                    m.processCommand(cmessage);
                } else if (!cmessage.equals("Q")) {
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
