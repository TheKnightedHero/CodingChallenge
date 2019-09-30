/*
 	Author: Caleb Herring
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
public class DatabaseParser {

	private List<List<String>> records = new ArrayList<>();
	private String csvFile = "";
	private String csvSplitBy = ",";
	private int numOfFailedRecords = 0;
	private int numOfGoodRecords = 0;
	private int numOfRecords = 0;
	
	/*
	 * Notes on what to finish with this function:
	 * - redo the try/catch block
	 * - rename the csv file to be read from
	 * - read each line into the 2D matrix, separated by the comma delimiter
	 * */
	public void readFromFile() {
		BufferedReader br = new BufferedReader(new FileReader(csvFile));
		try {
			String lineInput;
			while((lineInput = br.readLine()) != null) {
				//process line
				String[] inputValues = lineInput.split(csvSplitBy);
				records.add(Arrays.asList(inputValues));
			}
		} finally {
		    br.close();		
		}
	}
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		
		//Create 2D vector
		
		
		//Call read from file function that will enter data into vector
		//call matrixScan to sort through good/bad data
			//This function should also count:
				//# of records received
				//# of bad entries
				//# of successful entries
		//call function to write 
	}
	
	

}
