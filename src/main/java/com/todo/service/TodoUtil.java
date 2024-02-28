package com.todo.service;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TodoUtil {
    public static void createItem (TodoList list) {
        String title, desc;
        Scanner sc = new Scanner(System.in);

        System.out.println("[Add item]\n"
        + "Title > ");

        title = sc.next();
        if (list.isDuplicate(title)) {
            System.out.println("Title is already in use");
            return;
        }
        sc.nextLine();
        System.out.println("Content > ");
        desc = sc.nextLine().trim();

        TodoItem t = new TodoItem(title, desc);
        list.addItem(t);
        System.out.println("Added");
    }

    public static void deleteItem (TodoList l) {
        Scanner sc = new Scanner(System.in);

        System.out.println("[Delete item]\n"
             + "Please enter the title of the item to delete > ");
        String title = sc.next();

        for (TodoItem item : l.getList()) {
            if (title.equals(item.getTitle())) {
                l.deleteItem(item);
                System.out.println("Deleted");
                break;
            }
        }
    }

    public static void updateItem(TodoList l) {
        Scanner sc = new Scanner(System.in);

        System.out.println("[Edit item]\n"
                + "Please enter the title of the item to edit > ");
        String title = sc.next().trim();
        if (!l.isDuplicate(title)) {
            System.out.println("Title does not exist!");
            return;
        }

        System.out.println("New title > ");
        String new_title = sc.next().trim();
        if (l.isDuplicate(new_title)) {
            System.out.println("Title is already in use!");
            return;
        }
        sc.nextLine();
        System.out.println("New content > ");
        String new_description = sc.nextLine().trim();
        for (TodoItem item : l.getList()) {
            if (item.getTitle().equals(title)) {
                l.deleteItem(item);
                TodoItem t = new TodoItem(new_title, new_description);
                l.addItem(t);
                System.out.println("Updated");
            }
        }

    }

    public static void listAll (TodoList l) {
        System.out.println("[Complete list]");
        for (TodoItem item : l.getList()) {
            System.out.println(item.toString());
        }
    }

    public static void saveList(TodoList l, String filename) {
        Writer w = null;
        try {
            w = new FileWriter(filename);
            for (TodoItem item: l.getList()) {
                w.write(item.toSaveString());
            }
            w.close();
            System.out.println("All data has been saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadList (TodoList l, String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            int count = 0;
            while ((line = br.readLine()) !=null) {
                StringTokenizer st = new StringTokenizer(line, "##");
                String title = st.nextToken();
                String description = st.nextToken();
                String current_date = st.nextToken();
                TodoItem item = new TodoItem(title, description);
                item.setCurrent_date(current_date);
                l.addItem(item);
                count++;
            }
            br.close();
            System.out.println(count+"items found.");
        } catch (FileNotFoundException e) {
            System.out.println(filename+" does not exist.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
