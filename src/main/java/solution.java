import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class solution {

	List<String> highestSales = new ArrayList<String>();
	Map<String, Integer> productSalesNumber = new HashMap<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		solution sol = new solution();
		sol.findHighestSellingProduct();
	}

	private void findHighestSellingProduct() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		while (tc-- > 0) {
			int noOfLines = Integer.parseInt(br.readLine());
			while (noOfLines-- > 0) {
				String line = br.readLine();
				if (!"top".equals(line)) {
					String salesProduct = line.split(" ")[0];
					int salesCount = Integer.parseInt(line.split(" ")[1]);
					addSalesDataToMap(salesProduct, salesCount);
				} else {
					printOutput();
				}

			}
			productSalesNumber.clear();
		}
	}

	private void addSalesDataToMap(String salesProduct, int salesCount) {
		if (productSalesNumber.containsKey(salesProduct)) {
			productSalesNumber.put(salesProduct, productSalesNumber.get(salesProduct) + salesCount);
		} else
			productSalesNumber.put(salesProduct, salesCount);
	}

	private void printOutput() {
		int highestSaleNumber = -1;
		for(Entry<String, Integer> entry: productSalesNumber.entrySet()) {
			if (entry.getValue() > highestSaleNumber) {
				highestSales.clear();
				highestSaleNumber = entry.getValue();
				highestSales.add(entry.getKey());
			} else if (entry.getValue() == highestSaleNumber) {
				highestSales.add(entry.getKey());
			}
		}
		
		Collections.sort(highestSales);
		for(String product: highestSales) {
			System.out.print(product+" ");
		}
		System.out.println();
		highestSales.clear();
	}

}
