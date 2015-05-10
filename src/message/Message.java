package message;
import user.*;
import quiz_system.*;


public class Message implements Comparable{

	public String date;
	public User sender;
	public User receiver;
	public String textMessage;
	public int idCount;
	public boolean read;
	
	
	public Message(User sender, User receiver, String textMessage){
		this.sender=sender;
		this.receiver=receiver;
		this.textMessage=textMessage;
		date="";
	}
	
	public boolean isRead(){
		return read;
	}
	
	public String getDate(){
		return date;
	}
	
	public void markRead(){
		read=true;
	}
	
	public void markUnread(){
		read=false;
	}
	
	protected void setID(int num){
		idCount=num;
	}
	
	protected int getID(){
		return idCount;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Message [date=" + date + ", sender=" + sender + ", receiver="
				+ receiver + ", textMessage=" + textMessage + ", idCount="
				+ idCount + "]";
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + idCount;
		result = prime * result
				+ ((receiver == null) ? 0 : receiver.hashCode());
		result = prime * result + ((sender == null) ? 0 : sender.hashCode());
		result = prime * result
				+ ((textMessage == null) ? 0 : textMessage.hashCode());
		return result;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (idCount != other.idCount)
			return false;
		if (receiver == null) {
			if (other.receiver != null)
				return false;
		} else if (!receiver.equals(other.receiver))
			return false;
		if (sender == null) {
			if (other.sender != null)
				return false;
		} else if (!sender.equals(other.sender))
			return false;
		if (textMessage == null) {
			if (other.textMessage != null)
				return false;
		} else if (!textMessage.equals(other.textMessage))
			return false;
		return true;
	}



	@Override
	public int compareTo(Object arg0) {
		Message other = (Message)arg0;
		if(other.idCount>this.idCount)return -1;
		else if(this.idCount>other.idCount)return 1;
		else return 0;
	}



	public void setReadState(boolean read) {
		this.read=read;
		
	}
	
	
	
}
