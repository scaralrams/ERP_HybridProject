package utils;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.management.RuntimeErrorException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils 
{
	FileInputStream fi;
	Workbook wb;
	CellStyle style;
	Font font;
	Cell cell;
	public ExcelUtils(String XlPath) throws Throwable 
	{
        fi=new FileInputStream(XlPath);
        wb=WorkbookFactory.create(fi);
		
		
	}
	public int RowCount(String XLSheet) throws IOException
	{
		int TotalRows=wb.getSheet(XLSheet).getLastRowNum();
		
		
		return  TotalRows;
		
		
	}
	public String getcellData(String XLSheet,int row, int coloum) throws IOException
	{
		cell=wb.getSheet(XLSheet).getRow(row).getCell(coloum);
		if (cell!=null) 
		{
			switch (cell.getCellType()) 
			{
			case NUMERIC:
			{
				double NumData=cell.getNumericCellValue();
				String data=Long.toString((long)NumData);
				return data;
				
			}
			case STRING:
			{
				return cell.getStringCellValue();
				
			}
		   default:
		   {
			   String data="";
				return data;
		    }
		  }	
		} 
		else 
		{
			String data="";
			return data;

		}
	}
	public void setCellData(String XLSheet,int row, int coloum,String status ,String xlfile) throws Exception
	{
		switch (status.toLowerCase()) 
		{
		case "pass":
		{
			cell=wb.getSheet(XLSheet).getRow(row).createCell(coloum);
			cell.setCellValue(status);
			
			style=wb.createCellStyle();
			font=wb.createFont(); 
			font.setBold(true);
			font.setColor(IndexedColors.GREEN.getIndex());
			style.setFont(font);
			cell.setCellStyle(style);
			break;
		}
		case "fail":
		{
			cell=wb.getSheet(XLSheet).getRow(row).createCell(coloum);
			cell.setCellValue(status);
			style=wb.createCellStyle();
			font=wb.createFont(); 
			font.setBold(true);
			font.setColor(IndexedColors.RED.getIndex());
			style.setFont(font);
			cell.setCellStyle(style);

			break;
		}
		case "blocked":
		{
			
			cell=wb.getSheet(XLSheet).getRow(row).createCell(coloum);
			cell.setCellValue(status);
			style=wb.createCellStyle();
			font=wb.createFont(); 
			font.setBold(true);
			font.setColor(IndexedColors.BROWN.getIndex());
			style.setFont(font);
			cell.setCellStyle(style);
			break;
		}
		default:
		{
			throw new Exception( "You Are Upddating Wrong Status Which is not Defined by the TeamLead");
		}
		
	}
		

		
		FileOutputStream fo=new FileOutputStream(xlfile);
		wb.write(fo);
		
		
	}
}
	
