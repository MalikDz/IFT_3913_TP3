package ca.umontreal.teamz.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Malik Abada (matricule 20173066)
 * @author Christian Lungescu (matricule 20079725)
 * 
 */

public class FileManipulation {

	public static List<String> readLines(String fileName) throws IOException {
		String line;
		List<String> lines = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		while ((line = reader.readLine()) != null) {
			line = line.trim();
			lines.add(line);
		}
		reader.close();
		return lines;
	}

	public static void writeCsv(String path, String output) throws IOException {
		if (!path.endsWith("csv"))
			path += ".csv";
		File targetFile = new File(path);
		File parent = targetFile.getParentFile();
		if (parent != null && !parent.exists() && !parent.mkdirs())
			System.out.println("Could not create the file");
		FileWriter myWriter = new FileWriter(path);
		myWriter.write(output);
		myWriter.close();
	}
}