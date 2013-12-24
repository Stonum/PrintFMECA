/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package printfmeca;

import frames.ComponentsNode;
import frames.ComponentsRootCauses;
import java.io.File;
import jxl.*;
import jxl.write.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.read.biff.BiffException;
import jxl.format.UnderlineStyle;
import jxl.write.biff.RowsExceededException;
import struct.DataForXLS;
import struct.Top;

/**
 * @author Aleksey Arshinov
 */
public class EditXLS {

    ArrayList<DataForXLS> data = new ArrayList<DataForXLS>();

    Workbook workbook;
    WritableWorkbook copy;
    WritableSheet sheet;
    WritableCell cell;
    CellView cv;

    int[] mergeRange = new int[4];

    final int beginY = 11;

    public void edit(String filePath) {
        try {
            workbook = Workbook.getWorkbook(new File("input.xls"));
            copy = Workbook.createWorkbook(new File("output.xls"), workbook);
            sheet = copy.getSheet(0);

            cellTop();
            cellMergeBCDE();
            cellMergeG();
            cellAll();

            sheetAutoFitColumns();
            copy.write();
            copy.close();
            workbook.close();
        } catch (IOException ex) {
//            Log.PrintLog(ex.getMessage());
            Logger.getLogger(EditXLS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WriteException ex) {
//            Log.PrintLog(ex.getMessage());
            Logger.getLogger(EditXLS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BiffException ex) {
//            Log.PrintLog(ex.getMessage());
            Logger.getLogger(EditXLS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void editCell(int x, int y, String text, boolean merge) throws WriteException {
        WritableFont ff = new WritableFont(WritableFont.createFont("Calibri"), 16, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE);
        WritableCellFormat wcf = new WritableCellFormat(ff);
        wcf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
        wcf.setAlignment(jxl.format.Alignment.CENTRE);
        wcf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
        wcf.setWrap(true);

        if (merge) {
            sheet.mergeCells(mergeRange[0], mergeRange[1], mergeRange[2], mergeRange[3]);
        }

        Label label = new Label(x, y, text);
        label.setCellFormat(wcf);
        sheet.addCell(label);

    }

    public void addData(ComponentsNode c1, ComponentsRootCauses c2) {
        data.add(new DataForXLS(c1, c2));
    }

    private void cellMergeBCDE() throws WriteException {
        int count = 0;
        int counter = 0;
        for (int i = 0; i < data.size(); i++) {
            if (i == data.size() - 1) {
                counter++; // Если конец массива, то увиличить каунтер, т.к. ниже он не отработает
            }
            if (data.get(i).B.equals(data.get(count).B) && i != data.size() - 1) {
                counter++;
            } else {
                mergeRange = new int[]{1, beginY + count, 1, beginY + counter - 1};
                editCell(1, beginY + count, data.get(count).B, true);
                mergeRange = new int[]{2, beginY + count, 2, beginY + counter - 1};
                editCell(2, beginY + count, data.get(count).C, true);
                mergeRange = new int[]{3, beginY + count, 3, beginY + counter - 1};
                editCell(3, beginY + count, data.get(count).D, true);
                mergeRange = new int[]{4, beginY + count, 4, beginY + counter - 1};
                editCell(4, beginY + count, data.get(count).E, true);
                count = counter;
                counter++;
            }
        }
    }

    private void cellMergeG() throws WriteException {
        int count = 0;
        int counter = 0;
        for (int i = 0; i < data.size(); i++) {
            if (i == data.size() - 1) {
                counter++; // Если конец массива, то увиличить каунтер, т.к. ниже он не отработает
            }
            if (data.get(i).B.equals(data.get(count).B) && data.get(i).G.equals(data.get(count).G) && i != data.size() - 1) {
                counter++;
            } else {
                mergeRange = new int[]{6, beginY + count, 6, beginY + counter - 1};
                editCell(6, beginY + count, data.get(count).G, true);
                count = counter;
                counter++;
            }
        }
    }

    private void cellAll() throws WriteException {
        for (int i = 0; i < data.size(); i++) {
            editCell(5, beginY + i, data.get(i).F.toString(), false);
            editCell(7, beginY + i, data.get(i).H.toString(), false);
            editCell(8, beginY + i, data.get(i).I, false);
            editCell(9, beginY + i, data.get(i).J, false);
            editCell(10, beginY + i, data.get(i).K, false);
            editCell(11, beginY + i, data.get(i).L.toString(), false);
            editCell(12, beginY + i, data.get(i).M.toString(), false);
            editCell(13, beginY + i, data.get(i).N.toString(), false);
            editCell(14, beginY + i, data.get(i).O, false);
            editCell(15, beginY + i, data.get(i).P, false);
            editCell(16, beginY + i, data.get(i).Q, false);
            editCell(17, beginY + i, data.get(i).R, false);
            editCell(18, beginY + i, data.get(i).S.toString(), false);
            editCell(19, beginY + i, data.get(i).T.toString(), false);
            editCell(20, beginY + i, data.get(i).U.toString(), false);
            editCell(21, beginY + i, data.get(i).V.toString(), false);
        }
    }

    private void sheetAutoFitColumns() throws RowsExceededException {
        for (int i = 11; i < data.size() + 11; i++) {
            Cell[] cells = sheet.getRow(i);
            int longestStrLen = -1;
            int longtestWidthCell = 1;

            if (cells.length == 0) {
                continue;
            }

            /* Find the widest cell in the column. */
            for (int j = 0; j < cells.length; j++) {
                if (cells[j].getContents().length() > longestStrLen) {
                    String str = cells[j].getContents();
                    if (str == null || str.isEmpty()) {
                        continue;
                    }
                    longestStrLen = str.trim().length();
                    longtestWidthCell = sheet.getColumnView(j).getSize();
                }
            }

            /* If not found, skip the column. */
            if (longestStrLen == -1) {
                continue;
            }

            /* If wider than the max width, crop width */
            if (longestStrLen > 255) {
                longestStrLen = 255;
            }
            
            if(longestStrLen < 17){
                longestStrLen = 17;
            }
 
            int cellHeight = longestStrLen * 462 / longtestWidthCell * 700;
            if(cellHeight < 700) cellHeight = 700;
            CellView cv = sheet.getRowView(i);
            cv.setSize(cellHeight);
            sheet.setRowView(i, cv);
        }
    }
    
    private void cellTop() throws WriteException{
        WritableFont ff = new WritableFont(WritableFont.createFont("Calibri"), 16, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE);
        WritableCellFormat wcf = new WritableCellFormat(ff);
        Label label = new Label(1, 0, "Спецификация интенсивности отказов FMECA № "+Top.number);
        label.setCellFormat(wcf);
        sheet.addCell(label);

        editCell(2, 2, Top.equipment, false);
        editCell(2, 5, Top.function, false);
        editCell(7, 0, Top.develop, false);
        editCell(7, 2, Top.data1, false);
        editCell(7, 4, Top.data2, false);
        editCell(7, 6, Top.data3, false);
        editCell(11, 2, Top.documents, false);
        editCell(17, 2, Top.drawings, false);

    }
}
