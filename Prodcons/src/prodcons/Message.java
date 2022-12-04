package prodcons;

public class Message {
	private String content;
	private int contentLen;
	private int id;
	private boolean processed;

	public Message() {
		content = "";
		contentLen = 0;
		processed = false;
	}
	
	public Message(String text, int id) {
		content = text;
		contentLen = content.length();
		processed = false;
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public int getId() {
		return id;
	}
	
	public int getContentLen() {
		return contentLen;
	}
	
	public void process() {
		processed = true;
	}
	
	public boolean isProcessed() {
		return processed;
	}
}
