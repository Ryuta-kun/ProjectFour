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
        String str = "";
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

    public String change(E data, int pos){
        if (top == null) {
            top = new Node<E> (data, top);
        }else {
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


    public String delete (int pos) {

        if (top == null) {

        }

        if (pos == 0) {
                top = top.getNext();

        }
        else {
                Node<E> temp = null;
                int counter = 0;
                while (counter < (pos - 1)) {
                    temp = top.getNext();
                    counter++;
                }
                top = temp.getNext().getNext();

            }
        String str = "";
        Node<E> temp2 = top;
        while (temp2 != null) {
            str += (temp2.getData());
            temp2 = temp2.getNext();

        }
        return str;
    }

    public void deleteHalfWay() {


    }
}
