package utils;

import java.util.Scanner;

public class CallAndResponse {
    public String callAndResponse(String call){
        Scanner getResponse = new Scanner(System.in);
        System.out.println(call);
        getResponse.nextLine();
        getResponse.close();
    }
}
