package edu.eci.arsw.linked_stats_engine.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.eci.arsw.linked_stats_engine.model.CustomLinkedList;

/**
 * Service for reading data from files and converting them to linked lists.
 */
@Service
public class DataReaderService {
    
    /**
     * Reads numeric data from a file and returns two linked lists with the data from
     * the two columns of the file. Each column in the file is separated by a marker line.
     * 
     * @param fileName the name of the file to read
     * @return an array of two linked lists, each containing the data from one column
     * @throws IOException if an I/O error occurs reading from the file
     */
    public List<CustomLinkedList<Double>> readDataFromFile(String fileName) throws IOException {
        List<CustomLinkedList<Double>> columns = new ArrayList<>();
        CustomLinkedList<Double> column1 = new CustomLinkedList<>();
        CustomLinkedList<Double> column2 = new CustomLinkedList<>();
        
        columns.add(column1);
        columns.add(column2);
        
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            
            String line;
            int currentColumn = 0;
            
            while ((line = reader.readLine()) != null) {
                // Skip empty lines and comment lines
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    if (line.startsWith("# Column 2")) {
                        currentColumn = 1; // Switch to column 2 when marker is found
                    }
                    continue;
                }
                
                try {
                    double value = Double.parseDouble(line);
                    columns.get(currentColumn).add(value);
                } catch (NumberFormatException e) {
                    // Skip lines that don't contain valid numbers
                    System.err.println("Skipping invalid number: " + line);
                }
            }
        }
        
        return columns;
    }
}
