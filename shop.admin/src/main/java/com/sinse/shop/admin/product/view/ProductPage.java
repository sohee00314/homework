package com.sinse.shop.admin.product.view;

import java.awt.Color;
import java.awt.Dimension;

import com.sinse.shop.admin.AppMain;
import com.sinse.shop.admin.commom.view.Page;

public class ProductPage extends Page{
	
	public ProductPage(AppMain appMain) {
		super(appMain);
		
		Color bcf = Color.decode("#C9EBCF");
		setBackground(bcf);
		setPreferredSize(new Dimension(500,700));
		
	}
}
