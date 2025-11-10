package E2_design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class E11_FindDuplicateFileInSystem {

    public static List<List<String>> findDuplicate(String[] paths) {
        HashMap<String, List<String[]>> contentToPath=new HashMap<>();
        int n=paths.length;

        for (int i = 0; i < n; i++) {
            String path = paths[i];
            StringBuilder filePath=new StringBuilder();
            int j;

            int m=path.length();
            for(j=0;j<m;j++){
                if(path.charAt(j)==' '){
                    break;
                }
                filePath.append(path.charAt(j));
            }
            j++;
            String[] fileMetaDatas = path.substring(j).split(" ");
            for (String fileContent: fileMetaDatas){
                StringBuilder fileName=new StringBuilder();
                StringBuilder contentFile=new StringBuilder();
                int k;
                for (k = 0; k < fileContent.length(); k++) {
                    if(fileContent.charAt(k)=='('){
                        break;
                    }
                    fileName.append(fileContent.charAt(k));
                }
                for (; k < fileContent.length(); k++) {
                    if(fileContent.charAt(k)==')'){
                        break;
                    }
                    contentFile.append(fileContent.charAt(k));
                }
                String content=contentFile.toString();
                List<String[]> listPaths = contentToPath.getOrDefault(content, new ArrayList<>());
                listPaths.add(new String[]{filePath.toString(), fileName.toString()});
                contentToPath.put(content, listPaths);
            }
//            System.out.printf("%s %s %s\n", filePath, fileName, contentFile);
        }
        List<List<String>> rs=new ArrayList<>();

        for(Map.Entry<String, List<String[]>> e: contentToPath.entrySet()){
            List<String> curGroup=new ArrayList<>();
            if(e.getValue().size()==1){
                continue;
            }
            for (String[] s: e.getValue()){
                curGroup.add(s[0]+"/"+s[1]);
            }
            rs.add(curGroup);
        }
        return rs;
    }

    public static List <List < String >> findDuplicateRefer(String[] paths) {
        HashMap < String, List < String >> map = new HashMap < > ();
        for (String path: paths) {
            String[] values = path.split(" ");
            for (int i = 1; i < values.length; i++) {
                String[] name_cont = values[i].split("\\(");
                name_cont[1] = name_cont[1].replace(")", "");
                List < String > list = map.getOrDefault(name_cont[1], new ArrayList < String > ());
                list.add(values[0] + "/" + name_cont[0]);
                map.put(name_cont[1], list);
            }
        }
        List < List < String >> res = new ArrayList < > ();
        for (String key: map.keySet()) {
            if (map.get(key).size() > 1)
                res.add(map.get(key));
        }
        return res;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given (a list paths of directory info), including (the directory path),
        // and all (the files) (with contents) in this directory,
        // return all (the duplicate files) in the file system in terms of their paths.
        //- You may return the answer in any order.
        //- (A group of duplicate files) consists of (at least) (two files) that have (the same content).
        //
        //- (A single directory info string) in the input list has the following format:
        //  + "root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"
        //- It means there are n files (f1.txt, f2.txt ... fn.txt) with content (f1_content, f2_content ... fn_content)
        // respectively in the directory "root/d1/d2/.../dm".
        //
        //* Note that n >= 1 and m >= 0. If m = 0, it means the directory is just the root directory.
        //- The output is a list of groups of duplicate file paths.
        //- For each group, it contains all (the file paths of the files) that have (the same content).
        //- (A file path) is a string that has the following format:
        //  + "directory_path/file_name.txt"
        //- How about the path with (the unique content)?
        //  + Ignore
        //
        //Example 1:
        //
        //Input:
        // paths = [
        //  "root/a 1.txt(abcd) 2.txt(efgh)",
        //  "root/c 3.txt(abcd)",
        //  "root/c/d 4.txt(efgh)",
        //  "root 4.txt(efgh)"]
        //Output:
        // [
        //  ["root/a/2.txt",
        //  "root/c/d/4.txt",
        //  "root/4.txt"
        //  ],
        //  [
        //  "root/a/1.txt",
        //  "root/c/3.txt"]
        //  ]
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= paths.length <= 2 * 10^4
        //1 <= paths[i].length <= 3000
        //1 <= sum(paths[i].length) <= 5 * 10^5
        //paths[i] consist of English letters, digits, '/', '.', '(', ')', and ' '.
        //You may assume no files or directories share the same name in the same directory.
        //You may assume each given directory info represents a unique directory.
        //- A single blank space separates the directory path and file info.
        //
        //* Brainstorm:
        //
        //
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //- Imagine you are given a real file system, how will you search files? DFS or BFS?
        //DFS. In this case the directory path could be large.
        // DFS can reuse the shared the parent directory before leaving that directory. But BFS cannot.
        //
        //- If the file content is very large (GB level), how will you modify your solution?
        //In this case, not realistic to match the whole string of the content.
        // So we use file signitures to judge if two files are identical.
        // Signitures can include file size, as well as sampled contents on the same positions.
        // They could have different file names and time stamps though.
        //Hashmaps are necessary to store the previous scanned file info.
        //  + S = O(|keys| + |list(directory)|).
        //  The key and the directory strings are the main space consumption.
        //
        //a. Sample to obtain the sliced indices in the strings stored in the RAM only once and used for all the scanned files.
        // Accessing the strings is on-the-fly. But transforming them to hashcode used to look up in hashmap and storing the keys
        // and the directories in the hashmap can be time consuming. The directory string can be compressed to directory id.
        // The keys are hard to compress.
        //b. Use fast hashing algorithm e.g.
        // MD5 or use SHA256 (no collisions found yet). If no worry about the collision, meaning the hashcode is 1-1.
        // Thus in the hashmap, the storage consumption on key string can be replaced by key_hashcode, space usage compressed.
        //
        //- If you can only read the file by 1kb each time, how will you modify your solution?
        //That is the file cannot fit the whole ram. Use a buffer to read controlled by a loop;
        // read until not needed or to the end.
        // The sampled slices are offset by the times the buffer is called.
        //
        //- What is the time complexity of your modified solution?
        // What is the most time-consuming part and memory consuming part of it? How to optimize?
        //  + T = O(|num_files||sample||directory_depth|) + O(|hashmap.keys()|)
        //
        //- How to make sure the duplicated files you find are not false positive?
        //Add a round of final check which checks the whole string of the content.
        //  + T = O(|num_output_list||max_list_size||file_size|).
        //
        //1.3, Complexity
        //- Space: O(1)
        //- Time: O(upper-lower+len(diff)) ==> O(n)
        //
        String[] paths = {"root/a 1.txt(abcd) 2.txt(efgh)","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)","root 4.txt(efgh)"};
        System.out.println(findDuplicate(paths));
        System.out.println(findDuplicateRefer(paths));
    }
}
