package nbody;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class Looop extends Application{

    public static void main(String[]args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        Canvas canvas = new Canvas(1900, 1000);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        //Image planet = new Image("8086.png");

        ScientificNotation zero = new ScientificNotation(0,0);

        Body sun = new Body("sun", zero,zero, zero, zero, new ScientificNotation(1.989,30), 50);
        Body mercury = new Body("mercury", new ScientificNotation(5.79,10),zero, zero, new ScientificNotation(4.79,4), new ScientificNotation(1.989,30),10);
        Body earth = new Body("earth", new ScientificNotation(1.496,11), zero, zero, new ScientificNotation(2.98,4), new ScientificNotation(5.974,24), 10);

        ArrayList<Body> bodies = new ArrayList<>();
        bodies.add(sun);
        bodies.add(mercury);
        bodies.add(earth);

        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017), //end ups being 60 FPS
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        for(Body body : bodies){
                            body.update(bodies);
                        }
                       // gc.clearRect(0, 0, 600,600);
                        for(Body body : bodies){
                            body.render(gc);
                        }


                    }
                });

        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();

        primaryStage.show();
    }
}
