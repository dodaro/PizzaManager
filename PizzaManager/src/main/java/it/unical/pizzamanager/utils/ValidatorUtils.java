package it.unical.pizzamanager.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtils {
	
	public static Boolean ValidateString(String regex,String toValidate){
		
		 Pattern pattern = Pattern.compile(regex);
		 Matcher matcher = pattern.matcher(toValidate);
		 return matcher.matches();
	}
}
