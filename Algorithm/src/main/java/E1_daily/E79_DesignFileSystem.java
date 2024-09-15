package E1_daily;

public class E79_DesignFileSystem {

    static class FileSystem {

        public FileSystem() {

        }

        public boolean createPath(String path, int value) {
            return true;
        }

        public int get(String path) {
            return 1;
        }
    }

    public static void main(String[] args) {
        //**Requirement
        //- You are asked to design (a file system) that allows you to create (new paths) and associate them (with different values).
        //- The format of a path is (one or more concatenated strings) of the form: / followed by one or more lowercase English letters.
        //  + For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string "" and "/" are not.
        //- Implement the FileSystem class:
        //  + bool createPath(string path, int value) Creates a new path and associates a value to it if possible and returns true.
        //      + Returns false if the path already exists or its parent path doesn't exist.
        //  + int get(string path) Returns the value associated with path or returns -1 if the path doesn't exist.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //2 <= path.length <= 100
        //1 <= value <= 10^9
        //Each path is valid and consists of lowercase English letters and '/'.
        //At most 104 calls in total will be made to createPath and get.
        //
        //- Brainstorm
        //
        //
    }
}
