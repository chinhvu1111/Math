package E1_daily;

import java.util.Arrays;

public class E66_SpiralMatrixIV {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] rs=new int[m][n];
        int[][] dir = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        int x=0;
        int y=0;
        int d=0;
//        int count=0;

        for(int i=0;i<m;i++){
            Arrays.fill(rs[i], Integer.MAX_VALUE);
        }
        rs[0][0]=head.val;
        head=head.next;

        while(head!=null){
            int x1=x+dir[d][0];
            int y1=y+dir[d][1];
            if(x1<0||x1>=m||y1<0||y1>=n||rs[x1][y1]!=Integer.MAX_VALUE){
                d=(d+1)%4;
            }else{
                x=x1;
                y=y1;
                rs[x][y]=head.val;
                head=head.next;
            }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(rs[i][j]==Integer.MAX_VALUE){
                    rs[i][j]=-1;
                }
            }
        }
        return rs;
    }

    public static int[][] spiralMatrixOptimization(int m, int n, ListNode head) {
        int[][] arr = new int[m][n];
        for(int[] row: arr)   Arrays.fill(row,-1);
        int top = 0, left = 0, right = n-1, bottom = m-1;
        while(head != null){
            for(int i=left; i<=right && head != null; i++){
                arr[top][i] = head.val;
                head = head.next;
            }
            top++;
            for(int i=top; i<=bottom && head != null; i++){
                arr[i][right] = head.val;
                head = head.next;
            }
            right--;
            for(int i=right; i>=left && head != null; i--){
                arr[bottom][i] = head.val;
                head = head.next;
            }
            bottom--;
            for(int i=bottom; i>=top && head != null; i--){
                arr[i][left] = head.val;
                head = head.next;
            }
            left++;
        }
        return arr;
    }

    public static void main(String[] args) {
        // Requirement
        //- You are given two integers m and n, which represent the dimensions of a matrix.
        //- You are also given the head of a linked list of integers.
        //- Generate (an m x n matrix) that contains the integers in the linked list presented (in spiral order) (clockwise),
        // starting from (the top-left) of the matrix.
        //- If there are (remaining empty spaces), (fill them with -1).
        //* Return (the generated matrix).
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= m, n <= 10^5
        //1 <= m * n <= 10^5
        //The number of nodes in the list is in the range [1, m * n].
        //0 <= Node.val <= 1000
        //
        //- Brainstorm
        //- 1 điểm sẽ change direction khi:
        //  + Chạm biên
        //  + Next node là điểm đã đi rồi.
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(m*n)
        //- Time: O(m*n)
        //
        int m=10, n=1;
        int[] h={8,24,5,21,10,11,11,12,6,17};
        ListNode head=new ListNode(h[0]);
        ListNode node=head;
        for(int i=1;i<h.length;i++){
            ListNode nextNode=new ListNode(h[i]);
            node.next=nextNode;
            node=nextNode;
        }
//        int[][] rs=spiralMatrix(m, n, head);
        int[][] rs=spiralMatrixOptimization(m, n, head);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%s ", rs[i][j]);
            }
            System.out.print("\n");
        }
        //
    }
}
