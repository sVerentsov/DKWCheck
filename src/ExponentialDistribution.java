import java.util.Arrays;
import java.util.Random;

/**
 * Created by north on 14.11.2016.
 */
public class ExponentialDistribution {
    Random rand;
    double lambda;

    public ExponentialDistribution(double lambda) {
        this.lambda = lambda;
        rand = new Random();
    }

    public double getNext() { //returns a realization of exponential random variable
        return Math.log(1 - rand.nextDouble()) / (-lambda);
    }

    public double[] getSample(int n) { //returns sample of n values
        double[] ans = new double[n];
        for (int i = 0; i < n; i++)
            ans[i] = getNext();
        return ans;
    }

    public double CDF(double x) { //returns value of distribution function at point x
        return 1 - Math.exp(-lambda * x);
    }

    public double findNormSup(double[] sample) { //returns sqrt(n)*sup(empCDF(x)-CDF(x))
        Arrays.sort(sample);
        int n = sample.length;
        double sup = 0;
        for (int i = 0; i < n; i++) {
            double cdf = CDF(sample[i]);
            double empiricalCDF = (double)i/n;
            if (Math.abs(cdf - empiricalCDF) > sup)
                sup = Math.abs(cdf - empiricalCDF);
        }
        return sup;
    }
}
