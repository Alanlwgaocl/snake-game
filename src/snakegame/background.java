package snakegame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.TargetDataLine;
import javax.swing.ImageIcon;
//import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


public class background extends JPanel implements KeyListener, ActionListener{
	
	//
	
	ImageIcon body = new ImageIcon("src/circle.png");
	ImageIcon UP = new ImageIcon("src/up.png");
	ImageIcon DOWN = new ImageIcon("src/down.png");
	ImageIcon LEFT = new ImageIcon("src/left.png");
	ImageIcon RIGHT = new ImageIcon("src/right.png");
	ImageIcon food = new ImageIcon("src/food.png");
	ImageIcon bgimage = new ImageIcon("src/pause.jpg");
	
	int length = 3;
	int[] xsnake = new int[700];
	int[] ysnake = new int[700];
	
	String snakedir = "right";
	boolean isStarted = false;
	boolean isFailed = false;
	
	//timer 
	int speed =100;
	
	Timer timer = new Timer(speed,this);
	
	
	
	int xfood;
	int yfood;
	Random r = new Random();
	
	
	Clip clip;
	//constructor
	public background() {
		initsnake();
		this.setFocusable(true);
		this.addKeyListener(this);
		timer.start();
		bgmSound("src/music1.wav");
	
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.white);
		
		//title 
		g.setColor(Color.blue);
		g.setFont(new Font("sans-serif",Font.BOLD,40));
		g.drawString("Snake Game", 300, 50);
		g.setColor(Color.yellow);
		g.fillRect(30, 80, 750, 660);
		g.setColor(Color.black);
		//g.fillRect(25, 75, 850, 600);
		for(int i =0;i<24;i++) {
			g.drawLine(30, 80+30*i, 780, 80+30*i);
		}
		for(int i =0;i<27;i++) {
			g.drawLine(30+30*i, 80, 30+30*i, 740);
		}
		
//		 g.drawLine(0, 50*i, 900, 50*i);
		
//		RIGHT.paintIcon(this, g, 100, 100);
//		body.paintIcon(this, g, 75, 100);
//		body.paintIcon(this, g, 50, 100);
		switch (snakedir) {
		case "right":
			RIGHT.paintIcon(this, g, xsnake[0], ysnake[0]);
			break;
		case "left":
			LEFT.paintIcon(this, g, xsnake[0], ysnake[0]);
			break;
		case "up":
			UP.paintIcon(this, g, xsnake[0], ysnake[0]); 
			break;
		case "down":
			DOWN.paintIcon(this, g, xsnake[0], ysnake[0]);
			break;
			
			
		}
		for(int i = 1;i<length;i++) {
			body.paintIcon(this, g, xsnake[i], ysnake[i]);
		}
		if(isStarted == false) {
			g.setColor(Color.white);
			g.fillRect(0, 0, 810, 800);
			bgimage.paintIcon(this, g, 130, 200); 
			g.setColor(Color.blue);
			g.setFont(new Font("sans-serif",Font.BOLD,40));
			g.drawString("Press space to start game", 150, 320);
		}
		if(isFailed == true) {
			
			g.setColor(Color.red);
			g.setFont(new Font("sans-serif",Font.BOLD,40));
			
			g.drawString("Failed ,press space, try agian!", 150, 300);
			
		}
		
		g.setColor(Color.black);
		g.setFont(new Font("sans-serif",Font.BOLD,20));
		g.drawString("Score: "+(length*10-30), 650, 40);
		g.setColor(Color.blue); 
		
