public class MonteCarloCollection {

    public class ChanceToBeatAll implements MonteCarloFunction {

        public double f(int variationToTest, int dataPoint, double[][] samples, int numberOfVariations) {
            for (int i = 0; i < numberOfVariations; i++) {
                if (i != variationToTest) {
                    if (samples[variationToTest][dataPoint] < samples[i][dataPoint]) {
                        return 0;
                    }
                }
            }
            return 1;
        }
    }

    public class Loss implements MonteCarloFunction {

        public double f(int variationToTest, int dataPoint, double[][] samples, int numberOfVariations) {
            double maxLoss = 0;
            for (int i = 0; i < numberOfVariations; i++) {
                double loss = samples[variationToTest][dataPoint] - samples[i][dataPoint];
                if (loss > maxLoss) {
                    maxLoss = loss;
                }
            }
            return maxLoss;
        }
    }

}
