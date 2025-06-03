package com.sinse.exceltable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHomework extends JFrame{
	JPanel p_north;
	JButton bt_load;
	JButton bt_set;
	JTable table;
	JScrollPane scroll;
	
	Connection con;
	JFileChooser chooser;
	DataModel model;
//	ExcelHomework excel;
	
	//DB
	String url = "jdbc:mysql://localhost:3306/dev";
	String user= "java";
	String pwd = "1234";
	
	public ExcelHomework() {
		// 버튼영역
		p_north = new JPanel();
		bt_load = new JButton("Load");
		bt_set = new JButton("Set");
		p_north.setPreferredSize(new Dimension(650, 50));
		p_north.setBackground(Color.yellow);
		
		chooser = new JFileChooser();
		
		// 이벤트
		bt_load.addActionListener(new ActionListener() {
			// 파일 로드버튼
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = chooser.showOpenDialog(ExcelHomework.this);
				if(result == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					excelLoad(file);
				}
				
				
			}
		});
		bt_set.addActionListener(new ActionListener() {
			// DB 로드 버튼
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
		// 창 닫으면 DB연결 해제
		public void windowClosing(WindowEvent e) {
			if(con != null){
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
			}
		}
		});
		
		p_north.add(bt_load);
		p_north.add(bt_set);
		add(p_north,BorderLayout.NORTH);
		
		// 테이블 영역
		table = new JTable();
		scroll = new JScrollPane(table);
		add(scroll);
		
		connect();
		
		setSize(650, 600);
		setVisible(true);
		
		
	}
	
	// DB연결
	public void connect() {
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection(url,user,pwd);
				if(con !=null) {
					System.out.println("접속 성공");
				}else {
					System.out.println("접속 실패");					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 엑셀 연결
	public void excelLoad(File file) {
		
				try {
					XSSFWorkbook workbook = new XSSFWorkbook(file); 
					
					XSSFSheet sheet = workbook.getSheetAt(0); // 첫번쨰 시트로 연결
					XSSFRow row = sheet.getRow(0); // 첫번째 row접근
					
				} catch (InvalidFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
	}
	
	public static void main(String[] args) {
		new ExcelHomework();
	}

}
