import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Compiler2Run {
	public static String Tpath;
	private static String output="";
	
	public static void main(String[] args) {
		String[] s = readDirectory();
		String[] temp;
		for(int xp=0;xp<s.length;xp++) {
			//System.out.println(s[xp]);
			if(s[xp].endsWith(".xml")) {

				//temp=readFile(s[xp]);	

				//printArray(temp);
				
				//Tokenizer tt = new Tokenizer(); need to make XML reader
				Tag m = XMLreader.realXMLrun(s[xp]);
				
				
				
				if(!m.typeEquals("tokens")) {//make sure it is only compiling the full xml not the tokenized xml
				Compiler2Engine x = new Compiler2Engine();
				output=x.runCompliationEngine(m);
				
				//printXML(XMLreader.realXMLrun(s[xp]),-1);
				
				String filename = s[xp].substring( (s[xp].lastIndexOf("\\")==-1 ? s[xp].lastIndexOf("/")+1 : s[xp].lastIndexOf("\\"))+1 , s[xp].lastIndexOf("."));
				//writeToFile(output.split("\n"), filename+"-BAD");//-uncomment this to make it output the files
				System.out.println(output);
				output="";
				}
			}
		}
		
		
	}
	
	public static String[] readDirectory() {
		Scanner console = new Scanner(System.in);
		System.out.println("Give the directory of .XML files:");
		Tpath=console.nextLine();
		Path dir = Paths.get(Tpath);
		String output="";
		
		DirectoryStream<Path> stream;
		try {
			stream = Files.newDirectoryStream(dir);
		    for (Path file: stream) {
		        //System.out.println(file.getFileName());
		        output= output+"~"+file.toString();
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(out);
		return output.substring(1, output.length()).split("~");
		
	}
	
	public static String[] readFile(String Currentpath) {// reads file at path


		// Source of help for reading files
		// https://www.caveofprogramming.com/java/java-file-reading-and-writing-files-in-java.html
		ArrayList<String> list = new ArrayList<String>(0);
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(Currentpath);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			// String[] text = String[bufferedReader.lines()];//Needs to put the lines into
			// string array

			String current;
			while ((current = bufferedReader.readLine()) != null) {// Gets the amount of lines in the document

				list.add("" + current);
				 //System.out.println(current);
			}

			bufferedReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Not found");
			System.exit(0);
		} catch (IOException e) {
			System.out.println("File not read");
			System.exit(0);
		}

		
		return list.toArray(new String[list.size()]);
	}
	
	public static void writeToFile(Object[] text, String fname) {
		//System.out.println("TPATH:"+Tpath);
		// Source of knowledge
		// https://stackoverflow.com/questions/2885173/how-do-i-create-a-file-and-write-to-it-in-java
		System.out.println("\nOutput: " +  Tpath+"\\"+fname+".vm");

		//Path file = Paths.get(   Tpath+Tpath.substring( (Tpath.lastIndexOf("\\")==-1 ? Tpath.lastIndexOf("/") : Tpath.lastIndexOf("\\")) )+".XML");
		Path file = Paths.get (Tpath+"\\"+fname+".vm");
		// convert text to a list but delete the null values
		List<String> list = new ArrayList<String>(0);
		for (int i = 0; i < text.length; i++) {
			if (text[i] != null) {
				list.add((String) text[i]);
			}
		}

		try {
			Files.write(file, list, Charset.forName("UTF-8"));
			System.out.println("Done!");
		} catch (IOException e) {
			System.out.println("Wasnt able to create .XML file");
		}
	}
	
	
	public static void printXML(Tag o, int tabCount) {//prints the XML to the console
		tabCount++;
		System.out.println();
		for(int i=0;i<tabCount;i++) {
			System.out.print("  ");
		}
		System.out.print("<"+o.getType()+">");
		if(o.getData()!=null) {
			System.out.print(" "+o.getData()+" ");
		}
		for(Tag g : o.getChildren()) {
			printXML(g,tabCount);
		}
		if(o.getData()==null) {
			System.out.println();
			for(int i=0;i<tabCount;i++) {
				System.out.print("  ");
			}
		}

		System.out.print("</"+o.getType()+">");
	}
}
