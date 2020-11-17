package ADT;

public class DoublyLinkedList implements ListADT<Score> {

    private Node head;
    private Node tail;
    private int numEntry;
    public DoublyLinkedList() {
        this.numEntry = 0;
        this.head = null;
        this.tail = null;
    }

    @Override
    public void addData(Score input) {

        int newScore = input.getScore();
        Node newnode = new Node(input);

        if (this.head == null) {
            this.head = newnode;
            this.tail = newnode;
            return;
        } else if (head.data.getScore() < newScore) {
            this.head.prev = newnode;
            newnode.next = head;
            head = newnode;
            return;
        }

        Node p = this.head;
        while (newScore < p.data.getScore()) {
            if (newScore < p.next.data.getScore()) {
                p = p.next;
            } else {
                Node m = p.next;
                p.next = newnode;
                newnode.prev = p;
                newnode.next = m;
                m.prev = newnode;
                break;
            }

            // add at tail
            if (p.next == null) {
                newnode.prev = tail;
                tail.next = newnode;
                tail = newnode;
                break;
            }

        }


        this.numEntry = this.numEntry + 1;
    }

    public Score removeData(int index) {
        Node p = this.head;
        Score std = p.data;

        // Delete at Head
        if (index == 0) {
            if (this.numEntry == 1) {
                head = null;
                tail = null;
                this.numEntry = 0;
                return std;
            } else {
                head = head.next;
                head.prev = null;
                this.numEntry = this.numEntry - 1;
                return std;
            }
        }
        // Delete at Tail
        if (index == this.numEntry) {
            std = tail.data;
            tail = tail.prev;
            tail.next = null;
            this.numEntry = this.numEntry - 1;
            return std;
        }

        p = head.next;
        std = p.data;
        for (int i = 1; i < this.numEntry; i++) {
            if (index == i) {
                Node m = p.prev;
                Node n = p.next;
                m.next = n;
                n.prev = m;
                this.numEntry--;
                break;
            }
            p = p.next;
            std = p.data;
        }
        return std;

    }

    public int getDataAt(int index){
        Node p = this.head;
        for (int i = 1; i < this.numEntry; i++){
            if(index == i){
                break;
            }
            p = p.next;
        }
        return p.data.getScore();
    }


    public int getCount() {
        return numEntry;
    }

    public void displayAll() {
        Node p = this.head;
        while (p != null) {
            System.out.println(p.data.getName() + "  " + p.data.getScore());
            p = p.next;
        }
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean findEmptyNode() {
        return false;
    }

    private class Node {
        Score data;
        Node next;
        Node prev;

        public Node(Score score) {
            this.data = score;
            this.next = null;
            this.prev = null;
        }
    }


}