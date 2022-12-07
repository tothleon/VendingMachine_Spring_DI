package org.app.controller;

import org.app.dao.VendingMachineDataException;
import org.app.dto.Item;
import org.app.serviceLayer.InventoryException;
import org.app.serviceLayer.MachineService;
import org.app.ui.UserUIimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

@Component
public class Controller {

    private UserUIimpl ui;
    private MachineService service;

    @Autowired
    public Controller(UserUIimpl ui, MachineService service) {
        this.ui = ui;
        this.service = service;
    }

    //kickstart the application
    public void run() throws VendingMachineDataException, IOException {
        BigDecimal userInputMoney;
        //takes string to selected item, to be able to exit the application wit exit string
        String selectedItem = "";
        //variable to store value of the id converted to int
        int idToInt = 0;
        try {
            //prints out the menu
            displayMenu();
        } catch (VendingMachineDataException e) {
            ui.printErrorMessage(e.getMessage());
        }

        while (true) {
            //gets user money
            userInputMoney = getMoneyInput();
            //checks if the inputted value is positive
            if (userInputMoney.compareTo(new BigDecimal(0)) > 0) {
                //if yes continues
                break;
            } else {
                //if not asks again
                System.out.println("Invalid input...");
            }
        }
        while (true) {
            try {
                //gets the input selected item id
                selectedItem = getSelectedItem();
                //checks if the input is "exit"
                if (selectedItem.equals("exit")) {
                    System.out.println("\nYou get back: ");
                    //calculates the number of dimes you get back
                    Map<String, Integer> change = service.getChange(userInputMoney);
                    //prints out the number of dimes you get back
                    ui.printChange(change);
                    //stops the application
                    break;
                }
                try {
                    //converts/checks is the user input id is integer
                    idToInt = Integer.parseInt(selectedItem);
                    //checks if item is in inventory
                if (service.getItem(idToInt).getInventory() < 1) {
                    System.out.println(service.getItem(idToInt).getName() +
                            " is out of stock, select another item.");
                } else {
                    //checks if the money input is enough to purchase the selected item
                    if (service.getItem(idToInt).getCost().compareTo(userInputMoney) < 1) {
                        //starts the purchase process
                        purchaseItem(idToInt, userInputMoney);
                        break;
                    } else {
                        System.out.println("Insufficient founds...");
                    }
                }
                } catch (Exception e) {
                    System.out.println("Invalid input...");
                }
            } catch (Exception e) {
                ui.printErrorMessage(e.getMessage());
                System.out.println("Invalid selection...");
            }
        }
    }

    private void purchaseItem(int idToInt, BigDecimal userInputMoney) throws VendingMachineDataException, IOException {
        //gets the selected item by inputted id
        Item itemToBePurchase = service.getItem(idToInt);
        //gets the number of change after getting the balance after purchase
        Map<String, Integer> change = service.getChange(userInputMoney.subtract(itemToBePurchase.getCost()));
        //removes 1 item from the stock of the selected item
        service.updateItemStock(itemToBePurchase);
        //writes the transaction to the log file
        service.log(service.getItem(idToInt),userInputMoney);
        //prints out the change
        ui.printChangePrompt();
        ui.printChange(change);

        System.out.println("\nEnjoy your " + itemToBePurchase.getName());
    }


    private String getSelectedItem() {
        //print out prompt and takes user input
        return ui.getSelectedItem();
    }

    private BigDecimal getMoneyInput() {
        //print out prompt and takes user input
        return ui.getMoneyInput();
    }

    public void displayMenu() throws VendingMachineDataException, IOException {
        //ui prints out menu from a array list.
        ui.printMenu(service.getItems());
    }

}
