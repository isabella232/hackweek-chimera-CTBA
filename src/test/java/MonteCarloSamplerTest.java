import org.apache.commons.math3.distribution.BetaDistribution;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class MonteCarloSamplerTest {

    private static MonteCarloCollection collection;

    @BeforeClass
    public static void beforeClass() {
        collection = new MonteCarloCollection();
    }

    @Test
    public void testTwoUniformDistributions() {
        MonteCarloCollection.ChanceToBeatAll chanceToBeatAll = collection.new ChanceToBeatAll();
        double[][] betas = new double[][] {{1,1},{1,1}};
        MonteCarloSampler sampler = new MonteCarloSampler(betas, 10000);
        assertEquals(sampler.allVariationCalculation(0, chanceToBeatAll), 0.5, 0.01);
        assertEquals(sampler.allVariationCalculation(1, chanceToBeatAll), 0.5, 0.01);
    }

    @Test
    public void testThreeUniformDistributions() {
        MonteCarloCollection.ChanceToBeatAll chanceToBeatAll = collection.new ChanceToBeatAll();
        double[][] betas = new double[][] {{1,1},{1,1},{1,1}};
        MonteCarloSampler sampler = new MonteCarloSampler(betas, 10000);
        assertEquals(sampler.allVariationCalculation(0, chanceToBeatAll), 0.33, 0.01);
        assertEquals(sampler.allVariationCalculation(1, chanceToBeatAll), 0.33, 0.01);
        assertEquals(sampler.allVariationCalculation(2, chanceToBeatAll), 0.33, 0.01);
    }

    @Test
    public void testProbabilitiesAddUpToOne() {
        MonteCarloCollection.ChanceToBeatAll chanceToBeatAll = collection.new ChanceToBeatAll();
        double[][] betas = generateBetas(10);
        MonteCarloSampler sampler = new MonteCarloSampler(betas, 10000);
        double probabilitiesForAll = 0.0;
        for (int i = 0; i < 10; i++) {
            probabilitiesForAll += sampler.allVariationCalculation(i, chanceToBeatAll);
        }
        assertEquals(probabilitiesForAll, 1.0, 0.05);
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