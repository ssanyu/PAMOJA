/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package General;

/**
 *
 * @author Jssanyu
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author HP
 */
public class NumberValidator {

	private Pattern pattern;
	private Matcher matcher;

	private static final String NUM_PATTERN = 
		"^([0-9])*$";

    /**
     *
     */
    public NumberValidator() {
		pattern = Pattern.compile(NUM_PATTERN);
	}

	/**
	 * Validate hex with regular expression
	 * 
	 * @param hex
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public boolean validate(final String hex) {

		matcher = pattern.matcher(hex);
		return matcher.matches();

	}
}
