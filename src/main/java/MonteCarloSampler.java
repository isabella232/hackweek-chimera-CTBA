import org.apache.commons.math3.distribution.BetaDistribution;

public class MonteCarloSampler {

    private int sampleSize;
    private int numberOfVariations;
    private double[][] samples;
    private BetaDistribution[] distributions;
    private boolean[] hasBeenSampled;

    public MonteCarloSampler(double[][] input, int sampleSize) {
        this.sampleSize = sampleSize;
        numberOfVariations = input.length;
        samples = new double[numberOfVariations][sampleSize];
        hasBeenSampled = new boolean[numberOfVariations];
        distributions = new BetaDistribution[numberOfVariations];
        for (int i = 0; i < numberOfVariations; i++) {
            distributions[i] = new BetaDistribution(input[i][0], input[i][1]);
        }
    }
//
//    public double probabilityToBeatOneVariation(int variationToTest, int variationToBeat) {
//        if (!hasBeenSampled[variationToTest]) {
//            samples[variationToTest] = sampleVariation(variationToTest);
//            hasBeenSampled[variationToTest] = true;
//        }
//        if (!hasBeenSampled[variationToBeat]) {
//            samples[variationToBeat] = sampleVariation(variationToBeat);
//            hasBeenSampled[variationToBeat] = true;
//        }
//
//        return (double) pairwiseCompare(variationToTest, variationToBeat) / sampleSize;
//    }

//    public double probabilityToBeatAllVariations(int variationToTest) {
//        for (int i = 0; i < numberOfVariations; i++) {
//            if (!hasBeenSampled[i]) {
//                samples[i] = sampleVariation(i);
//                hasBeenSampled[i] = true;
//            }
//        }
//
//        int successes = 0;
//        for (int i = 0; i < sampleSize; i++) {
//            if (beatsAllForOneDataPoint(variationToTest, i)) {
//                successes++;
//            }
//        }
//        return (double) successes / sampleSize;
//    }

    public double allVariationCalculation(int variationToTest, MonteCarloFunction function) {
        for (int i = 0; i < numberOfVariations; i++) {
            if (!hasBeenSampled[i]) {
                samples[i] = sampleVariation(i);
                hasBeenSampled[i] = true;
            }
        }
        double total = 0;
        for (int i = 0; i < sampleSize; i++) {
            total += function.f(variationToTest, i, samples, numberOfVariations);
        }
        return total / sampleSize;
    }

//    public double calculateLoss(int variationToTest) {
//        for (int i = 0; i < numberOfVariations; i++) {
//            if (!hasBeenSampled[i]) {
//                samples[i] = sampleVariation(i);
//                hasBeenSampled[i] = true;
//            }
//        }
//        double loss = 0;
//        for (int i = 0; i < sampleSize; i++) {
//            loss += calculateLossForOneDataPoint(variationToTest, i);
//        }
//        return loss / sampleSize;
//    }
//
//    private double calculateLossForOneDataPoint(int variationToTest, int dataPoint) {
//        double maxLoss = 0;
//        for (int i = 0; i < numberOfVariations; i++) {
//            double loss = samples[variationToTest][dataPoint] - samples[i][dataPoint];
//            if (loss > maxLoss) {
//                maxLoss = loss;
//            }
//        }
//        return maxLoss;
//    }
//
//
//    private boolean beatsAllForOneDataPoint(int variationToTest, int dataPoint) {
//        for (int i = 0; i < numberOfVariations; i++) {
//            if (i != variationToTest) {
//                if (samples[variationToTest][dataPoint] < samples[i][dataPoint]) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    private int pairwiseCompare(int variationToTest, int variationToBeat) {
//        int successes = 0;
//        for (int i = 0; i < sampleSize; i++) {
//            if (samples[variationToTest][i] > samples[variationToBeat][i]) {
//                successes++;
//            }
//        }
//        return successes;
//    }

    private double[] sampleVariation(int variationToSample) {
        System.out.println("sampling " + variationToSample);
        BetaDistribution distribution = distributions[variationToSample];
        return distribution.sample(sampleSize);
    }
}
