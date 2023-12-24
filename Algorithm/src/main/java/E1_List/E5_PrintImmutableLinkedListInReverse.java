package E1_List;

import java.util.Stack;

public class E5_PrintImmutableLinkedListInReverse {

    interface ImmutableListNode {
        public void printValue(); // print the value of this node.

        public ImmutableListNode getNext(); // return the next node.
    }
    ;

    public static void printLinkedListInReverse(ImmutableListNode head) {
        Stack<ImmutableListNode> printStack=new Stack<>();
        ImmutableListNode node=head;

        while(node!=null){
            printStack.add(node);
            node=node.getNext();
        }
        while(!printStack.isEmpty()){
            ImmutableListNode curNode = printStack.pop();
            curNode.printValue();
        }
    }

    public static void main(String[] args) {
        //** Đề bài:
        //-You need to use the following functions to access the linked list (you can't access the ImmutableListNode directly):
        //
        //+ ImmutableListNode.printValue(): Print value of the current node.
        //+ ImmutableListNode.getNext(): Return the next node.
        //* Output the result in the reverse order.
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Constraints:
        //The length of the linked list is between [1, 1000].
        //The value of each node in the linked list is between [-1000, 1000].
        //
        //- Brainstorm
        //- Dùng stack add từng node vào
        //
        //1.2, Optimization
        //1.3, Complexity
        //- Space : O(N)
        //- Time : O(N)
        //
        //
    }
}
