import java.util.Stack;

class Solution {
    public static int calculate(String s) { //O(l) T.C, O(l) S.C where l is length of string
        s.trim(); //to remove trailing and leading empty spaces
        s = '(' + s + ')'; //hack to cater for the last char not having a closing bracket,
        // instead of operating on the last char separately
        int calc = 0;
        int num = 0;
        char sign = '+';
        int n = s.length();
        Stack<Integer> st = new Stack<>(); //as we cannot continue operations on the go as earlier due to brackets,
        //we will need a stack to store the characters and pop when needed to perform an operation, O(l) S.C
        for(int i=0; i<n; i++) { //iterating over the string, O(l) S.C
            char c = s.charAt(i);
            if(Character.isDigit(c)) { //if the char is a digit
                num = num*10 + c-'0'; //convert it to number
            } else if(c == ' ') continue; //else if it is space, do nothing and continue further
            else if(c == '(') { //opening bracket
                st.push(sign == '+' ? 1 : -1); //push +1 or -1 based on last sign to multiply later to the
                // result within the bracket
                st.push(Integer.MIN_VALUE); //min-value represents an opening bracket
                sign = '+'; //change to default sign
            } else if(c == ')') { //closing bracket
                st.push(sign == '+' ? num : -num); //depending on last sign, push either num or -num
                while(st.peek() != Integer.MIN_VALUE) { //pop values until opening bracket is encountered
                    calc += st.pop(); //and add those values to the calc
                }
                st.pop(); //pop the opening bracket too
                st.push(st.pop() * calc); //push the calculated result from within brackets
                // multiplied by the last sign before bracket i..e, -(4+5) should become -9
                calc = 0; //change the calc back to 0
                num = 0; //and current num to 0
                sign = '+'; //last sign as default
            } else { //else if it is an operator
                st.push(sign == '+' ? num : -num); //push the current num based on the sign to the stack
                sign = c; //change the sign to whatever is encountered
                num = 0; //make current num to 0
            }
        }
        while(!st.isEmpty()) { //once the iteration is done, if there are numbers still left inside stack
            calc += st.pop(); //add them to the calc
        }
        return calc;
    }

    public static void main(String[] args) {
        String s = "1+(5-4)+ (2+(78-9)-10+1)";

        System.out.println("The calculated value from the string " + s + " is: " + calculate(s));
    }
}