import java.io.BufferedReader;
import java.io.InputStreamReader;

public class KMedoids {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter number of rows:");
        int numRows = Integer.parseInt(br.readLine());

        int[][] data = new int[numRows][2];

        System.out.println("Enter the 2 significant column values for each row separated by space:");
        for (int i = 0; i < numRows; i++) {
            String input = br.readLine();
            data[i][0] = Integer.parseInt(input.split(" ")[0]);
            data[i][1] = Integer.parseInt(input.split(" ")[1]);
        }

        System.out.println("Enter number of clusters (k value):-");
        int k = Integer.parseInt(br.readLine());

        String[] clusters = new String[k];

        System.out.println("Enter " + k + " random indices from 0 to " + numRows + " to set as centroids:-");
        for (int i = 0; i < k; i++) {
            int index = Integer.parseInt(br.readLine());
            clusters[i] = index + ",";
        }

        for (int i = 0; i < numRows; i++) {
            int x1 = data[i][0];
            int y1 = data[i][1];

            int minDistanceCluster = 0;
            double minDistance = Double.MAX_VALUE;

            for (int j = 0; j < k; j++) {
                int x2 = data[Integer.parseInt(clusters[j].split(",")[0])][0];
                int y2 = data[Integer.parseInt(clusters[j].split(",")[0])][1];

                double distance = Math.sqrt( ((x2-x1)*(x2-x1)) + ((y2-y1)*(y2-y1)) );

                if(distance < minDistance) {
                    minDistance = distance;
                    minDistanceCluster = j;
                }
            }

            if(minDistance == 0) {
                //same value don't do anything
            }
            else {
                clusters[minDistanceCluster] += i + ",";
            }
        }

        for (int i = 0; i < clusters.length; i++) {
            System.out.println(clusters[i]);
        }

    }
}
