package org.academiadecodigo.javabank;

import org.academiadecodigo.javabank.controller.menu.MainMenuController;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("spring/config.xml")
                .getBean(MainMenuController.class, "mainMenuController")
                .execute();
    }


}
