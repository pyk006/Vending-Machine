package com.techelevator.application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TransactionAuditor {
    private File auditInventory = new File("audit.txt");
    private LocalDateTime date;


    public TransactionAuditor() {

    }



    public void audit() throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(auditInventory, true))) {
            // 01/01/2022 12:00:00 PM MONEY FED:          $5.00   $5.00
            DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm:ss a ");
            writer.println(LocalDateTime.now().format(format) + "HELLO-TEST");
        }

    }
}
