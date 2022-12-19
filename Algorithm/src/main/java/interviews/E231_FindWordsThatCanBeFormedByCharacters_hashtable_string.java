package interviews;

public class E231_FindWordsThatCanBeFormedByCharacters_hashtable_string {

    public static int countCharacters(String[] words, String chars) {
        int[] arr=new int[26];

        for(int i=0;i<chars.length();i++){
            arr[chars.charAt(i)-'a']++;
        }
        int n=words.length;
        int countRs=0;
        for(int i=0;i<n;i++){
            int[] check=new int[26];

            for(int j=0;j<26;j++){
                check[j]=arr[j];
            }

            String currentWord=words[i];
            int j;

            for(j=0;j<currentWord.length();j++){
                if(check[currentWord.charAt(j)-'a']==0){
                    break;
                }else{
                    check[currentWord.charAt(j)-'a']--;
                }
            }
            if(j==currentWord.length()){
                countRs+=currentWord.length();
            }
        }
        return countRs;
    }

    public static void main(String[] args) {
        String[] words = new String[]{"cat","bt","hat","tree"};
        String chars = "atach";

//        String[] words = new String[]{"hello","world","leetcode"};
//        String chars = "welldonehoneyr";

        System.out.println(countCharacters(words, chars));
    }
}
