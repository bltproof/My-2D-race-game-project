package ru.java2;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Enemy {
	
	public static final int MAX_TOP = 300;
	public static final int MAX_BOTTOM = 580;


	int x;
	int y;
	int v;
	
	Image img = new ImageIcon("res/rival.png").getImage();
	Road road;
	
	public Rectangle getRect(){
		return new Rectangle(x, y, 150, 30);
	}
	
	public Enemy(int x, int y, int v, Road road){
		this.x = x;
		this.y = y;
		this.v = v;
		this.road = road;
	}
	
	public void move(){
		x = x - road.player.v + v;
		
		if (y <= MAX_TOP) y = MAX_TOP;
		if (y >= MAX_BOTTOM) y = MAX_BOTTOM;
	}
}
