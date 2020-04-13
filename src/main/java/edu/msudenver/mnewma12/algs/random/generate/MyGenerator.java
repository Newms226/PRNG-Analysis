package edu.msudenver.mnewma12.algs.random.generate;

import edu.msudenver.mnewma12.algs.cli.CLI;

/**
 * https://cdsmith.wordpress.com/2011/10/10/build-your-own-simple-random-numbers/
 */
public class MyGenerator implements RandomGenerator {

    static byte getSeed() { return (byte) 137363;}

    private static int MOD_VAL = 24750915;

    private static int MAX_VAL = 254;

    private static int MULTIPLIER = 934521397;

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

        currentSeed = MULTIPLIER * currentSeed % MOD_VAL;
//        CLI.echoLn("New Seed: " + CLI.formatLong(currentSeed));

        int intRan = currentSeed % (MAX_VAL);
//        CLI.echoLn("Generated random int: " + CLI.formatLong(intRan));

        int normalizedRan = intRan - ((MAX_VAL) / 2);
//        CLI.echoLn("Generated normalized random int: " + CLI.formatLong(normalizedRan));

        byte ran = (byte) normalizedRan;
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
        byte[] rans = new byte[100];
        gen.nextBytes(rans);
    }

}
