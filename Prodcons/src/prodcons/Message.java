package prodcons;

public class Message {
	private String content;
	private int contentLen;
	private static int id = 0;

	public Message() {
		content = "";
		contentLen = 0;
	}
	
	public Message(String text) {
		content = text;
		contentLen = content.length();
		id++;
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
}
