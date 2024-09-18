package org.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * FileUtil.java
 * @author WODZAROD
 * @link https://www.w3schools.com/Java/java_files_read.asp
 */
public class FileUtil
{   
	/*
	 * REFERENCES
	 * 
	 * fileName.toUpperCase().startsWith("W_R15_REP_EVENTOS_DL521");
	 * 
	 * fileName.startsWith("03") && fileName.endsWith(".115");
	 * 
	 * fileName.substring(fileName.indexOf('.') - 6, fileName.indexOf('.'));
	 * 
	 */

	public static Boolean fileExist(String filePath)
	{
		return new File(filePath).exists();
	}
		
	public static Boolean folderExist(String folderPath)
	{
		return new File(folderPath).exists();
	}
	
	public static Boolean isFile(String filePath)
	{
		return new File(filePath).isFile();
	}
	
	public static Boolean isFolder(String folderPath)
	{
		return new File(folderPath).isFile();
	}
	
	public static Integer countFilesFromFolder(String folderPath)
	{
		return new File(folderPath).listFiles().length;
	}
		
	/**
	 * Get file path.
	 * @param first
	 * @param more
	 * @return
	 */
	public static String getPathStr(String first, String... more)
	{
		Path path = Paths.get(first, more);
		return path.toString();
	}
	   
	/**
	 * Get working directory.
	 * @return
	 */
	public static String getWorkingDirectory()
	{
		return Paths.get("").toAbsolutePath().toString();
	} 

   /**
    * Create directory
    * @param folderPath
    * @return
    */
	public static Boolean createFolder(String folderPath)
	{
		File myObj = new File(folderPath); 
		return myObj.mkdir();
	}
		
	/**
	 * 
	 * @param filePath: full file path with extension.
	 * @return
	 */
	public static Boolean deleteFile(String filePath)
	{
		File myObj = new File(filePath); 
		if (myObj.delete()) { 
			System.out.println("Deleted the file: " + myObj.getName());
			return true;
		} else {
			System.out.println("Failed to delete the file.");
			return false;
		} 
	} 
   
	public static Boolean deleteFolder(String folderPath)
	{
		File myObj = new File(folderPath); 
		if (myObj.delete()) { 
			System.out.println("Deleted the folder: " + myObj.getName());
			return true;
		} else {
			System.out.println("Failed to delete the folder.");
			return false;
		} 
	} 
	   
	/**
	 * Deletes files from directory.
	 * @param folderPath
	 */
	public static Boolean deleteFilesFromFolder(String folderPath)
	{		
		try {
			File folder = new File(folderPath);
			File[] files = folder.listFiles();
			//String[] listadoFileServer = folder.list();
			
			Boolean borrado = false;

			if (files != null) {
				for (File f : files) {
					borrado = f.delete();
					if(borrado)
						System.out.println("delete ok: file : " + f.getName());
				}
			}
			return true;
		} catch (Exception e) {
			System.out.println("deleteFilesDirecotry: Exception : " + e.getMessage());
		}
		return false;
	}
 
	/**
	 * Returns a list of the files in the folder.
	 * @param folderPath
	 * @return
	 */
	public static List<String> listFilesFromFolder(String folderPath)
	{		
		List<String> filenames = new ArrayList<String>();
		
		try {
			File folder = new File(folderPath);
			File[] files = folder.listFiles();
			
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) // only if file
				{
					filenames.add(files[i].getName());
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("listFilesFromFolder: Exception: " + e.getMessage());
		}
		return filenames;
	}
	
	public static List<String> listFilesFromFolderWithContain(String folderPath, String filter)
	{		
		List<String> filenames = new ArrayList<String>();
		
		try {
			File folder = new File(folderPath);
			String[] files = folder.list();
			
			for(String file: files)
			{
				if(file.contains(filter))
					filenames.add(file);
			}
		} catch (Exception e) {
			System.out.println("listFilesFromFolderWithContain: Exception: " + e.getMessage());
		}
		return filenames;
	}
	   
