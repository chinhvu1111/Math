package E1_weekly;

public class E13_AddTwoPolynomialsRepresentedAsLinkedLists_undone {

    static class PolyNode {
        int coefficient, power;
        PolyNode next = null;

        PolyNode() {
        }

        PolyNode(int x, int y) {
            this.coefficient = x;
            this.power = y;
        }

        PolyNode(int x, int y, PolyNode next) {
            this.coefficient = x;
            this.power = y;
            this.next = next;
        }
    }

    public static PolyNode addPoly(PolyNode poly1, PolyNode poly2) {
        //Dùng 2 prev để biết nên next thằng nào:
        //  + prev1!=null ==> traverse prev2 <>
        PolyNode node=null;
        PolyNode root=null;
        PolyNode nodePoly1=poly1;
        PolyNode nodePoly2=poly2;
        PolyNode prev1=null;
        PolyNode prev2=null;

        while(nodePoly1!=null||nodePoly2!=null||prev1!=null||prev2!=null){
            PolyNode newNode=new PolyNode();
            if(prev1 != null){
                if(nodePoly2!=null){
                    if(prev1.power == nodePoly2.power){
                        newNode.power = nodePoly2.power;
                        newNode.coefficient = prev1.coefficient + nodePoly2.coefficient;
                        prev1=null;
                        nodePoly2=nodePoly2.next;
                    }else if(prev1.power<nodePoly2.power){
                        newNode.power = nodePoly2.power;
                        newNode.coefficient = nodePoly2.coefficient;
                        nodePoly2=nodePoly2.next;
                    }else{
                        newNode.power = prev1.power;
                        newNode.coefficient = prev1.coefficient;
                        prev1=null;
                    }
                }else{
                    newNode.power = prev1.power;
                    newNode.coefficient = prev1.coefficient;
                    prev1=null;
                }
            } else if(prev2!=null){
                if(nodePoly1!=null){
                    if(prev2.power == nodePoly1.power){
                        newNode.power = nodePoly1.power;
                        newNode.coefficient = prev2.coefficient + nodePoly1.coefficient;
                        prev2=null;
                        nodePoly1=nodePoly1.next;
                    }else if(prev2.power<nodePoly1.power){
                        newNode.power = nodePoly1.power;
                        newNode.coefficient = nodePoly1.coefficient;
                        nodePoly1=nodePoly1.next;
                    }else{
                        newNode.power = prev2.power;
                        newNode.coefficient = prev2.coefficient;
                        prev2=null;
                    }
                }else{
                    newNode.power = prev2.power;
                    newNode.coefficient = prev2.coefficient;
                    prev2=null;
                }
            } else if (nodePoly1!=null&&nodePoly2==null) {
                newNode = nodePoly1;
                nodePoly1=nodePoly1.next;
            }else if (nodePoly1 == null) {
                newNode = nodePoly2;
                nodePoly2=nodePoly2.next;
            }
            else{
                if(nodePoly1.power==nodePoly2.power){
                    newNode.power = nodePoly1.power;
                    newNode.coefficient = nodePoly1.coefficient + nodePoly2.coefficient;
                    nodePoly1=nodePoly1.next;
                    nodePoly2=nodePoly2.next;
                }else if(nodePoly1.power>nodePoly2.power){
                    newNode.power = nodePoly1.power;
                    newNode.coefficient = nodePoly1.coefficient;
                    nodePoly1=nodePoly1.next;
                    prev2=nodePoly2;
                    nodePoly2=nodePoly2.next;
                }else{
                    newNode.power = nodePoly2.power;
                    newNode.coefficient = nodePoly2.coefficient;
                    nodePoly2=nodePoly2.next;
                    prev1=nodePoly1;
                    nodePoly1=nodePoly1.next;
                }
            }
            if(newNode.coefficient==0){
                continue;
            }
            if(node==null){
                root=newNode;
            }else{
                node.next=newNode;
            }
            node=newNode;
        }
        PolyNode nodeTmp=root;
        while (nodeTmp!=null){
            System.out.printf("%s %s\n",nodeTmp.coefficient, nodeTmp.power);
            nodeTmp=nodeTmp.next;
        }
        return root;
    }

