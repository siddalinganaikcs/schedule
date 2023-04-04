package com.jsp.medlife.image.compress;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageCompress 
{
   public static byte[] compressImage(byte[] data) 
   {
	   Deflater deflater = new Deflater();
	   deflater.setLevel(Deflater.BEST_COMPRESSION);
	   deflater.setInput(data);
	   deflater.finish();
	   
	   
	   ByteArrayOutputStream  baos=new ByteArrayOutputStream(data.length);
	   
	   byte []  by=new byte[4*1024];
	   
	  
			   try
			   {
				   if(!deflater.finished())
				   {
					  int minimise = deflater.deflate(by);
					  baos.write(by, 0, minimise);
				   }
				   baos.close();
			   }
			   catch(Exception ignore)
			   {
				   
			   }
	   return baos.toByteArray();
	   
   }
   
   
   
   public static byte[] deCompress(byte [] deco)
   {
	   Inflater inflater =new Inflater();
	   inflater.setInput(deco);
	   
	  
	   
	   ByteArrayOutputStream outputStream =new ByteArrayOutputStream(deco.length);
	   
	   byte[] bt=new byte[4*1024];
	   
	   
		   try
		   {
			   while(inflater.finished())
			   {
				   System.out.println("while loop executing.............!!!!!!!!!!!!!!!");
				  int maximize = inflater.inflate(bt);
				  outputStream.write(bt, 0, maximize);
			   }
			  outputStream.close();
		   }
		  catch(Exception ignore)
		   {
			   
		   }
		   
		   return outputStream.toByteArray();
	 }
   

}
