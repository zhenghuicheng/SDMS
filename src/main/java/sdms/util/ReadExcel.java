package sdms.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by cheng on 2017/2/20.
 */
public class ReadExcel {

    public static List<List<Object>> readExcel(File file) throws IOException{
        String fileName = file.getName();
        String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
        if("xls".equals(extension)){
            return read2003Excel(file);
        }else if("xlsx".equals(extension)){
            return read2007Excel(file);
        }else{
            throw new IOException("不支持的文件类型");
        }
    }

    private static List<List<Object>> read2003Excel(File file) throws IOException{
        List<List<Object>> list = new LinkedList<List<Object>>();
        HSSFWorkbook hwb = new HSSFWorkbook(new FileInputStream(file));
        HSSFSheet sheet = hwb.getSheetAt(0);
        Object value = null;
        HSSFRow row = null;
        HSSFCell cell = null;
        int counter = 0;
        for(int i = sheet.getFirstRowNum(); counter < sheet.getPhysicalNumberOfRows(); i++){
            row = sheet.getRow(i);
            if(null == row){
                continue;
            }
            else{
                counter++;
            }
            List<Object> linked = new LinkedList<Object>();
            DecimalFormat df = new DecimalFormat("0");
            for(int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++){
                cell = row.getCell(j);
                if(null == cell){
                    linked.add(null);
                }
                else{
                    switch (cell.getCellType()){
                        case XSSFCell.CELL_TYPE_STRING :
                            value = cell.getStringCellValue();
                            break;
                        case XSSFCell.CELL_TYPE_NUMERIC :
                            value = df.format(cell.getNumericCellValue());
                            break;
                        case XSSFCell.CELL_TYPE_BOOLEAN :
                            value = cell.getBooleanCellValue();
                            break;
                        case XSSFCell.CELL_TYPE_BLANK :
                            value = "";
                            break;
                        default:
                            value = cell.toString();
                    }

                    linked.add(value);
                }
            }
            list.add(linked);
        }

        return list;
    }

    private static List<List<Object>> read2007Excel(File file) throws IOException{

        List<List<Object>> list = new LinkedList<List<Object>>();
        XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));

        XSSFSheet sheet = xwb.getSheetAt(0);
        Object value = null;
        XSSFRow row = null;
        XSSFCell cell = null;
        int counter = 0;
        for (int i = sheet.getFirstRowNum(); counter < sheet.getPhysicalNumberOfRows(); i++) {
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            } else {
                counter++;
            }
            List<Object> linked = new LinkedList<Object>();
            DecimalFormat df = new DecimalFormat("0");
            for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
                cell = row.getCell(j);
                if (cell == null) {
                    linked.add(null);
                }
                else{

                    switch (cell.getCellType()){
                        case XSSFCell.CELL_TYPE_STRING :
                            value = cell.getStringCellValue();
                            break;
                        case XSSFCell.CELL_TYPE_NUMERIC :
                            value = df.format(cell.getNumericCellValue());
                            break;
                        case XSSFCell.CELL_TYPE_BOOLEAN :
                            value = cell.getBooleanCellValue();
                            break;
                        case XSSFCell.CELL_TYPE_BLANK :
                            value = "";
                            break;
                        default:
                            value = cell.toString();
                    }

                    linked.add(value);
                }
            }

            list.add(linked);
        }

        return list;
    }

    /**
     * 判断excel文件中学号是否重复,判断重复返回true
     * @param list
     * @return
     * @throws NullPointerException
     */
    private static List<String> judgeRepeat(List<List<Object>> list) throws NullPointerException{
        List<String> repeatList = new ArrayList<>();
        if(null == list || list.size() == 0){
            return repeatList;
        }
        else{
            Set<String> set = new HashSet<>();
            for(int i = 0; i < list.size(); i++){
                List<Object> item = list.get(i);
                if(item == null){
                    continue;
                }
                else{
                    if(set.contains(item.get(2).toString())){
                        repeatList.add(item.get(2).toString());
                    }
                    else{
                        set.add(item.get(2).toString());
                    }
                }
            }

            return repeatList;
        }
    }
}
