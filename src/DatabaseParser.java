/*
 	Author: Caleb Herring
*/

import java.io.*;
import java.util.*;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class DatabaseParser {

	private List<List<String>> badRecords = new ArrayList<>();
	private File csvFile = new File("./src/codingChallengeRecords.csv");
	//private String csvSplitBy = ",";
	private int numOfFailedRecords = 0;
	private int numOfGoodRecords = 0;
	private int numOfRecords = 0;
	
	
	public void readFromFile() 
	{
		try {
			//BufferedReader br = new BufferedReader(new FileReader(csvFile));
			CSVReader reader = new CSVReader(new FileReader(csvFile));
			String[] lineInput = reader.readNext();
			
			while(lineInput != null) 
			{
				//increment the number of records we have received
				numOfRecords++;
				//String[] inputValues = lineInput.split(csvSplitBy);			

				//check to see if number of columns match or if there is an empty column column
				//add to badRecords array if true
				if(lineInput.length > 10) 
				{
					numOfFailedRecords++;
					badRecords.add(Arrays.asList(lineInput));
				}
				else
				{
					numOfGoodRecords++;
				}
				lineInput = reader.readNext();
			}
			
			//subtract 1 from numOfRecords and numOfGoodRecords since initial pass is reading in the column names
			numOfGoodRecords--;
			numOfRecords--;
			
			reader.close();
		} catch(Exception e) {
			System.out.println("Something happened when reading the CSV");
		}
	}
	
	public void writeBadCsvToFile() 
	{
		try 
		{
			CSVWriter csvWriter = new CSVWriter(new FileWriter("badEntries.csv"));
			
			String[] header = ("A,B,C,D,E,F,G,H,I,J").split(",");
			csvWriter.writeNext(header);
			//csvWrite.append("A,B,C,D,E,F,G,H,I,J");
			//csvWriter.append("\n");
			
			for(List<String> rowData:badRecords)
			{
				//csvWriter.append(String.join(csvSplitBy, rowData));
				//csvWriter.append("\n");
				
				csvWriter.writeNext((String[]) rowData.toArray());
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
