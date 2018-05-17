package nbody;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

public class Bird {

    double position, velocity;

    Image img;

    public Bird(double pos, double velocity){
        position = pos;
        this.velocity = velocity;
        img = new Image("File:C:\\Users\\morri\\eclipse-workspace\\Default\\bird.png");
    }

    public void update(){
        velocity = velocity - 9.806 * 0.0166;
        position = position + velocity * 0.0166;

        System.out.println(velocity);

    }

    public void render(GraphicsContext gc){
        double y = 600 - position * 600/75;

        gc.save();
        rotate(gc, velocity * -3, 533, y + 25);
        gc.drawImage(img, 500, y, 65,50);
        gc.restore();
    }

    //100% original
    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    public double getPosition() {
        return position;
    }

    public void setPosition(double position) {
        this.position = position;
    }

    public double getVelocity() {
        return velocity;
    }

    public void jump(){
        velocity = 20;
        System.out.println(velocity);
    }
}
