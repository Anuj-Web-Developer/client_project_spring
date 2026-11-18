package com.billing.common;

import java.io.InputStream;
import java.io.OutputStream;

public class CommonUtils {

	public static void closeInputStream(InputStream in) {
		try{
			if(in != null){
				in.close();
			}
		}catch(Exception e){
			
		}
	}
	
	public static void closeOutputStream(OutputStream out) {
		try{
			if(out != null){
				out.flush();
				out.close();
			}
		}catch(Exception e){
		}
	}
}
