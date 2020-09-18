package toptalTests.task3;

public class SplitStringOfAAndBIntoThreeSeparateParts {

    public static void main(String[] args) {
        SplitStringOfAAndBIntoThreeSeparateParts splitStringOfAAndBIntoThreeSeparateParts = new SplitStringOfAAndBIntoThreeSeparateParts();

        splitStringOfAAndBIntoThreeSeparateParts.verifySolution("abbbaba", 4);
        splitStringOfAAndBIntoThreeSeparateParts.verifySolution("aaaaa", 0);
        splitStringOfAAndBIntoThreeSeparateParts.verifySolution("bbbbbbb", 100); // TODO: I don't know if the expected result is correct
        splitStringOfAAndBIntoThreeSeparateParts.verifySolution("bb", 0); // TODO: I don't know if the expected result is correct
        splitStringOfAAndBIntoThreeSeparateParts.verifySolution("ab", 0);
        splitStringOfAAndBIntoThreeSeparateParts.verifySolution("aa", 0);
        splitStringOfAAndBIntoThreeSeparateParts.verifySolution("b", 0); // TODO: I don't know if the expected result is correct

    }

    public void verifySolution(String inputString, int expectedResult) {
        int result = solution(inputString);
        if (result != expectedResult) {
            System.err.println("Result wrong for string " + inputString);
            System.err.println("Result is " + result + " but expected is " + expectedResult);
        } else {
            System.out.println("Result correct of " + expectedResult + " for string " + inputString);
        }
        System.out.println();
    }

    public int solution(String S) {
        char charForSplitting = 'a';
        int occurrences = countNumberOfOccurrences(S, charForSplitting);
        if (occurrences % 3 != 0) {
            return 0;
        }
        return 1; // TODO: complete
    }

    public int countNumberOfOccurrences(String string, char charToCount) {
        int count = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == charToCount) {
                count++;
            }
        }
        return count;
    }

}
