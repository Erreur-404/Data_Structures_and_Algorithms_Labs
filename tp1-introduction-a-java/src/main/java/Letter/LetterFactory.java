package Letter;

import Point.Point2d;
import Shape.*;
import org.w3c.dom.css.Rect;

public final class LetterFactory {
    final static Double maxHeight = 150.0;

    //final static Double halfStripeThickness = stripeThickness / 2;
    /*------------ MESSAGE IMPORTANT ------------
    Bonjour à vous cher chargé! Voici comment j'ai établis les nouvelles valeurs:
     En essayant plusieurs valeurs de combinaisons de taille pour le width et le height
     (ainsi que les translations et les rotations),
     j'analysais ce qui semble le plus attrayant visuellement en comparant les différentes
     formes et tailles des différentes lettres. Ainsi j'ajustais manuellement la valeur à
     passer à paramètre selon une taille similaire entre toutes les lettres.*/
    final static Double baseWidth = 5.0;
    final static Double mediumWidth = 10.0;
    final static Double largeWidth = 15.0;
    final static Double circleBaseWidth = 40.0;
    final static Double mediumCircleWidth = 50.0;
    final static Double circleHeight = 90.0;
    final static Double tiltedRectangleHeight = 105.0;
    final static Double tiltedRectangleWidth = 7.0;
    final static Double baseHeight = 100.0;
    final static Double smallHeight = 30.0;
    final static Double btwSmallHalfHeight = 40.0;
    final static Double halfBaseHeight = 50.0;
    final static Double btwSmallMediumHeight = 55.0;
    final static Double mediumBaseHeight = 60.0;
    final static Double smallAngleRotation = 15.0;
    final static Double mediumAngleRotation = 25.0;
    final static Double quarterAngleRotation = 90.0;
    final static Double largeTranslation = 50.0;
    final static Double mediumTranslation = 30.0;
    final static Double btwSmallMediumTranslation = 25.0;
    final static Double smallTranslation = 20.0;
    final static Double verySmallTranslation = 10.0;


    /**
     * Create the letter A graphically
     * @return BaseShape containing the letter A
     */
    public static BaseShape create_A()  {
        BaseShape A = new BaseShape();
        Rectangle leftVercticalBar = new Rectangle(baseWidth,baseHeight);
        leftVercticalBar.rotate(leftVercticalBar.getCoords(),
                Math.toRadians(smallAngleRotation));
        A.add(leftVercticalBar);

        Rectangle rightVercticalBar = new Rectangle(baseWidth,baseHeight);
        rightVercticalBar.translate(rightVercticalBar.getCoords(),
                new Point2d(mediumTranslation, 0.0));
        rightVercticalBar.rotate(rightVercticalBar.getCoords(),
                Math.toRadians(-smallAngleRotation));
        A.add(rightVercticalBar);

        Rectangle horizontalBar = new Rectangle(baseWidth,smallHeight);
        horizontalBar.rotate(horizontalBar.getCoords(),
                Math.toRadians(quarterAngleRotation));
        horizontalBar.translate(horizontalBar.getCoords(),
                new Point2d(smallTranslation, verySmallTranslation));
        A.add(horizontalBar);

        return A;
    }

    /**
     * Create the letter B graphically
     * @return BaseShape containing the letter B
     */
    public static BaseShape create_B() {
        BaseShape B = new BaseShape ();
        Circle upperCircle = new Circle(halfBaseHeight);
        Circle innerCircle = new Circle(btwSmallHalfHeight);
        upperCircle.remove(innerCircle);
        Circle lowerCircle = new Circle(halfBaseHeight);
        lowerCircle.remove(innerCircle);
        upperCircle.translate(upperCircle.getCoords(),
                new Point2d(0.0, halfBaseHeight));
        B.add(upperCircle);
        B.add(lowerCircle);
        Rectangle leftBar = new Rectangle(mediumWidth, baseHeight);
        leftBar.translate(leftBar.getCoords(),
                new Point2d(-smallTranslation, btwSmallMediumTranslation));
        B.add(leftBar);
        return B;
    }

