/*
 	Author: Caleb Herring
*/

import java.io.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class DatabaseParser {

	private List<List<String>> badRecords = new ArrayList<>();
	private List<List<String>> goodRecords = new ArrayList<>();
	private File csvFile = new File("./src/codingChallengeRecords.csv");
	private int numOfFailedRecords = 0;
	private int numOfGoodRecords = 0;
	private int numOfRecords = 0;
	
	
	
	
	public static void createNewDatabase(String fileName) 
	{
		 
        //String url = "jdbc:sqlite:C:/sqlite/db/" + fileName;
		//String url = "jdbc:sqlite:D:/Programs/GitHub/CodingChallenge/Database/" + fileName;
		String url = "jdbc:sqlite:validEntries.db";
 
        try (Connection conn = DriverManager.getConnection(url)) 
        {
            if (conn != null) 
            {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
                conn.close();
            }
 
        } catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
    }
	
	public static void createNewTable() 
	{
		String url = "jdbc:sqlite:validEntries.db";
		
		Connection conn = null;
		
		String sql = "CREATE TABLE IF NOT EXISTS csvData("
				+ "A TEXT PRIMARY KEY"
				+ "B TEXT"
				+ "C TEXT"
				+ "D TEXT"
				+ "E TEXT"
				+ "F TEXT"
				+ "G TEXT"
				+ "H TEXT"
				+ "I TEXT"
				+ "J TEXT"
				+ ");";
		
		try
		{
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			
			
			if(conn != null)
			{
				stmt.execute(sql);
				conn.close();
			}
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	
	public void readFromFile() 
	{
		try {
			CSVReader reader = new CSVReader(new FileReader(csvFile));
			String[] lineInput = reader.readNext();
			
			while(lineInput != null) 
			{
				//increment the number of records we have received
				numOfRecords++;
				

				//check to see if number of columns match
				//add to badRecords array if true
				if(lineInput.length > 10) 
				{
					numOfFailedRecords++;
					badRecords.add(Arrays.asList(lineInput));
				}
				else
				{
					numOfGoodRecords++;
					goodRecords.add(Arrays.asList(lineInput));
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
	
	public void writeToDatabase()
	{
		String url = "jdbc:sqlite:validEntries.db";
		
		Connection conn = null;
		
		String sql ="INSERT INTO csvData(A,B,C,D,E,F,G,H,I,J) VALUES ";
	}
	
	public void writeBadCsvToFile() 
	{
		try 
		{
			CSVWriter csvWriter = new CSVWriter(new FileWriter("badEntries.csv"));
			
			//Add column headers to bad.csv file
			String[] header = ("A,B,C,D,E,F,G,H,I,J").split(",");
			csvWriter.writeNext(header);
			
			for(List<String> rowData:badRecords)
			{
				csvWriter.writeNext((String[]) rowData.toArray());
			}
			
			csvWriter.flush();
			csvWriter.close();
			
		} catch(Exception e) {
			System.out.println("Failed to write to badEntries.csv");
		}
	}
	
	/*
	public void writeGoodCsvToFile() 
	{
		try 
		{
			CSVWriter csvWriter = new CSVWriter(new FileWriter("goodEntries.csv"));
			
			for(List<String> rowData:goodRecords)
			{
				csvWriter.writeNext((String[]) rowData.toArray());
			}
			
			csvWriter.flush();
			csvWriter.close();
			
		} catch(Exception e) {
			System.out.println("Failed to write to goodEntries.csv");
		}
	}
	*/
	
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
		createNewDatabase("validEntries.db");
		createNewTable();
		
		DatabaseParser Data = new DatabaseParser();
		
		Data.readFromFile();
		//Data.writeGoodCsvToFile();
		Data.writeBadCsvToFile();
		Data.writeLogToFile();
		
		
	}
}
