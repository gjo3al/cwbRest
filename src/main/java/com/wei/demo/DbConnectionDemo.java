package com.wei.demo;

import com.wei.urlbuilder.CwbRestURLBuilder;

public class DbConnectionDemo {

	public static void main(String[] args) {
		CwbRestURLBuilder builder= new CwbRestURLBuilder("新竹市", 7);
		
		System.out.println(builder.getCurrentUrl());
	}

}
