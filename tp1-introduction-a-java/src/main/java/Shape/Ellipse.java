package Shape;

import Point.Point2d;

import java.util.Collection;

public class Ellipse extends BaseShape {
    /**
     * Create a filled Ellipse that is centered on (0, 0)
     * @param widthDiameter Width of the Ellipse
     * @param heightDiameter Height of the Ellipse
     */
    public Ellipse(Double widthDiameter, Double heightDiameter) {
        double widthRadius = widthDiameter/2;
        double heightRadius = heightDiameter/2;
        for (double i = -widthRadius; i <= widthRadius; i += 0.5) {
            for (double j = -heightRadius; j <= heightRadius; j += 0.5) {
                // Equation is x^2/A^2 + y^2/B^2 = 1
                // Where A is the width radius and B is the height radius
                boolean isInside = ((Math.pow(i, 2) / Math.pow(widthRadius, 2)) + (Math.pow(j, 2) / Math.pow(heightRadius, 2))) <= 1;
                if (isInside) {
                    this.add(new Point2d(i, j));
                }
            }
        }
    }

    /**
     * Create a filled Ellipse that is centered on (0,0)
     * @param dimensions 2D point containing the width and height of the Ellipse
     */
    public Ellipse(Point2d dimensions) {
        this(dimensions.X(), dimensions.Y());
    }

    /**
     * Create an Ellipse from a given collection of 2D points
     * @param coords Collection of 2D points
     */
    private Ellipse(Collection<Point2d> coords) {
        this.addAll(coords);
    }

    /**
     * @return Deep Copy of the Ellipse
     */
    @Override
    public Ellipse clone() {
        return new Ellipse(this.getCoords());
    }
}
