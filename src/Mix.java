import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/*************************************************************************
 * Mix class for encoding a user input or string files into bits of
 * characters.
 *
 * @author Ryuta Hirano
 * @author Lanndon Rose
 * @version November 2015
 ************************************************************************/
public class Mix implements IMix {

    /**variable for the user message*/
    private LinkList<Character> message;

    /**variable for the commands*/
    private String commandos;

    /**variable for the clipboard function*/
    protected String clipBoard;

    /*********************************************************************
     * Constructor that instantiates the message list and command string
     ********************************************************************/
    public Mix(){

        //setting message to a linklist of type characters
        message = new LinkList<Character>();

        //setting commandos string to an empty string
        commandos = "";
    }

    /*********************************************************************
     * Constructor that input user message into a LinkList
     * @param message the user inputted string
     ********************************************************************/
    @Override
    public void setInitialMessage(String message) {
        LinkList<Character> m = new LinkList<Character>();

        //adds user message to the m linklist
        for (int i = 0; i < message.length(); i ++){
            m.addAtEnd(message.charAt(i));
        }

        //set message to m
        this.message = m;
    }

    /*********************************************************************
     * Constructor that takes user inputs as commands for
     * modifying the message
     * @param command user inputted string
     * @return - returns a string with values associated with each
     * commands.
     ********************************************************************/
    @Override
    public String processCommand(String command){
        //checks whether "b" command is entered
        if (command.charAt(0) == 'b') {
            try {
                String str = this.message.toString();
                String undo = "";
                LinkList<String> com = new LinkList<String>();

                // puts command into linklist
                for (String retval : command.split(" ")){

                    //if "_" is used turns into a space
                    if (retval.contains("_")){
                        retval = retval.replace('_', ' ');
                    }
                    com.addAtEnd(retval);
                }

                //takes information from the linked list
                int num = Integer.parseInt(com.readList(2).getData());
                if ((num >= 0) && (com.readList(1).getData().length() > 0)){
                    //adds each character strings (if more than one) to
                    //the list and addes them onto the String str variable
                    for (int i = 0; i < com.readList(1).getData().length(); i++) {
                        str = this.message.change(com.readList(1).getData().charAt(i), num++);
                    }

                    //sets the undo command for the unmix file.
                    if (com.readList(1).getData().length() > 1) {
                        int number = Integer.parseInt(com.readList(2).getData()) + (com.readList(1).getData().length() - 1);
                        undo = "x " + Integer.parseInt(com.readList(2).getData()) + " " + number;
                    } else {
                        undo = "r " + Integer.parseInt(com.readList(2).getData());
                    }
                    //sets each series of undo commands onto this method.
                    setCommandos(undo);
                }else{
                    throw new IllegalArgumentException();
                }

                return str;
            }catch(Exception b){
                throw b;
            }
        } else if (command.charAt(0) == 'r') {
            try {
                //pointer position for removal
                int numr = Integer.parseInt(command.substring(2));

                //throws an illegal argument exception if the pointer is
                //less than 0
                if (numr >= 0) {

                    //string value that calls the delete method in linklist
                    String str2 = this.message.delete(numr);

                    //this is to record the deleted message for undo.
                    char deleted = message.getRemoved().getData();

                    //sets the space as "_" if space is needed
                    if (deleted == ' ') {
                        deleted = '_';
                    }

                    //sets the undo command that unmixes this.
                    String undo = "b " + deleted + " " + numr;
                    setCommandos(undo);
                    return str2;
                }else{
                    throw new IllegalArgumentException();
                }

            }catch(Exception b){
                throw b;
            }
        } else if (command.charAt(0) == 'x') {
            try {
                String str = "";
                clipBoard = "";

                //splits each cut command values and saves it in each
                //array available.
                String[] values = command.split(" ");    //couldn't find another way to do it so
                int start = Integer.parseInt(values[1]);  //should work for now. need another way to split
                int end = Integer.parseInt(values[2]);    //these commands.

                //if the end pointer is less than the count and is greater
                //than or equals to the start, it will initiate with one
                //condition: start pointer must be greater than 0
                // (this is to prevent negative numbers.
                if (((this.message.count() - 1) >= end) && (start <= end) && (start >= 0)) {

                    //goes through the start and end pointer and removes
                    //each message. It will also save the removed value by
                    //calling the getRemoved() method in the linklist class
                    //to the clipboard
                    for (int i = start; i <= end; i++) {
                        str = this.message.delete(start);
                        clipBoard += this.message.getRemoved().getData().toString();
                    }

                    String clips = clipBoard;

                    //sets the space value as "_" if returns true
                    if (clipBoard.contains(" ")) {
                        clips = clipBoard.replace(' ', '_');
                    }

                    //sets the undo command by using the clipboard.
                    //here instead of paste, we used the "b" command to
                    //unmix the cut command
                    String undo = "b " + clips + " " + start;
                    setCommandos(undo);
                    return str;
                }else{
                    throw new IllegalArgumentException();
                }
            }catch(Exception b){
                throw b;
            }
        } else if (command.charAt(0) == 'p') {
            try {
                String str = "";

                //this is the start pointer for the paste command
                int start = Integer.parseInt(command.substring(2));

                //initiates the following commands if and only if the start
                //pointer is greater than or equal to 0 to prevent
                //negative user inputs.
                if (start >= 0) {

                    //initiates the following command if clipboard is not
                    //empty or null.
                    if (!clipBoard.equals("")) {

                        //this goes through the clipboard and adds each
                        //string as a character using charAt() method.
                        for (int i = 0; i < clipBoard.length(); i++) {
                            str = this.message.change(clipBoard.charAt(i), start);
                            start++;
                        }

                        //this is for the undo commands.
                        //on this int value, we added the start pointer
                        //and the num value together to get where the
                        //pasted commands are located and removes them
                        //using the cut command.
                        int num = Integer.parseInt(command.substring(2)) + clipBoard.length() - 1;
                        String undo = "x " + Integer.parseInt(command.substring(2)) + " " + num;
                        setCommandos(undo);
                        return str;
                    } else {
                        return this.message.toString();
                    }
                }else{
                    throw new IllegalArgumentException();
                }
            }catch(Exception b){
                throw b;
            }
        } else if (command.charAt(0) == 'c') {
            try {
                clipBoard = "";

                //splits each cut command values and saves it in each
                //array available.
                String[] values = command.split(" ");    //couldn't find another way to do it so
                int start = Integer.parseInt(values[1]);  //should work for now. need another way to split
                int end = Integer.parseInt(values[2]);    //these commands.

                //initiates the following command if and only if the
                //start pointer is less than the end pointer and greater
                //than 0.
                if ((start <= end) && (start >= 0)) {
                    //string that calls the copy() command on the linklist
                    //class to copy each command from start to end.
                    String str = this.message.copy(start, end);
                    clipBoard = str;
                }else{
                    throw new IllegalArgumentException();
                }
                return clipBoard;
            }catch(Exception b){
                throw b;
            }
        } else if (command.charAt(0) == 's') {

            //throws an exception if the file name area is empty
            if (command.charAt(2) == ' ') {
                throw new IllegalArgumentException();
            }else {
                try {
                    //calls the previus undo commands (sorry for the
                    //naming) and saves it as a string str3, and then
                    //calls the method save to save the commands.
                    String str3 = getCommandos();
                    save(str3, command.substring(2));
                    return str3;
                } catch (Exception e) {
                    throw e;
                }
            }
        } else if(command.charAt(0) == 'w'){
            try{
                String undo = "";

                //splits each cut command values and saves it in each
                //array available.
                String[] values = command.split(" ");    //couldn't find another way to do it so
                int num1 = Integer.parseInt(values[1]);  //should work for now. need another way to split
                int num2 = Integer.parseInt(values[2]);    //these commands.

                //initiates if and only if each num values are greater than
                // 0 to prevent negative user inputs.
                if ((num1 >= 0) && (num2 >= 0)) {
                    String str = "";

                    //this str value calls the swapChar in linklist and
                    //swaps the two values entered.
                    str = this.message.swapChar(num1, num2);

                    //sets the undo command for swap
                    undo = "w " + num1 + " " + num2;
                    setCommandos(undo);
                    return str;
                }else{
                    throw new IllegalArgumentException();
                }
            }catch (Exception e) {
                throw e;
            }
        }
        throw new IllegalArgumentException();
    }

    /*********************************************************************
     * Method that shows the current message when called.
     ********************************************************************/
    public void showMessage(){
        this.message.displayCounter();
        System.out.println();
        this.message.display();
    }

    /*********************************************************************
     * Saves the lists of undo commands in a file.
     * @param str
     * @param filename
     ********************************************************************/
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

    /*********************************************************************
     * Gets user inputted message
     * @return message
     ********************************************************************/
    public LinkList<Character> getMessage() {
        return message;
    }

    /*********************************************************************
     * gets user inputted commands
     * @return commandos user inputted commands
     ********************************************************************/
    public String getCommandos() {
        return commandos;
    }

    /*********************************************************************
     * Adds new user inputted commands to the list of previously
     * inputted commands
     * @return commandos string of previous commands along with the
     * most recent command
     ********************************************************************/
    public void setCommandos(String commandos) {
        this.commandos += commandos + "\n";
    }

    /*********************************************************************
     * Main methond that starts the console program
     * @param args arguments of some sort I think
     ********************************************************************/
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
        System.out.println("'_' \t\t" + "to insert a space into the message use");

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
