package prodcons;

public interface IProdConsBuffer {
	public void put(Message m) throws InterruptedException;

	public void put(Message m, int nb) throws InterruptedException;

	public Message get() throws InterruptedException;

	public Message[] get(int nb) throws InterruptedException;

	public int nmsg();

	public int totmsg();

}
