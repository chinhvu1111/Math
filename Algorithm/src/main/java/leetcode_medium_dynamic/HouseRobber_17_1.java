/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcode_medium_dynamic;

/**
 *
 * @author chinhvu
 */
public class HouseRobber_17_1 {

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

    public static class Pair {

        int include;
        int exclude;

        public Pair(int include, int exclude) {
            this.include = include;
            this.exclude = exclude;
        }

    }

    public static TreeNode structTree(Integer root[]) {
        int n = root.length;
        TreeNode nodes[] = new TreeNode[n];

        for (int i = 0; i < n; i++) {
            if (nodes[i] == null) {
                nodes[i] = new TreeNode(root[i]);
            }
            if (2 * i + 1 < n) {
                if (root[2 * i + 1] != null) {
                    nodes[2 * i + 1] = new TreeNode(root[2 * i + 1]);
                } else {
                    nodes[2 * i + 1] = new TreeNode();
                }
                nodes[i].left = nodes[2 * i + 1];
            }
            if (2 * i + 2 < n) {
                if (root[2 * i + 2] != null) {
                    nodes[2 * i + 2] = new TreeNode(root[2 * i + 2]);
                } else {
                    nodes[2 * i + 2] = new TreeNode();
                }
                nodes[i].right = nodes[2 * i + 2];
            }
        }
        return nodes[0];
    }

    //Phần này nhanh hơn vì đỡ các thao tác put vào hashMap với độ phức tạp O(1) nếu nhân theo mỗi vòng đệ quy cũng khá lớn
    //Nếu lần sau làm theo kiểu (include/ exclude) thì nên làm kiểu này
    //NODE: Ta chỉ dùng dynamic trong trường hợp các vòng đệ quy trùng nhau trong việc chạy / các kết quả vòng đệ quy này có thể
    //Dùng lại trong các vòng đệ quy khác
    //Truy cập biến trực tiếp (nhanh) > việc truy cập hashmap
    //Truy cập vào method không phải làm gì có nhanh hay không? --> Về cơ bản sẽ phải phải thực hiện thêm (1 lệnh check kết quả trả về)
    public static Pair solution(TreeNode node) {
        if (node == null) {
            return new Pair(0, 0);
        }
        Pair left = solution(node.left);
        Pair right = solution(node.right);

        int in = left.exclude + right.exclude + node.val;
        int ex = Math.max(left.exclude, left.include) + Math.max(right.exclude, right.include);

        return new Pair(in, ex);
    }

    public static int rob(TreeNode node) {
        Pair rs = solution(node);
        return Math.max(rs.include, rs.exclude);
    }

    public static void main(String[] args) {
        Integer root[] = new Integer[]{3, 4, 5, 1, 3, null, 1};

        TreeNode node = structTree(root);

        System.out.println(rob(node));
    }
}
