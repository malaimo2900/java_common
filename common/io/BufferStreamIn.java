package common.io;

import java.io.*;


public class BufferStreamIn {
	private BufferedInputStream buffer;
	private static BufferStreamIn instance;
	
	/// Ensure that a static input stream methd is used to create the object
	private BufferStreamIn() { }
	
	private static BufferStreamIn obj() {
		if (!(BufferStreamIn.instance instanceof BufferStreamIn)) {
			BufferStreamIn.instance = new BufferStreamIn();
		}
		
		return BufferStreamIn.instance;
	}
	
	private BufferStreamIn createBuffer(InputStream in)  {
		try {
			this.buffer = new BufferedInputStream(in);
		} catch (IllegalArgumentException iae) {
			System.out.println("Standard input failed to initialize BufferedInputStream.");
		}
		
		return this;
	}
	
	public static BufferStreamIn createStreamStdIn() {
		return BufferStreamIn.obj().createBuffer(System.in);
	}
	
	public byte[] readNumBytes(int length) {
		byte[] b;
		
		try {
			b = new byte[length];
			this.buffer.read(b, 0, length);
		} catch (IOException ioe) {
			b = null;
			System.out.println("Unable to read bytes");
		}
		
		return b;
	}
	
	public String readNextChar() {
		return new String(this.readNumBytes(1));
	}
	
	public boolean isEmpty() {
		boolean avail = true;
		try {
			if (this.buffer.available() > 0) {
				avail = false;
			}
		} catch (IOException e) {
			System.out.println("Buffer is invalid");
		}
		return avail;
	}
	
	public void close() {
		try {
			this.buffer.close();
		} catch(IOException ioe) {
			System.out.println("The buffer didi not contain any input from standard in");
		}
	}
}
