package message;

import user.User;
import quiz_system.Quiz;
import quiz_system.QuizScore;

public class ChallengeRequest extends Message{
	
	public Quiz quiz;
	public QuizScore highScore;
	
	
	public ChallengeRequest(User sender, User receiver, String textMessage, Quiz quiz) {
		super(sender, receiver, textMessage);
		this.quiz=quiz;
		
		if(quiz != null){
			if(!quiz.hasUserTakenQuiz(sender)){
				highScore = new QuizScore(0, System.currentTimeMillis(),sender);
			}else{
				highScore=quiz.getHighScore(sender);
			}			
		}

	}
	
	
	public QuizScore challengerHighScore(){
		
		return highScore;
	}
	
	
	
	

}
