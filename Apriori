import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;

public class Apriori {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("How many transactions?");
        int numTransactions = Integer.parseInt(br.readLine());

        System.out.println("Support value?");
        int support = Integer.parseInt(br.readLine());

        Dictionary<String, ArrayList<String>> transactions = new Hashtable<>();

        ArrayList<String> allItemsList = new ArrayList<>();

        for (int i = 0; i < numTransactions; i++) {
            System.out.println("Enter items for transaction " + (i+1) + " (comma separated)");

            String items[] = br.readLine().replaceAll(" ", "").split(",");

            for(String item : items) {
                if(!allItemsList.contains(item))
                    allItemsList.add(item);
            }

            transactions.put("t" + i, new ArrayList<>(Arrays.asList(items)));
        }

        //now we have to form 1 itemsets

        //we loop through all items in all transactions
        //if k1 already contains that items, increment its count by 1
        //if it doesn't contain the item, add it and set count to 1

        Dictionary<String, Integer> k1 = new Hashtable<>();

        for (int i = 0; i < numTransactions; i++) {
            ArrayList<String> itemsInThisTransaction = transactions.get("t" + i);

            for(String item : itemsInThisTransaction) {
                if(!((Hashtable<String, Integer>) k1).containsKey(item)) {
                    k1.put(item, 1);
                }
                else {
                    int count = k1.get(item);
                    k1.remove(item);
                    count++;
                    k1.put(item, count);
                }
            }
        }

        //now we formed uncounted k1 itemsets
        //now we need to prune k1

        Dictionary<String, Integer> k1Pruned = new Hashtable<>();
        ArrayList<String> k1PrunedItemsList = new ArrayList<>();

        for(String item : allItemsList) {
            int count = k1.get(item);
            if(count >= support) {
                k1Pruned.put(item, count);
                k1PrunedItemsList.add(item);
            }
        }

