package common.io;

import java.io.*;
import java.nio.BufferOverflowException;


public class StdIn {
	private BufferedInputStream buffer;
	
	public StdIn()  {
		try {
			this.buffer = new BufferedInputStream(System.in);
		} catch (IllegalArgumentException iae) {
			System.out.println("Standard input failed to initialize BufferedInputStream.");
		}
	}
	
	public byte[] readNumBytes(int length) {
		byte[] b = new byte[length];
		
		try {
			this.buffer.read(b, 0, length);
		} catch (IOException ioe) {
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
