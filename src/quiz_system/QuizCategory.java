package quiz_system;

import java.util.ArrayList;

public enum QuizCategory {	
	/*-------------------------- ENUMERATION TYPES -------------------------------------*/
	HISTORY ("History"),
	MATH ("Math"), 
	ENGLISH ("English"),
	LITERATURE ("Literature"),
	GEOGRAPHY ("Geography"),
	RELIGION ("Religion"),
	TECHNOLOGY ("Technology"),
	ENGINEERING ("Engineering"),
	COMPUTER_SCIENCE ("Computer Science"),
	ART ("Art"),
	MUSIC ("Music"),
	LANGUAGE ("Language"),
	BIOLOGY ("Biology"),
	CHEMISTRY ("Chemistry"),
	PHYSICS ("Physics"),
	SOCIOLOGY ("Sociology"),
	PSYCHOLOGY ("Psychology"),
	FOOD ("Food"),
	ECONOMICS ("Economics"),
	ETHICS ("Ethics"),
	NONE ("None");
	/*-------------------------- END ENUMERATION TYPES ---------------------------------*/
	
	
	/*-------------------------- PRIVATE INSTANCE VARIABLES ----------------------------*/
	private String description;
	/*-------------------------- END PRIVATE INSTANCE VARIABLES ------------------------*/
	
	
	/*-------------------------- CONSTRUCTOR -------------------------------------------*/
	QuizCategory(String str){
		description = str;
	}
	/*-------------------------- END CONSTRUCTOR ---------------------------------------*/
	
	
	/*-------------------------- PUBLIC FUNCTIONS --------------------------------------*/
	public String toString(){
		return description;
	}
	
	
	/**
	 * Given a String returns the correct quiz category, null if no match found
	 * @param str
	 * @return
	 */
	public static QuizCategory fromString(String str){
		for(QuizCategory qc: QuizCategory.values()){
			if(qc.description.equals(str)){
				return qc;
			}
		}
		return null;
	}
	
	
	/**
	 * Returns an ArrayList of all the QuizCategories
	 * @return
	 */
	public static ArrayList<QuizCategory> getAll(){
		ArrayList<QuizCategory> list = new ArrayList<QuizCategory>();
		for(QuizCategory qc: QuizCategory.values()){
			list.add(qc);
		}
		return list;
	}
	/*-------------------------- END PUBLIC FUNCTIONS ----------------------------------*/
}




