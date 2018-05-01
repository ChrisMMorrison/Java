package nbody;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Body {

    private String identifier;
    private Image img;
    private Vector position;
    private Vector velocity;
    private ScientificNotation mass;
    private double radius;

    public Body(ScientificNotation positionX, ScientificNotation positionY, ScientificNotation velocityX, ScientificNotation velocityY, ScientificNotation mass, String name, double radius){
        identifier = name;
        img = new Image("File:C:\\Users\\morri\\eclipse-workspace\\Default\\planets\\" + identifier);
        position = new Vector(positionX, positionY);
        velocity = new Vector(velocityX, velocityY);

        this.mass = mass;

        this.radius = radius;
    }

    public void update(ArrayList<Body> allBodies){
        Vector acceleration = new Vector();
        //find velocity from superposition of gravitational forces
        for(Body body : allBodies){
            if(!body.getIdentifier().equals(identifier)){
                acceleration = Vector.add(calculateGravitionalAcc(body), acceleration);
            }
        }
        velocity.setxComp(ScientificNotation.add(velocity.getxComp(), ScientificNotation.mul(new ScientificNotation(1.296, 4), acceleration.getxComp())));
        velocity.setyComp(ScientificNotation.add(velocity.getyComp(), ScientificNotation.mul(new ScientificNotation(1.296, 4), acceleration.getyComp())));

        position.setxComp(ScientificNotation.add(position.getxComp(), ScientificNotation.mul(new ScientificNotation(1.296, 4), velocity.getxComp())));
        position.setyComp(ScientificNotation.add(position.getyComp(), ScientificNotation.mul(new ScientificNotation(1.296, 4), velocity.getyComp())));
    }

    public Vector calculateGravitionalAcc(Body body){
        //X's then Y's
        Vector acc = new Vector();

        ScientificNotation deltaY = ScientificNotation.sub(body.getPosition().getyComp(), position.getyComp());
        ScientificNotation deltaX = ScientificNotation.sub(body.getPosition().getxComp(), position.getxComp());

        ScientificNotation distance = ScientificNotation.pow(ScientificNotation.add(ScientificNotation.pow(deltaX, 2), ScientificNotation.pow(deltaY,2)),0.5);
        ScientificNotation force = ScientificNotation.div(ScientificNotation.mul(ScientificNotation.mul(new ScientificNotation(6.67, -11), mass), body.getMass()),
            ScientificNotation.pow(distance,2));

        acc.setyComp(ScientificNotation.div(ScientificNotation.mul(ScientificNotation.mul(force, new ScientificNotation(1,0)), ScientificNotation.div(deltaY, distance)), mass));
        acc.setxComp(ScientificNotation.div(ScientificNotation.mul(ScientificNotation.mul(force, new ScientificNotation(1,0)), ScientificNotation.div(deltaX, distance)), mass));

        return acc;
    }

    public void render(GraphicsContext gc){
        ScientificNotation im = ScientificNotation.mul(position.getxComp(),new ScientificNotation(6, 2));

        ScientificNotation posX = ScientificNotation.div(im, new ScientificNotation(10, 11));
        ScientificNotation posY = ScientificNotation.div(ScientificNotation.mul(position.getyComp(),new ScientificNotation(6, 2)), new ScientificNotation(10, 11));

      double positionX = posX.getMantissa() * Math.pow(10, posX.getExponent());
      double positionY = posY.getMantissa() * Math.pow(10, posY.getExponent());

       // System.out.println(positionX + ":" + positionY);

      gc.drawImage(img,positionX + 500, positionY + 500, radius, radius);
    }

    public String getIdentifier() {
        return identifier;
    }

    public Vector getPosition() {
        return position;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public ScientificNotation getMass() {
        return mass;
    }
}
