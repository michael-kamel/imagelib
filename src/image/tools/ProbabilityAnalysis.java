package image.tools;

import java.awt.image.BufferedImage;
import tools.MathTools;
import tools.Structs.Pair;
import image.operations.PointOperations;

public class ProbabilityAnalysis 
{
	public static double[] grayScaleHistogram(BufferedImage in)
	{
		double[] out = new double[256];
		PointOperations.pointOperationsForAll(in, (oldColor) -> { out[oldColor.getRed()]++; return oldColor.getRGB(); });
		return out;
	}
	public static double[] probabilityDistribution(BufferedImage in)
	{
		double[] out = grayScaleHistogram(in);
		for(int i = 0; i < out.length; i++)
			out[i] /= (in.getHeight() * in.getWidth());
		return out;
	}
	public static double entropy(BufferedImage in)
	{
		double[] probDist = probabilityDistribution(in);
		double entropy = 0.0;
		for(int i = 0; i < probDist.length; i++)
			entropy += probDist[i] != 0 ? probDist[i] * MathTools.logOfBase(probDist[i], 2.0) : 0;
		return -1 * entropy;
	}
	public static double infoGain(BufferedImage in, int segmentationParameter)
	{
		Pair<BufferedImage, Double> fore = Splitter.extractForegroundWithProb(in, segmentationParameter);
		Pair<BufferedImage, Double> back = Splitter.extractBackgroundWithProb(in, segmentationParameter);
		return entropy(in) - ((entropy(fore.getLeft()) * fore.getRight()) + (entropy(back.getLeft()) * back.getRight()));
	}
}
