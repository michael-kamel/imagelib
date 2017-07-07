package image.IO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ReadWriteImage 
{
	public static BufferedImage readImage(String path) throws FileNotFoundException, IOException
	{
		return ImageIO.read(new FileInputStream(path));
	}
	public static void writeImage(BufferedImage image, String path, String name, String extension) throws IOException
	{
		File outputfile = new File(path+ "/" + name + "." + extension);
		ImageIO.write(image, extension, outputfile);
	}
}
