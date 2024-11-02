package E1_daily;

public class E124_SentenceSimilarityIII {

    public static boolean areSentencesSimilarFix(String sentence1, String sentence2) {
        int n=sentence1.length();
        int m=sentence2.length();

        if(n==m){
            return sentence1.equals(sentence2);
        }
        //s1, s2 in order
        if(n>m){
            String temp=sentence1;
            sentence1=sentence2;
            sentence2=temp;
//            int tempSize = n;
//            n=m;
//            m=tempSize;
        }
        String[] s1=sentence1.split(" ");
        String[] s2=sentence2.split(" ");
        n=s1.length;
        m=s2.length;
        //Insert to first
        boolean isValid=true;

        for(int i=0;i<n;i++){
            if(!s1[n-i-1].equals(s2[m-i-1])){
                isValid=false;
                break;
            }
        }
        if(isValid){
            return true;
        }
        isValid=true;
        //Insert to last
        for(int i=0;i<n;i++){
            if(!s1[i].equals(s2[i])){
                isValid=false;
                break;
            }
        }
        if(isValid){
            return true;
        }
        //abc
        //  + left=1 ==> right=3-left-1 = 1
        //abcd
//        int size=n%2==1?(n/2)+1:n/2;
//        System.out.println(sentence1);
//        System.out.println(sentence2);
//        System.out.printf("Size: %s\n",size);
        int i,j;
        for(i=0;i<n;i++){
            if(!s1[i].equals(s2[i])){
//                System.out.printf("Break: %s\n",i);
                break;
            }
        }
//        for(j=0;n-j-1>i;j++){
        for(j=0;j<n;j++){
            if(!s1[n-j-1].equals(s2[m-j-1])){
//                System.out.printf("Break1: %s\n",i);
//                System.out.println();
                break;
            }
        }
        System.out.printf("i:%s, j:%s\n", i, j);
        if(i==0||j==0){
            return false;
        }
        //If we use this condition"
        //==> i>=n-j-1 ==> FALSE
        if(i>n-j-1){
            return true;
        }
        //sentence1 = " My name is Haley",
        //sentence2 = "My Haley"
        //sentence1 = "cy name is Haley",
        //sentence2 = "My Haley"
        return false;
    }

