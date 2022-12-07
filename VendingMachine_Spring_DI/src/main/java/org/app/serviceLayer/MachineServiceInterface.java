package org.app.serviceLayer;

import org.app.dao.VendingMachineDataException;
import org.app.dto.Item;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface MachineServiceInterface {
    List<Item> getItems() throws VendingMachineDataException, IOException;

    Item getItem(int selectedItem) throws VendingMachineDataException;

    Map<String, Integer> getChange(BigDecimal balance);

    void updateItemStock(Item item) throws VendingMachineDataException, IOException;

    void log(Item item, BigDecimal userInputMoney) throws VendingMachineDataException, IOException;
}
