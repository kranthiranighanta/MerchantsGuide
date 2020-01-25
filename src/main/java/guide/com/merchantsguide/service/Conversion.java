package guide.com.merchantsguide.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import guide.com.merchantsguide.model.RomanNumber;

@Service
public interface Conversion {
	String INVALID_INPUT = "Please enter your criteria in valid format.";
	Map<String, RomanNumber> interGalacticRomanMapping = new HashMap<>();
	Map<String, Double> objectSoldPerUnitValue = new HashMap<>();
	void processInput(List<String> lines) throws Exception;
}
