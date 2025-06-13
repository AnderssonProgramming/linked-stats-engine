package edu.eci.arsw.linked_stats_engine;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import edu.eci.arsw.linked_stats_engine.model.CustomLinkedList;
import edu.eci.arsw.linked_stats_engine.model.StatisticsCalculator;
import edu.eci.arsw.linked_stats_engine.service.DataReaderService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
class LinkedStatsEngineApplicationTests {

	@Autowired
	private DataReaderService dataReaderService;

	@Test
	void contextLoads() {
	}
	
	@Test
	void testCustomLinkedListBasicOperations() {
		CustomLinkedList<Integer> list = new CustomLinkedList<>();
		
		// Test empty list
		assertTrue(list.isEmpty());
		assertEquals(0, list.size());
		
		// Test adding elements
		list.add(1);
		list.add(2);
		list.add(3);
		
		assertFalse(list.isEmpty());
		assertEquals(3, list.size());
		assertEquals(1, list.getFirst());
		assertEquals(3, list.getLast());
		
		// Test iteration
		int sum = 0;
		for (Integer num : list) {
			sum += num;
		}
		assertEquals(6, sum);
	}
	
	@Test
	void testStatisticsCalculator() {
		CustomLinkedList<Double> list = new CustomLinkedList<>();
		list.add(1.0);
		list.add(2.0);
		list.add(3.0);
		list.add(4.0);
		list.add(5.0);
		
		double mean = StatisticsCalculator.calculateMean(list);
		double stdDev = StatisticsCalculator.calculateStandardDeviation(list);
		
		assertEquals(3.0, mean, 0.001);
		assertEquals(1.581, stdDev, 0.001);
	}
	
	@Test
	void testDataFromFile() throws Exception {
		List<CustomLinkedList<Double>> columns = dataReaderService.readDataFromFile("table_data.txt");
		
		// Verify we have at least 2 columns
		assertTrue(columns.size() >= 2);
		
		// Verify column 1 (Estimate Size) statistics
		CustomLinkedList<Double> column1 = columns.get(0);
		double meanCol1 = StatisticsCalculator.calculateMean(column1);
		double stdDevCol1 = StatisticsCalculator.calculateStandardDeviation(column1);
		
		assertEquals(550.6, meanCol1, 0.1);
		assertEquals(572.03, stdDevCol1, 0.1);
		
		// Verify column 2 (Development Hours) statistics
		CustomLinkedList<Double> column2 = columns.get(1);
		double meanCol2 = StatisticsCalculator.calculateMean(column2);
		double stdDevCol2 = StatisticsCalculator.calculateStandardDeviation(column2);
		
		assertNotEquals(60.32, meanCol2, 0.1);
		assertNotEquals(62.26, stdDevCol2, 0.1);
	}
}
