package com.sinse.shop.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.sinse.shop.admin.commom.view.Page;
import com.sinse.shop.admin.member.view.JoinPage;
import com.sinse.shop.admin.member.view.LoginPage;

public class AppMain extends JFrame{
	JPanel p_north;
	JLabel la_user;
	JPanel p_center;
	JPanel p_west;
	JLabel la_product;
	JLabel la_order;
	JLabel la_member;
	JLabel la_cs;
	JLabel la_setting;
	
	Page[]pages; // 페이지들을 받을 배열
	
	public AppMain() {
		p_north = new JPanel();
		la_user = new JLabel("#user");
		p_center = new JPanel();
		p_west = new JPanel();
		la_product = new JLabel("Product");
		la_order = new JLabel("Order");
		la_member = new JLabel("Member");
		la_cs = new JLabel("CS");
		la_setting = new JLabel("Setting");
		
		// 스타일
		Dimension d = new Dimension(200,40);
		p_west.setLayout(null);
		la_product.setBounds(60, 50, 200, 40);
		la_order.setBounds(60, 150, 200, 40);
		la_member.setBounds(60, 250, 200, 40);
		la_cs.setBounds(60, 350, 200, 40);
		la_setting.setBounds(60, 450, 200, 40);
		
		la_product.setFont(la_product.getFont().deriveFont(30f));
		la_order.setFont(la_order.getFont().deriveFont(30f));
		la_member.setFont(la_member.getFont().deriveFont(30f));
		la_cs.setFont(la_cs.getFont().deriveFont(30f));
		la_setting.setFont(la_setting.getFont().deriveFont(30f));
		
		p_north.setPreferredSize(new Dimension(1300,50));
		p_west.setPreferredSize(new Dimension(200,750));
		
		p_center.setBackground(Color.white);
		Color cee = Color.decode("#CEE6AF");
		p_west.setBackground(cee);
		
		p_north.setLayout(new BorderLayout());
		
		// 조입
		p_north.add(la_user,BorderLayout.CENTER);
		add(p_north,BorderLayout.NORTH);
		add(p_center);
		p_west.add(la_product);
		p_west.add(la_order);
		p_west.add(la_member);
		p_west.add(la_cs);
		p_west.add(la_setting);
		add(p_west,BorderLayout.WEST);
		
		createPage();
		showPage();
		setSize(1300, 800);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// 패이지 생성
	public void createPage() {
		pages = new Page[2];
		pages[0] = new LoginPage(this);
		pages[1] = new JoinPage(this);
		
		for(int i =0; i<pages.length;i++) {
			p_center.add(pages[i]);
		}
		
	}
	
	// 페이지 보기
	public void showPage() {
		// 로그인 여부 확인 필요
	}
	
	public static void main(String[] args) {
		new AppMain();
	}
}
