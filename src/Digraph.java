import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Digraph<T> {
    /* A Digraph holds a set of elements and a binary relation.
     * The relation is set by overriding xRelatesToY.
     * The Digraph can be displayed as a circle of nodes with arrows indicating relationships between elements.
     * Otherwise, it can be displayed as a matrix with 1 meaning x relates y element, and 0 meaning x does not relate to y.
     */
    private LinkedHashSet<T> elements;
    private ArrayList<T> elementsAsArray;
    private ArrayList<DigraphVertex> vertices = new ArrayList<DigraphVertex>();
    private boolean[][] relationMatrix;
    private Relation<T> relation;

    Digraph (LinkedHashSet<T> elements, Relation<T> relation) {
        this.elements = elements;
        elementsAsArray = new ArrayList<T>(elements);
        relationMatrix = new boolean[elements.size()][elements.size()];
        this.relation = relation;
        updateRelationMatrix();
        generateVertices();
        positionVerticesInACircle();
    }

    public Group getPlottedGraph() {
        Group plot = new Group();
        for (DigraphVertex vertexA: vertices) {
            //TODO
            //for (DigraphVertex vertexB: vertices) {
                // draw an arrow from a to be if a relates to be
                // how do we figure out if a relates to b?
            //}
            plot.getChildren().add(makeCircleForVertex(vertexA));
            plot.getChildren().add(makeTextForVertex(vertexA));
        }
        return plot;
    }

    private Text makeTextForVertex(DigraphVertex vertex) {
        Text text = new Text(vertex.getLabel());
        text.setTextAlignment(TextAlignment.CENTER);
        text.setX(vertex.getPosition().getX() - text.getLayoutBounds().getWidth());
        text.setY(vertex.getPosition().getY() + text.getLayoutBounds().getHeight()/2);
        text.setFill(Color.BLACK);
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        return text;
    }

    private Circle makeCircleForVertex(DigraphVertex vertex) {
        Circle circle = new Circle();
        circle.setCenterX(vertex.getPosition().getX());
        circle.setCenterY(vertex.getPosition().getY());
        circle.setFill(Color.WHITE);
        circle.setStrokeWidth(3);
        circle.setStroke(Color.BLACK);
        circle.setRadius(30.0f);
        return circle;
    }

    private void positionVerticesInACircle() {
        double radius = 200;
        double centerX = 360;
        double centerY = 360;
        for (int i = 0; i < vertices.size(); i++) {
            double theta = (double)i * ((Math.PI * 2) / vertices.size());
            Point2D.Double vertPos = new Point2D.Double();
            vertPos.x = centerX + radius*Math.cos(theta);
            vertPos.y = centerY + radius*Math.sin(theta);
            vertices.get(i).setPosition(vertPos);
        }
    }

    private void generateVertices() {
        for (T element : elements) {
            vertices.add(new DigraphVertex<T>(element, new Point2D.Double(0,0)));
        }
    };

    private void updateRelationMatrix() {
        int col = 0;
        int row = 0;
        for (T elementX : elements) {
            for (T elementY : elements) {
                relationMatrix[row][col] = relation.xRelatesToY(elementX, elementY);
                col++;
            }
            col = 0;
            row++;
        }
    }

    private String relationMatrixToString() {
        //TODO The matrix should look aligned for elements longer than one char.
        StringBuilder output = new StringBuilder();
        output.append("   X ");
        for (T element : elements) output.append(element.toString() + "  ");
        output.append("\nY \n");
        for (int i = 0; i < relationMatrix.length; i++) {
            output.append(elementsAsArray.get(i) + "    ");
            for (int j = 0; j < relationMatrix.length; j++) {
                int relationAsInt = relationMatrix[i][j] ? 1 : 0;
                output.append(relationAsInt + "  ");
            }
            output.append("\n");
        }
        return output.toString();
    }

    @Override
    public String toString() {
        return relationMatrixToString();
    }
}
