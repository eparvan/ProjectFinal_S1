package org.example.presentation.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<MenuItem> menuItems = new ArrayList<>();
    public void addMenuItem(String name, MenuAction action){
        menuItems.add(new MenuItem(name, action));

    }
    public void onMenuItemSelected(int item){
        MenuItem menuItem = menuItems.get(item);
        if(menuItem == null){
            System.out.println("Nu exista asa optiune!");
            printMenu();
        }else{
            menuItem.getAction().execute();
        }
    }

    public void printMenu() {
        System.out.println("Menu");
        for(int i = 0; i< menuItems.size(); i++){
            System.out.println(i + " " + menuItems.get(i).getName());
        }
    }
}
