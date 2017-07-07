package image.tools;

import image.operations.PointOperations;

import java.awt.image.BufferedImage;

public class Threshold
{
	public static BufferedImage threshold(BufferedImage in, int threshold)
	{
		return PointOperations.pointOperationsForSome(PointOperations.pointOperationsForSome(in, oldColor -> 0xFFFFFFFF, oldColor -> oldColor.getRed() > threshold), oldColor -> 0x00000000, oldColor -> oldColor.getRed() <= threshold);
	}
}
