import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {

    public static void main(String[] args) {
        verifySolution(TestCaseInput.TEST_CASE_INPUT_1, Collections.singletonList("p1"));
        verifySolution(TestCaseInput.TEST_CASE_INPUT_2, Arrays.asList("rpaqgbjxik", "vjrrisdfxe"));
        verifySolution(TestCaseInput.TEST_CASE_INPUT_6, Collections.singletonList("motiyjqszo"));
    }

    public static void verifySolution(TestCaseInput testCaseInput, List<String> expectedResult) {
        List<String> result = fetchItemsToDisplay(testCaseInput.items, testCaseInput.sortParameter, testCaseInput.sortOrder, testCaseInput.itemsPerPage, testCaseInput.pageNumber);
        if (result.equals(expectedResult)) {
            System.out.println("Result correct of " + expectedResult + " for test case " + testCaseInput.testCase);
        } else {
            System.err.println("Result wrong for test case " + testCaseInput.testCase);
            System.err.println("Result is " + result + " but expected is " + expectedResult);
        }
        System.out.println();
    }

    /*
     * Complete the 'fetchItemsToDisplay' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     *  1. 2D_STRING_ARRAY items
     *  2. INTEGER sortParameter
     *  3. INTEGER sortOrder
     *  4. INTEGER itemsPerPage
     *  5. INTEGER pageNumber
     */

    public static List<String> fetchItemsToDisplay(List<List<String>> items, int sortParameter, int sortOrder, int itemsPerPage, int pageNumber) {

        List<List<String>> sortedList = new ArrayList<>();
        List<String> output = new ArrayList<>();

        if (sortParameter == 0) {
            while (items.size() > 0) {
                int currentIndex = 0;
                String currentGreatestValue = "";
                for (int i = 0; i < items.size(); i++) {
                    int resultLexicographically = currentGreatestValue.compareTo(items.get(i).get(sortParameter));
                    if (resultLexicographically < 0) {
                        currentGreatestValue = items.get(i).get(sortParameter);
                        currentIndex = i;
                    }
                }
                sortedList.add(items.get(currentIndex));
                items.remove(currentIndex);
            }
        } else {
            while (items.size() > 0) {
                int currentIndex = 0;
                int currentGreatestValue = 0;
                for (int i = 0; i < items.size(); i++) {
                    if (currentGreatestValue < Integer.parseInt(items.get(i).get(sortParameter))) {
                        currentGreatestValue = Integer.parseInt(items.get(i).get(sortParameter));
                        currentIndex = i;
                    }
                }
                sortedList.add(items.get(currentIndex));
                items.remove(currentIndex);
            }
        }
        if (sortOrder == 0) {
            Collections.reverse(sortedList);
        }
        int startingPosition = itemsPerPage * pageNumber;
        for (int i = startingPosition; i < startingPosition + itemsPerPage; i++) {
            if (sortedList.size() > i) {
                output.add(sortedList.get(i).get(0));
            }
        }
        System.out.println(output);
        return output;
    }

}