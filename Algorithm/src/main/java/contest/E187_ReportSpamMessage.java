package contest;

import java.util.Arrays;
import java.util.HashSet;

public class E187_ReportSpamMessage {

    public static boolean reportSpam(String[] message, String[] bannedWords) {
        int count=0;
        HashSet<String> setBannedWords=new HashSet<>(Arrays.asList(bannedWords));

        for(String m: message){
            if(setBannedWords.contains(m)){
                count++;
                if(count==2){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an array of strings message and an array of (strings bannedWords).
        //An array of words is considered spam if there are at least two words in it that exactly match any word in bannedWords.
        //Return true if the array message is spam, and false otherwise.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //
        //
        //** Brainstorm
        String[]  message = {"hello","programming","fun"}, bannedWords = {"world","programming","leetcode"};
        System.out.println(reportSpam(message, bannedWords));
    }
}
