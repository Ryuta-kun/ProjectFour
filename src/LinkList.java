
/*************************************************************************
 * Linklist class for the Mix class of the secret message decoding program
 *
 * @author Ryuta Hirano
 * @author Lanndon Rose
 * @version November 2015
 ************************************************************************/
public class LinkList<E> {

    /** pointer front for linklist*/
    private Node<E> top;

    /** pointer for back of list*/
    private Node<E> tail;

    /** pointer for items removed from list*/
    private Node<E> removed;

    /*********************************************************************
     * Constructor that instantiates top and tail pointers for the list
     ********************************************************************/
    public LinkList() {
        //sets top to null
        top = null;
        //sets tail to null
        tail = null;
    }

    /*********************************************************************
     * Method for adding data to the top of the list
     * @param data user inputted letter
     ********************************************************************/
    public void addfirst (E data) {

        //checks whether top is null, if null sets pointers to new node
        if (top == null)
            tail = top = new Node<E> (data, top);
        else
            top = new Node<E> (data, top);
    }

    /*********************************************************************
     * method that prints out the list to console
     ********************************************************************/
    public void display() {
        Node<E> temp = top;

        //while temp is not null prints out items in list
        while (temp != null) {
            System.out.print("\t" + temp.getData());
            temp = temp.getNext();
        }
    }

    /*********************************************************************
     * method for printing the list out in the correct format
     * @return str string format of the list
     ********************************************************************/
    public String toString() {
        String str = "";
        Node<E> temp = top;

        //while temp is not null adds each list item to string to be returned
        while (temp != null) {
            str += temp.getData();
            temp = temp.getNext();
        }
        return str;
    }

    /*********************************************************************
     * method that displays the total value of the list
     ********************************************************************/
    public void displayCounter(){

        int count = 0;

        Node<E> temp = top;

        //prints position of characters in list
        while (temp != null) {
            System.out.print("\t" + count);
            count++;
            temp = temp.getNext();
        }
    }

    /*********************************************************************
     * method that adds the data to the specified position in the list
     * @param data user specified character
     * @param pos user specified position
     * @return the changed message as string
     ********************************************************************/
    public String change(E data, int pos){
        int counter = 0;
        Node<E> temp1 = top;

        //checks if user wants character in front of the list
        if(pos == 0) {
            addfirst(data);
        }else{

            //loops over to the position before the given value
            while (counter < (pos - 1)) {
                counter++;
                temp1 = temp1.getNext();
            }

            //creates a new node and sets it at the given position by
            //setting temp.setNext to add
            Node add = new Node();
            add.setData(data);
            add.setNext(temp1.getNext());
            temp1.setNext(add);

            //if the next temp is null, then it sets this as the tail.
            if (temp1.getNext() == null){
                tail = temp1.getNext();
            }
        }

        //this just reprints the whole message to update the changed value
        String str = "";
        Node<E> temp = top;
        while (temp != null) {
            str += (temp.getData());
            temp = temp.getNext();
        }
        return str;
    }

    /*********************************************************************
     * method that return the total number of items in the list
     * @return count number of items in the list
     ********************************************************************/
    public int count() {
        int count = 0;

        //this will go through each temp node and count the total that
        //is inside this link list.
        Node<E> temp = top;
        while (temp != null) {
            count++;
            temp = temp.getNext();
        }

        return count;
    }

    /*********************************************************************
     * method that adds an item to the end of the list
     * @param data character/s inputted by the user
     ********************************************************************/
    public void addAtEnd (E data) {
        //if top is null, it sets top and tail as same as the new node.
        if (top == null) {
            tail = top = new Node<E> (data, top);
        }

        //else, it will set the tail as the new data given, and also add
        //it to the list
        else {
            tail.setNext(new Node<E>(data, null));
            tail = tail.getNext();
        }

    }


    /*********************************************************************
     * method that removes an item from the list at the specified position
     * @param pos character/s inputted by the user
     * @return the updated string value after deletion
     ********************************************************************/
    public String delete (int pos) {

        if (top == null) {
            tail = top = null;
        }

        //check if top element is the target
        if (pos == 0) {
            //this removed element records the deleted element for undo
            //command purposes
            removed = top;
            top = top.getNext();
            if (top == null){
                tail = null;
            }
        }

        //check if any other than the top is the target
        else {
            Node<E> temp = top;
            int counter = 0;

            //loops over to the position before the given parameter
            while (counter < (pos - 1)) {
                temp = temp.getNext();
                counter++;
            }

            //this will save the whole node onto removed for undo purposes
            removed = temp.getNext();

            //deletes the command by setting next values as two steps ahead
            temp.setNext(temp.getNext().getNext());

            //if next temp value is null, sets tail as temp
            if (temp.getNext() == null){
                tail = temp;
            }
        }

        //this will just reprint the updated value as string
        String str = "";
        Node<E> temp2 = top;
        while (temp2 != null) {
            str += (temp2.getData());
            temp2 = temp2.getNext();
        }
        return str;
    }

    /*********************************************************************
     * method swaps two specified letters in the list
     * @param pos1 specified letter position to be swapped
     * @param pos2 specified letter position to be swapped
     * @return the copied data as string
     ********************************************************************/
    public String copy(int pos1, int pos2){
        String str = "";
        Node<E> temp = top;

        //this method will loop over until it reaches param pos2.
        for (int i = 0; i <= pos2; i++){

            //adds the value if it is between param pos1 and pos2
            if (i >= pos1){
                str += (temp.getData());
            }
            temp = temp.getNext();
        }
        return str;
    }

    /*********************************************************************
     * method that gets removed item
     * @return removed points to where removed item was
     ********************************************************************/
    public Node<E> getRemoved() {
        return removed;
    }

    /*********************************************************************
     * method that returns a node at specified position this method was
     * created for adding character method (command b) to allow multiple
     * character inputs.
     * @param pos specified postion of on the list
     * @return temp node of the specified position
     ********************************************************************/
    public Node<E> readList(int pos) {
        Node<E> temp = top;
        int counter = 0;

        //this will loop over as long as temp is not null
        while (temp != null) {

            //if the counter equals the given param, it returns the node
            if (counter == pos){
                return temp;
            }
            temp = temp.getNext();
            counter++;
        }
        return null;
    }

    /*********************************************************************
     * method that swaps two specified items
     * @param pos1 specified position for swapping item in list
     * @param pos2 specified position for swapping item in list
     * @return returns the updated string value after the swap
     ********************************************************************/
    public String swapChar(int pos1, int pos2){
        Node<E> temp = top;
        Node<E> temp2 = top;

        //if pos1 is 0, then it sets temp as the top
        if (pos1 == 0){
            temp = top;
        }else {
            //else, it will loop over until it gets to the exact position
            for (int i = 0; i <= (pos1 - 1); i++) {
                temp = temp.getNext();
            }
        }
        //saves the value as data for swapping
        E data = temp.getData();

        //does the same except this is with param pos2
        for(int i = 0; i <= (pos2 -1); i++){
            temp2 = temp2.getNext();
        }

        //also saves the value but as dataTwo
        E dataTwo = temp2.getData();

        //swaps the data of each value with each other
        temp.setData(dataTwo);
        temp2.setData(data);


        //this will just reprint the whole string message after the swap
        String str = "";
        Node<E> temp3 = top;
        while (temp3 != null) {
            str += (temp3.getData());
            temp3 = temp3.getNext();
        }
        return str;
    }
}
