package org.syntax;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * A regular expression is a sequence of characters that forms a search pattern.
 * When you search for data in a text, you can use this search pattern to describe what you are searching for.
 * A regular expression can be a single character, or a more complicated pattern.
 * Regular expressions can be used to perform all types of text search and text replace operations.
 * Classes in java.util.regex package used to work with regular expressions:
 *   - Pattern class                - defines a pattern (to be used in a search)
 *   - Matcher class                - used to search for the pattern
 *   - PatternSyntaxException class - exception indicating syntax error in a regular expression pattern (@see class {@link Exceptions}
 *
 * Regular expressions:
 *  .     - matches any character
 * \d     - any digits, short for [0-9]
 * \D     - any non-digit, short for [^0-9]
 * \s     - any whitespace character
 * \S     - any non-whitespace character
 * \w     - any word character, short for [a-zA-Z_0-9]
 * \W     - any non-word character, short for [^\w]
 * |      - find a match for any one of the patterns separated by | as in: cat|dog|fish
 * ^      - find a match at the beginning of a string as in: ^Hellp
 * $      - find a match at the end of a string as in: World$
 * n+     - matches any string that contains at least one n (1-n)
 * n*     - matches any string that contains zero or more occurences of n (0-n)
 * n?     - matches any string that contains zero or one occurrences of n (0-1)
 * n{x}	  - matches any string that contains a sequence of X n's
 * n{x,y} - matches any string that contains a sequence of X to Y n's
 * n{x,}  - matches any string that contains a sequence of at least X n's
 *
 * Regular expression test page: https://www.regexplanet.com/advanced/java/index.html
 */
public class RegularExpressions {

    public static void main(String[] args) {

        //Indicate which pattern we search for ("regular" in our example) and with which optional flags (ignore case sensitivity)
        Pattern pattern = Pattern.compile("regular", Pattern.CASE_INSENSITIVE);

        //From out pattern, call a method matcher() that will try to match pattern to input we pass to function
        //Object Matcher will be returned, which contains information about the search that was performed
        Matcher matcher = pattern.matcher("This is a string that we will search in with Regular Expressions");
        boolean matchFound = matcher.find();
        if (matchFound) {
            System.out.println("Match found");
        } else {
            System.err.println("Match not found");
        }

        System.out.println("Pattern1 matches: " + Pattern.matches("[a-zA-Z0-9]{6}", "arun32"));    //true  (6 alphanum chars)
        System.out.println("Pattern2 matches: " + Pattern.matches("[a-zA-Z0-9]{6}", "kkvarun32")); //false (more than 6 chars)
        System.out.println("Pattern3 matches: " + Pattern.matches("[a-zA-Z0-9]", "JA2Uk2"));       //false (more than one char)
        System.out.println("Pattern4 matches: " + Pattern.matches(".*", "hello"));                 //true  (sequence of chars)
        System.out.println("Pattern5 matches: " + Pattern.matches("hello[0-9]+", "hello123"));     //true  (hello with numbers)
        System.out.println("Pattern6 matches: " + Pattern.matches("^hello.*", "hello$%^&||"));     //true  (starting with hello)
        System.out.println("Pattern7 matches: " + Pattern.matches("\\w+\\s\\w+", "hello world"));   //true  (2 words separated by single space)
        System.out.println("Pattern8 matches: " + Pattern.matches("\\w+\\s\\w+", "hello   world")); //false (2 words separated by single space)
        System.out.println("Pattern9 matches: " + Pattern.matches("^(dog|cat|fish) world$", "dog world"));  //true ("dog world" OR "cat world" OR "fish world")

        try {
            String invalidRegex = "["; // invalid regex
            Pattern illegalPattern = Pattern.compile(invalidRegex);
        } catch (PatternSyntaxException e) {
            System.err.println("Invalid regexes needs to be handled for Exception");
        }
    }
}
