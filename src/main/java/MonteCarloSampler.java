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

    private double[] sampleVariation(int variationToSample) {
        System.out.println("sampling " + variationToSample);
        BetaDistribution distribution = distributions[variationToSample];
        return distribution.sample(sampleSize);
    }
}
