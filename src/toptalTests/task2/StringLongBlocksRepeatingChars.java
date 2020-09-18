package toptalTests.task2;

import java.util.ArrayList;
import java.util.List;

public class StringLongBlocksRepeatingChars {

    public static void main(String... args) {
        StringLongBlocksRepeatingChars stringLongBlocksRepeatingChars = new StringLongBlocksRepeatingChars();
        stringLongBlocksRepeatingChars.verifySolution("ABBBCCDDCCC", 3, 5); // TODO: This one is the example why you shouldn't remove the first one that you met is the lowest one
        stringLongBlocksRepeatingChars.verifySolution("AAAAAAAAAAABXXAAAAAAAAAA", 3, 3);
        stringLongBlocksRepeatingChars.verifySolution("ABCDDDEFG", 2, 6);
        stringLongBlocksRepeatingChars.verifySolution("RRRTTTOOHHHGGGGAAAALL", 5, 10);
        stringLongBlocksRepeatingChars.verifySolution("RRRTTTOOHHHHHGGGGAAAALL", 5, 12);
        stringLongBlocksRepeatingChars.verifySolution("GGGGGGGGGGGGG", 5, 2);
        stringLongBlocksRepeatingChars.verifySolution("GGGGG", 5, 0);
        stringLongBlocksRepeatingChars.verifySolution("ABCDEFGHILMNOPQRSTUVZ", 5, 16);
    }

    public void verifySolution(String inputString, int inputInt, int expectedResult) {
        int result = solution(inputString, inputInt);
        if (result != expectedResult) {
            System.err.println("Result wrong for string " + inputString + " and int " + inputInt);
            System.err.println("Result is " + result + " but expected is " + expectedResult);
        } else {
            System.out.println("Result correct of " + expectedResult + " for string " + inputString + " and int " + inputInt);
        }
        System.out.println();
    }

    public int solution(String S, int K) {
        List<Substring> substringGroups = createSubstringGroup(S);
        List<Substring> substringGroupsAfterRemoval = removeElementsFromTheList(substringGroups, K);
        List<Substring> finalSubstringGroups = mergeRepetitions(substringGroupsAfterRemoval);
        String finalString = buildingTheFinalString(finalSubstringGroups);
        return finalString.length();
    }

    private List<Substring> createSubstringGroup(String S) {
        List<Substring> substringGroups = new ArrayList<>();
        String currentString = "";
        int currentOccurrences = 0;
        char currentCharacter = S.charAt(0);
        for (int i = 0; i < S.length(); i++) {
            if (currentString.equals("") || currentString.substring(0, 1).equals(S.substring(i, i + 1))) {
                currentOccurrences++;
                currentCharacter = S.charAt(i);
                currentString = currentString + S.charAt(i);
            } else {
                substringGroups.add(new Substring(currentCharacter, currentOccurrences));
                currentOccurrences = 1;
                currentCharacter = S.charAt(i);
                currentString = "" + S.charAt(i);
            }
        }
        substringGroups.add(new Substring(currentCharacter, currentOccurrences));
        return substringGroups;
    }

    private List<Substring> removeElementsFromTheList(List<Substring> substrings, int K) {

        // TODO: this should be more complete
        // Finding the smallest sequence
        int shortest = substrings.get(0).getOccurrences();
        int shortestIndex = 0;
        for (int i = 0; i < substrings.size(); i++) {
            if (substrings.get(i).getOccurrences() < shortest) {
                shortest = substrings.get(i).getOccurrences();
                shortestIndex = i;
            }
        }

        // Removing elements from the strings list
        int remainingToRemove = K;
        while (remainingToRemove > 0) {
            Substring currentRemovingSubstring = substrings.get(shortestIndex);
            if (currentRemovingSubstring.getOccurrences() == 1) {
                substrings.remove(shortestIndex);
            } else {
                substrings.set(shortestIndex, new Substring(currentRemovingSubstring.getCharachter(), currentRemovingSubstring.getOccurrences() - 1));
            }
            remainingToRemove--;
        }
        return substrings;
    }

    List<Substring> mergeRepetitions(List<Substring> substringList) {
        List<Substring> finalSubstringGroups = new ArrayList<>();
        Substring currentSubstring = null;
        for (Substring substring : substringList) {
            if (currentSubstring == null) {
                currentSubstring = substring;
                continue;
            }
            if (substring.getCharachter() == currentSubstring.getCharachter()) {
                int currentSubstringOccurrences = substring.getOccurrences() + currentSubstring.getOccurrences();
                currentSubstring = new Substring(substring.getCharachter(), currentSubstringOccurrences);
            } else {
                finalSubstringGroups.add(currentSubstring);
                currentSubstring = substring;
            }
        }
        if (currentSubstring != null) {
            finalSubstringGroups.add(currentSubstring);
        }
        return finalSubstringGroups;
    }

    private String buildingTheFinalString(List<Substring> substrings) {
        String finalString = "";
        for (Substring charsGroup : substrings) {
            if (charsGroup.getOccurrences() == 1) {
                finalString = finalString.concat(String.valueOf(charsGroup.getCharachter()));
            } else {
                finalString = finalString.concat(charsGroup.getOccurrences() + String.valueOf(charsGroup.getCharachter()));
            }
        }
        return finalString;
    }

    static class Substring {
        char charachter;

        public char getCharachter() {
            return charachter;
        }

        public int getOccurrences() {
            return occurrences;
        }

        int occurrences;

        Substring(char charachter, int occurrences) {
            this.charachter = charachter;
            this.occurrences = occurrences;
        }
    }

}