    /**
     * Create the letter C graphically
     * @return BaseShape containing the letter C
     */
    public static BaseShape create_C() {
        BaseShape c = create_O();
        BaseShape smallRectangle = new Rectangle(largeWidth,mediumBaseHeight);
        smallRectangle.translate(smallRectangle.getCoords(),
                new Point2d(smallTranslation,0.0));
        c.remove(smallRectangle);
        return c;
    }

    /**
     * Create the letter E graphically
     * @return BaseShape containing the letter E
     */
    public static BaseShape create_E() {
        BaseShape E = new BaseShape();
        Rectangle verticalBar = new Rectangle(baseWidth,baseHeight);
        verticalBar.translate(verticalBar.getCoords(),
                new Point2d(-btwSmallMediumTranslation,0.0));
        E.add(verticalBar);

        Rectangle middleHorizontalBar = new Rectangle(baseWidth,halfBaseHeight);
        middleHorizontalBar.rotate(middleHorizontalBar.getCoords(),
                Math.toRadians(quarterAngleRotation));
        E.add(middleHorizontalBar);

        Rectangle lowerHorizontalBar = new Rectangle(baseWidth,btwSmallMediumHeight);
        lowerHorizontalBar.rotate(lowerHorizontalBar.getCoords(),
                Math.toRadians(quarterAngleRotation));
        lowerHorizontalBar.translate(lowerHorizontalBar.getCoords(),
                new Point2d(0.0,halfBaseHeight));
        E.add(lowerHorizontalBar);

        Rectangle upperHorizontalBar = new Rectangle(baseWidth,btwSmallMediumHeight);
        upperHorizontalBar.rotate(upperHorizontalBar.getCoords(),
                Math.toRadians(quarterAngleRotation));
        upperHorizontalBar.translate(upperHorizontalBar.getCoords(),
                new Point2d(0.0,-halfBaseHeight));
        E.add(upperHorizontalBar);


        return E;
    }

    /**
     * Create the letter H graphically
     * @return BaseShape containing the letter H
     */
    public static BaseShape create_H() {
        BaseShape H = new BaseShape();
        Rectangle verticalBar = new Rectangle(baseWidth,baseHeight);
        H.add(verticalBar);
        verticalBar.translate(verticalBar.getCoords(),
                new Point2d(largeTranslation, 0.0));
        H.add(verticalBar);

        Rectangle horizontalBar = new Rectangle(baseWidth,halfBaseHeight);
        horizontalBar.rotate(horizontalBar.getCoords(),
                Math.toRadians(quarterAngleRotation));
        horizontalBar.translate(horizontalBar.getCoords(),
                new Point2d(btwSmallMediumTranslation, 0.0) );
        H.add(horizontalBar);

        return H;
    }

    /**
     * Create the letter N graphically
     * @return BaseShape containing the letter N
     */
    public static BaseShape create_N() {
        BaseShape N = new BaseShape();
        Rectangle verticalBar = new Rectangle(baseWidth,baseHeight);
        N.add(verticalBar);
        verticalBar.translate(verticalBar.getCoords(),
                new Point2d(largeTranslation, 0.0));
        N.add(verticalBar);
        Rectangle tiltedBar = new Rectangle(tiltedRectangleWidth,
                tiltedRectangleHeight);
        tiltedBar.rotate(tiltedBar.getCoords(),
                Math.toRadians(-mediumAngleRotation));
        tiltedBar.translate(tiltedBar.getCoords(),
                new Point2d(btwSmallMediumTranslation,0.0));
        N.add(tiltedBar);
        return N;
    }

    /**
     * Create the letter O graphically
     * @return BaseShape containing the letter O
     */
    public static BaseShape create_O() {
        Ellipse O = new Ellipse(mediumCircleWidth,baseHeight);
        Ellipse innerEllipse = new Ellipse(circleBaseWidth, circleHeight);
        O.remove(innerEllipse);
        return O;
    }

}
