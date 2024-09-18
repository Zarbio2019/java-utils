/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */

package org.util;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * A business plan demo
 * Usage:
 *  BusinessPlan -xls|xlsx
 */
@SuppressWarnings({"java:S106","java:S4823","java:S1192"})
public final class BusinessPlan {

    private static final String[] titles = {
            "ID", "Project Name", "Owner", "Days", "Start", "End"};

    //sample data to fill the sheet.
    private static final String[][] data = {
            {"1.0", "Marketing Research Tactical Plan", "J. Dow", "70", "9-Jul", null,
                "x", "x", "x", "x", "x", "x", "x", "x", "x", "x", "x"},
            null,
            {"1.1", "Scope Definition Phase", "J. Dow", "10", "9-Jul", null,
                "x", "x", null, null,  null, null, null, null, null, null, null},
            {"1.1.1", "Define research objectives", "J. Dow", "3", "9-Jul", null,
                    "x", null, null, null,  null, null, null, null, null, null, null},
            {"1.1.2", "Define research requirements", "S. Jones", "7", "10-Jul", null,
                "x", "x", null, null,  null, null, null, null, null, null, null},
            {"1.1.3", "Determine in-house resource or hire vendor", "J. Dow", "2", "15-Jul", null,
                "x", "x", null, null,  null, null, null, null, null, null, null},
            null,
            {"1.2", "Vendor Selection Phase", "J. Dow", "19", "19-Jul", null,
                null, "x", "x", "x",  "x", null, null, null, null, null, null},
            {"1.2.1", "Define vendor selection criteria", "J. Dow", "3", "19-Jul", null,
                null, "x", null, null,  null, null, null, null, null, null, null},
            {"1.2.2", "Develop vendor selection questionnaire", "S. Jones, T. Wates", "2", "22-Jul", null,
                null, "x", "x", null,  null, null, null, null, null, null, null},
            {"1.2.3", "Develop Statement of Work", "S. Jones", "4", "26-Jul", null,
                null, null, "x", "x",  null, null, null, null, null, null, null},
            {"1.2.4", "Evaluate proposal", "J. Dow, S. Jones", "4", "2-Aug", null,
                null, null, null, "x",  "x", null, null, null, null, null, null},
            {"1.2.5", "Select vendor", "J. Dow", "1", "6-Aug", null,
                null, null, null, null,  "x", null, null, null, null, null, null},
            null,
            {"1.3", "Research Phase", "G. Lee", "47", "9-Aug", null,
                null, null, null, null,  "x", "x", "x", "x", "x", "x", "x"},
            {"1.3.1", "Develop market research information needs questionnaire", "G. Lee", "2", "9-Aug", null,
                null, null, null, null,  "x", null, null, null, null, null, null},
            {"1.3.2", "Interview marketing group for market research needs", "G. Lee", "2", "11-Aug", null,
                null, null, null, null,  "x", "x", null, null, null, null, null},
            {"1.3.3", "Document information needs", "G. Lee, S. Jones", "1", "13-Aug", null,
                null, null, null, null,  null, "x", null, null, null, null, null},
    };

    private BusinessPlan() {}

