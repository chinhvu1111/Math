package interviews;

public class E3_DesignSet {

    float loadFactor=0.75F;
    int count=0;
    int length=1000;
    Node hashTable[];

    class Node{
        int key;
        Node next;

        public Node(int key) {
            this.key = key;
        }
    }

    public E3_DesignSet() {
        hashTable=new Node[length];
    }

    public void add(int key) {
        if(loadFactor*hashTable.length<=count){
            increaseLengthDymically();
        }
        int currentKey=Integer.hashCode(key)%hashTable.length;
        Node node=new Node(key);
        if(hashTable[currentKey]==null){
            hashTable[currentKey]=node;
            count++;
        }else{
            Node currentGroup=hashTable[currentKey];
            addNode(currentGroup, node);
        }
    }

    private void addNode(Node root, Node newNode){
        Node node=root;
        while(node.next!=null&&node.key!=newNode.key){
            node=node.next;
        }
        if(node.key!=newNode.key){
            node.next=newNode;
        }
    }

    private void increaseLengthDymically(){
        //1, Đoạn này thay vì viết add theo viểu vùng nhớ (storage)
        //--> Ta có thể put
        //2, Dùng vùng nhớ đê tăng tốc
        // --> Vì ta dùng phép % nên (Có thể xác định được key trước đó) --> Nhưng việc phân group khi tăng length có thẻ bị sai.
        // --> Cần put lại.
        Node hashTable1[]=hashTable;
        hashTable=new Node[hashTable1.length*2];
        count=0;
        for(Node node: hashTable1){
            Node tempNode=node;
            while (tempNode != null) {
                add(tempNode.key);
                tempNode=tempNode.next;
            }
        }
    }

    public void remove(int key) {
        int currentKey=Integer.hashCode(key)%hashTable.length;
        Node node=hashTable[currentKey];
        if(node==null){
            return;
        }
        //Cần chuẩn tử duy về (temp variable)
        Node nodeTemp=node;
        Node prevNodeTemp=null;
        while (nodeTemp.next!=null&&nodeTemp.key!=key){
            prevNodeTemp=nodeTemp;
            nodeTemp=nodeTemp.next;
        }
        if(nodeTemp.key==key){
            if(prevNodeTemp!=null){
                prevNodeTemp.next=nodeTemp.next;
            }else{
                hashTable[currentKey]=nodeTemp.next;
                if(nodeTemp.next==null){
                    count--;
                }
            }
        }
    }

    public boolean contains(int key) {
        int currentKey=Integer.hashCode(key)%hashTable.length;
        Node node=hashTable[currentKey];
        if(node==null){
            return false;
        }
        Node nodeTemp=node;
        while (nodeTemp.next!=null&&nodeTemp.key!=key){
            nodeTemp=nodeTemp.next;
        }
        if(nodeTemp.key==key){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        E3_DesignSet e3_designSet=new E3_DesignSet();
        e3_designSet.add(1);
        e3_designSet.add(2);
        e3_designSet.contains(1);
        e3_designSet.contains(3);
        e3_designSet.add(2);
        e3_designSet.contains(3);
        e3_designSet.remove(2);
        System.out.println(e3_designSet.contains(2));
    }
}
