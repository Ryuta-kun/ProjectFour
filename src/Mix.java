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
    protected String clipBoard;

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
    }

    @Override
    public String processCommand(String command){
        if (command.charAt(0) == 'b') {
            try {
                String str = "";
                String undo = "";
                LinkList<String> com = new LinkList<String>();
                for (String retval : command.split(" ")){
                    if (retval.contains("_")){
                        retval = retval.replace('_', ' ');
                    }
                    com.addAtEnd(retval);

                }
                int num = Integer.parseInt(com.readList(2).getData());
                for (int i = 0; i < com.readList(1).getData().length(); i++){
                    str = this.message.change(com.readList(1).getData().charAt(i), num++);
                }

                if(com.readList(1).getData().length() > 1){
                    int number = Integer.parseInt(com.readList(2).getData()) + (com.readList(1).getData().length() - 1);
                    undo = "x " + Integer.parseInt(com.readList(2).getData()) + " " + number;
                }else{
                    undo = "r " + Integer.parseInt(com.readList(2).getData());
                }

                setCommandos(undo);
                return str;
            }catch(Exception b){
                throw b;
            }
        } else if (command.charAt(0) == 'r') {
            try {
                int numr = Integer.parseInt(command.substring(2));
                String str2 = this.message.delete(numr);
                char deleted = message.getRemoved().getData();
                if (deleted == ' '){
                    deleted = '_';
                }
                String undo = "b " + deleted + " " + numr;
                setCommandos(undo);
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
                if ((this.message.count() - 1) >= end) {
                    for (int i = start; i <= end; i++) {
                        str = this.message.delete(start);
                        clipBoard += this.message.getRemoved().getData().toString();
                    }


                    if (clipBoard.contains(" ")) {
                        clipBoard = clipBoard.replace(' ', '_');
                    }
                    String undo = "b " + clipBoard + " " + start;
                    setCommandos(undo);
                    return str;
                }else{
                    System.err.println("Command Unknown");
                    return this.message.toString();
                }
            }catch(Exception b){
                throw b;
            }
        } else if (command.charAt(0) == 'p') {
            try {
                String str = "";
                int start = Integer.parseInt(command.substring(2));
                if (!clipBoard.equals("")) {
                    for (int i = 0; i < clipBoard.length(); i++) {
                        str = this.message.change(clipBoard.charAt(i), start);
                        start++;
                    }

                    int num = Integer.parseInt(command.substring(2)) + clipBoard.length() - 1;
                    String undo = "x " + Integer.parseInt(command.substring(2)) + " " + num;
                    setCommandos(undo);
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
            if (command.charAt(2) == ' ') {
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
        } else if(command.charAt(0) == 'w'){
            try{
                String[] values = command.split(" ");    //couldn't find another way to do it so
                int num1 = Integer.parseInt(values[1]);  //should work for now. need another way to split
                int num2 = Integer.parseInt(values[2]);    //these commands.
                String str = "";
                str = this.message.swapChar(num1, num2);
                return str;
            }catch (Exception e) {
                throw e;
            }
        }
        return this.message.toString();
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
    }

    public LinkList<Character> getMessage() {
        return message;
    }

    public String getCommandos() {
        return commandos;
    }

    public void setCommandos(String commandos) {
        this.commandos += commandos + "\n";
    }

    public static void main(String[] args){
        Mix m = new Mix();

        //displays the set of commands
        System.out.println("Here is the initial set of commands:");
        System.out.println("Q \t\t\t" + "Quit");
        System.out.println("b c # \t\t" + "means insert char 'c' before position #");
        System.out.println("r # \t\t" + "means remove a char at position #");
        System.out.println("w & # \t\t" + "means switch characters at postions & with #");
        System.out.println("x & # \t\t" + "means cut to clipboard, starting at & to # (inclusive)");
        System.out.println("p # \t\t" + "means paste from clipboard, start at #");
        System.out.println("c & # \t\t" + "means copy to clipboard, starting at & to # (inclusive)");
        System.out.println("'_' \t\t" + "to insert a space into the message use _");

        System.out.print("s filename \t" + "means, to save off the set ");
        System.out.println("of undo commands into text file named \"filename\".");


        //asks the user to enter the secret message
        System.out.println("\nEnter in initial message to mix up");
        Scanner sc = new Scanner(System.in);
        String imessage = sc.nextLine();
        System.out.println("\nMessage: \n");
        m.setInitialMessage(imessage);
        m.showMessage();
        String overall = imessage;

        //Asking user to enter the command
        System.out.print("\nCommand: ");
        String cmessage = sc.nextLine();

        if(!cmessage.equals("Q")) {
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
            }else {
//                try {
                overall = m.processCommand(cmessage);
                System.out.println("\nMessage: \n");
                m.setInitialMessage(overall);
                m.showMessage();
//                } catch (Exception e) {
//                    System.out.println("Command Unknown");
//                }
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
