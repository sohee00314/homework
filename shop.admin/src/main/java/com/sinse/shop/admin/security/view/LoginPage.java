package com.sinse.shop.admin.security.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sinse.shop.admin.AppMain;
import com.sinse.shop.admin.commom.config.Config;
import com.sinse.shop.admin.commom.view.Page;

public class LoginPage extends Page{
	JLabel la_id;
	JLabel la_pass;
	JTextField t_id;
	JTextField t_pass;
	JButton bt_login;
	JButton bt_join;
	
	
	public  LoginPage(AppMain appMain) {
		super(appMain);
		
		la_id = new JLabel("ID");
		la_pass = new JLabel("Password");
		t_id = new JTextField();
		t_pass = new JTextField();
		bt_join = new JButton("Sing out");
		bt_login = new JButton("Login");
		
		
		// 스타일
		
		la_id.setBounds(20,30,90,25);
		la_pass.setBounds(20,85,95,25);
		t_id.setBounds(125,27,240,30);
		t_pass.setBounds(125,81,240,30);
		bt_join.setBounds(87, 120, 80, 30);
		bt_login.setBounds(241, 120, 80, 30);
		
		Color cee = Color.decode("#CEE6AF");
		setBackground(cee);
		setLayout(null);
		Color dd = Color.decode("#D9D9D9");
		bt_join.setBackground(dd);
		bt_login.setBackground(dd);
		
		la_id.setFont(la_id.getFont().deriveFont(20f));
		la_pass.setFont(la_pass.getFont().deriveFont(20f));
		
		
		
		//조립
		add(la_id);
		add(t_id);
		add(la_pass);
		add(t_pass);
		add(bt_join);
		add(bt_login);
		
		// 이벤트 부여
		//JoinPage로 이동
		bt_join.addActionListener(e->{
			appMain.showPage(Config.Join_Page);
		});
		
		
		
		setPreferredSize(new Dimension(400, 200));
	}
}
