package interviews;

public class E53_ValidAnagram {

    /*
   Cách làm như thế này bị sai các cases bị duplicated nhiều ký tự
   //aacc
   //ccac
     */
    public static boolean isAnagramWrong(String s, String t) {
        boolean characters[]=new boolean[27];
        int countLeftChar=0;
        int countRightChar=0;

        for(int i=0;i<s.length();i++){
            characters[s.charAt(i)-'a']=true;
            if(s.charAt(i)!=' '){
                countLeftChar++;
            }
        }
        for(int i=0;i<t.length();i++){
            if(!characters[t.charAt(i)-'a']){
                return false;
            }
            if(t.charAt(i)!=' '){
                countRightChar++;
            }
        }
        if(countLeftChar!=countRightChar){
            return false;
        }
        return true;
    }

    public static boolean isAnagram(String s, String t) {
        int characters[]=new int[27];
        int numberCharLeft=0;
        int numberCharNotInclude=0;

        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!=' '){
                if(characters[s.charAt(i)-'a']==0){
                    numberCharLeft++;
                }
                characters[s.charAt(i)-'a']++;
            }
        }
        for(int i=0;i<t.length();i++){
            if(t.charAt(i)!=' '&&characters[t.charAt(i)-'a']>0){
                characters[t.charAt(i)-'a']--;

                if(characters[t.charAt(i)-'a']<=0){
                    numberCharLeft--;
                }
            }else if(t.charAt(i)!=' '){
                numberCharNotInclude++;
            }
        }
        if(numberCharLeft!=0||numberCharNotInclude!=0){
            return false;
        }
        return true;
    }

    public static boolean isAnagramOptimized(String s, String t) {
        int characters[]=new int[27];
        int n=s.length();

        if(s.length()!=t.length()){
            return false;
        }
        for(int i=0;i<n;i++){
            characters[s.charAt(i)-'a']++;
            characters[t.charAt(i)-'a']--;
        }

        for(int i=0;i<27;i++){
            if(characters[i]!=0){
                return false;
            }
        }
        return true;
    }

    public static boolean isAnagramOptimized1(String s, String t) {
        int characters[]=new int[27];
        int n=s.length();

        if(s.length()!=t.length()){
            return false;
        }
        for(int i=0;i<n;i++){
            characters[s.charAt(i)-'a']++;
            characters[t.charAt(i)-'a']--;
        }

        for(int i=0;i<27;i++){
            if(characters[i]!=0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        String s="anagram";
//        String t="nagaram";
        //Case 1 : Số ký tự 2 chuỗi không giống nhau length(s) > length(t)
//        String s="ab";
//        String t="a";
        //Case 2 : 2 chuỗi có 1 số ký tự bị duplicated (cc, aa...)
//        String s="aacc";
//        String t="ccac";
        //Case 3: 2 chuỗi có 1 chuỗi có độ dài ngắn hơn
        //--> Nên việc đếm số ký tự - đi sẽ bị âm thay vì =0 --> Nếu (số ký tự <0)
//        //Số ký tự <0 vẫn phải trả ra false
//        String s="a";
//        String t="ab";
        //Case 4: Bị 1 cases cuối (r) của chuỗi "car" không xuất hiện trong "cat"
        //--> Nên không được phép - đi.
        //VD: Không được characters[t.charAt(i)-'a']--;
        String s="cat";
        String t="car";
        System.out.println(isAnagram(s, t));
    }
}
