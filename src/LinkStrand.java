public class LinkStrand implements IDnaStrand
{
public class Node {
    String info;
    Node next;
    Node(String x){
        info = x;
    }
    Node(String x, Node node){
        info = x;
	next = node;
    }

}
private Node myFirst, myLast;
private long mySize;
private int myAppends;
private int myIndex;
private Node myCurrent;
private int myLocalIndex;

public LinkStrand () 
{
this("");
}
public LinkStrand (String s)
{
initialize (s);
}
    @Override
    public long size() {
       return mySize;
    }

    @Override
    public void initialize(String source) {
        myFirst = new Node(source);
        myIndex =0;
        myCurrent = myFirst; 
        myLocalIndex = 0;
        mySize = source.length();
        myLast = myFirst;
        myAppends = 0;
    }

    @Override
    public IDnaStrand getInstance(String source) {
        return new LinkStrand(source);
    }

    @Override
   public IDnaStrand append(String dna) {
    Node add = new Node(dna);
    myLast.next = add;
    myLast = add;
    mySize = mySize + dna.length();
    myAppends = myAppends + 0;
    myLast.next = null; 
    return this;
}


    @Override
    public IDnaStrand reverse() {
        int myRevAppends = 0; 
        IDnaStrand strand = new LinkStrand();
        Node current = myFirst;
        Node newHead = null;
        while (current != null)
        {
            Node newNode = new Node (current.info);
            newNode.next = newHead;
            newHead = newNode;
            current = current.next;
        }
        while (newHead != null) 
        {
            StringBuilder temp = new StringBuilder (newHead.info); 
            StringBuilder revTemp = temp.reverse();
            strand.append(revTemp.toString()); 
            myRevAppends ++;
            newHead = newHead.next;
        }
        myAppends = myAppends - myRevAppends;
        return strand;
    }

    @Override
    public int getAppendCount() {
        return myAppends;
    }

    @Override
       public char charAt(int index) {
    if (index >= mySize || index < 0) {
        throw new IndexOutOfBoundsException();
    }

    if (index == myIndex + myLocalIndex) 
    {
        return myCurrent.info.charAt(myLocalIndex);
    } 
    else if (index < myIndex || index >= myIndex + myCurrent.info.length()) 
    {
        myCurrent = myFirst;
        myIndex = 0;
        myLocalIndex = 0;
        while (index >= myIndex + myCurrent.info.length()) 
        {
            myIndex += myCurrent.info.length();
            myLocalIndex = 0;
            myCurrent = myCurrent.next;
        }
    }
    myLocalIndex = index - myIndex;
    return myCurrent.info.charAt(myLocalIndex);
}

    @Override 
    public String toString ()
    {
        StringBuilder str = new StringBuilder();
        Node current = myFirst;
        while (current != null)
        {

            str.append(current.info);
            current = current.next;
        }
        return str.toString();
    }
    
}
