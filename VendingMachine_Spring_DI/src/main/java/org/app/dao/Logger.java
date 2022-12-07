package org.app.dao;

import org.app.dto.Item;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class Logger {

    public void log(Item item, BigDecimal userInputMoney) throws VendingMachineDataException, IOException {
        File fileNameOut = new File("VendingMachineLogs.txt");
        PrintWriter pv;
        try {
            FileWriter fw = new FileWriter(fileNameOut, true);
            pv = new PrintWriter(fw);
        } catch (IOException e) {
            throw new VendingMachineDataException(
                    "Save Error..."
            );
        }
        LocalDateTime timestemp = LocalDateTime.now();
        String temp = timestemp.toString() + " - bought: "
                + "ID: " + item.getID() + ", " +
                "Name: " + item.getName() + ", " +
                "Price in EUR: " + item.getCost() + ", " +
                "Inventory left: " + item.getInventory() + ", " +
                "Amount put in: " + userInputMoney + ".\n";
        pv.write(temp);
        pv.flush();
        pv.close();
    }
}