    public static void main(String[] args) {
        //** Requirement
        //- A polynomial linked list is a special type of linked list where every node represents a term in a polynomial expression.
        //Each node has three attributes:
        //  + coefficient: an integer representing the number multiplier of the term.
        //      + The coefficient of the term 9x4 is 9.
        //  + power: an integer representing the exponent. The power of the term 9x4 is 4.
        //  + next: a pointer to the next node in the list, or null if it is the last node of the list.
        //- For example, the polynomial 5x3 + 4x - 7 is represented by the polynomial linked list illustrated below:
        //The polynomial linked list must be in its standard form:
        //  - the polynomial must be in strictly descending
        //  - order by its power value. Also, terms with a coefficient of 0 are omitted.
        //Given two polynomial linked list heads, poly1 and poly2, add the polynomials together and
        //  - return the head of the sum of the polynomials.
        //- PolyNode format:
        //The input/output format is as a list of n nodes, where each node is represented as its [coefficient, power].
        //For example, the polynomial 5x3 + 4x - 7 would be represented as: [[5,3],[4,1],[-7,0]].
        //
        //**Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //0 <= n <= 10^4
        //-10^9 <= PolyNode.coefficient <= 10^9
        //PolyNode.coefficient != 0
        //0 <= PolyNode.power <= 10^9
        //PolyNode.power > PolyNode.next.power
        //  + Do số 10^9:
        //      + Cần dùng Long.
        //
        //- Brainstorm
        //- Để dễ ta sẽ reverse linked list để cộng vào
        //  + Không cần reverse để có thể xử lý 2 nodes cùng 1 lúc
        //- Xét 2 nodes thì có thể có:
        //  + node1.power != node2.power
        //  ==> Ta sẽ lấy element có power smaller
        //  + Cache lại node có power lớn hơn để so sánh tiếp
        //==> 2 thằng chỉ next node khi prevNode == null
        //  + Tức là không có cache
        //- Dùng queue để lưu cache cũng được đỡ  phải check null
        //
//        int[][] poly1 = {{1,1}}, poly2 = {{1,0}};
//        int[][] poly1 = {{2,2},{4,1},{3,0}}, poly2 = {{3,2},{-4,1},{-1,0}};
//        int[][] poly1 = {{9,10},{-8,9},{1,5},{-7,3}}, poly2 = {{-4,4},{7,3}};
        int[][] poly1 = {{-3,8},{-4,3},{4,2}}, poly2 = {{3,8},{7,6},{4,3},{-5,2}};
        PolyNode polyNode1 = null;
        PolyNode polyNode1Root = null;
        PolyNode polyNode2 = null;
        PolyNode polyNode2Root = null;

        for(int[] e: poly1){
            PolyNode newNode=new PolyNode();
            newNode.coefficient=e[0];
            newNode.power = e[1];
            if(polyNode1!=null){
                polyNode1.next=newNode;
            }else{
                polyNode1Root=newNode;
            }
            polyNode1=newNode;
        }
        for(int[] e: poly2){
            PolyNode newNode=new PolyNode();
            newNode.coefficient=e[0];
            newNode.power = e[1];
            if(polyNode2!=null){
                polyNode2.next=newNode;
            }else{
                polyNode2Root=newNode;
            }
            polyNode2=newNode;
        }
        addPoly(polyNode1Root, polyNode2Root);
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(n+m)
        //
        //#Reference:
        //1916. Count Ways to Build Rooms in an Ant Colony
        //2979. Most Expensive Item That Can Not Be Bought
        //532. K-diff Pairs in an Array
    }
}
