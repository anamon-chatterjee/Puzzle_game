import java.awt.*;
import java.awt.event.*;
import java.applet.*;
public class GameOver extends Frame
{
	Label l1,l2,l3,l4,l5;
	Button b1,b2;
	GamePuzzle game;
	public GameOver(GamePuzzle game)
	{
		setResizable(false);
		this.game=game;
		Font font=new Font("Arial",Font.BOLD,32);
		l1=new Label("Game Over",Label.CENTER);
		l1.setFont(font);
		l2=new Label("Moves:-",Label.CENTER);
		l3=new Label(""+game.move);
		l4=new Label("Time:-",Label.CENTER);
		l5=new Label(""+game.l5.getText()+":"+game.l7.getText());
		b1=new Button("Play Again");
		b1.addActionListener(new MyButton());
		b2=new Button("Quit");
		b2.addActionListener(new MyButton());
		Panel p1=new Panel();
		p1.setLayout(new GridLayout(3,2,20,20));
		p1.add(l2);p1.add(l3);p1.add(l4);p1.add(l5);p1.add(b1);p1.add(b2);
		setLayout(new BorderLayout());
		add(l1,BorderLayout.CENTER);
		add(p1,BorderLayout.SOUTH);
	}
	public class MyButton implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			String str=ae.getActionCommand();
			if(str.equals("Quit"))
			{
				System.exit(0);
			}
			else
			{
				setVisible(false);
				game.setVisible(false);
				GamePuzzle game=new GamePuzzle();
				game.setSize(500,500);
				game.setVisible(true);
				game.setLocationRelativeTo(null);
			}
		}
	} 
	/*public static void main(String args[])
	{
		GameOver ob=new GameOver();
		ob.setSize(300,200);
		ob.setVisible(true);
		ob.setLocationRelativeTo(null);
	}*/
}