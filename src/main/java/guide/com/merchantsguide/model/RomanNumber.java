package guide.com.merchantsguide.model;

public enum RomanNumber {
	i(1, true), v(5, false), x(10, true), l(50, false), c(100, true), d(500, false), m(1000, true);

	private int value;
	private boolean repeatable;

	RomanNumber(int value, boolean repeatable) {
		this.value = value;
		this.repeatable = repeatable;
	}

	public int getValue() {
		return value;
	}

	public boolean isRepeatable() {
		return repeatable;
	}
	
	public boolean subtractableFrom(RomanNumber other) {
		// Currently subtractale digits happen to be the same ones that are
		// repeatable.
		// Should this change, then another solution is needed.
		if (other == null || !this.isRepeatable()) {
			return false;
		}

		int oridinal = this.ordinal();
		int otherOridinal = other.ordinal();

		return (oridinal == otherOridinal - 1 || oridinal == otherOridinal - 2);
	}

	public static int convertRomanNumber(String romanNumber) {
	    RomanNumber previousDigit = null;
	    int characterRepeatCount = 1;
	    int total = 0;
	
	    for(int i = 0; i < romanNumber.length(); i++){
	        // Also throws IllegalArgumentException if a digit is invalid
	        RomanNumber currentDigit = RomanNumber.valueOf(String.valueOf(romanNumber.charAt(i)));
	        int currentRomanCharNumericValue = currentDigit.getValue();
	
	        if (currentDigit.equals(previousDigit)) {
	            characterRepeatCount++;
	
	            if (characterRepeatCount > 3) {
	                throw new IllegalArgumentException("Repeatable Digit is repeated too often"); // Error message needs more details
	            }
	            if (currentDigit.isRepeatable()) {
	                total += currentRomanCharNumericValue;
	            } else {
	                throw new IllegalArgumentException("Unrepeatable Digit is repeated"); // Error message needs more details
	            }
	        } else if (previousDigit != null && previousDigit.compareTo(currentDigit) < 0) {
	            if (characterRepeatCount > 1) {
	                throw new IllegalArgumentException("Repeatable Digit is repeated before larger digit"); // Error message needs more details
	            }
	            if (previousDigit.subtractableFrom(currentDigit)) {
	                characterRepeatCount = 1;
	                total += currentRomanCharNumericValue - (2 * previousDigit.getValue()); 
	            } else {
	                throw new IllegalArgumentException("Digit may not be subtracted from other digit"); // Error message needs more details
	            }
	        } else {
	            characterRepeatCount = 1;
	            total += currentRomanCharNumericValue;
	        }
	
	        previousDigit = currentDigit;
	    }
	    return total;
	}
}
