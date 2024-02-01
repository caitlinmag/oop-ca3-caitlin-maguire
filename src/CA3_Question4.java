import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

/**
 * Name:
 * Class Group:
 */
public class CA3_Question4 {
/*
Using stack
-in the powerpoint - 15.6 stack and queue applications -
slide 55 is similar to the question on html tags-
only difference is dealing with the opening and closing tags

Program to check if a sequence of HTML tags are properly nested
For each opening tag (eg <p> must have a closing tag </p>)
- p tag may have other tags (eg <p> <ul> <li> </li> </u> <a> </p>)
inner tags must be closed before outer tags
process a file containing tags
ASSUME tags are separated by spaces & there is no text inside tags
 */

    /*
        filename: name of the file to test.
     */
    public static boolean validate(String filename) throws FileNotFoundException {
        File f = new File(filename);
//        scanner to scan through file
        Scanner in = new Scanner(f);

        //Create stack to hold html tags
        Stack<String> htmlStack = new Stack<String>();

        //if at the end of the stack is empty (top element) - opening tag
        while (in.hasNext()) {
            char openingTag = '<';
            char closingTag = '/';

            //Make a string to store tags in the file as strings
            String tag = in.next();

            int indexOpeningTag = tag.indexOf(openingTag);
            int indexClosingTag = tag.indexOf(closingTag);

            //check if opening tag - push to stack
            if (indexOpeningTag != -1) {
                htmlStack.push(tag);
            }
            while (!htmlStack.isEmpty() && indexClosingTag != -1) {
                //check if closing tag - pop from stack
//                if (indexClosingTag != -1) {
                //to get the opening inner tag -  eg for <ul> use 1,2 to get u
                String openingInnerTag = htmlStack.peek().substring(1, 2);
                //to get the closing inner tag - eg for </ul;> use 2,3 to get u
                String closingInnerTag = tag.substring(2, 3);

                //check if inner opening and closing tags match - then pop from stack
                if (openingInnerTag.equals(closingInnerTag)) {
                    htmlStack.pop();
                } else {
                    //inner opening and closing tags do not match
                    return false;
                }
                return true;
            }
//           int indexOpeningTag = tag.substring(0, 1).indexOf(openingTag);
//            int indexClosingTag = tag.substring(1, 2).indexOf(closingTag);
        }
//        if (htmlStack.isEmpty()) {
//            return true;
//        }
        return false;
    }

    /*
        This function tests the files in the files array to see if
         they are valid.
         tags_valid.txt should return true;
         tags_invalid.txt should output as invalid;


     */
    public static void main(String[] args) throws FileNotFoundException {
        //checking the correct files are valid and invalid
        String[] files = {"tags_valid.txt", "tags_invalid.txt"};
        for (String fName : files) {
            System.out.print(fName + ": ");
            if (validate(fName)) {
                System.out.println("This file is valid");
            } else {
                System.out.println("This file is invalid");
            }
        }

    }
}
