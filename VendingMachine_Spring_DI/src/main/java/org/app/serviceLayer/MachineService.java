package org.app.serviceLayer;

import org.app.dto.Change;
import org.app.dto.Item;
import org.app.dao.DaoImpl;
import org.app.dao.Logger;
import org.app.dao.VendingMachineDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component
public class MachineService implements MachineServiceInterface {
    private DaoImpl dao;
    private Logger logger;

    @Autowired
    public MachineService(DaoImpl dao, Logger logger) {
        this.dao = dao;
        this.logger = logger;
    }

    @Override
    public List<Item> getItems() throws VendingMachineDataException, IOException {
        //gets items from the Candy.txt
        return dao.getAllItems();
    }


    @Override
    public Item getItem(int selectedItem) throws VendingMachineDataException {
        return dao.getItem(selectedItem);
    }

    @Override
    public Map<String, Integer> getChange(BigDecimal balance) {
        return Change.calculateChange(balance);
    }

    @Override
    public void updateItemStock(Item item) throws VendingMachineDataException, IOException {
        dao.updateItemStock(item);
    }


    @Override
    public void log(Item item, BigDecimal userInputMoney) throws VendingMachineDataException, IOException {
        logger.log(item, userInputMoney);
    }
}
