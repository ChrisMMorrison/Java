package nbody;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class GameLoop extends Application{

    boolean dick = false;

    public static void main(String[]args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        Canvas canvas = new Canvas(1200, 800);
        root.getChildren().add(canvas);



        //setup stuff here (run once)
        Bird bird = new Bird(10, 0);

        scene.setOnMouseClicked(
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent e) {
                        //code when mouse is clicked
                        bird.jump();
                    }
                });

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                //code when a key is pressed
                if(event.getCode() == KeyCode.J){
                    bird.jump();
                }
            }
        });


        GraphicsContext gc = canvas.getGraphicsContext2D();

        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017), //end ups being 60 FPS
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        gc.clearRect(0, 0, 1200,800);
                        //DO STUFF HERE (loops forever)

                        bird.update();
                        bird.render(gc);

                    }
                });

        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();

        primaryStage.show();
    }
}
