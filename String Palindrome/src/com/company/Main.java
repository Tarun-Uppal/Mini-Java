package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.print("Please enter a string ");
            String string = scanner.nextLine();
            if (string.equals(" ")){
                System.out.println("Invalid Entry Please Retry ");
            }else{
                System.out.println(isStringPalindrome(string));
            }
        }
    }
    public static boolean isStringPalindrome(String string){
        String[] check = string.split("");

        if (check[0].equals("-")){
            check[0] = "";
            String[] newCheck = new String[check.length-1];
            int position = 1;

            for (int i = 0;i != check.length-1;i++){
                newCheck[i] = check[position];
                if (position == newCheck.length){
                    break;
                }
                position++;
            }
            check = new String[newCheck.length];
            check = newCheck;
        }

        boolean isPalindrome = false;
        if (isOdd(check.length) == false){
            isPalindrome = ifArrayInverseEqualsToTheOther(
                    splitArrayFirstPart(check),splitArraySecondPart(check));
        }else{
            check = removeCenterEntryOfArray(check);
            isPalindrome = ifArrayInverseEqualsToTheOther(
                    splitArrayFirstPart(check),splitArraySecondPart(check));
        }
        return isPalindrome;
    }
    public static String[] removeCenterEntryOfArray(String[] array){
        if (isOdd(array.length)) {
            int newArraySize = array.length - 1;
            int pointToRemove = array.length / 2;
            String[] answer = new String[newArraySize];
            int answerPointInt = 0;
            for (int i = 0;(i-1) != newArraySize; i += 1) {
                if (i != pointToRemove) {
                    answer[answerPointInt] = array[i];
                    answerPointInt += 1;

                }
            }
            return answer;
        }
        return null;
    }

    public static String[] splitArrayFirstPart (String[] array){
        int newArraySize = array.length / 2;
        String[] answer = new String[newArraySize];
        for (int i = 0;i != newArraySize;i+=1){
            answer[i] = array[i];
        }
        return answer;
    }

    public static String[] splitArraySecondPart (String[] array){
        int newArraySize = array.length / 2;
        String[] answer = new String[newArraySize];
        int answerInt = 0;
        for (int i = newArraySize;i != array.length;i+=1){
            answer[answerInt] = array[i];
            answerInt+=1;
        }
        return answer;
    }

    public static boolean ifArrayInverseEqualsToTheOther(String[] array1, String[] array2){
        boolean isEqual = true;
        array2 = inverseStringArray(array2);

        for (int i = 0;i != array2.length;i+=1){
            if (!array1[i].equals(array2[i])){
                isEqual = false;
            }
        }
        return isEqual;
    }

    public static String[] inverseStringArray(String[] array){
        String[] answer = new String[array.length];
        int currentInt = array.length - 1;
        for (int i = 0;i != answer.length;i+=1){
            answer[i] = array[currentInt];
            currentInt -=1;
        }
        return answer;
    }

    public static boolean isOdd(int isOdd){
        boolean answer = false;
        int even = 2;
        int odd = 1;
        for (int shot = 0; shot < isOdd;shot +=1) {
            if (isOdd == odd) {
                answer = true;
            }
            if (isOdd == even) {
                answer = false;
            }
            even += 2;
            odd += 2;
        }
        return answer;
    }
}
