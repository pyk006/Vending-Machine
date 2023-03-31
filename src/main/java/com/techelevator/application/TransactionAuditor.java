package com.techelevator.application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TransactionAuditor {
    private File auditInventory = new File("audit.txt");
    private LocalDateTime date;


    public TransactionAuditor() throws FileNotFoundException {
        auditInventory = new File("audit.txt");
        PrintWriter writer = new PrintWriter(auditInventory);
        writer.flush();
        writer.close();

    }



    public void audit(String inputType, String moneyAmount, String currBalance) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(auditInventory, true))) {
            // 01/01/2022 12:00:00 PM MONEY FED:          $5.00   $5.00
            DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a ");
            // Making sure the dollar amounts are right for feed money

            writer.println(LocalDateTime.now().format(format) + " " + inputType + " " + moneyAmount + " " + currBalance);
        }

    }
}
