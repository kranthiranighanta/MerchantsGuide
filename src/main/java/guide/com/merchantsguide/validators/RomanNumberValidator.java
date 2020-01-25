package guide.com.merchantsguide.validators;

import java.util.HashSet;
import java.util.Set;

import guide.com.merchantsguide.model.RomanNumber;

public class RomanNumberValidator {

    private RomanNumberValidator(){

    }

    private static final Set<Character> THREE_TIMES_REPEATED_CHARACTERS = new HashSet<Character>();
    private static final Set<Character> NOT_SUBTRACTED_CHARACTERS = new HashSet<Character>();

    static {
    	
        THREE_TIMES_REPEATED_CHARACTERS.add('I');
        THREE_TIMES_REPEATED_CHARACTERS.add('X');
        THREE_TIMES_REPEATED_CHARACTERS.add('C');
        THREE_TIMES_REPEATED_CHARACTERS.add('M');

        NOT_SUBTRACTED_CHARACTERS.add('V');
        NOT_SUBTRACTED_CHARACTERS.add('L');
        NOT_SUBTRACTED_CHARACTERS.add('D');
        NOT_SUBTRACTED_CHARACTERS.add('M');
    }

    /**
     * Use this for Roman number validation
     * The symbols "I", "X", "C", and "M" can be repeated three times in succession, but no more. 
     * (They may appear four times if the third and fourth are separated by a smaller value, such as XXXIX.) "D", "L", and "V" can never be repeated.
     * "I" can be subtracted from "V" and "X" only. "X" can be subtracted from "L" and "C" only. "C" can be subtracted from "D" and "M" only. "V", "L", and "D" can never be subtracted.
     * @param roman number to be validated
     * @return int value of the passed Roman number, -1 if not valid
     */
    public static int validateRoman(String romanNumber) throws Exception{
        char[] charArray = romanNumber.toCharArray();
        char previousChar = ' ';

        int characterRepeatCount = 1;
        int total = 0;
        int previousCharacterOrdinal = Integer.MAX_VALUE; 
        int currentCharacterOrdinal;

        for(int i = 0; i<charArray.length; i++){
            char currentChar = charArray[i];
            int currentRomanCharNumericValue = RomanNumber.valueOf(String.valueOf(currentChar)).getValue();

            if(previousChar != ' '){
                previousCharacterOrdinal = RomanNumber.valueOf(String.valueOf(previousChar)).ordinal();
            }
            currentCharacterOrdinal = RomanNumber.valueOf(String.valueOf(currentChar)).ordinal();

            if(currentChar == previousChar && ++characterRepeatCount < 4 && THREE_TIMES_REPEATED_CHARACTERS.contains(currentChar)){
                total += currentRomanCharNumericValue;
            }
            else if(characterRepeatCount > 3){
                total = -1;
            }
            else if(currentChar == previousChar && !THREE_TIMES_REPEATED_CHARACTERS.contains(currentChar)){
                total = -1;
            }
            else if(previousCharacterOrdinal < currentCharacterOrdinal  && !NOT_SUBTRACTED_CHARACTERS.contains(previousChar)){
                int previousRomanCharNumericValue = RomanNumber.valueOf(String.valueOf(previousChar)).getValue();
                if(previousCharacterOrdinal + 2 >= currentCharacterOrdinal){
                    total += currentRomanCharNumericValue - (2 * previousRomanCharNumericValue); 
                    characterRepeatCount = 1;
                }
                else{
                    total = -1;
                }
            }
            else if(previousCharacterOrdinal < currentCharacterOrdinal  && NOT_SUBTRACTED_CHARACTERS.contains(previousChar)){
                total = -1;
            }
            else{
                characterRepeatCount = 1;
                total += currentRomanCharNumericValue;
            }
            previousChar = currentChar;
            if(total == -1)
                break;
        }
        return total;
    }

}


    