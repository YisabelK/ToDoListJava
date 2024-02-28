package com.todo.TodoList;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

import java.util.Scanner;

public class TodoMain {
    public static void start () {
        Scanner sc = new Scanner(System.in);
        TodoList l = new TodoList ();
        boolean isList = false;
        boolean quit = false;
        TodoUtil.loadList(l,"todolist.txt");

        Menu.displaymenu ();
        do {
            Menu.prompt();
            isList = false;
            String choice = sc.next();
            switch (choice) {
                case "add":
                    TodoUtil.createItem(l);
                    break;
                case "del":
                    TodoUtil.deleteItem(l);
                    break;
                case "edit":
                    TodoUtil.updateItem(l);
                    break;
                case "ls":
                    TodoUtil.listAll(l);
                    break;
                case "ls_name_asc":
                    l.sortByName();
                    System.out.println("Sorted by title.");
                    isList = true;
                    break;
                case "ls_name_desc":
                    l.sortByName();
                    l.reverseList();
                    System.out.println("Sorted in reverse order by title.");
                    isList = true;
                    break;
                case "ls_date":
                    l.sortByDate();
                    System.out.println("Sorted by date.");
                    isList = true;
                    break;
                case "help":
                    Menu.displaymenu();
                    break;
                case "exit":
                    quit = true;
                    break;
                default:
                    System.out.println("Please enter a valid command. (For help - type help)");
                    break;
            }
            if (isList) TodoUtil.listAll(l);
        } while (!quit);
        TodoUtil.saveList(l, "todolist.txt");
    }
}