        if(k1PrunedItemsList.size() == 0) {
            System.out.println("No frequent itemsets found.");
        }
        else {

            //now we need to find a collection of 2 itemsets using k1PrunedItemsList

            ArrayList<ArrayList<String>> k2ItemsList = createK2ItemsList(k1PrunedItemsList);

            //now we put use these 2-itemsets to form k2 table
            //key is the 2-itemset and value is the number of occurrences

            Dictionary<ArrayList<String>, Integer> k2 = new Hashtable<>();

            for (ArrayList<String> twoItemSet : k2ItemsList) {
                int count = countOccurrencesInTransactions(twoItemSet, transactions, numTransactions);
                k2.put(twoItemSet, count);
            }

            //now we have k2, we need to prune it

            Dictionary<ArrayList<String>, Integer> k2Pruned = new Hashtable<>();
            ArrayList<ArrayList<String>> k2PrunedItemsList = new ArrayList<>();

            for (ArrayList<String> twoItemSet : k2ItemsList) {
                if (k2.get(twoItemSet) >= support) {
                    k2Pruned.put(twoItemSet, k2.get(twoItemSet));
                    k2PrunedItemsList.add(twoItemSet);
                }
            }

            if(k2PrunedItemsList.size() == 0) {
                System.out.println("Frequent itemsets are: \n" + k1Pruned);
            }
            else {
                //now we have pruned k2
                //using k2PrunedItemsList we need to form k3 items list

                ArrayList<ArrayList<String>> k3ItemsList = createK3ItemsList(k2PrunedItemsList);

                //now we use these 3-itemsets to form k3 table
                //key is the 3-itemset and value is the number of occurrences

                Dictionary<ArrayList<String>, Integer> k3 = new Hashtable<>();

                for (ArrayList<String> threeItemSet : k3ItemsList) {
                    int count = countOccurrencesInTransactions(threeItemSet, transactions, numTransactions);
                    k3.put(threeItemSet, count);
                }

                //now we have k3, we need to prune it

                Dictionary<ArrayList<String>, Integer> k3Pruned = new Hashtable<>();
                ArrayList<ArrayList<String>> k3PrunedItemsList = new ArrayList<>();

                for (ArrayList<String> threeItemSet : k3ItemsList) {
                    if (k3.get(threeItemSet) >= support) {
                        k3Pruned.put(threeItemSet, k3.get(threeItemSet));
                        k3PrunedItemsList.add(threeItemSet);
                    }
                }

                if(k3PrunedItemsList.size() == 0) {
                    System.out.println("Frequent itemsets are: \n" + k2Pruned);
                }
                else {
                    //now we have pruned k3
                    //using k3PrunedItemsList we need to form k4 items list

                    ArrayList<ArrayList<String>> k4ItemsList = createK4ItemsList(k3PrunedItemsList);

                    //now we use these 4-itemsets to form k4 table
                    //key is the 4-itemset and value is the number of occurrences

                    Dictionary<ArrayList<String>, Integer> k4 = new Hashtable<>();

                    for (ArrayList<String> fourItemSet : k4ItemsList) {
                        int count = countOccurrencesInTransactions(fourItemSet, transactions, numTransactions);
                        k4.put(fourItemSet, count);
                    }

                    //now we have k4, we need to prune it

                    Dictionary<ArrayList<String>, Integer> k4Pruned = new Hashtable<>();
                    ArrayList<ArrayList<String>> k4PrunedItemsList = new ArrayList<>();

                    for (ArrayList<String> fourItemSet : k4ItemsList) {
                        if (k4.get(fourItemSet) >= support) {
                            k4Pruned.put(fourItemSet, k4.get(fourItemSet));
                            k4PrunedItemsList.add(fourItemSet);
                        }
                    }

                    if(k4PrunedItemsList.size() == 0) {
                        System.out.println("Frequent itemsets are: \n" + k3Pruned);
                    }
                    else {
                        System.out.println("Frequent itemsets are: \n" + k4Pruned);
                    }
                }
            }
        }

    }

    private static ArrayList<ArrayList<String>> createK2ItemsList(ArrayList<String> k1PrunedItemsList) {
        ArrayList<ArrayList<String>> k2ItemsList = new ArrayList<>();

        for (int i = 0; i < k1PrunedItemsList.size()-1; i++) {
            for (int j = i+1; j < k1PrunedItemsList.size(); j++) {
                k2ItemsList.add(new ArrayList<>(Arrays.asList(k1PrunedItemsList.get(i), k1PrunedItemsList.get(j))));
            }
        }
        return k2ItemsList;
    }

    private static ArrayList<ArrayList<String>> createK3ItemsList(ArrayList<ArrayList<String>> k2PrunedItemsList) {
        ArrayList<ArrayList<String>> k3ItemsList = new ArrayList<>();

        //first we form a normal ArrayList containing all unique items present in k2PrunedItemsList

        ArrayList<String> uniqueItems = new ArrayList<>();

        for(ArrayList<String> k2ItemSet : k2PrunedItemsList) {
            for(String item : k2ItemSet) {
                if(!uniqueItems.contains(item))
                    uniqueItems.add(item);
            }
        }

        //now we form k3ItemsList using all the unique items

        for (int i = 0; i < uniqueItems.size()-2; i++) {
            for (int j = i+1; j < uniqueItems.size()-1; j++) {
                for (int k = j+1; k < uniqueItems.size(); k++) {
                    k3ItemsList.add(new ArrayList<>(Arrays.asList(uniqueItems.get(i), uniqueItems.get(j), uniqueItems.get(k))));
                }
            }
        }

        return k3ItemsList;
    }

    private static ArrayList<ArrayList<String>> createK4ItemsList(ArrayList<ArrayList<String>> k3PrunedItemsList) {
        ArrayList<ArrayList<String>> k4ItemsList = new ArrayList<>();

        ArrayList<String> uniqueItems = new ArrayList<>();

        for(ArrayList<String> k3ItemSet : k3PrunedItemsList) {
            for(String item : k3ItemSet) {
                if(!uniqueItems.contains(item))
                    uniqueItems.add(item);
            }
        }

        for (int i = 0; i < uniqueItems.size()-3; i++) {
            for (int j = i+1; j < uniqueItems.size()-2; j++) {
                for (int k = j+1; k < uniqueItems.size()-1; k++) {
                    for (int l = k+1; l < uniqueItems.size(); l++) {
                        k4ItemsList.add(new ArrayList<>(Arrays.asList(uniqueItems.get(i), uniqueItems.get(j), uniqueItems.get(k), uniqueItems.get(l))));
                    }
                }
            }
        }

        return k4ItemsList;
    }

    private static int countOccurrencesInTransactions(ArrayList<String> itemSet, Dictionary<String, ArrayList<String>> transactions, int numTransactions) {

        //input to this method is the particular itemset, the transaction table, and numTransactions
        //output (return value) is the count of itemset in transactions table
        //this will work for all itemsets (2-itemsets, 3-itemsets, etc) because it uses containsAll() method

        int count = 0;

        for (int i = 0; i < numTransactions; i++) {
            ArrayList<String> itemsInTransaction = transactions.get("t" + i);

            if(itemsInTransaction.containsAll(itemSet))
                count++;
        }
        return count;
    }
}
