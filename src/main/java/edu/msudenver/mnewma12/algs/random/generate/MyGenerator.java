package edu.msudenver.mnewma12.algs.random.generate;

import edu.msudenver.mnewma12.algs.cli.CLI;

/**
 * https://cdsmith.wordpress.com/2011/10/10/build-your-own-simple-random-numbers/
 */
public class MyGenerator implements RandomGenerator {

    static byte getSeed() { return (byte) 101;}

    private static int MOD_VAL = 256 * 256;

    /**
     * Assumed to be *exclusive*
     */
    private static int MAX_VAL = 256;

    private int currentSeed;

    public MyGenerator() {
        currentSeed = getSeed();
    }

    @Override
    public byte[] nextBytes(byte[] toFill) {
        for (int i = 0; i < toFill.length; i++) {
            toFill[i] = nextByte();
        }
        return toFill;
    }

    public byte nextByte() {
//        CLI.echoLn("Current Seed: " + CLI.formatLong(currentSeed));

        currentSeed = 7 * currentSeed % MOD_VAL;
//        CLI.echoLn("New Seed: " + CLI.formatLong(currentSeed));

        int intRan = (currentSeed - 1) % MAX_VAL;
//        CLI.echoLn("Generated random int: " + CLI.formatLong(intRan));

        byte ran = (byte) intRan;
//        CLI.echoLn("Converted to random byte: " + ran);

//        CLI.echoLn(ran);
        return ran;
    }

    @Override
    public String getName() {
        return "Custom Random Generator";
    }

    // Main method for testing

    public static void main(String[] args) {
        MyGenerator gen = new MyGenerator();
        byte[] rans = new byte[10];
        gen.nextBytes(rans);
    }

}
