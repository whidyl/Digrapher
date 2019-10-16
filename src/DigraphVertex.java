import java.awt.geom.Point2D;

public class DigraphVertex<T> {
    private T element;
    private Point2D.Double position;
    private DigraphVertex[] relatedVertices;

    DigraphVertex(T element, Point2D.Double position) {
        this.element = element;
        this.position = position;
    }

    public String getLabel() {
        return element.toString();
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D.Double position) {
        this.position = position;
    }
}
