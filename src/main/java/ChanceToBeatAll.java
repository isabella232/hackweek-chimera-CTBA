public class ChanceToBeatAll implements MonteCarloFunction {

    public double f(int variationToTest, int dataPoint, double[][] samples, int numberOfVariations) {
        for (int i = 0; i < numberOfVariations; i++) {
            if (i != variationToTest) {
                if (samples[variationToTest][dataPoint] < samples[i][dataPoint]) {
                    return 0.0;
                }
            }
        }
        return 1.0;
    }
}
