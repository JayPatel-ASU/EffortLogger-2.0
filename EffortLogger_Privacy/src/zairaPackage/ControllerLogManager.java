package zairaPackage;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

public class ControllerLogManager{
	private List<ControllerLogs> logEntries; //ControllerLogs is a data model class
	
	public ControllerLogManager() {
		logEntries = new ArrayList<>();
	}
	
	//import data from a csv file
	public void importCSV(String filePath) {
		//opens and reads CSV file contents from path
		try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(filePath)).withSkipLines(1).build()) {
			
			// record represents a line from csv file
			List<String[]> records;
			try {
	            records = csvReader.readAll();
	        } catch (CsvException e) {
	            //handle the exception
	        	System.err.println("CSV parsing error: " + e.getMessage());
	            e.printStackTrace();
	            return; // or throw a custom exception
	        }
			
			//assigning fields appropriate indexes with data from record
            for (String[] record : records) {
            	ControllerLogs logEntry = new ControllerLogs();
                logEntry.setField1(record[0]); 
                logEntry.setField2(record[1]);
                logEntry.setField2(record[2]);
                logEntry.setField2(record[3]);
                logEntry.setField2(record[4]);
                logEntry.setField2(record[5]);
                logEntry.setField2(record[6]);
                logEntry.setField2(record[7]);
                logEntry.setField2(record[8]);
                logEntry.setField2(record[9]);
                logEntry.setField2(record[10]);
                logEntry.setField2(record[11]);
                logEntry.setField2(record[12]);
                logEntry.setField2(record[13]);
                logEntry.setField2(record[14]);
                logEntry.setField2(record[15]);
                logEntry.setField2(record[16]);
                logEntry.setField2(record[17]);
                logEntry.setField2(record[18]);
                logEntry.setField2(record[19]);
                logEntry.setField2(record[20]);
                logEntry.setField2(record[21]);
                logEntry.setField2(record[22]);
                logEntry.setField2(record[23]);
                logEntry.setField2(record[24]);
                logEntry.setField2(record[25]);
                logEntry.setField2(record[26]);
                
             // add log entry to list of log entries
                addLogEntry(logEntry);
            }
        
		}
		catch (IOException e) {
			System.err.println("An IO error occurred: " + e.getMessage());
            e.printStackTrace(); // Handle exceptions appropriately
        }
	}
	
	//export data from a .csv file
	public void exportCSV(String filePath) {
		
	}
	
	//edit data in a log entry
	public void editLogEntry(ControllerLogs logEntry) {
		
	}
	
	//add a new log entry
	public void addLogEntry(ControllerLogs logEntry) {
		logEntries.add(logEntry);
	}
	
	//delete a log entry
	public void deleteLogEntry(ControllerLogs logEntry) {
		logEntries.remove(logEntry);
	}
	
	//save data to a .csv file
	public void saveToCSV(ControllerLogs logEntry) {
		
	}
	
    //get method
	public List<ControllerLogs> getLogEntries() {
		return logEntries;
	}
	
    // set method
	public void setLogEntries(List<ControllerLogs> logEntries) {
		this.logEntries = logEntries;
	}

}
