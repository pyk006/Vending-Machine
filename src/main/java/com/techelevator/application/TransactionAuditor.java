package com.techelevator.application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TransactionAuditor {
    private File auditInventory = new File("audit.txt");
    private LocalDateTime date;


    public TransactionAuditor() throws FileNotFoundException {

    }



    public void audit() throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(auditInventory, true))) {
            writer.println(LocalDateTime.now() + "HELLO-TEST");
        }

    }
}
