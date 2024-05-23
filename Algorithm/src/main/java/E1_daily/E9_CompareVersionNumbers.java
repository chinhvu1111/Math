package E1_daily;

public class E9_CompareVersionNumbers {

    public static int compareVersion(String version1, String version2) {
        String[] v1=version1.split("\\.");
        String[] v2=version2.split("\\.");
        int p=0,p1=0;

        while (p<v1.length&&p1<v2.length){
            String curRevision1=v1[p];
            String curRevision2=v2[p];
            int i=0,j=0;

            while (i<curRevision1.length()&&curRevision1.charAt(i)=='0'){
                i++;
            }
            while (j<curRevision2.length()&&curRevision2.charAt(j)=='0'){
                j++;
            }
            if(curRevision1.length()-i>curRevision2.length()-j){
                return 1;
            }
            if(curRevision1.length()-i<curRevision2.length()-j){
                return -1;
            }
            while(i<curRevision1.length()&&j<curRevision2.length()){
                if(curRevision1.charAt(i)<curRevision2.charAt(j)){
                    return -1;
                }else if(curRevision1.charAt(i)>curRevision2.charAt(j)){
                    return 1;
                }
                i++;
                j++;
            }
            for(;i<curRevision1.length();i++){
                if(curRevision1.charAt(i)!=0){
                    return 1;
                }
            }
            for(;j<curRevision2.length();j++){
                if(curRevision2.charAt(j)!=0){
                    return -1;
                }
            }
            p++;
            p1++;
        }
        if(v1.length>v2.length){
            while (p<v1.length){
                String curRevision1=v1[p];
                for(int i=0;i<curRevision1.length();i++){
                    if(curRevision1.charAt(i)!='0'){
                        return 1;
                    }
                }
                p++;
            }
        }else{
            while (p1<v2.length){
                String curRevision2=v2[p1];
                for(int i=0;i<curRevision2.length();i++){
                    if(curRevision2.charAt(i)!='0'){
                        return -1;
                    }
                }
                p1++;
            }
        }
        return 0;
    }

    public static int compareVersionOptimization(String version1, String version2) {
        String[] v1=version1.split("\\.");
        String[] v2=version2.split("\\.");
        int p=0,p1=0;

        while (p<v1.length&&p1<v2.length){
            String curRevision1=v1[p];
            String curRevision2=v2[p];
            int i=0,j=0;

            while (i<curRevision1.length()&&curRevision1.charAt(i)=='0'){
                i++;
            }
            while (j<curRevision2.length()&&curRevision2.charAt(j)=='0'){
                j++;
            }
            if(curRevision1.length()-i>curRevision2.length()-j){
                return 1;
            }
            if(curRevision1.length()-i<curRevision2.length()-j){
                return -1;
            }
            while(i<curRevision1.length()&&j<curRevision2.length()){
                if(curRevision1.charAt(i)<curRevision2.charAt(j)){
                    return -1;
                }else if(curRevision1.charAt(i)>curRevision2.charAt(j)){
                    return 1;
                }
                i++;
                j++;
            }
            for(;i<curRevision1.length();i++){
                if(curRevision1.charAt(i)!=0){
                    return 1;
                }
            }
            for(;j<curRevision2.length();j++){
                if(curRevision2.charAt(j)!=0){
                    return -1;
                }
            }
            p++;
            p1++;
        }
        if(v1.length>v2.length){
            while (p<v1.length){
                String curRevision1=v1[p];
                for(int i=0;i<curRevision1.length();i++){
                    if(curRevision1.charAt(i)!='0'){
                        return 1;
                    }
                }
                p++;
            }
        }else{
            while (p1<v2.length){
                String curRevision2=v2[p1];
                for(int i=0;i<curRevision2.length();i++){
                    if(curRevision2.charAt(i)!='0'){
                        return -1;
                    }
                }
                p1++;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given two version numbers, (version1) and (version2), compare them.
        //- Version numbers consist of one or more revisions joined by a dot '.'.
        //- Each revision consists of digits and may contain (leading zeros).
        //- Every revision contains (at least one character).
        //- Revisions are 0-indexed from left to right, with the leftmost revision being revision 0, the next revision being revision 1, and so on.
        // + For example 2.5.33 and 0.1 are valid version numbers.
        //To compare version numbers, compare their revisions in (left-to-right) order.
        //- Revisions are compared using their integer value ignoring any leading zeros.
        // => This means that revisions 1 and 001 are considered equal.
        //- If a version number does not specify (a revision) at an index,
        // then treat the revision as 0.
        // + For example, version 1.0 is (less than) version 1.1 because their revision 0s are the same,
        // but their revision 1s are 0 and 1 respectively, and 0 < 1.
        //- Return the following:
        //  + If version1 < version2, return -1.
        //  + If version1 > version2, return 1.
        //  + Otherwise, return 0.
        //
        //** Idea
        //1.
        //1.0,
        //* Constraint
        //1 <= version1.length, version2.length <= 500
        //version1 and version2 only contain digits and '.'.
        //version1 and version2 are valid version numbers.
        //All the given revisions in version1 and version2 can be stored in a 32-bit integer.
        //
        //* Brainstorm
        //- Bài này split đơn giản thôi
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space : O(1)
        //- Time : O(n+m)
        //
//        String version1 = "1.01", version2 = "1.001";
//        String version1 = "1.0", version2 = "1.0.0";
//        String version1 = "0.1", version2 = "1.1";
//        String version1 = "1.0001", version2 = "1.1";
        //return 0
//        String version1 = "1.00011", version2 = "1.1";
        //return 1
//        String version1 = "1.0001.2", version2 = "1.1";
//        String version1 = "1.0001.2", version2 = "1.1";
        //- Corner case 1:
//        String version1 = "1.2", version2 = "1.10";
        //- Corner case 2:
        String version1 = "1", version2 = "1.1";
        System.out.println(compareVersion(version1, version2));
        System.out.println(compareVersionOptimization(version1, version2));
        //#Reference:
        //953. Verifying an Alien Dictionary
        //2416. Sum of Prefix Scores of Strings
        //691. Stickers to Spell Word
    }
}
