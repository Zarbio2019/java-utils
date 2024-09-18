package org.util;

public class MainApp
{
	public static void main(String[] args)
	{
		System.out.println("Start");
		
		FileUtil fu = new FileUtil();
		
		Boolean ret = false;
		String str = "";
		String folderPath = "D:\\ROMULO\\PROJECTS\\AppWeb\\Backend\\Java8\\file\\file\\files";
		String filePath = "D:\\ROMULO\\PROJECTS\\AppWeb\\Backend\\Java8\\file\\file\\files\\text1.txt"; 
		 
		try {
		   
		   /*
		   System.out.println("Current absolute path is: " + fu.getWorkingDirectory());
		   */
		   
		   /*
		   List<String> fileNames = fu.readFileToList("D:\\ROMULO\\PROJECTS\\Automation\\Mapping\\input\\ignore.txt");
		   */
 
		   /*
		   fu.getFileInfo(filePath);
		   */
		   
		   /*
		   str = fu.getPathStr(folderPath, "text1.txt");
		   */
		   
		   // Create file
		   /*
		   filePath = "text1ROD.txt"; // for create in a project path
		   //filePath = "D:\\ROMULO\\PROJECTS\\AppWeb\\Backend\\Java8\\file\\file\\files\\text1ROD.txt";
		   ret = fu.createFile(filePath);
		   */
		   
		   // Create directory
		   /*
		   ret = fu.createFolder("D:\\ROMULO\\PROJECTS\\AppWeb\\Backend\\Java8\\file\\file\\temp");
		   */
		   
		   // Write
		   /*
		   filePath = "D:\\ROMULO\\PROJECTS\\AppWeb\\Backend\\Java8\\file\\file\\files\\file-write.txt"; 
		   
		   ret = fu.writeFile(filePath,"datos a escribir");
		   
		   fu.writeFileFromStrBytes(filePath, "ROMULO TEST");
		   */
		   
		   // Read
		   /*
		   ret = fu.readFile(filePath);
		   */
		   
		   /*
		   ret = fu.readFileBytes(filePath);
		   */
		   
		   /*
		   str = fu.findFileName(folderPath, "text1.txt");
		   */
		   
		   // Delete
		   /*
		   ret = fu.deleteFile(filePath);
		   */
		   
		   /*
		   ret = fu.deleteFolder(folderPath);
		   */
		   
		   /*
		   ret = fu.deleteFilesFromFolder(folderPath);
		   */
		   
		   return;
			   
	   } catch (Exception e) {
			System.out.println(e.getMessage());
	   }
	}
}