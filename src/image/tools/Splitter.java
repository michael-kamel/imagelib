package image.tools;

import image.operations.PointOperations;
import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicReference;

import tools.Structs.Pair;

public class Splitter 
{
	public static BufferedImage extractBackground(BufferedImage in, int segmentationParameter)
	{
		return PointOperations.pointOperationsForSome(in, oldColor -> 0x00000000 , oldColor -> oldColor.getRed() > segmentationParameter);
	}
	public static BufferedImage extractForeground(BufferedImage in, int segmentationParameter)
	{
		return PointOperations.pointOperationsForSome(in, oldColor -> 0x00000000 , oldColor -> oldColor.getRed() <= segmentationParameter);
	}
	public static Pair<BufferedImage, Double> extractBackgroundWithProb(BufferedImage in, int segmentationParameter)
	{
		AtomicReference<Double> prob = new AtomicReference<>(0.0);
		return new Pair<BufferedImage, Double>(PointOperations.pointOperationsForSome(in, oldColor -> { prob.set(prob.get() + 1); return 0x00000000; } , oldColor -> { return oldColor.getRed() > segmentationParameter;}), prob.get() / ((double)(in.getHeight() * in.getWidth())));
	}
	public static Pair<BufferedImage, Double> extractForegroundWithProb(BufferedImage in, int segmentationParameter)
	{
		AtomicReference<Double> prob = new AtomicReference<>(0.0);
		return new Pair<BufferedImage, Double>(PointOperations.pointOperationsForSome(in, oldColor -> { prob.set(prob.get() + 1); return 0x00000000; } , oldColor -> { return oldColor.getRed() <= segmentationParameter;}), prob.get() / ((double)(in.getHeight() * in.getWidth())));
	}
}