package com.vane.ophthacare.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Calendar;
import java.util.List;
import java.util.Random;	

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import com.vane.ophthacare.model.ProfessionMedecin;

public class Utils {

	private static final Logger logger = LoggerFactory.getLogger(Utils.class);
	
	// Création du code du patient au format: NNN-PPP-YY-X-DD
    // Où NNN: 3 premiers lettres du nom, PPP: 3 premiers lettres du prénom, 
	// YY: 2 chiffres de l'année de naissance, X: une lettres aléatoire et DD: 2 chiffres du jour de naissance
	public static String codePatient (String firstName, String lastName, String year) {
		
		logger.info("Start Calcul codePatient");
		
		String code = "";
		
		firstName = firstName.replaceAll(Constants.CHARACTERS, "");
		lastName = lastName.replaceAll(Constants.CHARACTERS, "");
		
		logger.info("firstName: " +firstName);
		if (firstName.length() >= 3) {
			code += firstName.substring(0, 3);
		} else {
			code += firstName.substring(0, firstName.length());
			code += getRandomLetters(firstName);
		}
		if (lastName.length() >= 3) {
			code += lastName.substring(0, 3);
		} else {
			code += lastName.substring(0, lastName.length());
			code += getRandomLetters(lastName);
		}
		code += year.substring((year.length() -2), year.length());
		code += getOnlyRandomCharacter();
		code += year.substring(0, 2);
		
		logger.info("End Calcul codePatient: Patient " +firstName+ " " +lastName+ " avec le code d'enregistrement: " +code.toUpperCase());
		return code.toUpperCase();
	}
	
	public static int calculAgePatient (String datePAtient) {
		
		Calendar now = Calendar.getInstance();
		int currentYear = now.get(Calendar.YEAR); 
		logger.info("Current year " +currentYear); 
		int yearPatient = Integer.parseInt(datePAtient.substring((datePAtient.length() - 4), datePAtient.length()));
		logger.info("datePAtient " +yearPatient); 
		
		if ((currentYear - yearPatient) == 0) {
			return new Integer(1);
		}
		return (currentYear - yearPatient);
	}	

	public static int getMonthPatient(Calendar now, String datePAtient) {

		int currentMonth = now.get(Calendar.MONTH); 
		int monthPatient = Integer.parseInt(datePAtient.substring((datePAtient.length() - 4), datePAtient.length()));
		
		return 30;
	}

	// Create a random ASCII printable character in Java
    // Returns both lowercase and uppercase letters
    private static String getRandomLetters(String str) {
        String character = "";
        
        for (int i = 0; i <= 3; i++) {
			if (i > str.length()) {
				character = getOnlyRandomCharacter();
			}
        }
        return character;
    }
    
    private static String getOnlyRandomCharacter() {
        Random r = new Random();
        return String.valueOf((char)(r.nextInt(26) + 'a'));
    }
    
	public static String checkAttributeFromObject(Object cvb,boolean checkAllFields) {	
		Class cvbClass =  cvb.getClass();
		Field[] fieldCvb = cvbClass.getDeclaredFields();
		for(Field obj : fieldCvb){
			obj.setAccessible(true);
			try {
				if(obj.get(cvb) == null || StringUtils.isEmpty(obj.get(cvb).toString())){
					
					 if (!checkAllFields && Modifier.isPrivate(obj.getModifiers())) {
					       continue;
					   } else {
						   return "Campo "+ obj.getName()+" vuoto" ;		   
					   }
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				return "Generic checkAttributeFromObject " + e.getMessage();
			}
		}
		return Constants.OK;
	}

	public static String matriculeMedecin(String professionMedecin, String dateNaisMedecin) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String codeProfessionMedecin(String nomProfession) {
		return nomProfession.substring(0, 3).toUpperCase();
	}

	// Création du matricule du médecin au format: NNNPPDD
    // Où NNN: code profession, PP: 3 lettre du nom et prénom, DD: 2 chiffres du jour de naissance
	public static String matriculeMedecin(List<ProfessionMedecin> professionMedecinList, String professionMedecin, String nomMedecin, String prenomMedecin,
			String dateNaisMedecin) {

		String matriculeMedecin = "";
		
		logger.info("Start Calcul matriculeMedecin");
	
		if (professionMedecinList != null) {
			for (ProfessionMedecin profession : professionMedecinList) {
				if (profession.getNomProfession().equals(professionMedecin)) {
					matriculeMedecin += profession.getCodeProfession();
				}
			}
		}
		
		matriculeMedecin += nomMedecin.substring(0, 1).toUpperCase();
		matriculeMedecin += prenomMedecin.substring(0, 1).toUpperCase();
		matriculeMedecin += dateNaisMedecin.substring((dateNaisMedecin.length() -2), dateNaisMedecin.length());
		
		logger.info("End Calcul matriculeMedecin: Médedcin " +nomMedecin+ " " +prenomMedecin+ " avec le matricule d'enregistrement: " +matriculeMedecin.toUpperCase());
		
		return matriculeMedecin;
	}
}