    public static boolean areSentencesSimilarWrong(String sentence1, String sentence2) {
        int n=sentence1.length();
        int m=sentence2.length();

        if(n==m){
            return sentence1.equals(sentence2);
        }
        //s1, s2 in order
        if(n>m){
            String temp=sentence1;
            sentence1=sentence2;
            sentence2=temp;
            int tempSize = n;
            n=m;
            m=tempSize;
        }
        //Insert to first
        boolean isValid=true;
        int count=0;

        for(int i=0;i<n;i++){
            if(sentence1.charAt(n-i-1)!=sentence2.charAt(m-i-1)){
                count++;
            }
            if(count>=2){
                isValid=false;
            }
        }
        if(isValid){
            return true;
        }
        isValid=true;
        count=0;
        //Insert to last
        for(int i=0;i<n;i++){
            if(sentence1.charAt(i)!=sentence2.charAt(i)){
                count++;
            }
            if(count>=2){
                isValid=false;
            }
        }
        if(isValid){
            return true;
        }
        //abc
        //  + left=1 ==> right=3-left-1 = 1
        //abcd
//        int size=n%2==1?(n/2)+1:n/2;
//        System.out.println(sentence1);
//        System.out.println(sentence2);
//        System.out.printf("Size: %s\n",size);
        int i,j;
        for(i=0;i<n;i++){
            if(sentence1.charAt(i)!=sentence2.charAt(i)){
//                System.out.printf("Break: %s\n",i);
                break;
            }
        }
        for(j=0;n-j-1>i;j++){
            if(sentence1.charAt(n-j-1)!=sentence2.charAt(m-j-1)){
//                System.out.printf("Break1: %s\n",i);
//                System.out.println();
                break;
            }
        }
        System.out.printf("i:%s, j:%s\n", i, j);
        if(i==0||j==0||i+1!=n-j-1){
            return false;
        }
        if(n-j<n&&sentence1.charAt(i-1)==' '&&sentence1.charAt(n-j)==' '){
            return true;
        }
        //sentence1 = " My name is Haley",
        //sentence2 = "My Haley"
        //sentence1 = "cy name is Haley",
        //sentence2 = "My Haley"
        return false;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (two strings sentence1 and sentence2), each representing (a sentence composed of words).
        //- A sentence is (a list of words) that are separated by (a single space) with (no leading) or (trailing spaces).
        //- Each word consists of (only uppercase and lowercase) English characters.
        //- Two sentences s1 and s2 are considered similar if it is possible to insert (an arbitrary sentence) (possibly empty) inside one of these sentences
        // such that (the two sentences become equal).
        //* Note that (only one) (the inserted sentence) must be separated from (existing words) (by spaces).
        //  + Insert làm sao cho (inserted word) sẽ cách các word còn lại (1 space)
        //      + (Nếu có cạnh word nào) : 1 space
        //      + Add vào first/ last: 0 space
        //For example,
        //  + s1 = "Hello Jane" and s2 = "Hello my name is Jane" can be made equal by inserting "my name is" between "Hello" and "Jane" in s1.
        //  + s1 = "Frog cool" and s2 = "Frogs are cool" are not similar, since although there is a sentence "s are" inserted into s1,
        //  it is not separated from "Frog" by a space.
        //- Given two sentences sentence1 and sentence2,
        //* Return true (if sentence1 and sentence2 are similar).
        //  Otherwise, return false.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= sentence1.length, sentence2.length <= 100
        //sentence1 and sentence2 consist of lowercase and uppercase English letters and spaces.
        //The words in sentence1 and sentence2 are separated by a single space.
        //  + Length của sentence1 và sentence2 small --> Time: O(n^3)
        //
        //- Brainstorm
        //- Bài này có thể chuyển thành bài matching sentence được.
        //- Ta có thể inserted 1 trong 2 sentences
        //  + Because we only insert one of these sentences
        //  ==> We will choose to insert to the shorter sentence.
        //
        //Example 1:
        //Input:
        //sentence1 = "My name is Haley",
        //sentence2 = "My Haley"
        //
        //sentence1 = "(My) My name is (chinh Haley)",
        //sentence2 = "My chinh Haley"
        //
        //sentence1 = "(My) My name is chinh abc Haley",
        //sentence2 = "My chinh Haley"
        //- Ta có thể có các cases insert như sau:
        //  + Add to first
        //  + Add to last
        //  + Add to middle
        //- (inserted x)y = z
        //- y(inserted x) = z
        //- y1(inserted x)y2 = z
        //  + two pointers từ first and last
        //  + Gặp được char nào khác nhau:
        //      + sentence1[left]!=sentence2[left] ==> break
        //      + sentence1[n-right-1]!=sentence2[m-right-1] ==> break
        //      + left==n/2+1 => return true <> false
        //
//        String sentence1 = "My name is Haley", sentence2 = "My Haley";
//        String sentence1 = "Cy name is Haley", sentence2 = "My Haley";
//        String sentence1 = "hsYZKp Cn eE", sentence2 = "hsYZKp eE";
//        String sentence1 = "hsYZKp Cn eE", sentence2 = "hsYZKp eE";
//        String sentence1 = "of", sentence2 = "A lot of words";
//        String sentence1 = "a BaabbAABbBbbaAb", sentence2 = "a BbbA baaaaBaAabB bbab AaAB";
        String sentence1 = "Game is ON", sentence2 = "Game are ON";
        //- 2 đầu left, right có thể:
        //  + left match nhiều hơn right <>
        //- Có trường hợp nào match left, right ưu tiên 1 trong 2
        //  ==> Ảnh hưởng đến kết quả không
        //- Match là match hết
        //  + Nếu left match nhiều hơn ==> right chỉ cần match ít hơn ==> Tỉ lệ lỗi từ right sẽ ít hơn.
//        System.out.println(areSentencesSimilar(sentence1, sentence2));
        //==> Nếu làm như bên trên thì sẽ gặp vấn đề liên quan đến check (left, right)
        //  + Các edge cases
        //- Thà ta slit ra cho nhanh:
        //
        //
        System.out.println(areSentencesSimilarWrong(sentence1, sentence2));
        System.out.println(areSentencesSimilarFix(sentence1, sentence2));
        //#Reference:
        //3036. Number of Subarrays That Match a Pattern II
        //3001. Minimum Moves to Capture The Queen
        //2644. Find the Maximum Divisibility Score
        //1267. Count Servers that Communicate
        //240. Search a 2D Matrix II
        //4. Median of Two Sorted Arrays
        //
    }
}
