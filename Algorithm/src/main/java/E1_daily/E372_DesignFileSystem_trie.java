package E1_daily;

import java.util.HashMap;

public class E372_DesignFileSystem_trie {

    public static class TrieNode{
        HashMap<String, TrieNode> children;
        int value=-1;
    }
    static class FileSystem {

        public static TrieNode root;

        public FileSystem() {
            root=new TrieNode();
        }

        public boolean createPath(String path, int value) {
            TrieNode node = root;
            String[] subPath = path.split("/");
            boolean isValid = true;
            int n=subPath.length;
//            System.out.println(n);

            for(int i=1;i<n;i++){
                String s = subPath[i];
                if(i==n-1){
                    if(node.children==null){
                        node.children=new HashMap<>();
                    }else if(node.children.containsKey(s)){
                        isValid = false;
                        break;
                    }
                    TrieNode newNode = new TrieNode();
                    newNode.value=value;
                    node.children.put(s, newNode);
                    node=newNode;
                }else if(node.children==null||!node.children.containsKey(s)){
                    isValid = false;
                    break;
                }else{
                    node=node.children.get(s);
                }
            }
            return isValid;
        }

        public int get(String path) {
            TrieNode node = root;
            String[] subPath = path.split("/");
            boolean isValid = true;
            int n=subPath.length;

            for(int i=1;i<n;i++) {
                String s = subPath[i];
                //a/b/c
                //==> a/b/d : not exists => false
                //==> a/b/d : not exists => false
                if (node.children == null || !node.children.containsKey(s)) {
                    isValid = false;
                    break;
                } else {
                    node = node.children.get(s);
                }
            }
            if(isValid){
                return node.value;
            }
            return -1;
        }
    }

    /**
     * Your FileSystem object will be instantiated and called as such:
     * FileSystem obj = new FileSystem();
     * boolean param_1 = obj.createPath(path,value);
     * int param_2 = obj.get(path);
     */

    public static void main(String[] args) {
        //** Requirement
        //- You are asked to design a file system that allows you to
        // create new paths and associate them with different values.
        //- The format of a path is one or more concatenated strings of the form:
        //  + / followed by one or more lowercase English letters.
        //  For example, "/leetcode" and "/leetcode/problems" are valid paths
        //  while an empty string "" and "/" are not.
        //
        //- Implement the FileSystem class:
        //  + bool createPath(string path, int value) Creates a new path and associates a value to it if possible
        //  and returns true. Returns false if the path already exists or its parent path doesn't exist.
        //  + int get(string path) Returns the value associated with path
        //  or returns -1 if the path doesn't exist.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //2 <= path.length <= 100
        //1 <= value <= 10^9
        //Each path is valid and consists of lowercase English letters and '/'.
        //At most 10^4 calls in total will be made to createPath and get.
        //  + path.length <= 100, call 10^4 times ==> Time: O(n*len)
        //
        //* Brainstorm:
        //
        //
        //1.1, Case
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Time: O(n)
        //- Space: O(1)
        //
        FileSystem fileSystem = new FileSystem();
        fileSystem.createPath("/leet", 1); // return true
        fileSystem.createPath("/leet/code", 2); // return true
        System.out.println(fileSystem.get("/leet/code")); // return 2
        fileSystem.createPath("/leet/code", 3); // return true
        System.out.println(fileSystem.get("/leet/code")); // return 2
//        fileSystem.createPath("/c/d", 1); // return false because the parent path "/c" doesn't exist.
//        System.out.println(fileSystem.get("/c")); // return -1 because this path doesn't exist.
        TrieNode node = FileSystem.root;
        System.out.println(node);
    }
}
