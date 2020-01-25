package guide.com.merchantsguide.service;

import java.util.HashMap;
import java.util.Map;

import guide.com.merchantsguide.model.RomanNumber;

public interface Generator {

	void mapInterGalacticToRomanUnits(String[] arr, Map<String, RomanNumber> interGalacticRomanMapping);

	int generateObjectSoldPerUnitMap(String[] arr, Map<String, RomanNumber> interGalacticRomanMapping,
			Map<String, Double> objectsoldperunitvalue) throws Exception;

	String generateRomanFromGalacticUnit(String[] arr, Map<String, RomanNumber> interGalacticRomanMapping);

	int generateGalacticUnitToNumericValue(String[] arr, Map<String, RomanNumber> interGalacticRomanMapping);

	Double generateCreditValue(String[] arr, Map<String, Double> objectSoldPerUnitValue,
			Map<String, RomanNumber> interGalacticRomanMapping);

}
