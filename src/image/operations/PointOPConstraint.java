package image.operations;

@FunctionalInterface
public interface PointOPConstraint
{
	boolean apply(int x, int y);
}
