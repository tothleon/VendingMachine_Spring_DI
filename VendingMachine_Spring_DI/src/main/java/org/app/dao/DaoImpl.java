package org.app.dao;

import org.app.dto.Item;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class DaoImpl implements DaoInterface{

    private HashMap<Integer, Item> items = new HashMap<>();

    private final String PATH = "Candy.txt";

    private final String DELIMITER = "::";

    @Override
    public void loadItems() throws VendingMachineDataException {
        File file = new File(PATH);
        FileReader fileReader = null;
        String[] line;
        String currentLine;

        try {
            fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            currentLine = reader.readLine();

        while (currentLine != null) {
            line = currentLine.split(DELIMITER);

            int id = Integer.parseInt(line[0]);
            String itemName = line[1];
            BigDecimal cost = new BigDecimal(line[2]);
            int inventory = Integer.parseInt(line[3]);

            Item temp = new Item(id, itemName, cost, inventory);
            items.put(temp.getID(), temp);
            //Iterates
            currentLine = reader.readLine();
        }
        } catch (IOException e) {
            throw new VendingMachineDataException(
                    "File load error...", e
            );
        }
    }
    @Override
    public Map<Integer, Item> getAllitemsInStock() {
        return items.entrySet()
                .stream()
                .filter(map -> map.getValue().getInventory() > 0)
                .collect(Collectors.toMap(item -> item.getKey(), item -> item.getValue()));
    }

    @Override
    public List<Item> getAllItems() throws VendingMachineDataException, IOException {
        loadItems();
        return new ArrayList<>(items.values());
    }

    @Override
    public void saveItems() throws VendingMachineDataException, IOException {
        File fileNameOut = new File(PATH);
        BufferedWriter bw;
        try {
            FileWriter fw = new FileWriter(fileNameOut);
            bw = new BufferedWriter(fw);
        } catch (IOException e) {
            throw new VendingMachineDataException(
                    "Save Error..."
            );
        }
        List<Item> itemList = new ArrayList<>(items.values());
        itemList.forEach(item -> {
            String temp = item.getID() + DELIMITER +
                    item.getName() + DELIMITER +
                    item.getCost() + DELIMITER +
                    item.getInventory() + "\n";

            try {
                bw.write(temp);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        bw.flush();
        bw.close();
    }

    @Override
    public Item getItem(int itemId) throws VendingMachineDataException {
        return items.get(itemId);
    }

    @Override
    public void updateItemStock(Item item) throws VendingMachineDataException, IOException {
        item.setInventory(item.getInventory() - 1);
        items.put(item.getID(), item);
        saveItems();
    }
}
