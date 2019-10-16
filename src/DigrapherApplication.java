import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedHashSet;

public class DigrapherApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Relations relations = new Relations();
        Integer[] myElements = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Digraph<Integer> myDigraph = new Digraph<>(
                new LinkedHashSet<Integer>(Arrays.asList(myElements)),
                Relations::xIsEvenlyDivisibleByY);
        System.out.println(myDigraph);
        primaryStage.setTitle("Digrapher");
        Scene scene = new Scene(myDigraph.getPlottedGraph(), 720, 720);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        /*Integer[] myElements = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        EvenlyDivisibleDigraph myDigraph = new EvenlyDivisibleDigraph(new LinkedHashSet<Integer>(Arrays.asList(myElements)));
        System.out.println(myDigraph);

        String[] myElements2 = {"cat", "allycat", "a", "b", "basketball", "ball", "cat", "hippo"};
        SubstringDigraph myDigraph2 = new SubstringDigraph(new LinkedHashSet<String>(Arrays.asList(myElements2)));
        System.out.println(myDigraph2); */
        Application.launch(args);
    }
}
