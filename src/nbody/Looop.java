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
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Looop extends Application{

    public static void main(String[]args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        Canvas canvas = new Canvas(600, 600);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Circle Sun = new Circle(50);
        Circle Earth = new Circle(10);
        Circle Venus = new Circle(11);

        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        final long timeStart = System.currentTimeMillis();

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017), //end ups being 60 FPS
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        double t = (System.currentTimeMillis() - timeStart) / 1000.0;

                        double e_x = 300 + 100*Math.cos(t);
                        double e_y = 300 + 100*Math.sin(t);

                        double v_x = 300 + 100*Math.cos(t-100);
                        double v_y = 300 + 100*Math.sin(t-100);

                        gc.clearRect(0,0,600,600);

                        gc.fillOval(e_x - 10, e_y - 10, 20, 20);
                        gc.fillOval(v_x - 10, v_y - 10, 20, 20);

                        gc.fillOval(280, 280, 40, 40);
                    }
                });

        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();

        primaryStage.show();
    }
}
