package interviews;

public class E2_DesignHashMap {

    float loadFactor=0.75F;
    int count=0;
    int length=1000;
    Node hashTable[];

    class Node{
        int key;
        int value;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }

        public Node getNext() {
            return next;
        }
    }

    public E2_DesignHashMap() {
        hashTable=new Node[length];
    }

    public void put(int key, int value) {
        if(loadFactor*hashTable.length<=count){
            increaseLengthDymically();
        }
        int currentKey=Integer.hashCode(key)%hashTable.length;
        Node node=new Node(key, value);
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
        if(node.key==newNode.key){
            node.value=newNode.value;
        }else{
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
                put(tempNode.key, tempNode.value);
                tempNode=tempNode.next;
            }
        }
    }

    public int get(int key) {
        int currentKey=Integer.hashCode(key)%hashTable.length;
        Node node=hashTable[currentKey];
        if(node==null){
            return -1;
        }
        Node nodeTemp=node;
        while (nodeTemp.next!=null&&nodeTemp.key!=key){
            nodeTemp=nodeTemp.next;
        }
        if(nodeTemp.key==key){
            return nodeTemp.value;
        }
        return -1;
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

    public static void main(String[] args) {
        //Case 1: Khi tăng length của array*2 --> Cần:
        //+ Check null --> mới get key ra
        //+ Count cần reset =0 trước khi put all elements again .
//        E2_DesignHashMap e2_designHashMap=new E2_DesignHashMap();
//        e2_designHashMap.put(1,3);
//        e2_designHashMap.put(2,5);
//        e2_designHashMap.put(3,6);
//        e2_designHashMap.put(4,7);
//        e2_designHashMap.put(5,7);
//        e2_designHashMap.put(6,7);
//        e2_designHashMap.put(7,7);
////        e2_designHashMap.remove(1);
////        e2_designHashMap.remove(2);
//        System.out.println(e2_designHashMap.get(1));
//        System.out.println(e2_designHashMap.get(2));

        //Case 2: Giữ nguyên raw key : hash(key) chỉ để dùng để phân group các elements
//        E2_DesignHashMap e2_designHashMap1=new E2_DesignHashMap();
//        System.out.println(e2_designHashMap1.get(14));
//        System.out.println(e2_designHashMap1.get(4));
//        e2_designHashMap1.put(7,3);
//        e2_designHashMap1.put(11,1);
//        e2_designHashMap1.put(12,1);
//        System.out.println(e2_designHashMap1.get(7));
//        e2_designHashMap1.put(1,19);
//        e2_designHashMap1.put(0,3);
//        e2_designHashMap1.put(1,8);
//        e2_designHashMap1.put(2,6);
        //Case 3: Liên quan đến phép remove viết sai
        E2_DesignHashMap e2_designHashMap2=new E2_DesignHashMap();
        e2_designHashMap2.remove(14);
        e2_designHashMap2.remove(7);
        e2_designHashMap2.remove(6);
        System.out.println(e2_designHashMap2.get(14));
        System.out.println(e2_designHashMap2.get(4));
        e2_designHashMap2.put(7,3);
        e2_designHashMap2.put(11,1);
        e2_designHashMap2.put(12,1);
        e2_designHashMap2.put(10,1);
        e2_designHashMap2.put(22,5);
        e2_designHashMap2.remove(7);
        e2_designHashMap2.remove(22);
        System.out.println(e2_designHashMap2.get(12));
        System.out.println(e2_designHashMap2.get(7));
        e2_designHashMap2.put(1,19);
        e2_designHashMap2.put(0,3);
        e2_designHashMap2.put(1,8);
        e2_designHashMap2.put(2,6);
        //Case 4: Chưa push lại đủ element khi duyệt lại all hashtable
        //(Node a: hashtable){ a-->next }
    }
}
