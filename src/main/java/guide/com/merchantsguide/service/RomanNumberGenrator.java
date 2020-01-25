package guide.com.merchantsguide.service;

import java.util.Arrays;
import java.util.Map;

import org.springframework.stereotype.Component;

import guide.com.merchantsguide.model.RomanNumber;
import guide.com.merchantsguide.validators.RomanNumberValidator;

@Component
public class RomanNumberGenrator implements Generator {
    /**
     * Receives inputs of the form "glob is I"
     * maps the alien language to the RomanType
     * @param arr
     */
	@Override
	public void mapInterGalacticToRomanUnits(String[] arr, Map<String, RomanNumber> interGalacticRomanMapping){
        try {
            interGalacticRomanMapping.put(arr[0], RomanNumber.valueOf(arr[2]));
        }
        catch(IllegalArgumentException e){
            System.out.println("This type of Roman is not defined, exiting the program " + e);
        }
    }

    /**
     * Receives input of the form "glob glob Silver is 34 Credits"
     * Creates a map of the object sold and (value/unit)
     * @param arr
     * @return int -1 in case of a validation error
     * @throws Exception
     */
	@Override
    public int generateObjectSoldPerUnitMap(String[] arr, Map<String, RomanNumber> interGalacticRomanMapping, Map<String, Double> objectSoldPerUnitValue) throws Exception{
        StringBuilder romanNumeral = new StringBuilder();
        int i;
        for(i = 0; i<arr.length; i++){
            RomanNumber roman = interGalacticRomanMapping.get(arr[i]); 
            if(roman != null){
                romanNumeral.append(roman);
            }
            else
                break;
        }

        int value = RomanNumberValidator.validateRoman(romanNumeral.toString());
        if(value == -1)
            return -1;
        String objectName = arr[i];
        int credit = Integer.parseInt(arr[i + 2]);

        objectSoldPerUnitValue.put(objectName, (double)credit/value);
        return 1;
    }

    /**
     * Receives input of the form "pish tegj glob glob" for questions like "how much is pish tegj glob glob ?"
     * returns -1 in case of validation exception
     * @param arr
     * @return
     * @throws Exception
     */
	@Override
    public int generateGalacticUnitToNumericValue(String[] arr, Map<String, RomanNumber> interGalacticRomanMapping){

        try {
        String romanNumeral = generateRomanFromGalacticUnit(arr, interGalacticRomanMapping);

        if(romanNumeral == null)
            return -1;

            return RomanNumberValidator.validateRoman(romanNumeral.toString());
        } catch (Exception e) {
            return -1;
        }

    }


    /**
     * Receives input of the form "glob prok Silver" for questions like "how many Credits is glob prok Silver ?"
     * returns -1 in case of validation exception
     * @param arr
     * @return
     * @throws Exception
     */
	@Override
	public Double generateCreditValue(String[] arr, Map<String, Double> objectSoldPerUnitValue, Map<String, RomanNumber> interGalacticRomanMapping){
        int currentValue = generateGalacticUnitToNumericValue(Arrays.copyOfRange(arr, 0, arr.length-1), interGalacticRomanMapping);

        if(currentValue == -1)
            return (double)-1;

        return currentValue * objectSoldPerUnitValue.get(arr[arr.length-1]);
    }

    /**
     * a utils method to generate Roman string from Alien input 
     * @param arr
     * @return
     */
	@Override
    public String generateRomanFromGalacticUnit(String[] arr, Map<String, RomanNumber> interGalacticRomanMapping){
        StringBuilder romanNumeral = new StringBuilder();
        int i;
        for(i = 0; i< arr.length; i++){
            RomanNumber roman = interGalacticRomanMapping.get(arr[i]);
            if(roman != null){
                romanNumeral.append(roman.getValue());
            }
            else
                break;
        }

        if(i != arr.length)
            return null;

        return romanNumeral.toString();
    }
}