	/**
	 * Find a file by name and if exist return its name.
	 * @param folderPath
	 * @param fileName
	 * @return
	 */
	public static String findFileName(String folderPath, String fileName)
	{
		File folder = new File(folderPath);
		String ret = "";
		String[] files = folder.list();
		
		for (int i = 0; i < files.length; i++) {
			if (files[i].equalsIgnoreCase(fileName)) {
				ret = files[i];
				break;
			}
		}
		System.out.println("findFileName: " + ret);
		return ret;
	}
	
	public static Boolean createFile(String filePath)
	{
		try {
	      File myObj = new File(filePath);
	      if (myObj.createNewFile()) {
	        System.out.println("File created: " + myObj.getName());
	        return true;
	      } else {
	        System.out.println("File already exists.");
	      }
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
		return false;
	}
	
	public static Boolean writeFile(String filePath, String data)
	{
	    try {
	      FileWriter myWriter = new FileWriter(filePath);
	      myWriter.write(data);
	      myWriter.close();
	      System.out.println("Successfully wrote to the file.");
	      return true;
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	    return false;
	}

	/**
	 * 
	 * @param file
	 * @param data
	 * @throws IOException
	 * @see https://www.baeldung.com/java-outputstream
	 */
	public static void writeFileFromStrBytes(String filePath, String data) throws IOException {
	    byte[] bytes = data.getBytes();
	    try (OutputStream out = new FileOutputStream(filePath)) {
	        out.write(bytes);
	    }
	}
	
	public static void writeFileFromStrByte(String filePath, String data) throws IOException {
	    byte[] bytes = data.getBytes();
	    try (OutputStream out = new FileOutputStream(filePath)) {
	        out.write(bytes[0]);
	    }
	}
	
	/*public static Boolean writeFileBytes(String filePath, InputStream inputStream)
	{
        File file = new File(filePath);
        String text = "ABCD";
 
        try (FileOutputStream fos = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                DataOutputStream dos = new DataOutputStream(bos))
        {
            dos.writeBytes(text);
            System.out.println("Successfully written data to the file");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
		//Path path = Paths.get(path, fileName);
		//try (OutputStream outputStream = new FileOutputStream(path) {

		//logger.info("tamaño"+file.getSize());
		//file.getBytes()
		
		//try (OutputStream os = Files.newOutputStream(filepath)) {
		//	        os.write(file.getBytes());
		//	    }
				
		try {
			OutputStream outputStream = new FileOutputStream(new File(filePath));
					
			int read = 0;
			byte[] bytes = new byte[1024];
			
			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			
			return true;
		}
		catch(Exception e) {
			System.out.println("Exception: " + e.getMessage());
			throw (e);
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
		
		return false;
	}
	*/
		
	public static Boolean readFile(String filePath)
	{
		try {
	      File myObj = new File(filePath);
	      Scanner myReader = new Scanner(myObj);
	      
	      while (myReader.hasNextLine()) {
	        String data = myReader.nextLine();
	        System.out.println(data);
	      }
	      myReader.close();
	      
	      return true;
	    } catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
		return false;
	}
	
	/**
	 * Read file and return List string.
	 * @param filePath
	 * @return
	 */
	public static List<String> readFileToList(String filePath)
	{
		List<String> filenames = new ArrayList<String>();
		 
		try {
			File myObj = new File(filePath);
			Scanner myReader = new Scanner(myObj);
	      
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				System.out.println(data);
				filenames.add(data);
			}
	     	myReader.close();
	      
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
	    }
		return filenames;
	}
	
	/**
	 * 
	 * @param path
	 * @see https://www.techiedelight.com/how-to-read-file-using-inputstream-java/
	 * @return
	 */
	public static Boolean readFileBytes(String filePath)
	{
		File file = new File(filePath);
		 
        try (InputStream in = new FileInputStream(file))
        {
            int content;
            while ((content = in.read()) != -1) {
                System.out.print((char)content);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
	}
	
	public static void getFileInfo(String filePath)
	{ 
	    File myObj = new File(filePath);
	    if (myObj.exists()) {
	      System.out.println("File name: " + myObj.getName());
	      System.out.println("Absolute path: " + myObj.getAbsolutePath());
	      System.out.println("Writeable: " + myObj.canWrite());
	      System.out.println("Readable " + myObj.canRead());
	      System.out.println("File size in bytes " + myObj.length());
	    } else {
	      System.out.println("The file does not exist.");
	    }
	}
}
/**
https://www.w3schools.com/java/java_files.asp
https://www.tabnine.com/web/assistant/code/rs/5c789397e70f870001a6ab93#L483

List all files in a directory and nested sub-directories | Recursive approach
https://www.geeksforgeeks.org/java-program-list-files-directory-nested-sub-directories-recursive-approach/
https://nirajsonawane.github.io/2018/05/29/Java-8-List-all-Files-in-Directory-and-Subdirectories/

import java.io.File;

File myObj = new File("filename.txt");

* Create a File:

import java.io.File; 
import java.io.IOException;

public class CreateFile {  
  public static void main(String[] args) {  
    try {  
      File myObj = new File("filename.txt");  
      if (myObj.createNewFile()) {  
        System.out.println("File created: " + myObj.getName());  
      } else {  
        System.out.println("File already exists.");  
      }  
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();  
    }  
  }  
} 
// output: File created: filename.txt

* Create a File in a specific directory:

import java.io.File; 
import java.io.IOException;
  
public class CreateFileDir {  
  public static void main(String[] args) {  
    try {  
      File myObj = new File("C:\\Users\\MyName\\filename.txt");  
      if (myObj.createNewFile()) {  
        System.out.println("File created: " + myObj.getName());  
        System.out.println("Absolute path: " + myObj.getAbsolutePath());  
      } else {  
        System.out.println("File already exists.");  
      }  
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();  
    }  
  }  
}
// output:
File created: filename.txt
Absolute path: C:\Users\MyName\filename.txt

// for Windows: \\Users\\MyName\\filename.txt
// for Linux: /Users/name/filename.txt

* Write to a File:

import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {  
  public static void main(String[] args) {  
    try {  
      FileWriter myWriter = new FileWriter("filename.txt");
      myWriter.write("Files in Java might be tricky, but it is fun enough!");
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    } 
  }  
}
// output: Successfully wrote to the file.

* Read a File:

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {  
  public static void main(String[] args) {  
    try {
      File myObj = new File("filename.txt");
      Scanner myReader = new Scanner(myObj);  
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        System.out.println(data);
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    } 
  }  
} 
// output: Files in Java might be tricky, but it is fun enough!

* Get File Information:

import java.io.File; 

public class GetFileInfo {  
  public static void main(String[] args) {  
    File myObj = new File("filename.txt");
    if (myObj.exists()) {
      System.out.println("File name: " + myObj.getName()); 
      System.out.println("Absolute path: " + myObj.getAbsolutePath()); 
      System.out.println("Writeable: " + myObj.canWrite()); 
      System.out.println("Readable: " + myObj.canRead()); 
      System.out.println("File size in bytes: " + myObj.length());
    } else {
      System.out.println("The file does not exist.");
    }
  }  
} 
// output:
File name: filename.txt
Absolute path: C:\Users\MyName\filename.txt
Writeable: true
Readable: true
File size in bytes: 0

* Delete Files:

import java.io.File; 

public class DeleteFile {
  public static void main(String[] args) { 
    File myObj = new File("filename.txt"); 
    if (myObj.delete()) { 
      System.out.println("Deleted the file: " + myObj.getName());
    } else {
      System.out.println("Failed to delete the file.");
    } 
  } 
}
// output: Deleted the file: filename.txt

* Delete a Folder:

import java.io.File; 

public class DeleteFolder {
  public static void main(String[] args) { 
    File myObj = new File("C:\\Users\\MyName\\Test"); 
    if (myObj.delete()) { 
      System.out.println("Deleted the folder: " + myObj.getName());
    } else {
      System.out.println("Failed to delete the folder.");
    } 
  } 
}
// output: Deleted the folder: Test

* Get directory of a .java
String path1 = Paths.get("").toAbsolutePath().toString();
URL url = StreamsUtil.class.getResource("StreamsUtil.class");
String path = url.getPath();
*/
