package maven;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


/**
* The HistogramGenerator program generates an histogram based on 
* a text file which contains the grades
* 
* @author  Sioziou Argyro
* @version 1.0
* @since   2018-03-12 
*/

public class HistogramGenerator {
	
	public static void main(String args[]) {
		
		//Creating an HistogramGenerator object.
		HistogramGenerator histogram = new HistogramGenerator();
		
		Scanner input = new Scanner(System.in);
		
		//The path of the file to open.
		String filePath;
		
		//Requesting input.
		System.out.println("Please insert the grade's file path:");
		
		//Make sure there are no blank spaces.
		filePath = input.next().replaceAll("\\s", "");	
		
		//Calls the needed methods to generate the histogram.
		histogram.generateChart(histogram.readGrades(filePath));
		
		//Closing scanner.
		input.close();
	}
	
	
	/**
	 * This method processes the file input.                          
	 * <p>
	 * Using the file path the readGrades(String) method 
	 * opens the text file and reads the grades line by line
	 * and saves them to an integer array.
	 * <p>
	 * @param filePath the path of the text file which contains the grades
	 * @return integer array with the grades
	 */
	
	public int[] readGrades(String filePath) {
		
		//The grade of each line.
		int[] grades = new int[11];
					
		// This will reference one line at a time
	    String line = null;

	   // FileReader reads text files in the default encoding.
	   FileReader fileReader;
						
	   // Wrapping FileReader in BufferedReader.
	   BufferedReader bufferedReader;
				
	   try {
					
		   // fileReader initialization. 		
		   fileReader = new FileReader(filePath);
					
		   // bufferedReader initialization.		
		   bufferedReader = new BufferedReader(fileReader);

		   // Populating the grades array
		   while((line = bufferedReader.readLine()) != null) {
			    		
			   line.replaceAll("\\s",""); //removing blank spaces
			   grades[Integer.parseInt(line)]++; 
			    					    		
		   }   
			    				    	
		   // Closing file.
		   bufferedReader.close();			    	
			    	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	   
	   return grades;
	   
	}
	
	
	/**
	 * This method generates the histogram.                          
	 * <p>
	 * Uses the integer array to get the coordinates which 
	 * will be used to generate the histogram
	 * <p>
	 * @param grades array with the grades
	 */
	
	public void generateChart(int[] grades) {
			
		// Creation of an empty dataset. 
		XYSeriesCollection dataset = new XYSeriesCollection();
		
		// Creation of an empty series
		XYSeries data = new XYSeries("random values");

		// Populating the data series with the coordinates taken from the input array.
		for (int i = 0; i < grades.length; i++) {
			data.add(i, grades[i]);
		}

		// Adding the series to the dataset.
		dataset.addSeries(data);

		boolean legend = false; // do not visualize a legend
		boolean tooltips = false; // do not visualize tooltips
		boolean urls = false; // do not visualize urls

		// Declaration and initialization of the histogram.
		JFreeChart chart = ChartFactory.createXYLineChart("Histogram", "grade", "number", dataset,
				PlotOrientation.VERTICAL, legend, tooltips, urls);

		// Creation of the histogram frame.
		ChartFrame frame = new ChartFrame("First", chart);
		
		// Fixing the frame's size.
		frame.pack();
		
		// Making the frame visible.
		frame.setVisible(true);
	}
	

}
