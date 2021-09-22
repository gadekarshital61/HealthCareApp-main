package in.nit.raghu.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import in.nit.raghu.entity.Specialization;

public class SpecializationExcelView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(
			Map<String, Object> model,
			Workbook workbook,
			HttpServletRequest request,
			HttpServletResponse response)
					throws Exception {
		// TODO Auto-generated method stub
		//1. define your own excel file name
		response.addHeader("Content-Disposition","attachment;filename=SPECS.xlsx");
		
		// 2 read data given by controller
		@SuppressWarnings("unchecked")
		List<Specialization> list=(List<Specialization>)model.get("list");
		
		// 3 create sheet
		Sheet sheet=workbook.createSheet("SPECIALIZATION");
		
		//4. create row#0 as header
		setHead(sheet);
				
		//5. create row#1 onwards from List<T> 
		setBody(sheet,list);
		
	}

	private void setBody(Sheet sheet, List<Specialization> list) {
		// TODO Auto-generated method stub
		int rowNum = 1;
		for(Specialization spec : list) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(spec.getId());
			row.createCell(1).setCellValue(spec.getSpecCode());
			row.createCell(2).setCellValue(spec.getSpecName());
			row.createCell(3).setCellValue(spec.getSpecNote());
		}
	}

	private void setHead(Sheet sheet) {
		// TODO Auto-generated method stub
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("CODE");
		row.createCell(2).setCellValue("NAME");
		row.createCell(3).setCellValue("NOTE");
	}

}
