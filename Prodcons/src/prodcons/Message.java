package prodcons;

public class Message {
	private long content;
	private int id;
	private int nbCopy;

	public Message() {
		content = 0;
	}
	
	public Message(int id) {
		this.id = id;
	}

	public Message (long msg) {
		content = msg;
		nbCopy = 1;
	}
	
	public Message(long msg, int id) {
		content = msg;
		this.id = id;
	}

	public long getContent() {
		return content;
	}

	public int getId() {
		return id;
	}
	
	public int getNbCopy() {
		return nbCopy;
	}
	
	public void setNbCopy(int nb) {
		nbCopy = nb;
	}
	
	public void consCopy() {
		nbCopy--;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}