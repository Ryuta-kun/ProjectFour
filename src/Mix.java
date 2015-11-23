import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.IllegalFormatException;
import java.util.Scanner;

/**
 * Created by Ryo-chan on 11/11/15.
 */
public class Mix implements IMix {
    private LinkList<Character> message;
    private String commandos;
    public static String clipBoard;

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
    }

    @Override
    public String processCommand(String command){
            if (command.charAt(0) == 'b') {
                try {
                    int num = Integer.parseInt(command.substring(4));
                    String str = this.message.change(command.charAt(2), num);
                    setCommandos(command);
                    return str;
                }catch(Exception b){
                    throw b;
                }
            } else if (command.charAt(0) == 'r') {
                try {
                    int numr = Integer.parseInt(command.substring(2));
                    String str2 = this.message.delete(numr);
                    setCommandos(command);
                    return str2;
                }catch(Exception b){
                    throw b;
                }
            } else if (command.charAt(0) == 'x') {
                try {
                    String str = "";
                    clipBoard = "";
                    String[] values = command.split(" ");    //couldn't find another way to do it so
                    int start = Integer.parseInt(values[1]);  //should work for now. need another way to split
                    int end = Integer.parseInt(values[2]);    //these commands.
                    for (int i = start; i <= end; i++) {
                        str = this.message.delete(start);
                        clipBoard += this.message.getRemoved().getData().toString();
                    }
                    setCommandos(command);
                    return str;
                }catch(Exception b){
                    throw b;
                }
            } else if (command.charAt(0) == 'p') {
                try {
                    String str = "";
                    int start = Integer.parseInt(command.substring(2));
                    if (!clipBoard.equals("")) {
                        System.out.println(clipBoard);
                        for (int i = 0; i < clipBoard.length(); i++) {
                            str = this.message.change(clipBoard.charAt(i), start);
                            start++;
                        }
                        setCommandos(command);
                        return str;
                    } else {
                        return this.message.toString();
                    }
                }catch(Exception b){
                    throw b;
                }
            } else if (command.charAt(0) == 'c') {
                try {
                    clipBoard = "";
                    String[] values = command.split(" ");    //couldn't find another way to do it so
                    int start = Integer.parseInt(values[1]);  //should work for now. need another way to split
                    int end = Integer.parseInt(values[2]);    //these commands.
                    String str = this.message.copy(start, end);
                    clipBoard = str;
                    return clipBoard;
                }catch(Exception b){
                    throw b;
                }
            } else if (command.charAt(0) == 's') {
                if (command.charAt(1) != ' ') {
                    throw new IllegalArgumentException();
                }else {
                    try {
                        String str3 = getCommandos();
                        save(str3, command.substring(2));
                        return str3;
                    } catch (Exception e) {
                        throw e;
                    }
                }
            }
        return null;
    }

    public void showMessage(){
        this.message.displayCounter();
        System.out.println();
        this.message.display();
    }

    public void save (String str, String filename) {
        if (!filename.contains(".txt")){
            filename = filename + ".txt";
        }
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        out.print(str);
        out.close();

        //Collections.reverse(out);
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
        System.out.println("' ' \t\t" + "to insert a space into the message three spaces must be used for one space");

        System.out.print("s filename \t" + "means, to save off the set ");
        System.out.println("of undo commands into text file named \"filename\".");


        //asks the user to enter the secret message
        System.out.println("\nEnter in initial message to mix up");
        Scanner sc = new Scanner(System.in);
        String imessage = sc.nextLine();
        System.out.println("\nMessage: \n");
        m.setInitialMessage(imessage);
        m.showMessage();

        //Asking user to enter the command
        System.out.print("\nCommand: ");
        String cmessage = sc.nextLine();

        if(!cmessage.equals("Q")) {
            if (cmessage.charAt(0) == 'c'){
                try {
                    m.processCommand(cmessage);
                } catch (Exception e) {
                    System.out.println("Command Unknown");
                }
            }else {
                try {
                    overall = m.processCommand(cmessage);
                    System.out.println("\nMessage: \n");
                    m.setInitialMessage(overall);
                    m.showMessage();
                } catch (Exception e) {
                    System.out.println("Command Unknown");
                }
            }
            while (!cmessage.equals("Q")) {
                System.out.print("\nCommand: ");
                cmessage = sc.nextLine();
                if (cmessage.charAt(0) == 's'){
                    try {
                        m.processCommand(cmessage);
                    } catch (Exception e) {
                        System.out.println("Command Unknown");
                    }
                }else if (cmessage.charAt(0) == 'c'){
                    try {
                        m.processCommand(cmessage);
                    } catch (Exception e) {
                        System.out.println("Command Unknown");
                    }
                } else if (!cmessage.equals("Q")) {
                    try {
                        overall = m.processCommand(cmessage);
                        System.out.println("\nMessage: \n");
                        m.setInitialMessage(overall);
                        m.showMessage();
                    } catch (Exception e) {
                        System.out.println("Command Unknown");
                    }
                }
            }
            System.out.println("\nFinal message: " + overall);
        }else{
            System.out.println("Final message: " + imessage);
        }
    }
}
