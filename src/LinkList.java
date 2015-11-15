import java.util.List;

/**
 * Created by Lanndon on 11/15/2015.
 */
public class LinkList<E> {
    private Node<E> top;

    public LinkList() {
        top = null;   // 0000
    }

    public void addfirst (E data) {

        top = new Node<E> (data, top);

        //		    Node temp = new Node();
        //		    temp.setData(data);
        //		    temp.setNext(top);
        //		    top = temp;
    }

    public void display() {
        //				System.out.println (top.getData());
        //				System.out.println (top.getNext().getData());
        //				System.out.println (top.getNext().getNext().getData());

        Node<E> temp = top;
        while (temp != null) {
            System.out.print("\t" + temp.getData());
            temp = temp.getNext();
        }
    }

    public void displayCounter(){
        int count = 0;

        Node<E> temp = top;
        while (temp != null) {
            System.out.print("\t" + count);
            count++;
            temp = temp.getNext();
        }
    }

    public int count() {
        int count = 0;

        Node<E> temp = top;
        while (temp != null) {
            count++;
            temp = temp.getNext();
        }


        return count;
    }

    public void addAtEnd (E data) {

        if (top == null) {
            top = new Node<E> (data, top);
        }

        else {
            Node<E> temp = top;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }

            Node<E> temp2 = new Node<E>();
            temp2.setData(data);
            temp2.setNext(null);

            temp.setNext(temp2);
        }

    }


    public boolean delete (E data) {

        if (top == null)
            return false;

        if (top.getData().equals(data)) {
            top = top.getNext();
            return true;
        }
        Node<E> temp = null;
        while(temp.equals(data) == false){
            temp = top.getNext();
        }
        top = temp.getNext();


        return true;

    }

    public void deleteHalfWay() {


    }


    public static void main (String[] args){
        LinkList<String> list = new
                LinkList<String>();

        list.addAtEnd("pizza5");
        list.addfirst("pizza1");
        list.addfirst("pizza2");
        list.addfirst("pizza3");
        list.addAtEnd("pizza4");

        list.display();

        list.delete("pizza1");
        list.display();


        //		list.addAtEnd("pizza11");

        //		list.addfirst("pizza3");
        //		list.addfirst("pizza4");
        //		list.addfirst("pizza5");
        //		list.addfirst("pizza6");
        //		list.addfirst("pizza7");
        //		list.addfirst("pizza8");
        //		list.addAtEnd("pizza9");

        //list.display();

    }
}
