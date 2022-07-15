package motorgui;

public class Datapackage {

	public char startchar;
	public char syncchar;
	public char packageno;
	public char command;
	public byte[] data;
	public int checksum;
	public char stop1;
	public char stop2;
	public char stop3;
	public char stop4;

	public Datapackage() {
		startchar = 'S';
		syncchar = 'Y';
		packageno = 0;
		command = 0;
		data = new byte[52];
		checksum = 0;
		stop1 = 'A';
		stop2 = 'B';
		stop3 = 'C';
		stop4 = 'D';
		// TODO Auto-generated constructor stub
	}

}
