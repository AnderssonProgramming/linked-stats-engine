package edu.eci.arsw.linked_stats_engine;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import edu.eci.arsw.linked_stats_engine.model.CustomLinkedList;
import edu.eci.arsw.linked_stats_engine.model.StatisticsCalculator;
import edu.eci.arsw.linked_stats_engine.service.DataReaderService;

/**
 * Command-line runner that calculates and displays statistics for the data in the file.
 */
@Component
public class StatisticsCommandLineRunner implements CommandLineRunner {

    @Autowired
    private DataReaderService dataReaderService;
    
    @Override
    public void run(String... args) throws Exception {
        try {
            System.out.println("\n=== Linked Stats Engine Results ===\n");
            
            List<CustomLinkedList<Double>> columns = dataReaderService.readDataFromFile("table_data.txt");
            
            if (columns.size() >= 2) {
                CustomLinkedList<Double> column1 = columns.get(0);
                CustomLinkedList<Double> column2 = columns.get(1);
                
                System.out.println("Column 1 (Estimate Size) Data:");
                printData(column1);
                
                double meanCol1 = StatisticsCalculator.calculateMean(column1);
                double stdDevCol1 = StatisticsCalculator.calculateStandardDeviation(column1);
                
                System.out.println("\nColumn 1 (Estimate Size) Statistics:");
                System.out.printf("Mean: %.2f (Expected: 550.60)\n", meanCol1);
                System.out.printf("Standard Deviation: %.2f (Expected: 572.03)\n", stdDevCol1);
                
                System.out.println("\nColumn 2 (Development Hours) Data:");
                printData(column2);
                
                double meanCol2 = StatisticsCalculator.calculateMean(column2);
                double stdDevCol2 = StatisticsCalculator.calculateStandardDeviation(column2);
                
                System.out.println("\nColumn 2 (Development Hours) Statistics:");
                System.out.printf("Mean: %.2f (Expected: 60.32)\n", meanCol2);
                System.out.printf("Standard Deviation: %.2f (Expected: 62.26)\n", stdDevCol2);
                
                System.out.println("\nWeb interface available at: http://localhost:8081/");
            } else {
                System.err.println("Error: Not enough columns found in the data file");
            }
        } catch (IOException e) {
            System.err.println("Error reading data file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error calculating statistics: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Prints the data in a linked list.
     * 
     * @param data the linked list to print
     */
    private void printData(List<Double> data) {
        int index = 0;
        for (Double value : data) {
            System.out.printf("%d: %.1f\n", ++index, value);
        }
    }
}
