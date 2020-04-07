package edu.msudenver.mnewma12.algs.random.generate;

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
}
