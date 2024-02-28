package com.todo.menu;

public class Menu {

    public static void displaymenu () {
        System.out.println();
        System.out.println("<Usage Guide for Managing ToDoList Commands>");
        System.out.println("add - Add item");
        System.out.println("del - Delete item");
        System.out.println("edit - Edit item");
        System.out.println("ls - Complete list");
        System.out.println("ls_name_asc - Sort by title");
        System.out.println("ls_name_desc - Sort by title in reverse order");
        System.out.println("ls_date - Sort by date");
        System.out.println("exit - Exit");
    }

    public static void prompt ()
    {
        System.out.println("\nCommand > ");
    }
}
