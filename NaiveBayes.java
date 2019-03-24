import java.io.BufferedReader;
import java.io.InputStreamReader;

public class NaiveBayes {

    static int totalCountOfClass0 = 0, totalCountOfClass1 = 0;
    static int[][] data;
    static int numTuples, numAttributes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter number of training tuples:");
        numTuples = Integer.parseInt(br.readLine());

        System.out.println("Enter number of attributes (including class label):");
        numAttributes = Integer.parseInt(br.readLine());

        data = new int[numTuples][numAttributes];

        System.out.println("Enter numeric values of attributes (eg, if income = low, med, high enter 0 for low, 1 for med, 2 for high) separated by space:");
        for (int i = 0; i < numTuples; i++) {
            String[] tupleValues = br.readLine().split(" ");
            for (int j = 0; j < numAttributes; j++) {
                data[i][j] = Integer.parseInt(tupleValues[j]);

                //if it is last column, then we are counting occurences of class label
                if(j == numAttributes-1 && Integer.parseInt(tupleValues[j]) == 0)
                    totalCountOfClass0++;
                else if(j == numAttributes-1 && Integer.parseInt(tupleValues[j]) == 1)
                    totalCountOfClass1++;
            }
        }

        System.out.println("Enter a test tuple attribute values (without class label) separated by space:");
        String[] testTupleValues = br.readLine().split(" ");

        //now we need to calculate 2 probabilities
        //p(class=no) and p(class=yes)

        double pclass0 = 1, pclass1 = 1;
        //we set initial probability to 1 because we need to multiply it by all attribute probabilities

        //test tuple has values for each attribute
        //we need to calculate probability of those values occurring for all attributes for pclass0 and pclass1

        double[] pAttr0 = new double[numAttributes-1];
        double[] pAttr1 = new double[numAttributes-1];
        //here -1 because we don't have class label attribute

        for (int i = 0; i < numAttributes-1; i++) {
            //here we'll calculate class0 probabilities

            int attributeValue = Integer.parseInt(testTupleValues[i]);
            //now we need to find probability of this attribute for class0

            pAttr0[i] = probability(i, attributeValue, 0);
        }

        for (int i = 0; i < numAttributes-1; i++) {
            //for class2

            int attributeValue = Integer.parseInt(testTupleValues[i]);

            pAttr1[i] = probability(i, attributeValue, 1);
        }

        for (int i = 0; i < numAttributes-1; i++) {
            pclass0 *= pAttr0[i];
            pclass1 *= pAttr1[i];
        }

        if(pclass1 > pclass0) {
            System.out.println("Predicted class is 1");
        }
        else
            System.out.println("Predicted class is 0");

    }

    public static double probability(int attributeColumnIndex, int attributeValue, int classValue) {
        double probability;
        //According to naive bayes P(y/(x1,x2...xn)) = P(x1,x2...xn/y)P(y)
        //this method will return the value of P(xn/y)

        int numerator = 0;

        for (int i = 0; i < numTuples; i++) {
            if(data[i][attributeColumnIndex] == attributeValue && data[i][numAttributes-1] == classValue) {
                numerator++;
            }
        }

        if(classValue == 0) {
            probability = numerator*1.0/totalCountOfClass0*1.0;
            probability *= totalCountOfClass0 / numTuples;
        }
        else {
            probability = numerator*1.0/totalCountOfClass1*1.0;
            probability *= totalCountOfClass1 / numTuples;
        }


        return probability;
    }
}
