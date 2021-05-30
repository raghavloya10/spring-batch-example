package io.rl.spring_batch.batch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.web.multipart.MultipartFile;

import io.rl.spring_batch.models.User;

public class CSVHelper {
	
  public static String TYPE = "text/csv";
  static String[] HEADERS = {"id", "name", "dept", "salary"};
  
  public static boolean hasCSVFormat(MultipartFile file) {
	   return TYPE.equals(file.getContentType());
  }

  public static List<User> csvToTutorials(InputStream is) {
    try (
		  BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		  CSVParser csvParser = new CSVParser(fileReader,
				CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
	) {

	      List<User> users = new ArrayList<User>();
	
	      csvParser.getRecords()
	      	.forEach(csvRecord -> {
	      		User user = new User();
	      		user.setId(Integer.parseInt(csvRecord.get("id")));
	      		user.setDept(csvRecord.get("dept"));
	      		user.setName(csvRecord.get("name"));
	      		user.setSalary(Integer.parseInt(csvRecord.get("salary")));
	      		users.add(user);
	      	});
	      
	      return users;
      
    } catch (IOException e) {
      throw new RuntimeException("Failed to parse CSV file: " + e.getMessage());
    }
  }

}