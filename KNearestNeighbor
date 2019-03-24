import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class KNearestNeighbor {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Number of training tuples?");
        int numTuples = Integer.parseInt(br.readLine());

        System.out.println("k value?");
        int k = Integer.parseInt(br.readLine());

        int[][] data = new int[numTuples][3];

        System.out.println("Enter the 2 significant column values AND class label (binary) for each training tuple (space separated)");
        for (int i = 0; i < numTuples; i++) {
            String[] input = br.readLine().split(" ");
            data[i][0] = Integer.parseInt(input[0]);
            data[i][1] = Integer.parseInt(input[1]);
            data[i][2] = Integer.parseInt(input[2]);
        }

        System.out.println("Enter the 2 significant column values for TEST tuple (space separated)");
        String[] testTupleValues = br.readLine().split(" ");
        int x1 = Integer.parseInt(testTupleValues[0]);
        int y1 = Integer.parseInt(testTupleValues[1]);

        //now we need to find k closest training tuples
        //we will store indices of those k nearest tuples
        //I am using an ArrayList instead of Array because I want to use the inbuilt contains() method

        ArrayList<Integer> kNearestIndices = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            double minDistance = Double.MAX_VALUE;
            int minDistanceTupleIndex = 0;

            for (int j = 0; j < numTuples; j++) {

                if(kNearestIndices.contains(j))
                    continue;
                //if this tuple has already been included in the k nearest neighbours, we shouldn't check it again

                int x2 = data[j][0];
                int y2 = data[j][1];

                double distance = Math.sqrt(((x1-x2)*(x1-x2)) + ((y1-y2)*y1-y2));
                if(distance < minDistance) {
                    minDistance = distance;
                    minDistanceTupleIndex = j;
                }
            }
            kNearestIndices.add(minDistanceTupleIndex);
        }

        int count0 = 0, count1 = 0;

        for(int index : kNearestIndices) {
            if(data[index][2] == 0)
                count0++;
            else
                count1++;
        }

        if(count0 > count1)
            System.out.println("Class label: no");
        else
            System.out.println("Class label: yes");

    }
}
