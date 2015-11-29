import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Lanndon on 11/15/2015.
 */
public class UnMix implements IUnMix {
    private LinkList<Character> message;

    public UnMix(){
        message = new LinkList<Character>();
    }

    @Override
    public String UnMixUsingFile(String filename, String userMessage) {
        String str;
        String unmix = userMessage;
        Mix mix = new Mix();

        //calls the mix command and then sets the unmix message
        //provided as the usermessage on mix class -> pretty much did
        //the same thing so I guessed I could use this for easier process.
        mix.setInitialMessage(userMessage);
        message = mix.getMessage();

        //sets the filename output extension if it was not provided
        if (!filename.contains(".txt")){
            filename = filename + ".txt";
        }

        try{
            // open the data file
            Scanner fileReader = new Scanner(new File(filename));
            LinkList<String> commands = new LinkList<String>();

            //goes through the file and adds it to the linked if it hasNextLine()
            while (fileReader.hasNextLine()){
                str = fileReader.nextLine();
                commands.addfirst(str);
            }

            //goes through the linked list and process each command to unmix the commands
            for (int i = 0; i < commands.count(); i++){
                String s = commands.readList(i).getData();
                unmix = mix.processCommand(s);
            }

            return unmix;
        }
        // could not find file
        catch(FileNotFoundException error) {
            System.err.println("File not found ");
        }

        // problem reading the file
        catch(Exception error){
            System.err.println("Oops!  Something went wrong.");
        }
        return null;
    }

    public static void main (String[] args){
        UnMix m = new UnMix();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter in the mixed up message");
        String userMessage = sc.nextLine();
        System.out.println("\nEnter in the file to unmix message");
        String filename = sc.nextLine();
        String message = m.UnMixUsingFile(filename, userMessage);
        if (message != null) {
            System.out.println("\nThe original message was: \n\t" + message);
        }
    }
}
