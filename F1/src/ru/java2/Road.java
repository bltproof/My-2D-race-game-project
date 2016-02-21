package ru.java2;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Road extends JPanel implements ActionListener, Runnable{
	
	Timer mainTimer = new Timer(20, this);

		Image img = new ImageIcon("res/road.png").getImage();
		
		Player player = new Player();
		
		Thread enemiesFactory = new Thread(this);
		
		List<Enemy> enemies = new ArrayList<Enemy>();
		
		public Road(){
			mainTimer.start();
			enemiesFactory.start();
			addKeyListener(new MyKeyAdapter());
			setFocusable(true);
		}
		private class MyKeyAdapter extends KeyAdapter{
			public void keyPressed(KeyEvent e){
				player.keyPressed(e);
			}
			public void keyReleased(KeyEvent e){
				player.keyReleased(e);
			}
		}
		
		public void paint(Graphics g){
			g = (Graphics2D) g;
			g.drawImage(img, player.layer1, 0, null);
			g.drawImage(img, player.layer2, 0, null);
			g.drawImage(player.img, player.x, player.y, null);
			
			Iterator <Enemy> i = enemies.iterator();
			while(i.hasNext()){
				Enemy e = i.next();
				if(e.x >= 2400 || e.x <= -2400){
					i.remove();
				}else{
					e.move();
					g.drawImage(e.img, e.x, e.y, null);
				}
			}
		}
		public void actionPerformed(ActionEvent e){
			player.move();
			repaint();
			testCollisionWithEnemies();
		}
		private void testCollisionWithEnemies() {
			Iterator <Enemy> i = enemies.iterator();
			while (i.hasNext()){
				Enemy e = i.next();
				if (player.getRect().intersects(e.getRect())){
					JOptionPane.showMessageDialog(null, "Куда едешь, сука!");
					System.exit(1);
				}
			}
			
		}
		@Override
		public void run() {
			while(true){
				Random rand = new Random();
				try {
					Thread.sleep(rand.nextInt(2000));
					enemies.add(new Enemy(1200, rand.nextInt(600), rand.nextInt(20), this));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
}
