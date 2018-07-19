import org.apache.commons.math3.distribution.BetaDistribution;
import org.junit.BeforeClass;
import org.junit.Test;

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
}