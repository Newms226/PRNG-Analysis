package edu.msudenver.mnewma12.algs.random.generate;

import edu.msudenver.mnewma12.algs.cli.CLI;

import java.util.Arrays;
import java.util.Random;

public class LibraryGenerator implements RandomGenerator {

    private Random random = new Random();

    @Override
    public byte[] nextBytes(byte[] toFill) {
        random.nextBytes(toFill);
        return toFill;
    }

    @Override
    public String getName() {
        return "JSL Implementation";
    }


    public static void main(String[] args) {
        LibraryGenerator gen = new LibraryGenerator();
        byte[] rans = new byte[10];
        gen.nextBytes(rans);
        CLI.echoLn(Arrays.toString(rans));
    }
}
