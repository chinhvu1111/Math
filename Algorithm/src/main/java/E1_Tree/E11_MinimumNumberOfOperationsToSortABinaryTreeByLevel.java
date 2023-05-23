package E1_Tree;

import java.util.*;

public class E11_MinimumNumberOfOperationsToSortABinaryTreeByLevel {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int minimumOperations(TreeNode root) {
        if(root==null){
            return 0;
        }
        return solution(root);
    }

    public static int solution(TreeNode node){
        Queue<TreeNode> listNodes=new LinkedList<>();
        listNodes.add(node);
        int numOps=0;

        while (!listNodes.isEmpty()){
            List<TreeNode> nextLevelNodes=new ArrayList<>();
            int tmpOps=0;

            while (!listNodes.isEmpty()){
                TreeNode currentNode=listNodes.poll();

                if(currentNode.left!=null){
                    nextLevelNodes.add(currentNode.left);
                }
                if(currentNode.right!=null){
                    nextLevelNodes.add(currentNode.right);
                }
            }
            int[] tmp=new int[nextLevelNodes.size()];
            int[] tmp1=new int[nextLevelNodes.size()];
            HashMap<Integer, Integer> mapValueIndex= new HashMap<>();

            for(int i=0;i<nextLevelNodes.size();i++){
                tmp[i]=nextLevelNodes.get(i).val;
                tmp1[i]=nextLevelNodes.get(i).val;
                // System.out.printf("%s, ", tmp1[i]);
                mapValueIndex.put(tmp1[i], i);
            }
//            System.out.println();
            Arrays.sort(tmp);
            // for(int i=0;i<tmp1.length;i++){
            //     System.out.printf("%s,", tmp1[i]);
            // }
            // System.out.println();

            for(int i=0;i<tmp1.length;i++){
                if(tmp1[i]!=tmp[i]){
                    int indexSwap=mapValueIndex.get(tmp[i]);

                    int tmpVal=tmp1[i];
                    tmp1[i]=tmp1[indexSwap];
                    tmp1[indexSwap]=tmpVal;
                    tmpOps++;
                    mapValueIndex.put(tmpVal, indexSwap);
                    mapValueIndex.put(tmp1[i], i);
                }
                // System.out.printf("%s,", tmp1[i]);
            }
            // System.out.println();
            numOps+=tmpOps;
            listNodes.addAll(nextLevelNodes);
        }
        return numOps;
    }

    public static void main(String[] args) {
        //** Đề bài
        //- Trả lại số lượng swap operation ít nhất để swap 2 nodes bất kỳ trong cùng 1 level để:
        //+ Biến values của chúng tăng dần trong cùng 1 level.
        //
        //** Tư duy
        //1.
        //1.0, Idea
        //- Ta sẽ tách tree thành list các nodes trên từng level để chuyển bài toán thành:
        //+ Tìm số lượng swap operations min nhất để làm 1 list thành 1 list of nodes tăng dần
        //VD:
        //(7), 6, 8, (5)
        //==> 5, 6, (8), (7)
        //==> 5, 6, 7, 8
        //
        //--> Đơn giản là ta sort sau đó là tìm các (số các số khác nhau - 1) ==> Chính là số operations.
        //==> Tư duy này sai ==> Ngay cả ta có chia trường hợp (lẻ/chẵn) (/2 or -1) : SAI.
        //
        //- Đếm số min swap để sort 1 array:
        //VD:
        //(7), 6, 8, (5)
        //--> Để chuyển thành
        //5, 6, 7, 8
        //
        //VD:
        //(20), 46, (15), 39 --> 15, 20, 39, 46
        //15, (46), (20), 39
        //15, 20, (46), (39)
        //15, 20, 39, 46 ==> 3 lần
        //
        //VD:
        //7, 6, 5, 4 --> 4, 5, 6, 7
        //(4), 6, 5, (7)
        //4, (5), (6), 7 --> Không cần thay đổi gì cả
        //4, (5), (6), 7 ==> 2 lần
        //- Để đếm số lần swap thì có 2 hướng tư duy:
        //+ Là count theo số lần 2 array khác nhau --> Failed như trên
        //+ Chạy swap thật sự ==> Thì sẽ đúng vì
        //VD: Sẽ có case sau khi swap (i) cho (j>i)
        //--> Về sau ta xét đến (j) đã thoả mãn rồi (Do bị ảnh hưởng bởi phép swap trước đó)
        //==> Số lượng swap count có thể giảm đi.
        //
        //1.1, Complexity:
        //- Time complexity : O(n)
        //- Space complexity : O(n)
        //+ n is the number of nodes.
        //
        //#Reference:
        //2472. Maximum Number of Non-overlapping Palindrome Substrings
        //2360. Longest Cycle in a Graph
    }
}
