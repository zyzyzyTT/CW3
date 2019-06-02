import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
	
	// create a file
	public static void createFile(String pathString) {
		Path path = Paths.get(pathString);
		//System.out.println("Path created: "+path.toString());
		//System.out.println("Absolute Path = "+path.toAbsolutePath());
		try {
			Files.createFile(path);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	// read a file 
	public static List<Stock> readFile(String fileName){
		List<Stock> results = new ArrayList<>();
		Path path = Paths.get(fileName);
		try(BufferedReader reader = Files.newBufferedReader(path)){
			String line = null;
			while((line = reader.readLine()) != null) {
				//System.out.println(line);
				results.add(new Stock(line));
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return results;
	}
	
	// write a text file
	public static void writeFile(String destination, String s) {
		//String x = "test only";
		BufferedWriter out = null;
		//Path path = Paths.get(destination);
		try{
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(destination, true)));
            out.write(s);
            out.newLine();
            out.close();  
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
