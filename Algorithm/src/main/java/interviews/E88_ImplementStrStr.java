package interviews;

public class E88_ImplementStrStr {

    public static int strStr(String haystack, String needle) {
        if("".equals(needle)){
            return 0;
        }
        int n=haystack.length();
        int m=needle.length();

        for(int i=0;i+m<=n;i++){
            //Chỗ này hoàn toàn có thể tối ưu bằng cách
            //Không tạo variable isExsist --> Dùng global (j) varible để check value
            boolean isExist=true;

            for(int j=0;j<m;j++){
                if(haystack.charAt(i+j)!=needle.charAt(j)){
                    isExist=false;
                    break;
                }
            }
            if(isExist){
                return i;
            }
        }
        return -1;
    }

    public static int strStrRollHashingFunction(String haystack, String needle) {
        if("".equals(needle)){
            return 0;
        }
        if(haystack.length()<needle.length()){
            return -1;
        }
        int hash1=0;
        int hash2=0;

        for(int i=0;i<needle.length();i++){
            hash1+=needle.charAt(i)-'a';
            hash2+=haystack.charAt(i)-'a';
        }
        int j=0;
        for(int i=0;i<=haystack.length()-needle.length();i++){

            if(hash1==hash2){
                for(j=0;j<needle.length();j++){
                    if(haystack.charAt(i+j)!=needle.charAt(j)){
                        break;
                    }
                }
            }
            //Khi j ra ngoài thì nó chính bằng length() của String
            if(j==needle.length()){
                return i;
            }
            hash2-=haystack.charAt(i)-'a';
            if(i+needle.length()<haystack.length()){
                hash2+=haystack.charAt(i+needle.length())-'a';
            }
        }

        return -1;
    }

    public static void main(String[] args) {
//        String haystack="hello";
//        String needle="ll";
//        String haystack="hello";
//        String needle="";
//        String haystack="hello";
//        String needle="a";
//        String haystack="a";
//        String needle="a";
//        String haystack="aaaaa";
//        String needle="bba";
        String haystack="mississippi";
        String needle="issip";
        System.out.println(strStr(haystack, needle));
        System.out.println(strStrRollHashingFunction(haystack, needle));
    }
}
