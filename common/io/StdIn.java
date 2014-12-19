package common.io;

import java.io.*;
import java.nio.BufferOverflowException;


public class StdIn {
	private BufferedInputStream buffer;
	private final String CHARSET = "UTF-8";
	
	public StdIn() throws IllegalArgumentException {
		this.buffer = new BufferedInputStream(System.in);
	}
	
	public byte[] readByesAt(int length) throws IOException {
		byte[] b = new byte[length+1];
		
		if (this.buffer.read(b, 0, length) == -1) {
			throw new BufferOverflowException();
		}
		return b;
	}
	
	public String readNextChar() {
		String s;
		
		try {
			byte[] b = this.readByesAt(1);
			s = new String(b, this.CHARSET).trim();			
		} catch(IOException ioe) {
			System.out.println("Unable to read characters");
			s = null;
		}
		return s;
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
