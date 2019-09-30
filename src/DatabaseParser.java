/*
 	Author: Caleb Herring
*/

import java.io.*;
import java.util.*;

public class DatabaseParser {

	private List<List<String>> badRecords = new ArrayList<>();
	private File csvFile = new File("./src/codingChallengeRecords.csv");
	private String csvSplitBy = ",";
	private int numOfFailedRecords = 0;
	private int numOfGoodRecords = 0;
	private int numOfRecords = 0;
	
	
	public void readFromFile() 
	{
		try {
			BufferedReader br = new BufferedReader(new FileReader(csvFile));
			String lineInput = br.readLine();
			
			while(lineInput != null) 
			{
				//increment the number of records we have received
				numOfRecords++;
				String[] inputValues = lineInput.split(csvSplitBy);

				//check to see if number of columns match or if there is a missing column
				//add to badRecords array if true
				if(inputValues.length > 10) 
				{
					numOfFailedRecords++;
					badRecords.add(Arrays.asList(inputValues));
				}
				else
				{
					numOfGoodRecords++;
				}
			}
			
			br.close();
		} catch(Exception e) {
			System.out.println("Something happened when reading the CSV");
		}
	}
	
	public void writeBadCsvToFile() 
	{
		try 
		{
			FileWriter csvWriter = new FileWriter("badEntries.csv");
			
			csvWriter.append("A,B,C,D,E,F,G,H,I,J");
			csvWriter.append("\n");
			
			for(List<String> rowData:badRecords)
			{
				csvWriter.append(String.join(csvSplitBy, rowData));
				csvWriter.append("\n");
			}
			
			csvWriter.flush();
			csvWriter.close();
			
		} catch(Exception e) {
			System.out.println("Failed to write to badEntries.csv");
		}
	}
	
	public void writeLogToFile()
	{
		try 
		{
			FileWriter logWriter = new FileWriter("log.txt");
			
			logWriter.append("Records Received: " + numOfRecords + " \n");
			logWriter.append("Records Successful: " + numOfGoodRecords + " \n");
			logWriter.append("Records Failed: " + numOfFailedRecords);
			
			logWriter.flush();
			logWriter.close();
			
		}catch(Exception e) {
			System.out.println("Failed to write log.txt");
		}		
	}
	
	public static void main(String[] args) 
	{
		DatabaseParser Data = new DatabaseParser();
		
		Data.readFromFile();
		Data.writeBadCsvToFile();
		Data.writeLogToFile();
	}
}
