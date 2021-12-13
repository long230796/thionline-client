package thionline.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.tool.XSTCTester.TestCase;

import com.google.gson.Gson;

import thionline.client.view.LoginView;
import thionline.entities.Permission;
import thionline.entities.dethi.Cauhoi;
import thionline.entities.dethi.CauhoiPackage;
import thionline.entities.dethi.Traloi;
import thionline.entities.diemthi.Diemthi;
import thionline.entities.diemthi.DiemthiPackage;
import thionline.entities.login.Login;
import thionline.entities.phongthi.PhongthiData;
import thionline.entities.phongthi.PhongthiPackage;
import thionline.entities.sinhvien.Sinhvien;
import thionline.entities.sinhvien.SinhvienPackage;

// chỉ xử lí những phần chung
public class Controller {
	Gson gson = new Gson();
	private String jsonString;
	
	public Controller() {
		
	}
	public Controller(String jsonString) {
		this.jsonString = jsonString;
	}
	
	public List<String> convertToList() {
		return gson.fromJson(jsonString, List.class);
	}
	
	public String convertToString() {
		return gson.fromJson(jsonString, String.class);
	}
	
	public Permission setPermisstion() {
		PhongthiPackage pPkg = gson.fromJson(jsonString, PhongthiPackage.class);
		Permission per = pPkg.getPermission();
		
		return per;
		
	}
	
	public float tinhDiem(List<Traloi> listTraloi, List<Cauhoi> listCauhoi) {
		int size = listTraloi.size();
		float diemMotCau = (float) 10 / size;
		float diemTong = 0;
		for (int i = 0; i < size; i ++) {
			if (listTraloi.get(i) != null) {
				Cauhoi ques = listCauhoi.get(i);
				Traloi ans = listTraloi.get(i);
				if (!new String("null").equals(ans.getDapan())) {
					if (ans.getDapan().toLowerCase().equals(ques.getDapAn().toLowerCase())) {
						diemTong += diemMotCau;
					}
				}
			}
		}
		
		return diemTong;
	}
	
	public List<PhongthiData> getListPhongthi() {
		PhongthiPackage pPkg = gson.fromJson(jsonString, PhongthiPackage.class);
		List<PhongthiData> pData = pPkg.getListPhongthi();
		
		return pData;
	}
	
	public List<Cauhoi> getListCauhoi() {
		CauhoiPackage cauhoiPkg = gson.fromJson(jsonString, CauhoiPackage.class);
		List<Cauhoi> listCauhoi = cauhoiPkg.getListCauhoi();
		
		return listCauhoi;
	}
	
	public List<Sinhvien> getListSinhvien() {
		SinhvienPackage svPkg = gson.fromJson(jsonString, SinhvienPackage.class);
		List<Sinhvien> listSv = svPkg.getListSinhvien();
		
		return listSv;
	}
	
	public List<Diemthi> getListDiemthi() {
		DiemthiPackage diemthiPkg = gson.fromJson(jsonString, DiemthiPackage.class);
		List<Diemthi> listDiemthi = diemthiPkg.getListDiemthi();
		
		return listDiemthi;
	}
	
	public int getThoigianthi() {
		CauhoiPackage cauhoiPkg = gson.fromJson(jsonString, CauhoiPackage.class);
		int thoigianthi = cauhoiPkg.getThoigianthi();
		
		return thoigianthi;
	}
	
