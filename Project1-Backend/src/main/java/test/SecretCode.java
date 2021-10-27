package test;

import java.util.LinkedList;
import java.util.List;

public class SecretCode {

    public String encrypt(String s) {
        // encryption code here
        return "Hello";
    }

    public String decrypt(String s) {
        //take string convert to list
        String[] arr1 = s.split(" ");

        //swap order of words
        int index = 0;
        int revindex = arr1.length - 1;
        String[] arr2 = new String[arr1.length];
        while (index < arr1.length) {
            arr2[index] = arr1[revindex];
            index++;
            revindex--;
        }
//I feeeeeeeel sssoooooo lazy!!!!  ----> Expected final step..
//I fe8l s3o6 lazy!!!! ---> Expected first step
//lazy!!!! s3o6 fe8l I
        //replace numbers with previous letters
        //iterate through each word
        //iterate through characters.. when we get to a number replace with previous character said many times ---> string
        String s2 = "";
        for (String word : arr2) {
            String s3 = "";
            for (int i = 0; i < word.length(); i++) {
                if (Character.isDigit(word.charAt(i))) {
                   String c=""; //= (String) word.charAt(i);
                    int number_of_letters = Integer.parseInt(c);
                    System.out.print(number_of_letters+" ");
                    for (int j = 0; j < number_of_letters-1; j++) {
                        s3 = s3 + String.valueOf(word.charAt(i - 1));
                    }
                } else {
                    s3 = s3 + word.charAt(i);
                }
            }
            s2 = s2 + s3 + " ";

        }
        return s2;
    }
}
        // encryption code here
//        return "Hello";
//    }


//2) Java Decryption... words are reversed then duplicates are converted to a number...
//        Example... Need you --> you Ne2d  (we will keep spaces)
//        Write a decryption method.