    public static void main(String[] args) throws Exception {
        Workbook wb;

        if(args.length > 0 && args[0].equals("-xls")) wb = new HSSFWorkbook();
        else wb = new XSSFWorkbook();

        final SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM", Locale.ROOT);

        Map<String, CellStyle> styles = createStyles(wb);

        Sheet sheet = wb.createSheet("Business Plan");

        //turn off gridlines
        sheet.setDisplayGridlines(false);
        sheet.setPrintGridlines(false);
        sheet.setFitToPage(true);
        sheet.setHorizontallyCenter(true);
        PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setLandscape(true);

        //the following three statements are required only for HSSF
        sheet.setAutobreaks(true);
        printSetup.setFitHeight((short)1);
        printSetup.setFitWidth((short)1);

        //the header row: centered text in 48pt font
        Row headerRow = sheet.createRow(0);
        headerRow.setHeightInPoints(12.75f);
        for (int i = 0; i < titles.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(titles[i]);
            cell.setCellStyle(styles.get("header"));
        }
        //columns for 11 weeks starting from 9-Jul
        Calendar calendar = LocaleUtil.getLocaleCalendar();
        int year = calendar.get(Calendar.YEAR);

        calendar.setTime(fmt.parse("9-Jul"));
        calendar.set(Calendar.YEAR, year);
        for (int i = 0; i < 11; i++) {
            Cell cell = headerRow.createCell(titles.length + i);
            cell.setCellValue(calendar);
            cell.setCellStyle(styles.get("header_date"));
            calendar.roll(Calendar.WEEK_OF_YEAR, true);
        }
        //freeze the first row
        sheet.createFreezePane(0, 1);

        Row row;
        Cell cell;
        int rownum = 1;
        for (int i = 0; i < data.length; i++, rownum++) {
            row = sheet.createRow(rownum);
            if(data[i] == null) continue;

            for (int j = 0; j < data[i].length; j++) {
                cell = row.createCell(j);
                String styleName;
                boolean isHeader = i == 0 || data[i-1] == null;
                switch(j){
                    case 0:
                        if(isHeader) {
                            styleName = "cell_b";
                            cell.setCellValue(Double.parseDouble(data[i][j]));
                        } else {
                            styleName = "cell_normal";
                            cell.setCellValue(data[i][j]);
                        }
                        break;
                    case 1:
                        if(isHeader) {
                            styleName = i == 0 ? "cell_h" : "cell_bb";
                        } else {
                            styleName = "cell_indented";
                        }
                        cell.setCellValue(data[i][j]);
                        break;
                    case 2:
                        styleName = isHeader ? "cell_b" : "cell_normal";
                        cell.setCellValue(data[i][j]);
                        break;
                    case 3:
                        styleName = isHeader ? "cell_b_centered" : "cell_normal_centered";
                        cell.setCellValue(Integer.parseInt(data[i][j]));
                        break;
                    case 4: {
                        calendar.setTime(fmt.parse(data[i][j]));
                        calendar.set(Calendar.YEAR, year);
                        cell.setCellValue(calendar);
                        styleName = isHeader ? "cell_b_date" : "cell_normal_date";
                        break;
                    }
                    case 5: {
                        int r = rownum + 1;
                        String fmla = "IF(AND(D"+r+",E"+r+"),E"+r+"+D"+r+",\"\")";
                        cell.setCellFormula(fmla);
                        styleName = isHeader ? "cell_bg" : "cell_g";
                        break;
                    }
                    default:
                        styleName = data[i][j] != null ? "cell_blue" : "cell_normal";
                }

                cell.setCellStyle(styles.get(styleName));
            }
        }

        //group rows for each phase, row numbers are 0-based
        sheet.groupRow(4, 6);
        sheet.groupRow(9, 13);
        sheet.groupRow(16, 18);

        //set column widths, the width is measured in units of 1/256th of a character width
        sheet.setColumnWidth(0, 256*6);
        sheet.setColumnWidth(1, 256*33);
        sheet.setColumnWidth(2, 256*20);
        sheet.setZoom(75); //75% scale


        // Write the output to a file
        String file = "businessplan.xls";
        if(wb instanceof XSSFWorkbook) file += "x";
        FileOutputStream out = new FileOutputStream(file);
        wb.write(out);
        out.close();

        wb.close();
    }

