package com.sinse.shop.admin.commom.view;

import javax.swing.JPanel;

import com.sinse.shop.admin.AppMain;

public class Page extends JPanel{
	protected AppMain appMain;
	public Page(AppMain appMain) {
		this.appMain = appMain;
		setVisible(false);
	}
}
