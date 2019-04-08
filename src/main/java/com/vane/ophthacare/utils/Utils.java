package com.vane.ophthacare.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class Utils {

	private static final Logger logger = LoggerFactory.getLogger(Utils.class);
	
	
	public static String codePatient (String firstName, String lastName, String year) {
		
		logger.info("Start Calcul codePatient");
		
		String code = "";
		
		if (firstName.length() >= 3) {
			code += firstName.substring(0, 3).toUpperCase();
		} else {
			code += firstName.substring(0, firstName.length()).toUpperCase();
		}
		code += firstName.substring(0, 3).toUpperCase();
		code += lastName.substring(0, 3).toUpperCase();
		code += year.substring((year.length() -2), year.length());
		
		logger.info("End Calcul codePatient: Patient " +firstName+ " " +lastName+ " avec le code d'enregistrement: " +code);
		return code;
	}
	
	public static int calculAgePatient (String datePAtient) {
		
		Date now = new Date();
		logger.info("new date() " +now); 
		return 30;
	}	

	public static String checkAttributeFromObject(Object cvb,boolean checkAllFields) {	
		Class cvbClass =  cvb.getClass();
		Field[] fieldCvb = cvbClass.getDeclaredFields();
		for(Field obj : fieldCvb){
			obj.setAccessible(true);
			try {
				if(obj.get(cvb)==null || StringUtils.isEmpty(obj.get(cvb).toString())){
					
					 if (!checkAllFields && Modifier.isPrivate(obj.getModifiers())) {
					       continue;
					   }else{
						   return "Campo "+ obj.getName()+" vuoto" ;		   
					   }
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				return "Generic checkAttributeFromObject " + e.getMessage();
			}
		}
		return Constants.OK;
	}
}
