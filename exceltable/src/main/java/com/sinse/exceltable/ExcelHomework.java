package com.sinse.exceltable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jdk.internal.classfile.impl.Options.Key;

public class ExcelHomework extends JFrame{
	JPanel p_north;
	JButton bt_load;
	JButton bt_set;
	JButton bt_del;
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
		bt_load = new JButton("파일찾기");
		bt_set = new JButton("DB로 보내기");
		bt_del = new JButton("모든 데이터 삭제");
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
			// DB에 insert 버튼
			PreparedStatement pstmt;
			@Override
			public void actionPerformed(ActionEvent e) {	
				
				StringBuffer sql = new StringBuffer();
				sql.append("insert into userexcel(id,pwd,name,email) values(?,?,?,?)");
				try {
					pstmt = con.prepareStatement(sql.toString());
					for(int i=0; i<model.data.size(); i++) {
						List<String>row = model.data.get(i);
						if(row !=null && row.size()>=4) {
							for(int a =0;a<4; a++) {
								pstmt.setString(a+1,row.get(a));
							}
							pstmt.executeUpdate();
							
						}
					}
					JOptionPane.showMessageDialog(bt_set,"데이터베이스에 추가 성공");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					if(pstmt !=null) {
						try {
							pstmt.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				
			}
		});
		
		bt_del.addActionListener(new ActionListener() {
			// 테이터베이스 table 내용 삭제
			public void actionPerformed(ActionEvent e) {
				String sql = "TRUNCATE TABLE userexcel";
				PreparedStatement pstmt = null;
				try {
					
					int result = JOptionPane.showConfirmDialog(
						   bt_del,
						    "정말 전체를 삭제하시겠습니까?",
						    "삭제 확인",
						    JOptionPane.YES_NO_OPTION,
						    JOptionPane.INFORMATION_MESSAGE
						);

						if (result == JOptionPane.YES_OPTION) {
							pstmt = con.prepareStatement(sql);
							pstmt.executeUpdate();
						}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				finally {
					try {
						pstmt.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
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
		p_north.add(bt_del);
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
					XSSFRow row = sheet.getRow(0);
					this.model = new DataModel();
					
					model.title = new ArrayList<>();
					
					// 타이틀내용 담기
					for(int i=0;i<row.getLastCellNum();i++) {
						XSSFCell c = row.getCell(i);
						model.title.add(c.getStringCellValue());
					}
					
					for(int i=sheet.getFirstRowNum()+1;i<=sheet.getLastRowNum();i++) {
						XSSFRow r= sheet.getRow(i);
						List<String> datarow = new ArrayList<String>();
						if(r !=null) {
							for(int j =0; j<r.getLastCellNum();j++) {
								XSSFCell cell = r.getCell(j);
								datarow.add(cell.getStringCellValue());
							}
						}
						model.data.put(i-1, datarow);
						
					}
					table.setModel(model);
					table.updateUI();
					
				} catch (InvalidFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				// cell 한개만 수정하고 업데이트
				
				table.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						if(e.getKeyCode()== KeyEvent.VK_ENTER) {
							
							e.consume();
							
							int row = table.getSelectedRow();
							int col = table.getSelectedColumn();
							
							System.out.println(row);
							
							PreparedStatement pstmt = null;
							StringBuffer sql = new StringBuffer();
							sql.append("UPDATE userexcel SET pwd=?, name=?, email=? WHERE id=?");
							try {
								pstmt = con.prepareStatement(sql.toString());
								for(int i=0; i<4; i++) {
									pstmt.setString(i+1,table.getValueAt(row,i).toString());
								}
								pstmt.setString(4, table.getValueAt(row, 0).toString());
								int result = pstmt.executeUpdate();
								if(result !=0) {
									System.out.println("업데이트 성공");
								}else {
									System.out.println("업데이트 실패");
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							finally {
								try {
									pstmt.close();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							
						}
					}
				});	
		
		
	}
	
	public static void main(String[] args) {
		new ExcelHomework();
	}

}
