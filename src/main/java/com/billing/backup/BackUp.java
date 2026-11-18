package com.billing.backup;

import com.billing.common.DateUtil;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class BackUp {
	
	
	
	  public static PdfPTable createTableForTax() throws DocumentException {
			//float tblWidth = 100.0f;
			// int border_1 = 1;
				int border_0 = 0;
				float bwl_1 = 1.0f;
				//float bl_0 = 0.0f;
			
			//float cpadding = 8.0f;
		//	float dpadding = 4.0f;
			 final Font fontTIMESBoldBlack = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK);
			 final Font fontTIMESBoBlack = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK);
			   
		        PdfPTable table = new PdfPTable(4);
		        table.setWidthPercentage(100.00f);
		        table.setWidths(new int[]{3,2,3,2});
		       
		        PdfPCell cell;
		        cell = new PdfPCell(new Phrase("Sub Total",fontTIMESBoldBlack));
		        cell.setBorder(border_0);
		    	cell.setBorderWidthTop(bwl_1);
		    	cell.setBorderWidthRight(bwl_1);
		    	cell.setBorderWidthLeft(bwl_1);
		    	cell.setBorderWidthBottom(bwl_1);
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(cell);
		        cell = new PdfPCell(new Phrase("150000",fontTIMESBoBlack));
		        cell.setBorder(border_0);
		    	cell.setBorderWidthTop(bwl_1);
		    	cell.setBorderWidthRight(bwl_1);
		    	cell.setBorderWidthLeft(bwl_1);
		    	cell.setBorderWidthBottom(bwl_1);
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(cell);
		        cell = new PdfPCell(new Phrase("Shipping Charges",fontTIMESBoldBlack));
		        cell.setBorder(border_0);
		    	cell.setBorderWidthTop(bwl_1);
		    	cell.setBorderWidthRight(bwl_1);
		    	cell.setBorderWidthLeft(bwl_1);
		    	cell.setBorderWidthBottom(bwl_1);
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(cell);
		        cell = new PdfPCell(new Phrase("10000",fontTIMESBoBlack));
		        cell.setBorder(border_0);
		    	cell.setBorderWidthTop(bwl_1);
		    	cell.setBorderWidthRight(bwl_1);
		    	cell.setBorderWidthLeft(bwl_1);
		    	cell.setBorderWidthBottom(bwl_1);
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(cell);
		        cell = new PdfPCell(new Phrase("CGST Amount",fontTIMESBoldBlack));
		        cell.setBorder(border_0);
		    	cell.setBorderWidthTop(bwl_1);
		    	cell.setBorderWidthRight(bwl_1);
		    	cell.setBorderWidthLeft(bwl_1);
		    	cell.setBorderWidthBottom(bwl_1);
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(cell);
		        cell = new PdfPCell(new Phrase("1000",fontTIMESBoBlack));
		        cell.setBorder(border_0);
		    	cell.setBorderWidthTop(bwl_1);
		    	cell.setBorderWidthRight(bwl_1);
		    	cell.setBorderWidthLeft(bwl_1);
		    	cell.setBorderWidthBottom(bwl_1);
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(cell);
		        cell = new PdfPCell(new Phrase("P & F Charge",fontTIMESBoldBlack));
		        cell.setBorder(border_0);
		    	cell.setBorderWidthTop(bwl_1);
		    	cell.setBorderWidthRight(bwl_1);
		    	cell.setBorderWidthLeft(bwl_1);
		    	cell.setBorderWidthBottom(bwl_1);
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(cell);
		        cell = new PdfPCell(new Phrase("10000",fontTIMESBoBlack));
		        cell.setBorder(border_0);
		    	cell.setBorderWidthTop(bwl_1);
		    	cell.setBorderWidthRight(bwl_1);
		    	cell.setBorderWidthLeft(bwl_1);
		    	cell.setBorderWidthBottom(bwl_1);
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(cell);
		        cell = new PdfPCell(new Phrase("SGST Amount",fontTIMESBoBlack));
		        cell.setBorder(border_0);
		    	cell.setBorderWidthTop(bwl_1);
		    	cell.setBorderWidthRight(bwl_1);
		    	cell.setBorderWidthLeft(bwl_1);
		    	cell.setBorderWidthBottom(bwl_1);
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(cell);
		        cell = new PdfPCell(new Phrase("1000",fontTIMESBoBlack));
		        cell.setBorder(border_0);
		    	cell.setBorderWidthTop(bwl_1);
		    	cell.setBorderWidthRight(bwl_1);
		    	cell.setBorderWidthLeft(bwl_1);
		    	cell.setBorderWidthBottom(bwl_1);
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(cell);
		        cell = new PdfPCell(new Phrase("Discount",fontTIMESBoldBlack));
		        cell.setBorder(border_0);
		    	cell.setBorderWidthTop(bwl_1);
		    	cell.setBorderWidthRight(bwl_1);
		    	cell.setBorderWidthLeft(bwl_1);
		    	cell.setBorderWidthBottom(bwl_1);
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(cell);
		        cell = new PdfPCell(new Phrase("500",fontTIMESBoBlack));
		        cell.setBorder(border_0);
		    	cell.setBorderWidthTop(bwl_1);
		    	cell.setBorderWidthRight(bwl_1);
		    	cell.setBorderWidthLeft(bwl_1);
		    	cell.setBorderWidthBottom(bwl_1);
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(cell);
		        cell = new PdfPCell(new Phrase("IGST Amount",fontTIMESBoldBlack));
		        cell.setBorder(border_0);
		    	cell.setBorderWidthTop(bwl_1);
		    	cell.setBorderWidthRight(bwl_1);
		    	cell.setBorderWidthLeft(bwl_1);
		    	cell.setBorderWidthBottom(bwl_1);
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(cell);
		        cell = new PdfPCell(new Phrase("2000",fontTIMESBoBlack));
		        cell.setBorder(border_0);
		    	cell.setBorderWidthTop(bwl_1);
		    	cell.setBorderWidthRight(bwl_1);
		    	cell.setBorderWidthLeft(bwl_1);
		    	cell.setBorderWidthBottom(bwl_1);
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(cell);
		        cell = new PdfPCell(new Phrase("GST Amount",fontTIMESBoldBlack));
		        cell.setBorder(border_0);
		    	cell.setBorderWidthTop(bwl_1);
		    	cell.setBorderWidthRight(bwl_1);
		    	cell.setBorderWidthLeft(bwl_1);
		    	cell.setBorderWidthBottom(bwl_1);
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(cell);
		        cell = new PdfPCell(new Phrase("5000",fontTIMESBoBlack));
		        cell.setBorder(border_0);
		    	cell.setBorderWidthTop(bwl_1);
		    	cell.setBorderWidthRight(bwl_1);
		    	cell.setBorderWidthLeft(bwl_1);
		    	cell.setBorderWidthBottom(bwl_1);
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(cell);
		        cell = new PdfPCell(new Phrase("",fontTIMESBoldBlack));
		        cell.setBorder(border_0);
		    	cell.setBorderWidthTop(bwl_1);
		    	cell.setBorderWidthRight(bwl_1);
		    	cell.setBorderWidthLeft(bwl_1);
		    	cell.setBorderWidthBottom(bwl_1);
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(cell);
		        cell = new PdfPCell(new Phrase("",fontTIMESBoBlack));
		        cell.setBorder(border_0);
		    	cell.setBorderWidthTop(bwl_1);
		    	cell.setBorderWidthRight(bwl_1);
		    	cell.setBorderWidthLeft(bwl_1);
		    	cell.setBorderWidthBottom(bwl_1);
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(cell);
		        cell = new PdfPCell(new Phrase("Total Amount",fontTIMESBoldBlack));
		        cell.setBorder(border_0);
		    	cell.setBorderWidthTop(bwl_1);
		    	cell.setBorderWidthRight(bwl_1);
		    	cell.setBorderWidthLeft(bwl_1);
		    	cell.setBorderWidthBottom(bwl_1);
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(cell);
		        cell = new PdfPCell(new Phrase("2000",fontTIMESBoBlack));
		        cell.setBorder(border_0);
		    	cell.setBorderWidthTop(bwl_1);
		    	cell.setBorderWidthRight(bwl_1);
		    	cell.setBorderWidthLeft(bwl_1);
		    	cell.setBorderWidthBottom(bwl_1);
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(cell);
		        return table;
		    }
	  
	  
		
	  public static PdfPTable PotHeader() throws DocumentException {
			 final Font fontTIMESNormalBlack = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL, BaseColor.BLACK);
			 Font fontTIMEBoldBlack = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD, BaseColor.BLACK);
			 int border_1 = 1;
				int border_0 = 0;
				float bwl_1 = 1.0f;
		        PdfPTable table = new PdfPTable(2);
		        table.setWidthPercentage(100f);
		        table.setWidths(new int[]{60,40});
		        PdfPCell cell;
		        cell = new PdfPCell(new Phrase("PO Date :-",fontTIMEBoldBlack));
		        cell.setBorder(border_1);
				cell.setBorderWidthRight(bwl_1);
				cell.setBorderWidthLeft(bwl_1);
				cell.setBorderWidthBottom(bwl_1);
		        table.addCell(cell);
		        
		        cell = new PdfPCell(new Phrase(DateUtil.getCurrentDate()+"",fontTIMESNormalBlack));
		        cell.setBorder(border_1);
				cell.setBorderWidthRight(bwl_1);
				cell.setBorderWidthLeft(bwl_1);
				cell.setBorderWidthBottom(bwl_1);
		        table.addCell(cell);
		        
		        cell = new PdfPCell(new Phrase("Delivery Date :-",fontTIMEBoldBlack));
		        cell.setBorder(border_0);
				cell.setBorderWidthRight(bwl_1);
				cell.setBorderWidthLeft(bwl_1);
				cell.setBorderWidthBottom(bwl_1);
		        table.addCell(cell);
		        
		        cell = new PdfPCell(new Phrase(DateUtil.getCurrentDate()+"",fontTIMESNormalBlack));
		        cell.setBorder(border_0);
				cell.setBorderWidthRight(bwl_1);
				cell.setBorderWidthLeft(bwl_1);
				cell.setBorderWidthBottom(bwl_1);
		        table.addCell(cell);
		        
		        cell = new PdfPCell(new Phrase("PO Days:-",fontTIMEBoldBlack));
		        cell.setBorder(border_0);
				cell.setBorderWidthRight(bwl_1);
				cell.setBorderWidthLeft(bwl_1);
				cell.setBorderWidthBottom(bwl_1);
		        table.addCell(cell);
		        
		        cell = new PdfPCell(new Phrase(""+15,fontTIMESNormalBlack));
		        cell.setBorder(border_0);
				cell.setBorderWidthRight(bwl_1);
				cell.setBorderWidthLeft(bwl_1);
				cell.setBorderWidthBottom(bwl_1);
		        table.addCell(cell);
		     
		        cell = new PdfPCell(new Phrase("Mode of Shipment :-",fontTIMEBoldBlack));
		        cell.setBorder(border_0);
				cell.setBorderWidthRight(bwl_1);
				cell.setBorderWidthLeft(bwl_1);
				cell.setBorderWidthBottom(bwl_1);
		        table.addCell(cell);
		        cell = new PdfPCell(new Phrase("Shipment0001",fontTIMESNormalBlack));
		        cell.setBorder(border_0);
				cell.setBorderWidthRight(bwl_1);
				cell.setBorderWidthLeft(bwl_1);
				cell.setBorderWidthBottom(bwl_1);
		        table.addCell(cell);
		        
		        cell = new PdfPCell(new Phrase("Price Terams :-",fontTIMEBoldBlack));
		        cell.setBorder(border_0);
				cell.setBorderWidthRight(bwl_1);
				cell.setBorderWidthLeft(bwl_1);
				cell.setBorderWidthBottom(bwl_1);
		        table.addCell(cell);
		        
		        cell = new PdfPCell(new Phrase("price Term"+"",fontTIMESNormalBlack));
		        cell.setBorder(border_0);
				cell.setBorderWidthRight(bwl_1);
				cell.setBorderWidthLeft(bwl_1);
				cell.setBorderWidthBottom(bwl_1);
		        table.addCell(cell);

		        cell = new PdfPCell(new Phrase("Country Of Origen :-",fontTIMEBoldBlack));
		        cell.setBorder(border_0);
				cell.setBorderWidthRight(bwl_1);
				cell.setBorderWidthLeft(bwl_1);
				cell.setBorderWidthBottom(bwl_1);
		        table.addCell(cell);
		        
		        cell = new PdfPCell(new Phrase("India",fontTIMESNormalBlack));
		        cell.setBorder(border_0);
				cell.setBorderWidthRight(bwl_1);
				cell.setBorderWidthLeft(bwl_1);
				cell.setBorderWidthBottom(bwl_1);
		        table.addCell(cell);
		        

		        cell = new PdfPCell(new Phrase("Transit Insurance :-",fontTIMEBoldBlack));
		        cell.setBorder(border_0);
				cell.setBorderWidthRight(bwl_1);
				cell.setBorderWidthLeft(bwl_1);
				cell.setBorderWidthBottom(bwl_1);
		        table.addCell(cell);
		        
		        cell = new PdfPCell(new Phrase("",fontTIMESNormalBlack));
		        cell.setBorder(border_0);
				cell.setBorderWidthRight(bwl_1);
				cell.setBorderWidthLeft(bwl_1);
				cell.setBorderWidthBottom(bwl_1);
		        table.addCell(cell);
		        return table;
		    }
	  
	  public static PdfPTable indentApproval() throws DocumentException {
			 final Font fontTIMESNormalBlack = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD, BaseColor.BLACK);
				int border_0 = 0;
				float bl_0 = 0.0f;
		        PdfPTable table = new PdfPTable(3);
		        table.setWidthPercentage(100f);
		        table.setWidths(new float[]{33.33f,33.33f,33.33f});
		        
		        PdfPCell cell;
		        cell = new PdfPCell(new Phrase("Date",fontTIMESNormalBlack));
		        cell.setBorder(border_0);
				cell.setBorderWidthTop(bl_0);
				cell.setBorderWidthRight(bl_0);
				cell.setBorderWidthLeft(bl_0);
				cell.setBorderWidthBottom(bl_0);
		        table.addCell(cell);
		        cell = new PdfPCell(new Phrase("",fontTIMESNormalBlack));
		        cell.setBorder(border_0);
		    	cell.setBorderWidthTop(bl_0);
		    	cell.setBorderWidthRight(bl_0);
		    	cell.setBorderWidthLeft(bl_0);
		    	cell.setBorderWidthBottom(bl_0);
		        table.addCell(cell);
		        cell = new PdfPCell(new Phrase("Signature",fontTIMESNormalBlack));
		        cell.setBorder(border_0);
		    	cell.setBorderWidthTop(bl_0);
		    	cell.setBorderWidthRight(bl_0);
		    	cell.setBorderWidthLeft(bl_0);
		    	cell.setBorderWidthBottom(bl_0);
		        table.addCell(cell);
		        return table;
		    }

	  
		
	  public static PdfPTable potHeader1() throws DocumentException {
			 final Font fontTIMESNormalBlack = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL, BaseColor.BLACK);
			 Font fontTIMEBoldBlack = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK);
				int border_0 = 0;
			
		        PdfPTable table = new PdfPTable(1);
		        table.setWidthPercentage(100f);
		        PdfPCell cell;
	

		      
		        cell = new PdfPCell(new Phrase("\n\n",fontTIMESNormalBlack));
		        cell.setBorder(border_0);
				cell.setBorderWidthRight(border_0);
				cell.setBorderWidthLeft(border_0);
				cell.setBorderWidthBottom(border_0);
				cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		        table.addCell(cell);
		        
		        cell = new PdfPCell(new Phrase("$500.00 ",fontTIMEBoldBlack));
		        cell.setBorder(border_0);
				cell.setBorderWidthRight(border_0);
				cell.setBorderWidthLeft(border_0);
				cell.setBorderWidthBottom(border_0);
				cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		        table.addCell(cell);
		        
		       
		        
		        cell = new PdfPCell(new Phrase("AMOUNT DUE",fontTIMESNormalBlack));
		        cell.setBorder(border_0);
				cell.setBorderWidthRight(border_0);
				cell.setBorderWidthLeft(border_0);
				cell.setBorderWidthBottom(border_0);
				cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		        table.addCell(cell);
		       
		    
		        return table;
		    }
	  
}
