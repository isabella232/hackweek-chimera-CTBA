public interface MonteCarloFunction {

    double f(int variationToTest, int dataPoint, double[][] samples, int numberOfVariations);

}
