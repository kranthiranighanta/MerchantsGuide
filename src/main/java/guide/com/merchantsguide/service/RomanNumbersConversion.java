package guide.com.merchantsguide.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guide.com.merchantsguide.model.RomanNumber;
import guide.com.merchantsguide.validators.RomanNumberValidator;

/**
 * Class to read and process the data at the same time
 * The processing starts as soon as the input is received
 *
 */

@Service
public class RomanNumbersConversion implements Conversion {

    @Autowired
    private Generator generator;
    /**
     * Parses the input line by line and decides the type of request and appropriately forwards the request
     * @param line
     * @throws Exception
     */
	@Override
	public void processInput(List<String> lines) throws Exception{
        lines.forEach(line -> {
        	line = line.toLowerCase();
            String[] inputs = line.split("\\s+");
            List<String> inputQuestions = DisplayOutput.inputQuestions;
            List<String> outputValues = DisplayOutput.outputValues;

            if(line.startsWith("how many credits is")) {
                inputQuestions.add(line);
                outputValues.add(String.valueOf(generator.generateCreditValue(Arrays.copyOfRange(inputs, 4, inputs.length-1), objectSoldPerUnitValue, interGalacticRomanMapping)));
            }
            else if(line.startsWith("how much is")){
                inputQuestions.add(line);
                outputValues.add(String.valueOf(generator.generateGalacticUnitToNumericValue(Arrays.copyOfRange(inputs, 3, inputs.length-1), interGalacticRomanMapping)));
            }
            else if(line.contains("is") && !line.contains("credits"))
            	generator.mapInterGalacticToRomanUnits(inputs, interGalacticRomanMapping);
            else if(line.contains("is") && line.contains("credits"))
				try {
					generator.generateObjectSoldPerUnitMap(inputs, interGalacticRomanMapping, objectSoldPerUnitValue);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else{
                inputQuestions.add(line);
                outputValues.add(INVALID_INPUT);
            }
        });
    }



}