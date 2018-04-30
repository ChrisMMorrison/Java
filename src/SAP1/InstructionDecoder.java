package SAP1;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InstructionDecoder {

    private static final String FILE_A = "E:\\Libraries\\Documents\\SAP-1-master\\InstructionA.txt";
    private static final String FILE_B = "E:\\Libraries\\Documents\\SAP-1-master\\InstructionB.txt";

    //for ROM A
    public static final int HLT = 0b00010000;
    public static final int Lo  = 0b00001000;
    public static final int Lb  = 0b00000100;
    public static final int Eo  = 0b00000010;
    public static final int Su  = 0b00000001;

    //for ROM B
    public static final int Ea  = 0b10000000;
    public static final int La  = 0b01000000;
    public static final int Ei  = 0b00100000;
    public static final int Li  = 0b00010000;
    public static final int Em  = 0b00001000;
    public static final int Lm  = 0b00000100;
    public static final int Ep  = 0b00000010;
    public static final int Cp  = 0b00000001;

    StringBuilder memoryContentsA, memoryContentsB;

    public void write(){
        Charset charset = Charset.forName("US-ASCII");

        String a = memoryContentsA.toString();
        Path fileA = Paths.get(FILE_A);

        try (BufferedWriter writer = Files.newBufferedWriter(fileA, charset)) {
            writer.write(a, 0, a.length());
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

        String b = memoryContentsB.toString();
        Path fileB = Paths.get(FILE_B);

        try (BufferedWriter writer = Files.newBufferedWriter(fileB, charset)) {
            writer.write(b, 0, b.length());
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

    public void build(){
        memoryContentsA = new StringBuilder();
        memoryContentsB = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            memoryContentsA.append(Math.random() * 60);
            memoryContentsB.append(Math.random() * 60);
        }
    }

    public static void main(String[] args) {
//        InstructionDecoder decoder = new InstructionDecoder();
//        decoder.build();
//        decoder.write();

    }
}
