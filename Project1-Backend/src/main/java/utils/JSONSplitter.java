package utils;

public class JSONSplitter {
    public static String[] jsonSplitter(String json) {

        /**
         * This method takes a String of JSON and returns an array of strings with each sub-array
         * of size 2 being the key and corresponding value.
         */
        String[] firstSplit = json.split("[{:,}]");
        return firstSplit;
    }
}