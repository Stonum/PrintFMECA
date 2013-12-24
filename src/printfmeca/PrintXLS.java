/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package printfmeca;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.*;

/**
 * @author Aleksey Arshinov
 */
public class PrintXLS {

    
    static public void print(String aFileName) {

        ActiveXComponent excel = null;
        Dispatch workbooks;
        Variant workbook;

        try {
            excel = new ActiveXComponent("Excel.Application");
            excel.setProperty("Visible", new Variant(false));
            workbooks = excel.getProperty("WorkBooks").toDispatch();
            workbook = Dispatch.callN(workbooks, "Open", new Object[]{aFileName});
            Dispatch.call(Dispatch.get(workbook.toDispatch(), "ActiveSheet").toDispatch(), "PrintOut");
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
//                Log.PrintLog(ex.getMessage());
                ex.printStackTrace();
            }
        } catch (Exception ex) {
//            Log.PrintLog(ex.getMessage());
            ex.printStackTrace();
        } finally {
            excel.invoke("Quit", new Variant[]{});
            ComThread.Release();
        }
    }

}
