package snakegame;



import javax.swing.JFrame;


public class snake extends JFrame{
	public static void main(String []args){
		JFrame frame = new JFrame();
		background bg = new background();
		frame.setBounds(0,0,810,800);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(bg);
		//frame.add(new background()); //another way to new 

		
		
	}
	

}
