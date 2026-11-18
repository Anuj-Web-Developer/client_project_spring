package com.billing.common;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	
	public static XSSFWorkbook exportExcel(List<Map<String, Object>> data, String fromDate, String todate, String reportName) {

		XSSFWorkbook workbookCreate = new XSSFWorkbook();
		XSSFSheet sheetCreate = workbookCreate.createSheet("Report Data");

		int rowNo = 2;
		Row rowHeader = sheetCreate.createRow(rowNo);

		// header creation
		Map<String, Object> headerMap = data.get(0);
		Iterator<Map.Entry<String, Object>> itrHeader = headerMap.entrySet().iterator();
		int i = 0;
		while (itrHeader.hasNext()) {
			Map.Entry<String, Object> entry = itrHeader.next();
			rowHeader.createCell(i).setCellValue(entry.getKey());
			rowHeader.getCell(i).setCellStyle(getHeaderCellStyle(workbookCreate));
			i++;
		}
		// header creation ends
		sheetCreate.addMergedRegion(new CellRangeAddress(0,0,0,i-1));
	    rowHeader = sheetCreate.createRow(rowNo-2);
		Cell cell1 = CellUtil.createCell(rowHeader, 0, reportName);
		cell1.setCellStyle(getTopHeaderCellStyle(workbookCreate));
		
		sheetCreate.addMergedRegion(new CellRangeAddress(1,1,0,i-1));
		rowHeader = sheetCreate.createRow(rowNo-1);
		cell1 = CellUtil.createCell(rowHeader, 0, "  From:- "+ fromDate+"       To :-  "+todate);
		cell1.setCellStyle(getTopHeaderCellStyle(workbookCreate));
		
//		sheetCreate.addMergedRegion(new CellRangeAddress(2,2,0,i-1));
//		rowHeader = sheetCreate.createRow(rowNo-1);
//		cell1 = CellUtil.createCell(rowHeader, 0, "  From:- "+ fromDate+"       To :-  "+todate);
//		cell1.setCellStyle(getDetailTopHeaderCellStyle(workbookCreate));
		
		rowNo++;
		Iterator<Map<String, Object>> itrBody = data.iterator();
		while (itrBody.hasNext()) {

			Row row = sheetCreate.createRow(rowNo);
			i = 0;
			Map<String, Object> map = itrBody.next();
			Iterator<Map.Entry<String, Object>> itr = map.entrySet().iterator();
			while (itr.hasNext()) {
				Map.Entry<String, Object> me = itr.next();
				Cell cell = row.createCell(i);
				if (me.getValue() instanceof Long) {
					cell.setCellValue((Long) me.getValue());
				} else if (me.getValue() instanceof Double) {
					cell.setCellValue((Double) me.getValue());
				} else if (me.getValue() instanceof String) {
					cell.setCellValue((String) me.getValue());
				} else if (me.getValue() instanceof Float) {
					cell.setCellValue((Float) me.getValue());
				} else if (me.getValue() instanceof Integer) {
					cell.setCellValue((Integer) me.getValue());
				} else if (me.getValue() instanceof Boolean) {
					cell.setCellValue((Boolean) me.getValue());
				} else {
					cell.setCellValue(me.getValue() + "");
				}
				// row.createCell(i).setCellValue((double) me.getValue());
				i++;
			}
			rowNo++;
		}

		headerMap = data.get(0);
		itrHeader = headerMap.entrySet().iterator();
		i = 0;
		while (itrHeader.hasNext()) {
			itrHeader.next();
			sheetCreate.autoSizeColumn(i);
			i++;
		}

		sheetCreate.createFreezePane(0, 2, 0, i);

		return workbookCreate;
	}

	public static CellStyle getHeaderCellStyle(XSSFWorkbook workbook) {

		XSSFFont font = workbook.createFont();
		font.setColor(IndexedColors.BLACK.getIndex());
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
		cellStyle.setWrapText(true);
		return cellStyle;
	}
	
	public static CellStyle getTopHeaderCellStyle(XSSFWorkbook workbook) {

		XSSFFont font = workbook.createFont();
		font.setColor(IndexedColors.BLACK.getIndex());
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setFillForegroundColor(IndexedColors.TAN.getIndex());
		cellStyle.setWrapText(true);
		return cellStyle;
	}
	public static CellStyle getDetailTopHeaderCellStyle(XSSFWorkbook workbook) {

		XSSFFont font = workbook.createFont();
		font.setColor(IndexedColors.BLACK.getIndex());
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setFillForegroundColor(IndexedColors.TAN.getIndex());
		cellStyle.setWrapText(true);
		return cellStyle;
	}

}
