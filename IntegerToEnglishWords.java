public class IntegerToEnglishWords { //O(1) T.C, O(1) S.C
    String[] under20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven",
            "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
            "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty",
            "Seventy", "Eighty", "Ninety"};
    String[] suffixes = {"", "Thousand", "Million", "Billion"};
    public String numberToWords(int num) { //O(1) T.C, O(1) S.C
        if(num == 0) return "Zero";
        int index = 0; //iterate over the suffixes array
        String res = "";
        while(num > 0) {
            int chunk = num % 1000; //break the input into chunks of three numbers
            if(chunk != 0) { //to handle 000 case
                //call recursive method on current chunk, add an applicable suffix and append it to result
                res = helper(chunk) + suffixes[index] + " " + res;
            }
            num = num / 1000; //the number for next chunk gathering will have to remove the last three digits
            index++; //increment the index for each chunk
        }
        return res.trim();
    }

    private String helper(int chunk) {
        if(chunk == 0) return ""; //to append empty space in case of zero
        else if(chunk >= 100) {
            return under20[chunk/100] + " Hundred " + helper(chunk%100);
        } else if (chunk > 19) {
            return tens[chunk/10] + " " + helper(chunk%10);
        } else {
            return under20[chunk] + " ";
        }
    }

    public static void main(String[] args) {
        IntegerToEnglishWords integerToEnglishWords = new IntegerToEnglishWords();

        int num = 7082898;
        System.out.println("The integer " + num + " converted to english words is: " +
                integerToEnglishWords.numberToWords(num));
    }
}
