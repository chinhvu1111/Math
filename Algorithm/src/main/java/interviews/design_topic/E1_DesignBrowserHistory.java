package interviews.design_topic;

import java.util.LinkedList;

public class E1_DesignBrowserHistory {

    public static class Node{
        String url;
        Node next;
        Node prev;

        public Node(String url) {
            this.url = url;
        }
    }
    public int length=0;
    public Node root;
    public Node currentNode;

    public E1_DesignBrowserHistory(String homepage) {
        root=new Node(homepage);
        currentNode=root;
        length++;
    }

    public void visit(String url) {
        if(length>=1000){
            if(root!=null){
                root=root.next;
                root.prev=null;
                length--;
            }
        }
        Node node=new Node(url);
        node.prev=currentNode;
        //Deleting all next nodes before visiting
        Node temp=currentNode.next;
        while (temp!=null){
            temp=temp.next;
            length--;
        }
        currentNode.next=node;
        currentNode=node;
        length++;
//        System.out.printf("Visit to %s, length %s\n",currentNode.url, length);
    }

    public String back(int steps) {
        if(currentNode==null){
            return null;
        }
//        System.out.println(currentNode.url);
//        System.out.println(currentNode.prev.url);
        while (steps>0&&currentNode.prev!=null){
            currentNode=currentNode.prev;
            steps--;
        }
//        System.out.printf("Back to %s, length %s\n",currentNode.url, length);
        return currentNode.url;
    }

    public String forward(int steps) {
        if(currentNode==null){
            return null;
        }
        while (steps>0&&currentNode.next!=null){
            currentNode=currentNode.next;
            steps--;
        }
//        System.out.printf("Forward to %s, length %s\n",currentNode.url, length);
        return currentNode.url;
    }

    public static void main(String[] args) {
        E1_DesignBrowserHistory e1DesignBrowserHistory=new E1_DesignBrowserHistory("leetcode.com");
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
        //
        //["BrowserHistory",
        //"visit",
        //"visit",
        //"visit",
        //"back",
        //"back",
        //"forward",
        //"visit",
        //"forward",
        //"back",
        //"back"]
        //
        //
        //[
        //["leetcode.com"],
        //["google.com"],
        //["facebook.com"],
        //["youtube.com"],
        //[1],
        //[1],
        //[1],
        //["linkedin.com"],
        //[2],
        //[2],
        //[7]
        //]
        //Output:
        //[null,
        //null,
        //null,
        //null, visit(1) : queue [leetcode.com, google.com, facebook.com, (youtube.com)]
        //+ "facebook.com", back(1) : queue [leetcode.com, google.com, (facebook.com), youtube.com]
        //+ "google.com", back(1) : queue [leetcode.com, (google.com), facebook.com, youtube.com]
        //+ "facebook.com", forward(1) : queue [leetcode.com, google.com, (facebook.com), youtube.com]
        //+ null, visit['linkedin.com'], : queue [leetcode.com, google.com, facebook.com, (linkedin.com)] ==> Visit đến x clear history của (x --> next) <=> ("youtube.com") phía trước.
        //+ "linkedin.com", forward(2)  : queue [leetcode.com, google.com, facebook.com, (linkedin.com)] : Chỉ có thể forward có giới hạn
        //+ "google.com", back(2)  : queue [leetcode.com, (google.com), facebook.com, linkedin.com]
        //+ "leetcode.com"], back(2)  : queue [(leetcode.com), google.com, facebook.com, linkedin.com] : Chỉ có thể back có giới hạn ==> Vẫn store forward data.
        //
        //** Đề bài:
        //- Design 1 history web browser sao cho:
        //- Có thể:
        //+ visit: Visit đến x clear history của (x --> next) phía trước.
        //+ Chỉ có thể forward có giới hạn về x phần tử đẳng trước
        //+ Chỉ có thể back có giới hạn ==> Vẫn store forward data.
        //
        //** Bài này tư duy như sau:
        //1.
        //1.1,
        //
        //* Yêu cầu:
        //
        //Vì dynamic data dựa trên số phần tử:
        //- Visit --> Clear data ở đầu bên trái ==> Nếu vượt số lượng elements.
        //- Back lại:
        //+ Access index fast
        //+ Back lại nhưng phần tử trước đó --> Vẫn có thể acress lại được.
        //- Ngoài ra có thể clear được phần tử phía sau:
        //A,(b),c ==> đang ở (b) visit c ==> Clear c
        //==> a,b,d
        //
        //* Data structure:
        //- Dùng custom LinkedList + kết hợp hashMap để có thể access nhanh ==> Cái này còn tuỳ.
        //1.2, Implementation:
        //- List: a --> b -->c
        //- hash: hashMap<1, a> ==> Nếu dùng nên ==> delete thì ta phải clear phần tử phía trước đó đi --> Kiểu gì cũng phải traverse all forward.
        //==> Ta dùng thử only linked list trước.
        //
        //- Back: a-->b-->(c)-->d
        //a-->b-->(c)-->d
        //==>
        //a-->(b)-->c-->d
        //<=>
        //currentNode=currentNode.previous;
        //
        //1.3, Có thể dùng hashmap cũng được --> Lưu currentPos
        //
        //#Reference:
        //1473. Paint House III
        //2254. Design Video Sharing Platform
    }
}
