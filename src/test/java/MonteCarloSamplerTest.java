import org.apache.commons.math3.distribution.BetaDistribution;
import org.junit.Test;

import static org.junit.Assert.*;

public class MonteCarloSamplerTest {

    @Test
    public void testThreeUniformDistributions() {
        double[][] betas = new double[][] {{1,1},{1,1},{1,1}};
        MonteCarloSampler sampler = new MonteCarloSampler(betas, 1000);
        assertEquals(sampler.probabilityToBeatAllVariations(0), 0.33, 0.01);
        assertEquals(sampler.probabilityToBeatAllVariations(1), 0.33, 0.01);
        assertEquals(sampler.probabilityToBeatAllVariations(2), 0.33, 0.01);
    }
}