    /**
     * create a library of cell styles
     */
    private static Map<String, CellStyle> createStyles(Workbook wb){
        Map<String, CellStyle> styles = new HashMap<>();
        DataFormat df = wb.createDataFormat();

        CellStyle style;
        Font headerFont = wb.createFont();
        headerFont.setBold(true);
        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFont(headerFont);
        styles.put("header", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFont(headerFont);
        style.setDataFormat(df.getFormat("d-mmm"));
        styles.put("header_date", style);

        Font font1 = wb.createFont();
        font1.setBold(true);
        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setFont(font1);
        styles.put("cell_b", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font1);
        styles.put("cell_b_centered", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.RIGHT);
        style.setFont(font1);
        style.setDataFormat(df.getFormat("d-mmm"));
        styles.put("cell_b_date", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.RIGHT);
        style.setFont(font1);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setDataFormat(df.getFormat("d-mmm"));
        styles.put("cell_g", style);

        Font font2 = wb.createFont();
        font2.setColor(IndexedColors.BLUE.getIndex());
        font2.setBold(true);
        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setFont(font2);
        styles.put("cell_bb", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.RIGHT);
        style.setFont(font1);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setDataFormat(df.getFormat("d-mmm"));
        styles.put("cell_bg", style);

        Font font3 = wb.createFont();
        font3.setFontHeightInPoints((short)14);
        font3.setColor(IndexedColors.DARK_BLUE.getIndex());
        font3.setBold(true);
        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setFont(font3);
        style.setWrapText(true);
        styles.put("cell_h", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setWrapText(true);
        styles.put("cell_normal", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setWrapText(true);
        styles.put("cell_normal_centered", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.RIGHT);
        style.setWrapText(true);
        style.setDataFormat(df.getFormat("d-mmm"));
        styles.put("cell_normal_date", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setIndention((short)1);
        style.setWrapText(true);
        styles.put("cell_indented", style);

        style = createBorderedStyle(wb);
        style.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        styles.put("cell_blue", style);

        return styles;
    }

    private static CellStyle createBorderedStyle(Workbook wb){
        BorderStyle thin = BorderStyle.THIN;
        short black = IndexedColors.BLACK.getIndex();

        CellStyle style = wb.createCellStyle();
        style.setBorderRight(thin);
        style.setRightBorderColor(black);
        style.setBorderBottom(thin);
        style.setBottomBorderColor(black);
        style.setBorderLeft(thin);
        style.setLeftBorderColor(black);
        style.setBorderTop(thin);
        style.setTopBorderColor(black);
        return style;
    }
}


//package org.util;
//
//import java.io.BufferedOutputStream;
//import java.io.DataOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Map;
//import java.util.Scanner;
//
///**
// * File functions.
// * @author WODZAROD
// * @link https://www.w3schools.com/Java/java_files_read.asp
// */
//
//public class ReportExcelUtil
//{
//   public static void main(String[] args)
//   {
//	   FileUtil fu = new FileUtil();
//	   
//	   Boolean ret = false;
//	   String fileName = "";
//	   String[] filesName = null;
//	   
//	   try {
//		   // for directory
//		   String path = "D:\\ROMULO\\PROJECTS\\AppWeb\\Backend\\Java8\\file\\file\\files";
//		   File myObj = new File(path);
//		   
//		   // for a file
//		   String filePath = "D:\\ROMULO\\PROJECTS\\AppWeb\\Backend\\Java8\\file\\file\\files\\text1.txt"; 
//		   File myObj2 = new File(filePath);
//		   
//		   // Get file info
//		   fu.getFileInfo(filePath);
//		   
//		   // Get  file path
//		   Path filepath = Paths.get(path, "text1.txt");
//		   
//		   // Create file
//		   filePath = "text1ROD.txt"; // for create in a project path
//		   //filePath = "D:\\ROMULO\\PROJECTS\\AppWeb\\Backend\\Java8\\file\\file\\files\\text1ROD.txt"; 
//		   ret = fu.createFile(filePath);
//		   
//		   // Create directory
//		   File myObj3 = new File("D:\\ROMULO\\PROJECTS\\AppWeb\\Backend\\Java8\\file\\file\\temp");
//		   ret = myObj3.mkdir();
//		   
//		   // Returns an array of the files in the directory
//		   File[] files = myObj.listFiles();
//		   
//		   for (int i = 0; i < files.length; i++) {
//				if (files[i].isFile()) {
//					fileName = files[i].getName();
//					System.out.println(fileName);
//					break;
//				}
//			}
//		   //fileName.toUpperCase().startsWith("W_R15_REP_EVENTOS_DL521");
//		   //fileName.startsWith("03") && fileName.endsWith(".115");
//		   //fileName.substring(fileName.indexOf('.') - 6, fileName.indexOf('.'));
//		   
//		   // Count
//		   System.out.println("Count files: " + myObj.listFiles().length);
//		   
//		   // List
//		   filesName = myObj.list();
//		   if(filesName[0].contains(".txt"))
//			   System.out.println("filesName contain");
//		   		   
//		   // Exist	   
//		   //ret = myObj.exists();
//		   ret = myObj2.exists();
//		   
//		   // Validation isFile, isDirectory
//		   ret = myObj2.isFile();
//		   ret = myObj2.isDirectory();
//	
//		   // Returns the name of the file
//		   fileName = files[0].getName(); // with extension
//		   
//		   // Write
//		   filePath = "D:\\ROMULO\\PROJECTS\\AppWeb\\Backend\\Java8\\file\\file\\files\\file-write.txt"; 
//		   ret = fu.writeFile(filePath);
//		   fu.fileOutputStreamByteSequence(filePath, "ROMULO TEST");
//		   
//		   // Read
//		   ret = fu.readFile(filePath);
//		   ret = fu.readFileBytes(filePath);
//				   
//		   // filterFileName
//		   String sRet = fu.filterFileName(path, "text1.txt");
//		   
//		   // Delete
//		   ret = fu.deleteFile(filePath);
//		   ret = fu.deleteFolder(path);
//		   
//		   fu.deleteFilesDirectory(path); // Deletes files from directory
//		   
//	   } catch (Exception e) {
//  			System.out.println(e.getMessage());
//  	   }
//   }
//   
//   public Boolean deleteFile(String path)
//   {
//     File myObj = new File(path); 
//     if (myObj.delete()) { 
//       System.out.println("Deleted the file: " + myObj.getName());
//       return true;
//     } else {
//       System.out.println("Failed to delete the file.");
//       return false;
//     } 
//   } 
//   
//   public Boolean deleteFolder(String path)
//   {
//     File myObj = new File(path); 
//     if (myObj.delete()) { 
//       System.out.println("Deleted the folder: " + myObj.getName());
//       return true;
//     } else {
//       System.out.println("Failed to delete the folder.");
//       return false;
//     } 
//   } 
//	   
//	private void deleteFilesDirectory(String path) {
//		
//		System.out.println("deleteFilesDirecotry");
//		
//		try {
//
//			File folder = new File(path);
//			File[] files = folder.listFiles();
//			//String[] listadoFileServer = folder.list();
//			
//			Boolean borrado = false;
//
//			if (files != null) {
//				for (File f : files) {
//					borrado = f.delete();
//					if(borrado)
//						System.out.println("delete ok: file : " + f.getName());
//				}
//			}
//
//		} catch (Exception e) {
//			System.out.println("deleteFilesDirecotry: Exception : " + e.getMessage());
//		}
//	}
//	
//	public String filterFileName(String path, String fileName) {
//		File folder = new File(path);
//		String ret = "";
//		String[] files = folder.list();
//		
//		for (int i = 0; i < files.length; i++) {
//			if (files[i].equalsIgnoreCase(fileName)) {
//				ret = files[i];
//				break;
//			}
//		}
//		System.out.println("filterFileName: " + ret);
//		return ret;
//	}
//	
//	public Boolean createFile(String path)
//	{
//		try {
//	      File myObj = new File(path);
//	      if (myObj.createNewFile()) {
//	        System.out.println("File created: " + myObj.getName());
//	        return true;
//	      } else {
//	        System.out.println("File already exists.");
//	      }
//	    } catch (IOException e) {
//	      System.out.println("An error occurred.");
//	      e.printStackTrace();
//	    }
//		return false;
//	}
//	
//	public Boolean writeFile(String path)
//	{
//	    try {
//	      FileWriter myWriter = new FileWriter(path);
//	      myWriter.write("Files in Java might be tricky, but it is fun enough!");
//	      myWriter.close();
//	      System.out.println("Successfully wrote to the file.");
//	      return true;
//	    } catch (IOException e) {
//	      System.out.println("An error occurred.");
//	      e.printStackTrace();
//	    }
//	    return false;
//	}
//
//	/**
//	 * 
//	 * @param file
//	 * @param data
//	 * @throws IOException
//	 * @see https://www.baeldung.com/java-outputstream
//	 */
//	public static void fileOutputStreamByteSequence(String file, String data) throws IOException {
//	    byte[] bytes = data.getBytes();
//	    try (OutputStream out = new FileOutputStream(file)) {
//	        out.write(bytes);
//	    }
//	}
//	
//	public static void fileOutputStreamByteSingle(String file, String data) throws IOException {
//	    byte[] bytes = data.getBytes();
//	    try (OutputStream out = new FileOutputStream(file)) {
//	        out.write(bytes[6]);
//	    }
//	}
//	
//	/*public Boolean writeFileBytes(String filePath, InputStream inputStream)
//	{
//        File file = new File(filePath);
//        String text = "ABCD";
// 
//        try (FileOutputStream fos = new FileOutputStream(file);
//                BufferedOutputStream bos = new BufferedOutputStream(fos);
//                DataOutputStream dos = new DataOutputStream(bos))
//        {
//            dos.writeBytes(text);
//            System.out.println("Successfully written data to the file");
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//        
//		//Path path = Paths.get(path, fileName);
//		//try (OutputStream outputStream = new FileOutputStream(path) {
//
//		//logger.info("tamaño"+file.getSize());
//		//file.getBytes()
//		
//		//try (OutputStream os = Files.newOutputStream(filepath)) {
//		//	        os.write(file.getBytes());
//		//	    }
//				
//		try {
//			OutputStream outputStream = new FileOutputStream(new File(filePath));
//					
//			int read = 0;
//			byte[] bytes = new byte[1024];
//			
//			while ((read = inputStream.read(bytes)) != -1) {
//				outputStream.write(bytes, 0, read);
//			}
//			
//			return true;
//		}
//		catch(Exception e) {
//			System.out.println("Exception: " + e.getMessage());
//			throw (e);
//		} finally {
//			if (inputStream != null) {
//				inputStream.close();
//			}
//		}
//		
//		return false;
//	}
//	*/
//		
//	public Boolean readFile(String path)
//	{
//		try {
//	      File myObj = new File(path);
//	      Scanner myReader = new Scanner(myObj);
//	      
//	      while (myReader.hasNextLine()) {
//	        String data = myReader.nextLine();
//	        System.out.println(data);
//	      }
//	      myReader.close();
//	      
//	      return true;
//	    } catch (FileNotFoundException e) {
//	      System.out.println("An error occurred.");
//	      e.printStackTrace();
//	    }
//		return false;
//	}
//	
//	/**
//	 * 
//	 * @param path
//	 * @see https://www.techiedelight.com/how-to-read-file-using-inputstream-java/
//	 * @return
//	 */
//	public Boolean readFileBytes(String path)
//	{
//		File file = new File(path);
//		 
//        try (InputStream in = new FileInputStream(file))
//        {
//            int content;
//            while ((content = in.read()) != -1) {
//                System.out.print((char)content);
//            }
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//	}
//	
//	public void getFileInfo(String path)
//	{ 
//	    File myObj = new File(path);
//	    if (myObj.exists()) {
//	      System.out.println("File name: " + myObj.getName());
//	      System.out.println("Absolute path: " + myObj.getAbsolutePath());
//	      System.out.println("Writeable: " + myObj.canWrite());
//	      System.out.println("Readable " + myObj.canRead());
//	      System.out.println("File size in bytes " + myObj.length());
//	    } else {
//	      System.out.println("The file does not exist.");
//	    }
//	}
//}