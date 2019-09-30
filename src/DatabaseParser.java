/*
 	Author: Caleb Herring
*/
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.*;

public class DatabaseParser {

	private List<List<String>> badRecords = new ArrayList<>();
	//private String csvFile;
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
	public void readFromFile() 
	{
		BufferedReader br = new BufferedReader(new FileReader("./codingChallengeRecords.csv"));
		try {
			String lineInput = br.readLine();
			
			while(lineInput != null) 
			{
				//increment the number of records we have received
				numOfRecords++;
				String[] inputValues = lineInput.split(csvSplitBy);
				boolean missingColumn = false;

				//check to see if the lineInput is valid
				
				/*
				 * This for loop statement is checking for empty columns
				 * If the line contains empty columns, we append it to bad data
				 * This is under the assumption that empty columns are not allowed.
				 * */				
				for(String i:inputValues)
				{
					if(i.contentEquals(""))
					{
						missingColumn = true;
					}
				}

				//check to see if number of columns match or if there is a missing column
				//add to badRecords array if true
				if(inputValues.length > 10 || missingColumn == true) 
				{
					numOfFailedRecords++;
					badRecords.add(Arrays.asList(inputValues));
				}
				else
				{
					numOfGoodRecords++;
				}
				

				
			}
				
				badRecords.add(Arrays.asList(inputValues));
		} finally {
		    br.close();		
		}
	}
	
	
	
	
	public void writeToFile() {
		FileWriter csvWriter = new FileWriter("badEntires.csv");
		FileWriter logWriter = new FileWriter("log.txt");
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
