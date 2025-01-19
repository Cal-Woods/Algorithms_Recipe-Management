package utils;

/**
 * Contains static methods to assist programmers.
 * 
 * @author Cal Woods
 */
public class Helpers {
    /**
     * Checks the validity of a given String.
     * @param str Given String to check
     * @return A boolean indicating validity
     * 
     * @throws IllegalArgumentException If string is null
     * @throws IllegalArgumentException If string has more than one consecutive space
     */
    public static boolean isValidString(String str) {
        if(str == null)
            throw new IllegalArgumentException("Given str must NOT be null.");
        
            //Initialise for loop
            for (int i = 0; i < str.length()-1; i++) {
                //Check each character in str
                if(str.charAt(i) == ' ' && str.charAt(i+1) == ' ') {
                    return false;
                }
            }
        return true;
    }
}
