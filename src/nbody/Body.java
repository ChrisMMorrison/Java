package nbody;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Body {

    private String identifier;
    private Image img;
    private Vector position;
    private Vector velocity;
    private double mass;
    private double radius;

    public Body(String name, double positionX, double positionY, double velocityX, double velocityY, double mass, double radius){
        identifier = name;
       // img = image;
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

        velocity.setxComp(velocity.getxComp() + 1296000*acceleration.getxComp());
        velocity.setyComp(velocity.getyComp() + 1296000*acceleration.getyComp());

        position.setxComp(position.getxComp() + 1296000*velocity.getxComp());
        position.setyComp(position.getyComp() + 1296000*velocity.getyComp());
    }

    public Vector calculateGravitionalAcc(Body body){
        //X's then Y's
        Vector vector = new Vector();
        double forceX = (6.67e-11 * mass * body.getMass())/ Math.pow(position.getxComp() - body.getPosition().getxComp(),2);
        System.out.println(6.67e-11);
        vector.setxComp(forceX/mass);

        double forceY = (6.67e-11 * mass * body.getMass())/ Math.pow(position.getyComp() - body.getPosition().getyComp(),2);
        vector.setxComp(forceY/mass);

        return vector;
    }

    public void render(GraphicsContext gc){
      double posX = (position.getxComp() * 600) / 2.5e11;
      double posY = (position.getyComp() * 600) / 2.5e11;

      //gc.fillText(Double.toString(posX), 20, 20);
     //   System.out.println(posX);
      gc.fillOval(posX, posY, radius, radius);
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

    public double getMass() {
        return mass;
    }
}
