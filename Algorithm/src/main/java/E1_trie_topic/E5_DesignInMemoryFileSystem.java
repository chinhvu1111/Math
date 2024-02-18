package E1_trie_topic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class E5_DesignInMemoryFileSystem {
    public static class FileSystem {

        class Node{

            public boolean isFile;
            String content;
            String curPath;
            HashMap<String, Node> children;
            public Node(String curPath, HashMap<String, Node> children){
                this.curPath=curPath;
                this.children=children;
                this.content="";
                isFile=false;
            }
        }

        public Node root;

        public FileSystem() {
            root=new Node("", new HashMap<>());
        }

        public List<String> ls(String path) {
            String[] folders=path.split("/");
            int n=folders.length;
            Node node=root;

            for(int i=1;i<n;i++){
                Node child=node.children.get(folders[i]);
                node=child;
            }
            List<String> result=new ArrayList<>(node.children.keySet());
            if(result.size()!=0){
                Collections.sort(result);
            }else if(n != 0 && node.isFile){
                result.add(folders[n-1]);
            }
            return result;
        }

        public void mkdir(String path) {
            String[] folders=path.split("/");
            int n=folders.length;
            Node node=root;

            for(int i=1;i<n;i++){
                Node child=node.children.get(folders[i]);
                if(child==null){
                    child=new Node(folders[i], new HashMap<>());
                }
                node.children.put(folders[i], child);
                node=child;
            }
        }

        public void addContentToFile(String filePath, String content) {
            String[] folders=filePath.split("/");
            int n=folders.length;
            Node node=root;

            for(int i=1;i<n;i++){
                Node child=node.children.get(folders[i]);
                if(child==null){
                    child=new Node(folders[i], new HashMap<>());
                }
                node.children.put(folders[i], child);
                node=child;
            }
            node.content+=content;
            node.isFile=true;
        }

        public String readContentFromFile(String filePath) {
            String[] folders=filePath.split("/");
            int n=folders.length;
            Node node=root;

            for(int i=1;i<n;i++){
                Node child=node.children.get(folders[i]);
                if(child==null){
                    child=new Node(folders[i], new HashMap<>());
                }
                node=child;
            }
            return node.content;
        }
    }

    public static void main(String[] args) {
        //** Requirement
        //- Design a data structure that simulates an in-memory file system.
        //
        //- Implement the FileSystem class:
        //  - FileSystem() Initializes the object of the system.
        //  - List<String> ls(String path)
        //      - If path is a file path, returns a list that only contains this file's name.
        //      - If path is a directory path, returns the list of file and directory names in this directory.
        //  The answer should in lexicographic order.
        //  - void mkdir(String path) Makes a new directory according to the given path.
        //  The given directory path does not exist. If the middle directories in the path do not exist, you should create them as well.
        //  - void addContentToFile(String filePath, String content)
        //      - If filePath does not exist, creates that file containing given content.
        //      - If filePath already exists, appends the given content to original content.
        //  - String readContentFromFile(String filePath) Returns the content in the file at filePath.
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= path.length, filePath.length <= 100
        //path and filePath are absolute paths which begin with '/' and do not end with '/' except that the path is just "/".
        //  ==> Handle ít hơn
        //You can assume that all directory names and file names only contain lowercase letters, and the same names will not exist in the same directory.
        //  ==> xử lý đơn giản hơn với 26 characters
        //You can assume that all operations will be passed valid parameters, and users will not attempt to retrieve file content or list a directory or file that does not exist.
        //  ==> Read file, list file luôn exists.
        //1 <= content.length <= 50
        //At most 300 calls will be made to ls, mkdir, addContentToFile, and readContentFromFile.
        //
        //- Brainstorm
        //- Build trie:
        // node - a - node - c
        //     \
        //      b - node
        //
//        FileSystem fileSystem=new FileSystem();
////        System.out.println(fileSystem.ls("/"));
//        fileSystem.mkdir("/a/b/c");
//        System.out.println(fileSystem.ls("/a"));
//        fileSystem.addContentToFile("/a/b/c/d", "hello");
//        System.out.println(fileSystem.ls("/"));
//        System.out.println(fileSystem.readContentFromFile("/a/b/c/d"));
        //
        //- Special cases:
        //+ Cần có define là edge là file or not + content
        //+ split("/"): "/"(len=0), "/a"(len=2)
        //
        //1.1, Optimization
        //1.2, Complexity
        //- L is the number of nested folder
        //- Time:
        //+ ls: O(L)
        //+ mkdir: O(L)
        //+ addContentToFile: O(L)
        //+ readContentFromFile: O(L)
        //
        //#Referencea:
        //460. LFU Cache
        //635. Design Log Storage System
        FileSystem fileSystem=new FileSystem();
//        System.out.println(fileSystem.ls("/"));
        fileSystem.mkdir("/a");
        System.out.println(fileSystem.ls("/a"));
        //#Reference:
        //460. LFU Cache
        //635. Design Log Storage System
    }
}
