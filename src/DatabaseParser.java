/*
 	Author: Caleb Herring
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
public class DatabaseParser {

	private Vector<Vector<String>> database = new Vector<Vector<String>>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Create 2D vector
		
		
		//Call read from file function that will enter data into vector
		//call matrixScan to sort through good/bad data
			//This function should also count:
				//# of records received
				//# of bad entries
				//# of successful entries
		//call function to write 
	}
	
	
	/*
	 * Notes on what to finish with this function:
	 * - redo the try/catch block
	 * - rename the csv file to be read from
	 * - read each line into the 2D matrix, separated by the comma delimiter
	 * */
	public void readFromFile() {
		BufferedReader br = new BufferedReader(new FileReader(pathToCSV));
		try {
			String line;
			while((line = br.readLine()) != null) {
				//process line
			}
		} finally {
		    br.close();		
		}
	}
}
