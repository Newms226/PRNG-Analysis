package edu.msudenver.mnewma12.algs.random.generate;

public interface RandomGenerator {

    /**
     * See java.util.random.Random#nextBytes
     *
     * @param toFill the user supplied array to be filled with random byes
     */
    byte[] nextBytes(byte[] toFill);

    default String getName() { return "UNNAMED"; }
}
