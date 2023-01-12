package org.example.presentation.menu;

public class MenuItem {
    private String name;
    private MenuAction action;
    public MenuItem(String name, MenuAction action){
        this.name = name;
        this.action = action;
    }
    public String getName(){ return name;}
    public MenuAction getAction(){return action;}
}
