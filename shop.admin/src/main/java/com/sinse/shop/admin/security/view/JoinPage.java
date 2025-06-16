package com.sinse.shop.admin.security.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.sinse.shop.admin.AppMain;
import com.sinse.shop.admin.commom.view.Page;

public class JoinPage extends Page{
	JLabel la_id;
	JLabel la_pass;
	JLabel la_name;
	JLabel la_email;
	JTextField t_id;
	JTextField t_pass;
	JTextField t_name;
	JTextField t_email;
	JButton bt_join;
	JButton bt_login;
	
	public JoinPage(AppMain appMain) {
		super(appMain);
		
		la_id = new JLabel("ID");
		la_pass = new JLabel("Password");
		la_name = new JLabel("Name");
		la_email = new JLabel("Email");
		t_id = new JTextField();
		t_pass = new JTextField();
		t_name = new JTextField();
		t_email = new JTextField();
		bt_join = new JButton("Join");
		bt_login = new JButton("Login");
		
		// 스타일
		setLayout(null);
		Color cee = Color.decode("#CEE6AF");
		setBackground(cee);
		Color dd = Color.decode("#D9D9D9");
		bt_join.setBackground(dd);
		bt_login.setBackground(dd);
		
		la_id.setBounds(22,30,93,25);
		la_pass.setBounds(22,84,93,25);
		la_name.setBounds(22,144,93,25);
		la_email.setBounds(22,210,93,25);
		t_id.setBounds(125,27,240,30);
		t_pass.setBounds(125,83,240,30);
		t_name.setBounds(125,141,240,30);
		t_email.setBounds(125,207,240,30);
		bt_join.setBounds(83,260,80,30);
		bt_login.setBounds(237,260,80,30);
		
		la_id.setFont(la_id.getFont().deriveFont(20f));
		la_pass.setFont(la_pass.getFont().deriveFont(20f));
		la_name.setFont(la_name.getFont().deriveFont(20f));
		la_email.setFont(la_email.getFont().deriveFont(20f));
		
		
		// 조립
		add(la_id);
		add(t_id);
		add(la_pass);
		add(t_pass);
		add(la_name);
		add(t_name);
		add(la_email);
		add(t_email);
		add(bt_join);
		add(bt_login);
		
		setPreferredSize(new Dimension(400,340));
	}
}
