package message;

import java.sql.SQLException;
import java.util.ArrayList;

import user.*;


public class FriendRequest extends Message {

	public Mailman mm;

	
	
	public FriendRequest(User sender, User receiver, String textMessage) {
		super(sender, receiver, textMessage);
		
		
	}
	
	
	public void confirmFriend() throws SQLException{
		sender.addFriend(receiver.getUserName());
		receiver.addFriend(sender.getUserName());
		mm.removeFriendRequests(sender, receiver);
		ArrayList<User> friends = receiver.getFriendList();
		if(friends.size()==5){
			receiver.addAchievement(Achievement.SOCIAL_BUTTERFLY);
		}
		ArrayList<User> oFriends = sender.getFriendList();
		if(oFriends.size()==5){
			sender.addAchievement(Achievement.SOCIAL_BUTTERFLY);
		}
	}
	
	public void rejectFriend() throws SQLException{
		mm.removeFriendRequests(sender, receiver);
		
	}



}
