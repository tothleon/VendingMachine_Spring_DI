package org.app.dao;

import org.app.dto.Item;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface DaoInterface {

    public void loadItems() throws VendingMachineDataException, IOException, VendingMachineDataException, VendingMachineDataException;

    public Map<Integer, Item> getAllitemsInStock() throws VendingMachineDataException;
    public List<Item> getAllItems() throws VendingMachineDataException, IOException;
    public void saveItems() throws VendingMachineDataException, IOException;

    public Item getItem(int itemId) throws VendingMachineDataException;

    public void updateItemStock(Item item) throws VendingMachineDataException, IOException;


}