	public String runGetter(Field field, Object o)
	{
	    // MZ: Find the correct method
	    for (Method method : o.getClass().getMethods())
	    {
	        if ((method.getName().startsWith("get")) && (method.getName().length() == (field.getName().length() + 3)))
	        {
	            if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase()))
	            {
	                // MZ: Method found, run it
	                try
	                {
	                	System.out.println(method.invoke(o));
	                    return String.valueOf(method.invoke(o));
	                }
	                catch (IllegalAccessException e)
	                {
	                	System.out.println("alo");
//	                    Logger.fatal("Could not determine method: " + method.getName());
	                }
	                catch (InvocationTargetException e)
	                {
	                	System.out.println("alo2");
//	                    Logger.fatal("Could not determine method: " + method.getName());
	                }

	            }
	        }
	    }


	    return null;
	}
	
	
	private Object getCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
			
		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue();
			
		case Cell.CELL_TYPE_NUMERIC:
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
				return sdf.format(cell.getDateCellValue());
			}
			return cell.getNumericCellValue();
		}
		
		
		
		return null;
	}
	
	
	 public List<Sinhvien> readBooksFromExcelFile(String excelFilePath) throws IOException {
		 List<Sinhvien> listSv = new ArrayList<Sinhvien>();
		 FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		 
		 Workbook workbook = new XSSFWorkbook(inputStream);
		 Sheet firstSheet = workbook.getSheetAt(0);
		 Iterator<Row> iterator = firstSheet.iterator();
		 
		 while (iterator.hasNext()) {
			 Row nextRow = iterator.next();
			 Iterator<Cell> cellIterator
			 = nextRow.cellIterator();
			 Sinhvien sv = new Sinhvien();
			 
			 while (cellIterator.hasNext()) {
				 Cell nextCell = cellIterator.next();
				 int columnIndex = nextCell.getColumnIndex();
				 
				 switch (columnIndex) {
				 case 0:
					 sv.setMssv(String.valueOf((int) Math.round((Double)getCellValue(nextCell))));
					 break;
				 case 1:
					 sv.setHo((String)getCellValue(nextCell));
					 break;
				 case 2:
					 sv.setTen((String)getCellValue(nextCell));
					 break;
				 case 3:
					 sv.setNgaysinh(String.valueOf(getCellValue(nextCell)));
					 break;
				 case 4:
					 sv.setDiachi((String)getCellValue(nextCell));
					 break;
				 case 5:	
					 sv.setUsername((String)getCellValue(nextCell));
					 break;
				 case 6:	 
					 sv.setPassword(String.valueOf((int) Math.round((Double)getCellValue(nextCell))));
					 break;
				 case 7:	 
					 sv.setMavaitro((int) Math.round((Double)getCellValue(nextCell)));
					 break;
				 case 8:	 
					 sv.setMatrangthai((int) Math.round(Double.valueOf((String.valueOf(getCellValue(nextCell))))));
					 break;
				 case 9: 
					 sv.setThi((String)getCellValue(nextCell));
					 break;
				 }
			 }
			 listSv.add(sv);
		 }
//		 ((FileInputStream)workbook).close();
		 inputStream.close();
		 
		 return listSv;
	 }
	 
	 
	 public List<Cauhoi> readBodeFromExcelFile(String excelFilePath) throws IOException {
		 List<Cauhoi> listCauhoi = new ArrayList<Cauhoi>();
		 FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		 
		 Workbook workbook = new XSSFWorkbook(inputStream);
		 Sheet firstSheet = workbook.getSheetAt(0);
		 Iterator<Row> iterator = firstSheet.iterator();
		 
		 while (iterator.hasNext()) {
			 Row nextRow = iterator.next();
			 Iterator<Cell> cellIterator
			 = nextRow.cellIterator();
			 Cauhoi cauhoi = new Cauhoi();
			 
			 while (cellIterator.hasNext()) {
				 Cell nextCell = cellIterator.next();
				 int columnIndex = nextCell.getColumnIndex();
				 
				 switch (columnIndex) {
				 case 0:
					 cauhoi.setTrinhDo((String)getCellValue(nextCell));
					 break;
				 case 1:
					 cauhoi.setNoiDung((String)getCellValue(nextCell));
					 break;
				 case 2:
					 cauhoi.setCauA((String)getCellValue(nextCell));
					 break;
				 case 3:
					 cauhoi.setCauB((String)getCellValue(nextCell));
					 break;
				 case 4:	
					 cauhoi.setCauC((String)getCellValue(nextCell));
					 break;
				 case 5:	 
					 cauhoi.setCauD((String)getCellValue(nextCell));
					 break;
				 case 6:	 
					 cauhoi.setDapAn((String)getCellValue(nextCell));
					 break;
				 
				 }
			 }
			 listCauhoi.add(cauhoi);
		 }
//		 ((FileInputStream)workbook).close();
		 inputStream.close();
		 
		 return listCauhoi;
	 }
	 
	
	public static class RandomString {

	    /**
	     * Generate a random string.
	     */
	    public String nextString() {
	        for (int idx = 0; idx < buf.length; ++idx)
	            buf[idx] = symbols[random.nextInt(symbols.length)];
	        return new String(buf);
	    }

	    public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	    public static final String lower = upper.toLowerCase(Locale.ROOT);

	    public static final String digits = "0123456789";

	    public static final String alphanum = upper + lower + digits;

	    private final Random random;

	    private final char[] symbols;

	    private final char[] buf;

	    public RandomString(int length, Random random, String symbols) {
	        if (length < 1) throw new IllegalArgumentException();
	        if (symbols.length() < 2) throw new IllegalArgumentException();
	        this.random = Objects.requireNonNull(random);
	        this.symbols = symbols.toCharArray();
	        this.buf = new char[length];
	    }

	    /**
	     * Create an alphanumeric string generator.
	     */
	    public RandomString(int length, Random random) {
	        this(length, random, alphanum);
	    }

	    /**
	     * Create an alphanumeric strings from a secure generator.
	     */
	    public RandomString(int length) {
	        this(length, new SecureRandom());
	    }

	    /**
	     * Create session identifiers.
	     */
	    public RandomString() {
	        this(21);
	    }

	}
	
	
	public boolean checkExistMaPhong(List<PhongthiData> pData, String maphong) {
		for (PhongthiData p: pData) {
			if (new String(maphong).equals(p.getMaphong().trim())) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isPhongOpen(List<PhongthiData> pData, String maphong) {
		for (PhongthiData p: pData) {
			if (new String(maphong).equals(p.getMaphong().trim())) {
				if (new String("mo").equals(p.getTrangthai().toLowerCase())) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean isPhongClose(List<PhongthiData> pData, String maphong) {
		for (PhongthiData p: pData) {
			if (new String(maphong).equals(p.getMaphong().trim())) {
				if (new String("khoa").equals(p.getTrangthai().toLowerCase())) {
					return true;
					
				}
			}
		}
		
		return false;
	}
	
	
	public void writeToExcell(JTable table, Path path) throws FileNotFoundException, IOException {
	    new WorkbookFactory();
	    Workbook wb = new XSSFWorkbook(); //Excell workbook
	    Sheet sheet = wb.createSheet(); //WorkSheet
	    Row row = sheet.createRow(2); //Row created at line 3
	    TableModel model = table.getModel(); //Table model


	    Row headerRow = sheet.createRow(0); //Create row at line 0
	    for(int headings = 0; headings < model.getColumnCount(); headings++){ //For each column
	        headerRow.createCell(headings).setCellValue(model.getColumnName(headings));//Write column name
	    }

	    for(int rows = 0; rows < model.getRowCount(); rows++){ //For each table row
	        for(int cols = 0; cols < table.getColumnCount(); cols++){ //For each table column
	            row.createCell(cols).setCellValue(model.getValueAt(rows, cols).toString()); //Write value
	        }

	        //Set the row to the next one in the sequence 
	        row = sheet.createRow((rows + 3)); 
	    }
	    wb.write(new FileOutputStream(path.toString()));//Save the file     
	}
	
	
}
