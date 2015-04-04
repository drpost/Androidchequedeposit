package ImageConverter;


import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;

import java.io.*;

public class ImageConveter {
	private int byteCount;
	private int count;
	public ImageConveter()
	{
		setByteCount(0);
		setCount(0);
	}
	
	 
	
	public byte[] getImage() throws Exception 
	 {
		 File file = new File("C:\\Users\\Kevin\\Pictures\\check_04.jpg");
		 byte[] bytes = new byte [(int)file.length()];
		 
		 

		ImageInputStream myStream = new FileImageInputStream(file);

		 this.setCount((int)myStream.length());
		 myStream.seek(0);
		 this.setByteCount(myStream.read(bytes, 0, getCount()));
	
	 myStream.close();

	
	return bytes;
	 }



	public int getCount() {
		return count;
	}



	public void setCount(int count) {
		this.count = count;
	}



	public int getByteCount() {
		return byteCount;
	}



	public void setByteCount(int byteCount) {
		this.byteCount = byteCount;
	}
	
}
