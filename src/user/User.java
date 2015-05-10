package user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import quiz_system.*;
import user.*;


public class User {
	
	public String username;
	private Connection con;
	private ResultSet rs;
	private UserManager manager;
	private boolean admin;
	private QuizManager qMan;
	private boolean privateFlag;
	
	
	
	private static int FRIENDS_COL=0;
	private static int FRIEND_REQUEST_COL=1;
	private static int NOTES=2;
	private static int CHALLENGES=3;
	private static int MYQUIZES=4;
	/*
	 * GET score from specific quiz object...each quiz stores a history of the 
	 * users that have taken that quiz and their most recent score. 
	 */
	
	private static int QUIZES_TAKEN=5; 
	
	
	public String getHTML() {
		String result = "";
		result += "<a href=\"UserServlet?name=" + username + "\">" + username + "</a>";
		return result;
	}
	
	public static String getHTMLBullets(ArrayList<User> users,int num){
		String str = "";
		str += "<ul>";
		for(int i=0; i<users.size(); i++){
			if(num == -1 || i < num){
				str += "<li>";
				str += users.get(i).getHTML();
				str += "</li>";
			}
		}
		str += "</ul>";
		return str;
	}
	
	public User (String username, Connection con, UserManager manager, QuizManager qMan) throws SQLException {
		rs=null;
		this.manager=manager;
		this.username=username;
		this.con=con;
		this.qMan=qMan;
		setAdmin();
		privateFlag=manager.isPrivate(username);
	}
	
	
	public void setUserAsPrivate() throws SQLException{
		privateFlag=true;
		manager.makeUserPrivate(username);
		
	}
	
	public void setUserAsPublic() throws SQLException{
		privateFlag=false;
		manager.makeUserPublic(username);
	}
	
	public boolean isPrivate(){
		return privateFlag;
	}
	
	/*
	 * only call from an instance of user....user.isAdmin()
	 */
	public boolean isAdmin() throws SQLException{
		return manager.isAdmin(this.username);
	}
	
	
	
	
	
	
	public ArrayList<Achievement> getAchievements() throws SQLException{
		ArrayList<Achievement> achievements = new ArrayList<Achievement>();
		Statement state = (Statement) con.createStatement();
		rs = state.executeQuery("SELECT achievements FROM USER_"+username+";");
		while(rs.next()){
			String achievementString = rs.getString("achievements");
			if(achievementString!=null&&!(achievementString.equals(null)) && 
					!(achievementString.equals("null")) && !(achievementString.equals(""))){
				Achievement achievement = Achievement.valueOf(achievementString);
				achievements.add(achievement);
			}
			
			
		}
		return achievements;
	}
	
	
	public void addAchievement(Achievement a) throws SQLException{
		Statement state = (Statement) con.createStatement();
		String achievementToAdd = "";
		switch(a){
			case AMATEUR_AUTHOR:
				achievementToAdd="AMATEUR_AUTHOR";
				break;
			case PRODIGIOUS_AUTHOR:
				achievementToAdd="PRODIGIOUS_AUTHOR";
				break;
			case QUIZ_MACHINE:
				achievementToAdd ="QUIZ_MACHINE";
				break;
			case I_AM_THE_GREATEST:
				achievementToAdd ="I_AM_THE_GREATEST";
				break;
			case PRACTICE_MAKES_PERFECT:
				achievementToAdd ="PRACTICE_MAKES_PERFECT";
				break;
			case PROLIFIC_AUTHOR:
				achievementToAdd ="PROLIFIC_AUTHOR";
				break;
			case SOCIAL_BUTTERFLY:
				achievementToAdd ="SOCIAL_BUTTERFLY";
				break;
		}
		
		state.executeUpdate("INSERT into USER_"+username+" VALUES(null,\""+achievementToAdd+"\");");
		
	}
		
		
		
		
		
		
		
		
		
		
	
	
	
	public String getUserName(){
		return username;
	}
	//
	public void addFriend(String username) throws SQLException{
		ArrayList<User> friends = getFriendList();
		if(!friends.contains(manager.getExistingUser(username))){
			Statement state = (Statement) con.createStatement();
			if(!(manager.isUserNameAvailable(username))){
				state.executeUpdate("INSERT INTO USER_"+this.username+" VALUES (\"USER_"+
						username+"\", null);");
			}

		}
	}
	
	
	public void removeFriend(String username) throws SQLException{
		Statement state = (Statement) con.createStatement();
		state.executeUpdate("DELETE FROM USER_"+this.username+" WHERE friends=\"USER_"+username+"\";");
		User friend = manager.getExistingUser(username);
		friend.removeFriendForMe(this.username);
		
	}
	
	private void removeFriendForMe(String username2) throws SQLException {
		Statement state = (Statement) con.createStatement();
		state.executeUpdate("DELETE FROM USER_"+this.username+" WHERE friends=\"USER_"+username2+"\";");
		
	}


	public ArrayList<User> getFriendList() throws SQLException{
		ArrayList<User> friends = new ArrayList<User>();
		Statement state = (Statement) con.createStatement();
		rs=state.executeQuery("SELECT* FROM USER_"+username+";");
		while(rs.next()){
			String friend = rs.getString("friends");
			if(friend!=null && !(friend.equals("NULL")) && !(friend.equals(""))){
				String friendString = friend.substring(5);
				if(!manager.isUserNameAvailable(friendString)){
					User user = manager.getExistingUser(friendString);
					friends.add(user);
				}
			}
		}
		return friends;
	}
	
	
	public void changePassword(String newPassword) throws NoSuchAlgorithmException, SQLException{
		manager.changePassword(this.username,newPassword);
	}
	
	boolean friendInFriendList(String username) throws SQLException{
		User friend = manager.getExistingUser(username);
		ArrayList<User> friends = getFriendList();
		return friends.contains(friend);
		
		
	}
	
	
	public ArrayList<Quiz> getTakenQuizes() throws SQLException{
		return qMan.getQuizzesTakenByUser(this);
		
		
	}
	
	
	
	public ArrayList<Quiz> getCreatedQuizes() throws SQLException{
		return qMan.getQuizzesCreatedByUser(this);
		
	}
	



	
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [admin=" + admin + ", username=" + username + "]";
	}

	public void setAdmin() throws SQLException {
		admin = manager.isAdmin(username);
	}
	
	
	
}
	
