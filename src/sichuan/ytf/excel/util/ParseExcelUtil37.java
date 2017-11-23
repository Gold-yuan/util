package sichuan.ytf.excel.util;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * ����EXCEL03 07�����
 * 
 * @author adminytf
 * 
 */
public class ParseExcelUtil37 {

	/** ������ʼ��,0��ʼ */
	private int startRow = 0;
	/** ������ʼ��,0��ʼ */
	private int startCell = 0;
	/** �����ڵ��ֶΣ����ڸ�ʽ,{"ym","2017-05"} */
	private Map<String, String> dateFormats = new HashMap<String, String>();
	/** excel��Ĺ̶��ֶ�,{"id":"1001"} */
	private Map<String, String> finalValueMap = new HashMap<String, String>();

	private List<String> notNullParameter = new ArrayList<String>();
	public static final String SHOW_DATE_FORMAT_STR = "yyyy-MM-dd";

	public static void main(String[] args) throws Exception {
		String filePath = "F:/project/����/�ĵ�/�������ε�λ��Ӧ��.xlsx";
		ParseExcelUtil37 pe = new ParseExcelUtil37();
		pe.setStartCell(0);
		pe.setStartRow(1);
		String[] names = {"xsdw", "isJs", "sairport", "xsqy", "gnzb"};
		List<Map<String, String>> listOld = pe.parseExcel(filePath, names);
		System.out.println(listOld.size()+"  "+listOld);
		
	}

	public List<Map<String, String>> parseExcel(String filePath, String[] fieldNames) throws Exception {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Workbook workbook = null;
		// �����ļ�
		try {
			workbook = WorkbookFactory.create(new FileInputStream(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ��ȡÿһ��������
		Sheet sheet = workbook.getSheetAt(0);
		if (sheet == null) {
			System.out.println("��");
			return null;
		}
		// ��ȡ��ǰ��������ÿһ�� sheet.getLastRowNum() 0��ʼ�� ֵ�������-1��������Ϊ-1
		for (int rowNum = startRow; rowNum <= sheet.getLastRowNum(); rowNum++) {

			Row row = sheet.getRow(rowNum);
			if (row == null) {
				continue;
			}
			Map<String, String> map = new HashMap<String, String>();
			// ��ȡÿ��
			for (int cellCount = startCell; cellCount < fieldNames.length; cellCount++) {
				String propertyName = fieldNames[cellCount];
				if (propertyName == null || propertyName.equals("")) {
					continue;
				}
				Cell cell = row.getCell(cellCount);
				if (cell == null) {
					continue;
				}
				map.put(propertyName, this.getValue(cell, propertyName));
			}
			// У���
			boolean flag = true;
			for (String key : notNullParameter) {
				String val = map.get(key);
				if (val == null || val.length() == 0) {
					flag = false;
				}
			}
			if (flag) {
				list.add(map);
			}
		}
		return list;
	}
	@SuppressWarnings("deprecation")
	protected String getValue(Cell cell, String propertyName) {
		String value = null;
		if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
			if (HSSFDateUtil.isCellDateFormatted(cell) || dateFormats.keySet().contains(propertyName)) {
				SimpleDateFormat sf = new SimpleDateFormat(dateFormats.get(propertyName));
				value = sf.format(cell.getDateCellValue());
			} else {
				value = String.valueOf(cell.getNumericCellValue());
			}
		} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
			value = String.valueOf(cell.getBooleanCellValue());
		} else if (dateFormats.keySet().contains(propertyName)) {
			value = cell.getRichStringCellValue().getString();
		} else {
			value = cell.getRichStringCellValue().getString();
		}
		return value;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public void setStartCell(int startCell) {
		this.startCell = startCell;
	}

	public void setDateFormats(Map<String, String> dateFormats) {
		this.dateFormats = dateFormats;
	}

	public void setFinalValueMap(Map<String, String> finalValueMap) {
		this.finalValueMap = finalValueMap;
	}

	public Map<String, String> getDateFormats() {
		return dateFormats;
	}

	public Map<String, String> getFinalValueMap() {
		return finalValueMap;
	}

	public List<String> getNotNullParameter() {
		return notNullParameter;
	}

	public void setNotNullParameter(List<String> notNullParameter) {
		this.notNullParameter = notNullParameter;
	}

	public int getStartRow() {
		return startRow;
	}

	public int getStartCell() {
		return startCell;
	}

}
