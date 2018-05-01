package nbody;

public class Vector {

    private ScientificNotation xComp, yComp;

    public Vector(ScientificNotation xComp, ScientificNotation yComp) {
        this.xComp = xComp;
        this.yComp = yComp;
    }

    public Vector(){
        xComp = new ScientificNotation(0,0);
        yComp = new ScientificNotation(0,0);
    }

    public ScientificNotation getxComp() {
        return xComp;
    }

    public void setxComp(ScientificNotation xComp) {
        this.xComp = xComp;
    }

    public ScientificNotation getyComp() {
        return yComp;
    }

    public void setyComp(ScientificNotation yComp) {
        this.yComp = yComp;
    }

    public static Vector add(Vector otherVector, Vector vector){
        Vector finalVector = new Vector();
//        System.out.println(vector.getxComp());
//        System.out.println(otherVector.getxComp());
        finalVector.setxComp(ScientificNotation.add(vector.getxComp(), otherVector.getxComp()));
        finalVector.setyComp(ScientificNotation.add(vector.getyComp(), otherVector.getyComp()));

        return finalVector;
    }

    @Override
    public String toString() {
        return "X: " + xComp + " Y: " + yComp;
    }
}
