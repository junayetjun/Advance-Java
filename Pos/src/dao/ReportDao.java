package dao;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entity.Purchase;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import pos.utill.DbUtill;

public class ReportDao {

    DbUtill db = new DbUtill();
    PreparedStatement ps;
    String sql;
    ResultSet rs;

    CategoryDao categoryDao = new CategoryDao();
    StockDao stockDao = new StockDao();

    public List<Purchase> purchaseReportByDate(Date from, Date to, JTable jt) {

        List<Purchase> purchaseList = new ArrayList<>();

        String[] columnsName = {"Product Name", "Unit Price", "Quantity", "Total Price", "Category", "Supplier", "Date"};
        DefaultTableModel tableModel = new DefaultTableModel(columnsName, 0);
        jt.setModel(tableModel);

        sql = "select * from purchase where date between ? and ?";

        try {
            ps = db.getCon().prepareStatement(sql);

            ps.setDate(1, from);
            ps.setDate(2, to);

            rs = ps.executeQuery();

            while (rs.next()) {

                Purchase p = new Purchase(
                        rs.getString("name"),
                        rs.getFloat("unitPrice"),
                        rs.getFloat("quantity"),
                        rs.getFloat("totalPrice"),
                        rs.getDate("date"),
                        rs.getString("category"),
                        rs.getString("supplier")
                );
                purchaseList.add(p);
                Object[] rowData = {p.getName(), p.getUnitPrice(), p.getQuantity(), p.getTotalPrice(),
                     p.getCategory(), p.getSupplier(), p.getDate()};
                tableModel.addRow(rowData);

            }

        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return purchaseList;
    }

    public void generatePDFReportForPurchase(Date from, Date to, JTable jt) {

        List<Purchase> purchases = purchaseReportByDate(from, to, jt);

        //Create PDF document 
        try {
            Document document = new Document(PageSize.A4);
            String filePath = "Purchase_Report.pdf";
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            
            
            //Add title to the Document
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD,16);
            Paragraph tilte = new Paragraph("Purchase Report",titleFont);
            tilte.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(tilte);
            
            //Add a line Break
            document.add(new Paragraph("\n"));
            
            //Add teble Headers
            
            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);
            
            //Set table column width
            float[] columnWidths ={2.5f,2.5f,2.0f,1.5f,1.5f,2f,2f};
            table.setWidths(columnWidths);
            
            //Add column Name
            String[] headers = {"Product Name","Unit Price","Quantity","Total Price","Category","Supplier","Date"};
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD,12);
            
            for(String header : headers){
                PdfPCell headerCell = new PdfPCell(new Phrase(
                        header, 
                        headerFont));
                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                headerCell.setBackgroundColor(BaseColor.BLACK.LIGHT_GRAY);
                table.addCell(headerCell);
            
            }
            
            
        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReportDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ReportDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

}
