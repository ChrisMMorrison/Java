package nbody;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Body {

    private String identifier;
    private Image img;
    private double posX, posY;
    private double velX, velY;
    private double mass;
    private double radius;

    public Body(String name, Image image, double positionX, double positionY, double velocityX, double velocityY, double radius){
        identifier = name;
        img = image;

        posX = positionX;
        posY = positionY;

        velX = velocityX;
        velY = velocityY;

        this.radius = radius;
    }

    public void update(){

    }

    public void render(GraphicsContext gc){
        gc.drawImage(img, posX, posY, radius);
    }
}
