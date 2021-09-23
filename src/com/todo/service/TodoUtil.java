package com.todo.service;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[할 일 추가]\n"
				+ "제목 > ");
		
		title = sc.next().trim();
		if (list.isDuplicate(title)) {
			System.out.printf("중복되는 제목입니다.");
			return;
		}
		
		System.out.print("내용 > ");
		desc = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[할 일 삭제]\n"
				+ "삭제할 할 일의 제목 > ");
		
		String title = sc.next().trim();
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[할 일 수정]\n"
				+ "수정할 할 일의 제목 > ");
		
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("해당 제목은 존재하지 않습니다.");
			return;
		}

		System.out.print("새로운 할 일의 제목 > ");
		String new_title = sc.nextLine().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("중복되는 제목입니다.");
			return;
		}
		
		System.out.print("새로운 할 일의 내용 > ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("수정되었습니다.");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("[할 일 목록]");
		for (TodoItem item : l.getList()) {
			System.out.println("[" + item.getTitle() + "] " + item.getDesc() + " - " + item.getCurrent_date());
		}
	}
}
