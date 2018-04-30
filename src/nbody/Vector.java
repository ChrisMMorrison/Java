package nbody;

public class Vector {

    private double xComp, yComp;

    public Vector(double xComp, double yComp) {
        this.xComp = xComp;
        this.yComp = yComp;
    }

    public Vector(){
        xComp = 0;
        yComp = 0;
    }

    public double magnitude(){
        return Math.sqrt(Math.pow(xComp,2) + Math.pow(yComp, 2));
    }

    public double getxComp() {
        return xComp;
    }

    public void setxComp(double xComp) {
        this.xComp = xComp;
    }

    public double getyComp() {
        return yComp;
    }

    public void setyComp(double yComp) {
        this.yComp = yComp;
    }

    public static Vector add(Vector otherVector, Vector vector){
        Vector finalVector = new Vector();
        finalVector.setxComp(vector.getxComp() + otherVector.getxComp());
        finalVector.setyComp(vector.getyComp() + otherVector.getyComp());

        return finalVector;
    }
}
