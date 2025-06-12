package edu.eci.arsw.linked_stats_engine.model;

import java.util.List;

/**
 * Class for calculating statistical measures on a list of numbers.
 * Provides methods for calculating mean and standard deviation.
 */
public class StatisticsCalculator {
    
    /**
     * Calculates the mean (average) of a list of numbers.
     * 
     * @param numbers the list of numbers to calculate the mean for
     * @return the mean of the numbers
     * @throws IllegalArgumentException if the list is empty
     */
    public static double calculateMean(List<Double> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("Cannot calculate mean of an empty list");
        }
        
        double sum = 0;
        for (Double number : numbers) {
            sum += number;
        }
        
        return sum / numbers.size();
    }
    
    /**
     * Calculates the standard deviation of a list of numbers.
     * The standard deviation is a measure of how spread out the numbers are.
     * 
     * @param numbers the list of numbers to calculate the standard deviation for
     * @return the standard deviation of the numbers
     * @throws IllegalArgumentException if the list has fewer than 2 elements
     */
    public static double calculateStandardDeviation(List<Double> numbers) {
        if (numbers == null || numbers.size() < 2) {
            throw new IllegalArgumentException("Cannot calculate standard deviation with fewer than 2 values");
        }
        
        double mean = calculateMean(numbers);
        double sumOfSquaredDifferences = 0;
        
        for (Double number : numbers) {
            double difference = number - mean;
            sumOfSquaredDifferences += difference * difference;
        }
        
        return Math.sqrt(sumOfSquaredDifferences / (numbers.size() - 1));
    }
}
