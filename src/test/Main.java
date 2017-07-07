package test;
import java.awt.image.BufferedImage;
import java.io.IOException;
import image.tools.*;
import image.IO.*;

public class Main 
{
	public static final String DIR = System.getProperty("user.dir") + "/Test/";
	public static void main(String[] args) 
	{
		try 
		{
			int thresh = 110;
			BufferedImage original = ReadWriteImage.readImage(DIR + "sat_noisy.gif");
			BufferedImage Binary_I = Threshold.threshold(original, thresh);
			BufferedImage FG_I = Splitter.extractForeground(original, thresh);
			BufferedImage BG_I = Splitter.extractBackground(original, thresh);
			
			double e_I = ProbabilityAnalysis.entropy(original);
			System.out.println("E Orig:" + e_I);
			double e_FG_I = ProbabilityAnalysis.entropy(FG_I);
			System.out.println("E FG:" + e_FG_I);
			double e_BG_I = ProbabilityAnalysis.entropy(BG_I);
			System.out.println("E BG:" + e_BG_I);
			double infoGain = ProbabilityAnalysis.infoGain(original, thresh);
			System.out.println("INFO GAIN:" + infoGain);
			ReadWriteImage.writeImage(Binary_I, DIR , "sn2", "jpg");
			ReadWriteImage.writeImage(FG_I, DIR , "snfg2", "jpg");
			ReadWriteImage.writeImage(BG_I, DIR, "snbg2", "jpg");
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
	}

}
