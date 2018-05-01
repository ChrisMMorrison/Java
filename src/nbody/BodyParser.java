package nbody;

import bibleWords.EasyReader;

import java.util.ArrayList;

public class BodyParser {

    EasyReader reader;

    public BodyParser(String fileName){
        reader = new EasyReader(fileName);
        if (reader.bad()){
            System.err.println("Can't open body list");
            System.exit(1);
        }

    }

    public ArrayList<Body> parseBodies(){
        ArrayList<Body> bodies = new ArrayList<>();

        String line = null;

        while((line = reader.readLine()) != null){
            ArrayList<String> components = new ArrayList<>();
            while(line.length() > 0 ){
                if(line.indexOf(" ") == -1){
                    components.add(line);
                    line = "";
                } else {
                    components.add(line.substring(0, line.indexOf(" ")));
                }
                line = line.substring(line.indexOf(" ")+1,line.length());
            }
            ArrayList<ScientificNotation> numbers = new ArrayList<>();
            String name ="";
            for(String comp : components){
               char[] chars = comp.toCharArray();
                if(Character.isDigit(chars[1]) || chars[1] == '.') {
                    double mantissa = Double.parseDouble(comp.substring(0, comp.indexOf("e")));
                    double exponent = Double.parseDouble(comp.substring(comp.indexOf("e")+1, comp.length()));

                    numbers.add(new ScientificNotation(mantissa, exponent));
                } else {
                    name = comp;
                }
            }

            bodies.add(new Body(numbers.get(0), numbers.get(1), numbers.get(2), numbers.get(3), numbers.get(4), name, numbers.get(4).getExponent()));
        }
        return bodies;
    }
}
