package image.operations;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

public class PointOperations 
{
	public static BufferedImage pointOperationsForAll(BufferedImage in, ToIntFunction<Color> operation) 
	{
		BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_RGB);
		for(int y = 0; y < in.getHeight(); y++)
			for(int x = 0; x < in.getWidth(); x++)
				out.setRGB(x, y, operation.applyAsInt(new Color(in.getRGB(x, y))));
		return out;
	}
	public static BufferedImage pointOperationsForSome(BufferedImage in, ToIntFunction<Color> operation, PointOPConstraint pConstraints, Predicate<Color> cConstraints) 
	{
		BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_RGB);
		for(int y = 0; y < in.getHeight(); y++)
			for(int x = 0; x < in.getWidth(); x++)
			{
				Color curr = new Color(in.getRGB(x, y));
				if(pConstraints.apply(x, y) && cConstraints.test(curr))
					out.setRGB(x, y, operation.applyAsInt(curr));
				else
					out.setRGB(x, y, in.getRGB(x, y));
			}
		return out;
	}
	public static BufferedImage pointOperationsForSome(BufferedImage in, ToIntFunction<Color> operation, PointOPConstraint pConstraints) 
	{
		BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_RGB);
		for(int y = 0; y < in.getHeight(); y++)
			for(int x = 0; x < in.getWidth(); x++)
			{
				Color curr = new Color(in.getRGB(x, y));
				if(pConstraints.apply(x, y))
					out.setRGB(x, y, operation.applyAsInt(curr));	
				else
					out.setRGB(x, y, in.getRGB(x, y));
			}
		return out;
	}
	public static BufferedImage pointOperationsForSome(BufferedImage in, ToIntFunction<Color> operation, Predicate<Color> cConstraints) 
	{
		BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_RGB);
		for(int y = 0; y < in.getHeight(); y++)
			for(int x = 0; x < in.getWidth(); x++)
			{
				Color curr = new Color(in.getRGB(x, y));
				if(cConstraints.test(curr))
					out.setRGB(x, y, operation.applyAsInt(curr));
				else
					out.setRGB(x, y, in.getRGB(x, y));
			}
		return out;
	}
}
