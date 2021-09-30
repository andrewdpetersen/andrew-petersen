package utils;

import java.util.Scanner;

public class MenuSwitch {

    public void insertSwitch(String menuHeader, int choices, MyArrayList<String> menuLabels){

        //Creates a menu from the choices and labels provided

        System.out.print(menuHeader);
        int i = 0;
        while (i < choices - 1) {
            System.out.print("("+i+1+") "+menuLabels.get(i)+"\n");
            i++;
        }

        //instantiates a Scanner object 'menuScanner'
        Scanner menuScanner = new Scanner(System.in);

        //Scans the next line from the System.in, and assigns it to 'buttonPress'
        System.out.println("Please make a selection and press 'Enter'");
        String buttonPress = menuScanner.nextLine();


        //inserts a switch from the menu options


    }
}
