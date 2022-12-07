package org.app.ui;

import org.app.dto.Item;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
@Component
public class UserUIimpl implements UserUIimplInterface {

    //prints out menu from items
    @Override
    public void printMenu(List<Item> items) {
        System.out.println("********** MENU **********");
        items.forEach(i -> {
            System.out.println("-----------------------------\n" +
                      "| " + i.getID() + " - " +
                            i.getName() + "  " +
                            i.getCost() + "€  " +
                            i.getInventory() + " left |");
        });
    }
    
    @Override
    public void printErrorMessage(String errorMesage) {
        System.out.println(" ERROR ");
        System.out.println(errorMesage);
    }

    Scanner scan = new Scanner(System.in);

    @Override
    public BigDecimal getMoneyInput() {
        BigDecimal outputBigDecimal = null;
        boolean isInputValid = false;
        while(!isInputValid) {
            try {
                System.out.print("\nPlease input amount here: ");
                String input = scan.nextLine();
                outputBigDecimal = new BigDecimal(input);
                isInputValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Ivalid input. Enter just numbers");
            }
        }
        System.out.println("YOU PUT IN: " + outputBigDecimal);
        return outputBigDecimal;
    }

    @Override
    public String getSelectedItem() {
        System.out.print("Please select an Item or exit: ");
        return scan.nextLine();
    }

    @Override
    public void printChange(Map<String, Integer> change) {
        change.forEach((key, value) -> System.out.println(key + " : " + value));
    }
    @Override
    public void printChangePrompt(){
        System.out.println("\nYour change is:");
    }
}
