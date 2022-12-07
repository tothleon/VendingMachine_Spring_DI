package org.app.ui;

import org.app.dto.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface UserUIimplInterface {
    void printMenu(List<Item> items);

    void printErrorMessage(String errorMesage);

    BigDecimal getMoneyInput();

    String getSelectedItem();

    void printChange(Map<String, Integer> change);

    void printChangePrompt();
}
