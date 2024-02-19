package com.ty.webscraping;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
public class WebScraping {
	 public static void main(String[] args) {
	 
		 String url = "https://www.amazon.in";

	        try {
	            // Connect to the website and get the HTML document
	            Document document = Jsoup.connect(url).get();

	            System.out.println(document.html());
	            // Extract product information
	            Elements products = document.select(".product-item");

	            // Create a CSV file dynamically in the current working directory
	            String csvFilePath = System.getProperty("user.dir") + "/products.csv";

	            // Create a CSV file and write headers
	            try (FileWriter csvWriter = new FileWriter(csvFilePath)) {
	                csvWriter.append("Product Name,Price,Rating\n");

	                // Iterate through each product and write data to the CSV file
	                for (Element product : products) {
	                    String productName = product.select(".product-name").text();
	                    String price = product.select(".product-price").text();
	                    String rating = product.select(".product-rating").text();

	                    // Write data to the CSV file
	                    csvWriter.append(String.format("%s,%s,%s\n", productName, price, rating));
	                }

	                System.out.println("Scraping completed. Data saved to " + csvFilePath);
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }

		 
 }
}
