import java.util.List;

/*****************************************************************
 Linklist class for the Mix class of the secret message decoding program

 @author Ryuta Hirano
 @author Lanndon Rose
 @version November 2015
 *****************************************************************/
public class LinkList<E> {
    private Node<E> top;
    private Node<E> tail;
    private Node<E> removed;

    /*****************************************************************
     Constructor that instantiates top and tail pointers for the list
     *****************************************************************/
    public LinkList() {
        top = null;   // 0000
        tail = null;
    }

    /*****************************************************************
     Method for adding data to the top of the list
     @param data user inputted letter
     *****************************************************************/
    public void addfirst (E data) {
        if (top == null)
            tail = top = new Node<E> (data, top);
        else
            top = new Node<E> (data, top);
    }

    /*****************************************************************
     method that prints out the list to console
     *****************************************************************/
    public void display() {
        Node<E> temp = top;
        while (temp != null) {
            System.out.print("\t" + temp.getData());
            temp = temp.getNext();
        }
    }

    /*****************************************************************
     method for printing the list out in the correct format
     @return str string format of the list
     *****************************************************************/
    public String toString() {
        String str = "";
        Node<E> temp = top;
        while (temp != null) {
            str += temp.getData();
            temp = temp.getNext();
        }
        return str;
    }

    /*****************************************************************
     method that displays the total value of the list
     *****************************************************************/
    public void displayCounter(){
        int count = 0;

        Node<E> temp = top;
        while (temp != null) {
            System.out.print("\t" + count);
            count++;
            temp = temp.getNext();
        }
    }

    /*****************************************************************
     method that adds the data to the specified position in the list
     *****************************************************************/
    public String change(E data, int pos){
        int counter = 0;
        Node<E> temp1 = top;
        if(pos == 0) {
            addfirst(data);
        }else{
            while (counter < (pos - 1)) {
                counter++;
                temp1 = temp1.getNext();
            }
            Node add = new Node();
            add.setData(data);
            add.setNext(temp1.getNext());
            temp1.setNext(add);
            if (temp1.getNext() == null){
                tail = temp1.getNext();
            }
        }

        String str = "";
        Node<E> temp = top;
        while (temp != null) {
            str += (temp.getData());
            temp = temp.getNext();
        }
        return str;
    }

    /*****************************************************************
     method that return the total number of items in the list
     @return count number of items in the list
     *****************************************************************/
    public int count() {
        int count = 0;

        Node<E> temp = top;
        while (temp != null) {
            count++;
            temp = temp.getNext();
        }

        return count;
    }

    /*****************************************************************
     method that adds an item to the end of the list
     @param data character/s inputted by the user
     *****************************************************************/
    public void addAtEnd (E data) {

        if (top == null) {
            tail = top = new Node<E> (data, top);
        }

        else {
            tail.setNext(new Node<E>(data, null));
            tail = tail.getNext();
        }

    }


    /*****************************************************************
     method that removes an item from the list at the specified position
     @param pos character/s inputted by the user
     *****************************************************************/
    public String delete (int pos) {

        if (top == null) {
            tail = top = null;
        }

        //check if top element is the target
        if (pos == 0) {
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
            while (counter < (pos - 1)) {
                temp = temp.getNext();
                counter++;
            }
            removed = temp.getNext();
            temp.setNext(temp.getNext().getNext());
            if (temp.getNext() == null){
                tail = temp;
            }
        }

        String str = "";
        Node<E> temp2 = top;
        while (temp2 != null) {
            str += (temp2.getData());
            temp2 = temp2.getNext();
        }
        return str;
    }

    /*****************************************************************
     method swaps two specified letters in the list
     @param pos1 specified letter position to be swapped
     @param pos2 specified letter position to be swapped
     *****************************************************************/
    public String copy(int pos1, int pos2){
        String str = "";
        Node<E> temp = top;
        for (int i = 0; i <= pos2; i++){
            if (i >= pos1){
                str += (temp.getData());
            }
            temp = temp.getNext();
        }
        return str;
    }

    /*****************************************************************
     method that gets removed item
     @return removed points to where removed item was
     *****************************************************************/
    public Node<E> getRemoved() {
        return removed;
    }
    /*****************************************************************
     method that returns a node at specified position
     @param pos specified postion of on the list
     @return temp node of the specified position
     *****************************************************************/
    public Node<E> readList(int pos) {
        Node<E> temp = top;
        int counter = 0;
        while (temp != null) {
            if (counter == pos){
                return temp;
            }
            temp = temp.getNext();
            counter++;
        }
        return null;
    } /*****************************************************************
     method that swaps two specified items
     @param pos1 specified position for swapping item in list
     @param pos2 specified position for swapping item in list
     *****************************************************************/
    public String swapChar(int pos1, int pos2){
        Node<E> temp = top;
        Node<E> temp2 = top;
        if (pos1 == 0){
            temp = top;
        }else {
            for (int i = 0; i <= (pos1 - 1); i++) {
                temp = temp.getNext();
            }
        }
        E data = temp.getData();

        for(int i = 0; i <= (pos2 -1); i++){
            temp2 = temp2.getNext();
        }
        E dataTwo = temp2.getData();

        temp.setData(dataTwo);
        temp2.setData(data);


        String str = "";
        Node<E> temp3 = top;
        while (temp3 != null) {
            str += (temp3.getData());
            temp3 = temp3.getNext();
        }
        return str;
    }
}
