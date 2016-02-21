package ru.java2;

import javax.swing.*;

public class Main {

	public static void main(String[] args) throws Exception{
		JFrame f = new JFrame("Java F1");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		f.setSize(1200, 700);
		f.add(new Road());
		f.setVisible(true);
		
	}

}
