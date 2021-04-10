package com.company;

public class Main {

    public static void main(String[] args) {
        Stopwatch.start();
        System.out.println(ifStringExistsInOtherString(
                "abcdeabcdeabcdeabcde","abcde"));
        Stopwatch.stop();
    }

    public static String ifStringExistsInOtherString(String initialString, String stringToFind) {
        String toReturn;
        String[] initial = initialString.split("");
        int[] positions = new int[initial.length * 2];
        int stringToFindLength = stringToFind.split("").length;
        int positionsPosition = 0;
        for (int i = 0; i < initial.length; ++i) {
            if (initial[i].equals(stringToFind.split("")[0])) {
                String toCheck = removeAllLettersFromXtoY(initial, i, i + stringToFindLength);
                if (stringToFind.equals(toCheck)) {
                    positions[positionsPosition] = i;
                    positionsPosition++;
                    positions[positionsPosition] = i + stringToFindLength;
                    positionsPosition++;
                }
            }
        }
        positions = removeExcessEntries(positions, positionsPosition);
        toReturn = formatString(initial, positions);
        return toReturn;
    }

    public static String formatString(String[] array, int[] positions) {
        StringBuilder toReturn = new StringBuilder();
        boolean inColin = false;
        int positionInArray = 0;
        for (int i = 0; i < array.length || positionInArray != array.length; ++i) {
            if (ifEntryExistsInArray(positions, i)) {
                if (inColin) {
                    inColin = false;

                } else if (!inColin) {
                    inColin = true;
                }
                toReturn.append("'");
            }
            toReturn.append(array[positionInArray]);
            positionInArray++;
        }
        toReturn.append("'");
        return toReturn.toString();
    }

    public static boolean ifEntryExistsInArray(int[] array, int ifExists) {
        boolean answer = false;
        for (int j : array) {
            if (j == ifExists) {
                answer = true;
                break;
            }
        }
        return answer;
    }

    public static int[] removeExcessEntries(int[] array, int tillWhereAreTheEntries) {
        int[] answer = new int[tillWhereAreTheEntries];
        if (tillWhereAreTheEntries >= 0) System.arraycopy(array, 0, answer, 0, tillWhereAreTheEntries);
        return answer;
    }

    public static String removeAllLettersFromXtoY(String[] array, int x, int y) {
        if (y > array.length) {
            y = array.length;
        }
        StringBuilder toReturn = new StringBuilder();
        for (; x < y; x++) {
            toReturn.append(array[x]);
        }
        return toReturn.toString();
    }
}
