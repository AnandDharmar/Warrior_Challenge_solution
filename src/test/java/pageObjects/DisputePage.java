package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.io.IOException;
import java.util.*;

public class DisputePage {


    public WebDriver driver;
    HashMap<String, String> disputes = new HashMap<String, String>();
    HashMap<String, String> disputeExtract = new HashMap<String, String>();

    public DisputePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addToArray() {
        List<WebElement> links = driver.findElements(By.className("show"));
        System.out.println("links.size() = " + links.size());
        int i = 0;
        while (i < links.size()) {
            disputes.put(links.get(i).getText(), links.get(i).getAttribute("href"));
            i++;
        }
     }

     public void extractData() throws IOException {

         Iterator iter = (Iterator) disputes.entrySet().iterator();
         while(iter.hasNext()) {
             Map.Entry entry = (Map.Entry) iter.next();
             driver.get((String) entry.getValue());
             String reason = driver.findElement(By.className("reason")).getText();
              if (reason.equals("Incorrect Quantity")) {
                  String name =  driver.findElement(By.className("name")).getText();
                  String ref = driver.findElement(By.className("ref")).getText();
                  disputeExtract.put((String) entry.getKey(), name+ ","+ ref+","+reason);
             }
         }
         createReport();
     }

    public void createReport() throws IOException {
        String[] columns = {"Dispute-ID", "Details"};
        Workbook workbook = new XSSFWorkbook();
        CreationHelper createHelper = workbook.getCreationHelper();
        // Create a Sheet
        Sheet sheet = workbook.createSheet("Dispute Details");

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }


        Iterator iter = (Iterator) disputeExtract.entrySet().iterator();
        int rowNum = 1;

        while(iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue((String) entry.getKey());
            row.createCell(1).setCellValue((String) entry.getValue());
        }

            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            FileOutputStream fileOut = new FileOutputStream("excel-report.xls");
            workbook.write(fileOut);
            fileOut.close();

            // Closing the workbook
            workbook.close();

        }
    }

