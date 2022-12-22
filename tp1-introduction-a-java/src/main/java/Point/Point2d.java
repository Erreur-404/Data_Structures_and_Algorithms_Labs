package Point;

public class Point2d extends AbstractPoint {
    private final Integer X = 0;
    private final Integer Y = 1;

    /**
     * 2D Point Constructor from coordinates
     * @param x X coordinate
     * @param y Y coordinate
     */
    public Point2d(Double x, Double y) {
        super(new double[] {x, y});
    }

    /**
     * 2D Point Constructor from vector
     * @param vector Vector containing X and Y coordinates
     */
    public Point2d(Double[] vector) {
        this(vector[0], vector[1]);
    }

    /**
     * @return X coordinate
     */
    public double X() { return vector[X];}

    /**
     * @return Y coordinate
     */
    public double Y() { return vector[Y];}

    /**
     * Translate the point by the given vector
     * @param translateVector The vector by which to translate
     * @return Translated point
     */
    @Override
    public Point2d translate(Double[] translateVector) {
        PointOperator.translate(this.vector, translateVector);
        return this;
    }

    /**
     * Translate the point by the given 2D Point
     * @param translateVector The 2D Point by which to translate
     * @return Translated point
     */
    public Point2d translate(Point2d translateVector) {
        this.translate(translateVector.vector);
        return this;
    }

    /**
     * Rotate the point by the given rotation Matrix
     * @param rotationMatrix Matrix by which to rotate
     * @return Rotated point
     */
    @Override
    public Point2d rotate(Double[][] rotationMatrix) {
        PointOperator.rotate(this.vector, rotationMatrix);
        return this;
    }

    /**
     * Rotate the point by the given angle
     * @param angle Angle in radians
     * @return Rotated point
     */
    public Point2d rotate(Double angle) {
        if( vector[X] == 0.0 && vector[Y] == 0.0){
            return this;
        }
        double norm = Math.sqrt(Math.pow(vector[X], 2) + Math.pow(vector[Y], 2));
        Double former_angle = Math.atan(vector[Y] / vector[X]);
        double new_angle = former_angle + angle;
        vector[X] = norm * Math.cos(new_angle);
        vector[Y] = norm * Math.sin(new_angle);
        return this;
    }

    /**
     * Divide the X and Y coordinates of a 2D point by a scalar
     * @param divider Scalar used to divide
     * @return Divided point
     */
    @Override
    public Point2d divide(Double divider) {
        for (int i = 0; i < vector.length; i++) {
            vector[i] /= divider;
        }
        return this;
    }

    /**
     * Multiply the X and Y coordinates of a 2D point by a scalar
     * @param multiplier Scalar used to multiply
     * @return Multiplied point
     */
    @Override
    public Point2d multiply(Double multiplier) {
        for (int i = 0; i < vector.length; i++) {
            vector[i] *= multiplier;
        }
        return this;
    }

    /**
     * Add a scalar to the X and Y coordinates of a 2D point
     * @param adder Scalar to add
     * @return Added point
     */
    @Override
    public Point2d add(Double adder) {
        for (int i = 0; i < vector.length; i++) {
            vector[i] += adder;
        }
        return this;
    }

    /**
     * @return Deep copy of the 2D point
     */
    @Override
    public Point2d clone() {
        return new Point2d(vector[X], vector[Y]);
    }
}
