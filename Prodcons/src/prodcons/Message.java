package prodcons;

public class Message {
	private String content;
	private int contentLen;

	public Message(String text) {
		content = text;
		contentLen = content.length();
	}

	public String getContent() {
		return content;
	}

	public int getContentLen() {
		return contentLen;
	}
}
