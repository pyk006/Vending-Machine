package com.techelevator.application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SalesReport {
    DateTimeFormatter fileNamePattern = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    String reportName = "salesreport_" + LocalDateTime.now().format(fileNamePattern) +  ".txt";
    private File salesReport = new File(reportName);
    BigDecimal totalSales = new BigDecimal("0");


    public SalesReport() {

    }

    public void generateSalesReport(List<VendingItem> inventory) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(salesReport, true))) {
            for (VendingItem item : inventory) {
                for(int i = 0; i < item.getPurchasedAtFullPrice(); i++){
                    totalSales = totalSales.add(item.getPrice());
                }
                for(int i = 0; i < item.getPurchasedWithDiscount(); i++) {
                    totalSales = totalSales.add(item.getPrice());
                    totalSales = totalSales.subtract(new BigDecimal("1.00"));
                }
                writer.println(item.getCandyName() + "|" + item.getPurchasedAtFullPrice() + "|" + item.getPurchasedWithDiscount());
            }
            writer.println("TOTAL SALES " + totalSales);
        }
    }
}
