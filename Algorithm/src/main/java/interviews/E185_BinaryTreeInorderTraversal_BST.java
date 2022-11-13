package interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class E185_BinaryTreeInorderTraversal_BST {

    public static class TreeNode {
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

    public static List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack=new Stack<>();
        List<TreeNode> result=new ArrayList<>();
        TreeNode tmp=root;

        while (tmp!=null){
            System.out.println(tmp.val);
            TreeNode currentTmp=tmp;

            //Viết kiểu này sẽ bị sai
            //tmp =2
            //--> add thêm 2 là sai
            while (currentTmp!=null){
                stack.add(currentTmp);
                currentTmp=currentTmp.left;
            }

            boolean isRight=false;

            while (!stack.isEmpty()){
                if(stack.peek().right!=null){
                    tmp=stack.peek().right;
                    result.add(stack.pop());
                    isRight=true;
                    break;
                }else{
                    result.add(stack.pop());
                }
            }
            // 1 đường thẳng đứng
            //VD:
            //        a
            //     (b)
            //   c
            if(stack.isEmpty()&&!isRight){
                tmp=null;
            }
        }
        List<Integer> rs=result.stream().map(treeNode -> treeNode.val).collect(Collectors.toList());
        return rs;
    }

    public static List<Integer> inorderTraversalOptimization(TreeNode root) {
        Stack<TreeNode> stack=new Stack<>();
        List<TreeNode> result=new ArrayList<>();
        TreeNode tmp=root;

        while (tmp!=null){
            TreeNode currentTmp=tmp;

            //Viết kiểu này sẽ bị sai
            //tmp =2
            //--> add thêm 2 là sai
            while (currentTmp!=null){
                stack.add(currentTmp);
                currentTmp=currentTmp.left;
            }
            tmp=null;

            while (!stack.isEmpty()){
                if(stack.peek().right!=null){
                    tmp=stack.peek().right;
                    result.add(stack.pop());
                    break;
                }else{
                    result.add(stack.pop());
                }
            }
        }
        List<Integer> rs=result.stream().map(treeNode -> treeNode.val).collect(Collectors.toList());
        return rs;
    }

    public static List<Integer> inorderTraversalOptimization2(TreeNode root) {
        Stack<TreeNode> stack=new Stack<>();
        List<Integer> result=new ArrayList<>();
        TreeNode tmp=root;

        while (tmp!=null||!stack.isEmpty()){
            TreeNode currentTmp=tmp;

            //Viết kiểu này sẽ bị sai
            //tmp =2
            //--> add thêm 2 là sai
            while (currentTmp!=null){
                stack.add(currentTmp);
                currentTmp=currentTmp.left;
            }
            tmp=null;
            if(!stack.isEmpty()){
                tmp=stack.peek().right;
                result.add(stack.pop().val);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Integer[] arr =new Integer[]{1,null,2, null, null, null, 3};
        TreeNode[] treeNodes=new TreeNode[arr.length];
        for(int i=0;i<treeNodes.length;i++) treeNodes[i]=new TreeNode(0);

        TreeNode root=null;

        for(int i=arr.length-1;i>=0;i--){
            if(arr[i]==null){
                continue;
            }
            TreeNode currentNode=treeNodes[i];
            currentNode.val=arr[i];

            TreeNode treeNodeNext1=null;
            if(2*i+1<arr.length&&arr[2*i+1]!=null){
                treeNodeNext1=treeNodes[2*i+1];
                treeNodeNext1.val=arr[2*i+1];
            }

            TreeNode treeNodeNext2=null;
            if(2*i+2<arr.length){
                treeNodeNext2=treeNodes[2*i+2];
                treeNodeNext2.val=arr[2*i+2];
            }
            currentNode.left=treeNodeNext1;
            currentNode.right=treeNodeNext2;
            if(i==0){
                root=currentNode;
            }
        }

        System.out.println(inorderTraversal(root));

        //
        //** Đề bài
        //VD:
        //      a
        //  b       c
        //e   f   g   h
        //==> e b f a (g c h)
        //- inorder traversal
        //
        //** Bài này tư duy như sau:
        //
        //1,
        //1.1
        //Các bước tư duy như sau:
        //- Dùng stack để left node --> null thì thôi.
        //+ Luôn add root vào stack ==> Để có thể đến left liên tục
        //+ Root chính là tmp node sau khi right
        //
        //- pop từ stack ra + add vào result --> Cho đến khi tìm được node có (right != null)
        //==> Node đó vẫn phải add vào result : result.add(stack.pop)
        //+ Sau đó right đó sẽ thành new root.
        //** New root ==> Sẽ được ( add vào stack + left --> Đến khi null lại từ đầu)
        //
        //- tmp --> Cần restart khi (stack == empty && không còn right nữa)
        //
        //2,
        //Refactor
        //2.1,
        //- Sửa lại đoạn tmp trong while gán là null
        //--> Gán cho right --> tmp mới khác null
        //<> thì end.
        System.out.println(inorderTraversalOptimization(root));
        //2.2,
        //Ta có thể sửa lại
        //+ Điểm nào ta cũng kiểm tra right!=null <> add vào result + stack pop
        //==> Không cần đoạn while ngay trong while nữa
        //+ Cần có thểm điều kiện ( tmp!=null||!stack.isEmpty() )
        //==> Vì khi left --> null (Đến chết) thì ( tmp ==null ) ===> Vẫn cần add vào result mặc dù không còn right nữa.
        //+ Result chỉ cần Integer là đủ ---> Không điền TreeNode.

        System.out.println(inorderTraversalOptimization2(root));
    }
}
