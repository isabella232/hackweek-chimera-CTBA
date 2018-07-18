import java.util.Random;

public class Main {

    public static void main(String[] args) {
        double[][] input = generateBetas(50);
        MonteCarloSampler sampler = new MonteCarloSampler(input, 100000);
        System.out.println(sampler.probabilityToBeatAllVariations(8));
        System.out.println(sampler.probabilityToBeatOneVariation(8,4));
    }

    private static double[][] generateBetas(int numberOfVariations) {
        Random rand = new Random();
        double[][] betas = new double[numberOfVariations][2];
        for (int i = 0; i < numberOfVariations; i++) {
            betas[i][0] = rand.nextDouble();
            betas[i][1] = rand.nextDouble();
        }
        return betas;
    }
}
