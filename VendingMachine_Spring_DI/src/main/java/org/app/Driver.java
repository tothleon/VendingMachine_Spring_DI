package org.app;

import org.app.controller.Controller;
import org.app.dao.DaoImpl;
import org.app.dao.Logger;
import org.app.dao.VendingMachineDataException;
import org.app.serviceLayer.MachineService;
import org.app.ui.UserUIimpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Driver {

    public static void main(String[] args) throws VendingMachineDataException, IOException {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("org.app");
        appContext.refresh();

        Controller controller = appContext.getBean("controller", Controller.class);
        controller.run();
    }
//    public static void main(String[] args) throws VendingMachineDataException, IOException {
//        UserUIimpl ui = new UserUIimpl();
//        Logger logger = new Logger();
//        DaoImpl dao = new DaoImpl();
//
//        MachineService service = new MachineService(dao, logger);
//
//        Controller controller = new Controller(ui, service);
//
//        controller.run();
//    }
}