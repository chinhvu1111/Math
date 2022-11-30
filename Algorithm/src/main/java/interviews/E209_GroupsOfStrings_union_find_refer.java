package interviews;

import java.util.HashMap;
import java.util.Map;

public class E209_GroupsOfStrings_union_find_refer {

    public static int[] groupStrings(String[] words) {
        int n = words.length;
        Map<Integer, Integer> maskToIndex = new HashMap<>();
        int[] masks = new int[n];
        for (int i = 0; i < n; i++) {
            for (char ch : words[i].toCharArray()) {
                masks[i] |= (1 << ch - 'a');
            }
            maskToIndex.put(masks[i], i);
        }

        long startTime=System.currentTimeMillis();
        long endTime=System.currentTimeMillis();
        DisjointSet disjointSet = new DisjointSet(n);
        for (int i = 0; i < n; i++) {
            // This is necessary to union the duplicate words
            disjointSet.union(i, maskToIndex.get(masks[i]));
            for (char ch : words[i].toCharArray()) {
                // Removing ch from the word
                int maskWithoutCh = masks[i] ^ (1 << ch - 'a');
                if (maskToIndex.containsKey(maskWithoutCh)) {
                    disjointSet.union(i, maskToIndex.get(maskWithoutCh));
                }

                // Replace ch with any other charactor
                for (int j = 0; j < 26; j++) {
                    // Skip if the word already contains the char at j
                    if (j == ch - 'a' || (maskWithoutCh | (1 << j)) == maskWithoutCh) {
                        continue;
                    }
                    int maskWithReplace = maskWithoutCh | (1 << j);
                    if (maskToIndex.containsKey(maskWithReplace)) {
                        disjointSet.union(i, maskToIndex.get(maskWithReplace));
                    }
                }
            }
            if(i%1000==0){
                endTime=System.currentTimeMillis();
                System.out.println("Run in :"+(endTime-startTime)+ " seconds");
            }
        }
        endTime=System.currentTimeMillis();
        System.out.println("Run in :"+(endTime-startTime)+ "millis seconds");
        return disjointSet.getState();
    }

    private static final class DisjointSet {
        private int[] parent;
        private int[] size;
        private int groupCount;
        private int maxSize;

        DisjointSet(int n) {
            groupCount = n;
            maxSize = 1;
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int a) {
            if (parent[a] != a) {
                parent[a] = find(parent[a]);
            }
            return parent[a];
        }

        public void union(int a, int b) {
            if (a == b) {
                return;
            }
            int pa = find(a);
            int pb = find(b);
            if (pa != pb) {
                // Improving the runtime for find operations by joining the small group to large one
                if (size[pb] < size[pa]) {
                    parent[pa] = pb;
                    size[pb] += size[pa];
                    maxSize = Math.max(maxSize, size[pb]);
                } else {
                    parent[pb] = pa;
                    size[pa] += size[pb];
                    maxSize = Math.max(maxSize, size[pa]);
                }
                groupCount--;
            }
        }

        public int[] getState() {
            return new int[]{groupCount, maxSize};
        }
    }

    public static void main(String[] args) {
        String[] words=new String[]{"ghnv","uip","tenv","hvepx","e","ktc","byjdt","ulm","cae","ea"};
        int[] result=groupStrings(words);
        System.out.println(result[0]+" "+result[1]+"\n");
    }
}

