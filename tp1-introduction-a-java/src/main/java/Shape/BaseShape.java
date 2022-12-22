package Shape;

import Interface.Transform;
import Point.Point2d;

import java.util.*;
import java.util.stream.Collectors;

public class BaseShape extends Transform implements Cloneable {
    private final Collection<Point2d> coords;

    /**
     * Create a BaseShape with empty coordinates
     */
    public BaseShape() {
        this.coords = new ArrayList<> ();
    }

    /**
     * Create a BaseShape from a collection of 2D points
     * @param coords The collection of 2D points
     */
    public BaseShape(Collection<Point2d> coords) {
        this.coords = new ArrayList<> ();
        coords.stream()
                .forEach((coord) -> {
                    this.coords.add(new Point2d(coord.X(), coord.Y()));
                });
    }

    /**
     * Add a deep copy of the 2D point to the Shape
     * @param coord 2D point to add
     * @return Updated BaseShape
     */
    public BaseShape add(Point2d coord) {
        coords.add(new Point2d(coord.X(), coord.Y()));
        return this;
    }

    /**
     * Add a deep copy of the 2D points of the shape to the current shape
     * @param shape Shape to add to the current shape
     * @return Updated BaseShape
     */
    public BaseShape add(BaseShape shape) {
       addAll(shape.getCoords());
       return this;
    }

    /**
     * Add a deep copy of the points in the collection to the shape
     * @param coords Collections of point to add
     * @return Updated BaseShape
     */
    public BaseShape addAll(Collection<Point2d> coords) {
        coords.stream()
                .forEach((coord) -> {
                    this.add(coord);
                });
        return this;
    }

    /**
     * Remove the 2D point from the shape
     * @param coord Point to remove
     * @return Updated BaseShape
     */
    public BaseShape remove(Point2d coord) {
        coords.remove(coord);
        return this;
    }

    /**
     * Remove the 2D points in the shape from the current shape
     * @param shape Shape containing the points to remove
     * @return Updated BaseShape
     */
    public BaseShape remove(BaseShape shape) {
        coords.removeAll(shape.getCoords());
        return this;
    }

    /**
     * Remove the 2D points in the collection from the current shape
     * @param coords Collection of 2D points to remove
     * @return Updated BaseShape
     */
    public BaseShape removeAll(Collection<Point2d> coords) {
        this.coords.removeAll(coords);
        return this;
    }

    /**
    *  Replace all the coords in a shape with new ones
    * @param newCoords new coords to replace the old one
    * @return Updated BaseShape
    * */
    public BaseShape replaceAll(Collection<Point2d> newCoords) {
        coords.clear();
        coords.addAll(newCoords);
        return this;
    }


    /**
     * Return a shallow copy of the coordinates of the shape
     * @return Shallow copy of all coordinates contained by this BaseShape
     */
    public Collection<Point2d> getCoords() {
        return new ArrayList<Point2d> (coords);
    }

    /**
     * Create and return a deep copy of the coordinates of the shape
     * @return Deep copy of all coordinates contained by this BaseShape
     */
    public Collection<Point2d> cloneCoords() {
        Collection<Point2d> newCoords = new ArrayList<Point2d>();
        coords.stream()
                .forEach((coord) -> {
                        newCoords.add(coord.clone());
                });
        return newCoords;
    }

    /**
     * @return Maximum X coordinate of the shape
     */
    public Double getMaxX() {
        Double max = -Double.MAX_VALUE;
        Iterator<Point2d> it = coords.iterator();
        while (it.hasNext()) {
            Point2d coord = it.next();
            max = Math.max(coord.X(), max);
        }
        return max;
    }

    /**
     * @return Maximum Y coordinate of the shape
     */
    public Double getMaxY() {
        Double max = -Double.MAX_VALUE;
        Iterator<Point2d> it = coords.iterator();
        while (it.hasNext()) {
            Point2d coord = it.next();
            max = Math.max(coord.Y(), max);
        }
        return max;
    }

    /**
     * @return 2D Point containing the maximum X and Y coordinates of the shape
     */
    public Point2d getMaxCoord() {
        return new Point2d(this.getMaxX(), this.getMaxY());
    }

    /**
     * @return Minimum X coordinate of the shape
     */
    public Double getMinX() {
        Double min = Double.MAX_VALUE;
        Iterator<Point2d> it = coords.iterator();
        while (it.hasNext()) {
            Point2d coord = it.next();
            min = Math.min(coord.X(), min);
        }
        return min;
    }

    /**
     * @return Minimum Y coordinate of the shape
     */
    public Double getMinY() {
        Double min = Double.MAX_VALUE;
        Iterator<Point2d> it = coords.iterator();
        while (it.hasNext()) {
            Point2d coord = it.next();
            min = Math.min(coord.Y(), min);
        }
        return min;
    }

    /**
     * @return 2D point containing the minimum X and Y coordinate of the shape
     */
    public Point2d getMinCoord() {
        return new Point2d(this.getMinX(), this.getMinY());
    }

    /**
     * @return Deep copy of the current shape
     */
    public BaseShape clone() {
        return new BaseShape(coords);
    }
}
