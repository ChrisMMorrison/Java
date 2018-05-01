package nbody;

public class ScientificNotation {

    private double mantissa;
    private double exponent;

    public ScientificNotation(double mantissa, double exponent) {
        this.mantissa = mantissa;
        this.exponent = exponent;
    }
    //changes mantissa of lesser number to match power then add
    public static ScientificNotation add(ScientificNotation num, ScientificNotation otherNum){
        double expDifference = num.getExponent() - otherNum.getExponent();
        double mantissa;
        double exponent;
        if(expDifference < 0){
            double adjMantissa = num.getMantissa() / Math.pow(10, Math.abs(expDifference));
            mantissa = adjMantissa + otherNum.getMantissa();
            exponent = otherNum.getExponent();

        } else {
            double adjMantissa = otherNum.getMantissa() / Math.pow(10, expDifference);
            mantissa = num.getMantissa() + adjMantissa;
            exponent = num.getExponent();
        }

        while(Math.abs(mantissa) >= 10){
            mantissa /= 10;
            exponent++;
        }

        return new ScientificNotation(mantissa, exponent);
    }

    public static ScientificNotation sub(ScientificNotation num, ScientificNotation otherNum){
        double expDifference = num.getExponent() - otherNum.getExponent();
        double mantissa;
        double exponent;
        if(expDifference < 0){
            double adjMantissa = num.getMantissa() / Math.pow(10, Math.abs(expDifference));
            mantissa = adjMantissa - otherNum.getMantissa();
            exponent = otherNum.getExponent();

        } else {
            double adjMantissa = otherNum.getMantissa() / Math.pow(10, expDifference);
            mantissa = num.getMantissa() - adjMantissa;
            exponent = num.getExponent();
        }

        while(Math.abs(mantissa) < 1 && mantissa != 0){
            mantissa *= 10;
            exponent--;
        }

        return new ScientificNotation(mantissa, exponent);
    }

    public static ScientificNotation mul(ScientificNotation num, ScientificNotation otherNum){
        double mantissa = num.getMantissa() * otherNum.getMantissa();
        double exponent = num.getExponent() + otherNum.getExponent();

        while(Math.abs(mantissa) >= 10){
            mantissa /= 10;
            exponent++;
        }

        return new ScientificNotation(mantissa, exponent);
    }

    public static ScientificNotation div(ScientificNotation num, ScientificNotation otherNum){
        double mantissa = num.getMantissa() / otherNum.getMantissa();
        double exponent = num.getExponent() - otherNum.getExponent();

        while(Math.abs(mantissa) < 1 && mantissa != 0){
            mantissa *= 10;
            exponent--;
        }
        return new ScientificNotation(mantissa, exponent);
    }

    public static ScientificNotation pow(ScientificNotation num, double pow){
        double mantissa = Math.pow(num.getMantissa(), pow);
        double exponent = num.getExponent() * pow;

        while(Math.abs(mantissa) >= 10){
            mantissa /= 10;
            exponent++;
        }
        return new ScientificNotation(mantissa, exponent);
    }

    public static ScientificNotation abs(ScientificNotation num){
        if(num.getMantissa() < 0){
            num.setMantissa(-1*num.getMantissa());
        }
        return num;
    }

    public double getMantissa() {
        return mantissa;
    }

    public void setMantissa(double mantissa) {
        this.mantissa = mantissa;
    }

    public double getExponent() {
        return exponent;
    }

    public void setExponent(int exponent) {
        this.exponent = exponent;
    }

    @Override
    public String toString() {
        return mantissa + "E" + exponent;
    }
}
