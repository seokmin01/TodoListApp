package com.todo.service;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

import java.io.Writer;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[할 일 추가]\n"
				+ "제목 > ");
		
		title = sc.nextLine().trim();
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
		
		String title = sc.nextLine().trim();
		
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
		
		String title = sc.nextLine().trim();
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
		System.out.println("[전체 목록]");
		for (TodoItem item : l.getList()) {
			System.out.println("[" + item.getTitle() + "] " + item.getDesc() + " - " + item.getCurrent_date());
		}
	}
	
	public static void saveList(TodoList l, String fileName) {
		try {
			Writer w = new FileWriter(fileName);

			for (TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();

			System.out.println("모든 할 일 목록이 저장되었습니다.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadList(TodoList l, String fileName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			int count = 0;
			String todoLine;
			while((todoLine = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(todoLine, "##");
				String title = st.nextToken();
				String desc = st.nextToken();
				String date = st.nextToken();

				TodoItem t = new TodoItem(title, desc, date);
				l.addItem(t);
				count++;
			}
			br.close();
			System.out.println(count + "개의 항목을 읽었습니다.");
		} catch (FileNotFoundException e) {
			System.out.println(fileName + "라는 파일이 존재하지 않습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
