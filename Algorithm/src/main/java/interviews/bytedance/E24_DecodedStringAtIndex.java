package interviews.bytedance;

public class E24_DecodedStringAtIndex {
    public String decodeAtIndex(String s, int k) {
        StringBuilder currentStr=new StringBuilder();
        int n=s.length();
        int currentNum=0;

        for(int i=0;i<n;i++){
            char ch=s.charAt(i);

            if(ch>='0'&&ch<='9'){
                currentNum=currentNum*10+(ch-'0');
            }
        }
        return null;
    }

    public static String decodeAtIndexRef(String s, int k) {
        long lastLength = 0;
        int i;

        for (i = 0; lastLength < k; i++) {
            lastLength = Character.isDigit(s.charAt(i)) ? lastLength * (s.charAt(i) - '0') : lastLength + 1;
        }
        System.out.println(lastLength);
        while (i--!=0){
            if(Character.isDigit(s.charAt(i))){
                lastLength/=s.charAt(i)-'0';
                k%=lastLength;
            }else{
                if(k%lastLength==0){
                    break;
                }
                lastLength--;
            }
        }
        return s.charAt(i)+"";
    }

    public static void main(String[] args) {
        String s="leet22code3";
        int k=20;
        System.out.println(decodeAtIndexRef(s, k));
    }
}
