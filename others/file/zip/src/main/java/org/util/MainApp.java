package org.util;

public class MainApp
{
   public static void main(String[] args)
   {
	   /*
		* REFERENCES:
		* 2021 BBVA_WorkflowComex\Source
		*/

	   try {
		   System.out.println("Start");
		   
		   System.out.println("COMPRIMIR");
	       String strAComprimir = new String(new char[10000]).replace('\0', 'A');
		   System.out.println("String a comprimir: " + strAComprimir);
		   System.out.println("length String a comprimir: " + strAComprimir.length());

		   byte[] byteStr = strAComprimir.getBytes();
		   byte[] strZip = ZipUtil.zipByteArray(byteStr);
		   
		   String strComprimido = new String(strZip); // "UTF-8"
		   System.out.println("String comprimido: " + strComprimido);
		   System.out.println("length String comprimido: " + strComprimido.length());

		   System.out.println("\n DESCOMPRIMIR");
		   byte[] byteStrDescomprimido = ZipUtil.unzipByteArray(strZip);
		   String strDescomprimido = new String(byteStrDescomprimido); // "UTF-8"
		   System.out.println("String descomprimido: " + strDescomprimido);
		   System.out.println("length String descomprimido: " + strDescomprimido.length());
		   
		   return;
		   
	   } catch (Exception e) {
   			System.out.println(e.getMessage());
   	   }
   }
}