		if(isStarted) {
			g.drawRect(640, 10, 130, 50);
			
			food.paintIcon(this, g, xfood, yfood);
			g.setColor(Color.black);
			g.setFont(new Font("sans-serif",Font.BOLD,12));
			g.drawString("Up:    Press 🡩", 10, 20);
			g.drawString("Down:  Press 🡣", 10, 30);
			g.drawString("Left:  Press 🡠", 10, 40);
			g.drawString("Right: Press 🡢", 10, 50);
		}
	}
	public void initsnake() {
		length = 3;
		xsnake[0] = 120;
		ysnake[0] = 110;
		xsnake[1] = 90;
		ysnake[1] = 110;
		xsnake[2] = 60;
		ysnake[2] = 110;
		
		xfood = 30+ 30*r.nextInt(24);
		yfood = 80+ 30*r.nextInt(21);
		snakedir="right";
		
	}
	
	public void makeSound(String file){
	    File lol = new File(file);
	 
	    try{
	        Clip clip1 = AudioSystem.getClip();
	        clip1.open(AudioSystem.getAudioInputStream(lol));
	        
	        clip1.start();
//	        Clip bgm = AudioSystem.getClip();
//	        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("src/eat.wav");
//	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
//	        bgm.open(audioInputStream);
//	        bgm.start();
	        
	    } catch (Exception e){
	        e.printStackTrace(); 
	    }
	}
	public void bgmSound(String file){
	    File lol = new File(file);
	 
	    try{
	        clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(lol));
	        
	        //clip.loop(20);
//	        Clip bgm = AudioSystem.getClip();
//	        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("src/eat.wav");
//	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
//	        bgm.open(audioInputStream);
//	        bgm.start();
	        
	    } catch (Exception e){
	        e.printStackTrace();
	    }
	}
	public void playbgm (){
		clip.loop(20);
	}
	public void stopbgm() {
		clip.stop();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_SPACE) {
			isStarted = !isStarted;
			if(isFailed) {
				isFailed = false;
				stopbgm();
				initsnake();
			}else {
				if(isStarted) {
					playbgm();
				}else{
					stopbgm();
					
				}
			}
			repaint(); 
			
		} 
		switch (keyCode) {
		case KeyEvent.VK_LEFT: 
			if(snakedir != "right") snakedir = "left";
			break;
			
		
		case KeyEvent.VK_RIGHT:
			if(snakedir !="left")snakedir = "right";
			break;
		
		case KeyEvent.VK_UP: 
			if(snakedir != "down")snakedir = "up";
			break;
		
		case KeyEvent.VK_DOWN: 
			if(snakedir !="up")snakedir = "down";
			break;
		default:
			
		}
		 
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if(isStarted && !isFailed) {
			
			for(int i=length-1;i>0;i--) {
				xsnake[i]=xsnake[i-1];
				ysnake[i]=ysnake[i-1];
				
				
			}
			
			if(snakedir == "right") {
				
				
				xsnake[0]=xsnake[0]+30;
				if(xsnake[0]>750) {
					xsnake[0]=30;
				}
			}else if(snakedir == "left") {
				xsnake[0]=xsnake[0]-30;
				if(xsnake[0]<30) {
					xsnake[0]=750;
				}
			}else if (snakedir == "up") {
				ysnake[0]=ysnake[0]-30;
				if(ysnake[0]<80) {
					ysnake[0]=710;
				}
			}else if (snakedir == "down") {
				ysnake[0]=ysnake[0]+30; 
				if(ysnake[0]>710) {
					ysnake[0]=80;
				}
			}
			if(xsnake[0] == xfood && ysnake[0] == yfood) {
				makeSound("src/eat.wav");
				length++;
				if(length>5) {
					speed = 100;
					
				}
				//System.out.println(length+" "+speed);
				
				xfood = 30+ 30*r.nextInt(25); 
				yfood = 80+ 30*r.nextInt(22);
			}
			for(int i=1; i<length;i++) {
				if(xsnake[0]==xsnake[i] && ysnake[0]==ysnake[i]) {
					isFailed = true;
					   
					if(isFailed) {
						stopbgm();
						makeSound("src/gameover.wav");
					}
					
				}
			}
	
			repaint();
		}
		
		timer.start();  
		
		
	}

}
