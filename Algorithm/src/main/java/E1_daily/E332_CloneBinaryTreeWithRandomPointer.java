package E1_daily;

import java.util.HashMap;
import java.util.Map;

public class E332_CloneBinaryTreeWithRandomPointer {

    public static class Node {
        int val;
        Node left;
        Node right;
        Node random;

        Node() {
        }

        Node(int val) {
            this.val = val;
        }

        Node(int val, Node left, Node right, Node random) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.random = random;
        }
    }

    public static class NodeCopy {
        int val;
        NodeCopy left;
        NodeCopy right;
        NodeCopy random;

        NodeCopy() {
        }

        NodeCopy(int val) {
            this.val = val;
        }

        NodeCopy(int val, NodeCopy left, NodeCopy right, NodeCopy random) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.random = random;
        }
    }

    public static NodeCopy getNode(Node node, HashMap<Node, NodeCopy> indexToNode) {
        if (node == null) {
            return null;
        }
        NodeCopy newCurNode = new NodeCopy(node.val);
        newCurNode.left = getNode(node.left, indexToNode);
        newCurNode.right = getNode(node.right, indexToNode);
        indexToNode.put(node, newCurNode);
        return newCurNode;
    }

    public static NodeCopy copyRandomBinaryTree(Node root) {
        HashMap<Node, NodeCopy> indexToNode = new HashMap<>();
        NodeCopy newRoot = getNode(root, indexToNode);

        for(Map.Entry<Node, NodeCopy> e: indexToNode.entrySet()){
            Node nextNode = e.getKey().random;
            // System.out.println(e.getKey().val);
            if(nextNode==null){
                continue;
            }
            // System.out.println(indexToNode.get(e.getKey()).val);
            e.getValue().random= indexToNode.get(nextNode);
        }
        return newRoot;
    }

    private HashMap<Node, NodeCopy> newOldPairs = new HashMap<>();

    private  NodeCopy deepCopy(Node root) {
        if (root == null) {
            return null;
        }
        NodeCopy newRoot = new NodeCopy(root.val);
        // Deep copy left subtree and attach it on new node's left.
        newRoot.left = deepCopy(root.left);
        // Deep copy right subtree and attach it on new node's right.
        newRoot.right = deepCopy(root.right);
        // Store the new node and old node's pair in hash map.
        newOldPairs.put(root, newRoot);
        return newRoot;
    }

    private void mapRandomPointers(Node oldNode) {
        if (oldNode == null) {
            return;
        }
        NodeCopy newNode = newOldPairs.get(oldNode);
        Node oldNodeRandom = oldNode.random;
        NodeCopy newNodeRandom = newOldPairs.get(oldNodeRandom);
        // Map newNode with it's respective random node.
        newNode.random = newNodeRandom;
        // Traverse on rest nodes to map their respective new node's random pointers.
        mapRandomPointers(oldNode.left);
        mapRandomPointers(oldNode.right);
    }

    public NodeCopy copyRandomBinaryTreeRefer(Node root) {
        // Create a new tree for each node of old tree and map new node with old respective node.
        NodeCopy newRoot = deepCopy(root);
        // We will assign random pointers of new tree to respective correct new nodes.
        mapRandomPointers(root);
        return newRoot;
    }

    public static void main(String[] args) {
        //** Requirement
        //- A binary tree is given such that each node contains an additional random pointer
        // which could point to any node in the tree or null.
        //* Return a deep copy of the tree.
        //- The tree is represented in the same input/output way as normal binary trees
        // where each node is represented as a pair of [val, random_index] where:
        //  + val: an integer representing Node.val
        //  + random_index: the index of the node (in the input) where the random pointer points to,
        // or null if it does not point to any node.
        //You will be given the tree in class Node and you should return the cloned tree in class NodeCopy.
        //- NodeCopy class is just a clone of Node class with the same attributes and constructors.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //The number of nodes in the tree is in the range [0, 1000].
        //1 <= Node.val <= 10^6
        //  + the number of node <= 1000 ==> Time: O(n^2)
        //
        //* Brainstorm:
        //- HashMap from index ==> Node.
        //- How to traverse to (all of nodes) in the binary tree
        //- Random is not integer ==> We don't need to user DFS and BFS at the same time:
        //  + DFS ==> Traverse
        //  + BFS ==> For index
        //
        //1.1, Case
        //
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
    }
}
