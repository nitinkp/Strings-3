public class BasicCalculator2 {
    public static int calculate(String s) { //O(l) T.C, O(1) S.C where l is length of string
        s.trim(); //to remove trailing and leading empty spaces
        int calc = 0; //calculated value
        int tail = 0; //the last performed operation
        int num = 0; //the number built from the string
        char sign = '+'; //to store the last sign encountered, default +
        int n = s.length();
        for(int i=0; i<n; i++) { //iterating over the string
            char c = s.charAt(i);
            if(Character.isDigit(c)) { //if the char is a digit
                num = num*10 + c-'0'; //convert it to number
            }
            //if it is not a digit and space, or the last character in the string
            if((!Character.isDigit(c) && c != ' ') || i == n-1){
                if(sign == '+') { //if the last sign is addition
                    calc += num; //add the current num to calculated value
                    tail = num; //and assign tail as +num
                } else if(sign == '-') { //if the last sign is subtraction
                    calc -= num; //subtract the current num from calculated value
                    tail = -num; //and assign tail as -num
                } else if(sign == '*') { //if the last sign is multiplication
                    calc = calc - tail + tail * num; //subtract the prev tail from calc and add the tail*num
                    tail = tail * num; //new tail becomes tail*num
                } else { //if the last sign is division
                    calc = calc - tail + tail / num; //subtract the prev tail from calc and add the tail/num
                    tail = tail / num; //new tail becomes tail/num
                }
                num = 0; //if an operation is performed, change the num to 0
                sign = c; //make this as the last sign
            }
        }
        return calc;
    }

    public static void main(String[] args) {
        String s = " 31-23+17*78/5 ";

        System.out.println("The calculated value of string " + s + " is: " + calculate(s));
    }
}