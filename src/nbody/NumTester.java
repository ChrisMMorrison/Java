package nbody;

public class NumTester {

    public static void main(String[] args) {
        ScientificNotation mass = new ScientificNotation(0, 0);
        ScientificNotation otherMass = new ScientificNotation(5.79, 10);

        ScientificNotation forceX = ScientificNotation.sub(mass, otherMass);

        System.out.println(forceX);
    }
}
