package interviews.design_topic;

import java.util.HashMap;

public class E1_DesignBrowserHistory_hash {

    public HashMap<Integer, Node> nodeHashMap;

    public static class Node{
        String url;
        public Node(String url) {
            this.url = url;
        }
    }
    public int length=0;
    public int currentPos=0;
    public Node root;
    public Node currentNode;

    public E1_DesignBrowserHistory_hash(String homepage) {
        root=new Node(homepage);
        currentNode=root;
        nodeHashMap=new HashMap<>();
        length++;
        currentPos++;
        nodeHashMap.put(length, currentNode);
    }

    public void visit(String url) {
        //Deleting all next nodes before visiting ==> Có thể bỏ
//        Node temp=currentNode.next;
//        while (temp!=null){
//            temp=temp.next;
//            length--;
//        }
        currentNode= new Node(url);
        currentPos++;
        length=currentPos;
        nodeHashMap.put(currentPos, currentNode);
//        System.out.printf("Visit to %s, length %s\n",currentNode.url, length);
    }

    public String back(int steps) {
        if(currentNode==null){
            return null;
        }
        if(currentPos>steps){
//            System.out.printf("Back %s %s %s\n",currentPos, currentPos-steps, nodeHashMap.get(currentPos-steps).url);
            currentPos=currentPos-steps;
            return nodeHashMap.get(currentPos).url;
        }
//        System.out.println(currentNode.url);
//        System.out.println(currentNode.prev.url);
        currentPos=1;
        return nodeHashMap.get(1).url;
//        System.out.printf("Back to %s, length %s\n",currentNode.url, length);
    }

    public String forward(int steps) {
        if(currentNode==null){
            return null;
        }
        if(currentPos+steps<=length){
//            System.out.printf("Forward %s %s %s\n",currentPos, currentPos+steps, nodeHashMap.get(currentPos+steps).url);
            currentPos=currentPos+steps;
            return nodeHashMap.get(currentPos).url;
        }
//        System.out.printf("%s %s\n",currentPos, length);
        currentPos=length;
//        System.out.println(nodeHashMap);
//        System.out.printf("Forward to %s, length %s\n",currentNode.url, length);
        return nodeHashMap.get(currentPos).url;
    }

    public static void main(String[] args) {
        E1_DesignBrowserHistory_hash e1DesignBrowserHistory=new E1_DesignBrowserHistory_hash("leetcode.com");
        e1DesignBrowserHistory.visit("google.com");
        e1DesignBrowserHistory.visit("facebook.com");
        e1DesignBrowserHistory.visit("youtube.com");
        e1DesignBrowserHistory.visit("abc.com");
//        //(facebook.com)
//        System.out.println(e1DesignBrowserHistory.back(2));
//        //google.com, facebook.com, (hhh.com)
//        e1DesignBrowserHistory.visit("hhh.com");
//        //(google.com), facebook.com, hhh.com
//        System.out.println(e1DesignBrowserHistory.back(2));
//        e1DesignBrowserHistory.visit("ahhh.com");
//        System.out.println(e1DesignBrowserHistory.forward(7));

        //(leetcode.com), google.com, facebook.com, youtube.com, hhh.com
        System.out.println(e1DesignBrowserHistory.back(200));
        //leetcode.com, (hhh.com)
        e1DesignBrowserHistory.visit("hhh.com");
        //(leetcode.com), (hhh.com)
        System.out.println(e1DesignBrowserHistory.back(2));
        e1DesignBrowserHistory.visit("ahhh.com");
        System.out.println(e1DesignBrowserHistory.forward(7));
        //#Reference:
        //1473. Paint House III
        //2254. Design Video Sharing Platform
    }
}
