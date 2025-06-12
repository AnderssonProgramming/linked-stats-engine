package edu.eci.arsw.linked_stats_engine.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.linked_stats_engine.model.CustomLinkedList;
import edu.eci.arsw.linked_stats_engine.model.StatisticsCalculator;
import edu.eci.arsw.linked_stats_engine.service.DataReaderService;

/**
 * Controller for the statistics web interface.
 */
@RestController
public class StatisticsController {
    
    @Autowired
    private DataReaderService dataReaderService;
    
    /**
     * Returns the statistics for the data in table_data.txt.
     * 
     * @return a map containing the mean and standard deviation for each column
     */
    @GetMapping("/stats")
    public Map<String, Object> getStatistics() {
        Map<String, Object> result = new HashMap<>();
        
        try {
            List<CustomLinkedList<Double>> columns = dataReaderService.readDataFromFile("table_data.txt");
            
            if (columns.size() >= 2) {
                CustomLinkedList<Double> column1 = columns.get(0);
                CustomLinkedList<Double> column2 = columns.get(1);
                
                double meanCol1 = StatisticsCalculator.calculateMean(column1);
                double stdDevCol1 = StatisticsCalculator.calculateStandardDeviation(column1);
                
                double meanCol2 = StatisticsCalculator.calculateMean(column2);
                double stdDevCol2 = StatisticsCalculator.calculateStandardDeviation(column2);
                
                Map<String, Double> column1Stats = new HashMap<>();
                column1Stats.put("mean", meanCol1);
                column1Stats.put("stdDev", stdDevCol1);
                column1Stats.put("expectedMean", 550.6);
                column1Stats.put("expectedStdDev", 572.03);
                
                Map<String, Double> column2Stats = new HashMap<>();
                column2Stats.put("mean", meanCol2);
                column2Stats.put("stdDev", stdDevCol2);
                column2Stats.put("expectedMean", 60.32);
                column2Stats.put("expectedStdDev", 62.26);
                
                result.put("column1", column1Stats);
                result.put("column2", column2Stats);
                result.put("column1Data", column1);
                result.put("column2Data", column2);
                result.put("success", true);
            } else {
                result.put("success", false);
                result.put("error", "Not enough columns found in the data file");
            }
        } catch (IOException e) {
            result.put("success", false);
            result.put("error", "Error reading data file: " + e.getMessage());
        } catch (Exception e) {
            result.put("success", false);
            result.put("error", "Error calculating statistics: " + e.getMessage());
        }
        
        return result;
    }
}
