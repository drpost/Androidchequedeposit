package ImageConverter;

public class ImageConverterDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ImageConverter.ImageConveter img = new ImageConverter.ImageConveter();
		try
		{
			 img.getImage();
			 System.out.println("The byte array count is " + img.getCount());
			 System.out.println("The number ofbytes in bytes array is "+ img.getByteCount());
		}
		catch(Exception e)
		{
			System.out.println("Error: " + e);
		}

	}

}
