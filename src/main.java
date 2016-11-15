import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class main {
    public static double rightPart(double z)
    {
        return 1 - 2* Math.exp(-2*z*z);
    }

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        int m = 1000; //number of samples of G
        int n = 1000;
        double lambda = 2;
        double[] gValues = new double[m];
        ExponentialDistribution exponentialDistribution = new ExponentialDistribution(lambda);
        for (int i = 0; i < m; i++) {
            double[] sample = exponentialDistribution.getSample(n);
            gValues[i] = exponentialDistribution.findNormSup(sample);
        }
        Arrays.sort(gValues);
        int trueValues=0;
        int falseValues = 0;
        PrintWriter printWriter = new PrintWriter("results"+n+".csv");
        for(int i=0;i<m;i++)
        {
            printWriter.println(gValues[i]+";"+(double)i/m+";"+rightPart(gValues[i]));
            if((double)i/m>=rightPart(gValues[i])) trueValues++;
            else
                falseValues++;
        }
        printWriter.close();
        System.out.println("true: "+trueValues+"\nfalse: "+falseValues);
    }
}
