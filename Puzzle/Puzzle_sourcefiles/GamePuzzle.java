import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
public class GamePuzzle extends Frame implements Runnable
{
	int move=0,game_end;
	Button cell[][]=new Button[4][4];
	Button b1,b2,b3;
	Label l1,l2,l3,l4,l5,l6,l7;
	GamePuzzle ob;
	int number[][]={{1,2,3,4},{5,6,7,8},{9,10,11,12},{14,15,13,999}};
	int check[]=new int[16];
	Random ran=new Random();
	Thread t;
	public GamePuzzle()
	{
		super("puzzle");
		ob=this;
		setResizable(false);
		/*{
			for(int j=0;j<4;j++)
			{
				int k=0;
				while(k<4)
				{
					int value=1+ran.nextInt(15);
					if(j==3&&k==3)
					{	
						number[3][3]=999;
						break;
					}
					else
					{
						if(check[value]==0)
						{
							System.out.println(value);
							System.out.println("prev:"+check[value]);
							number[j][k]=value;
							k++;
							check[value]=1;
							System.out.println("after:"+check[value]);
						}
					}
				}
			}
		}*/
		
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
		{
			cell[i][j]=new Button(""+number[i][j]);
			cell[i][j].addActionListener(new MyButton());
			cell[i][j].setEnabled(false);
		}
			cell[3][3].setLabel("");
			b1=new Button("play");
			b1.addActionListener(new MyButton());
			b2=new Button("refresh");
			b2.addActionListener(new MyButton());
			b2.setEnabled(false);
			b3=new Button("new game");
			b3.addActionListener(new MyButton());
			b3.setEnabled(false);
			l1=new Label("moves");
			l2=new Label(""+0);
			l3=new Label("validity");
			l4=new Label("N/A");
			l5=new Label(""+0,Label.RIGHT);
			l5.setForeground(Color.red);
			l6=new Label(":",Label.CENTER);
			l7=new Label(""+0);
			l7.setForeground(Color.red);
			Panel p=new Panel();
			Panel p1=new Panel();
			Panel p2=new Panel();
			p.setLayout(new GridLayout(4,4));
			for(int i=0;i<4;i++)
				for(int j=0;j<4;j++)
			{
				p.add(cell[i][j]);
			}
			p1.setLayout(new GridLayout(1,4));
			p1.add(l1);p1.add(l2);p1.add(l3);p1.add(l4);
			p2.setLayout(new GridLayout(1,6,20,20));
			p2.add(b1);p2.add(l5);p2.add(l6);p2.add(l7);p2.add(b2);p2.add(b3);
			setLayout(new BorderLayout());
			add(p,BorderLayout.CENTER);
			add(p1,BorderLayout.SOUTH);
			add(p2,BorderLayout.NORTH);
			addWindowListener(new MyWindow());
			t=new Thread(this);
			game_end=0;
	}
	public void run()
	{
		while(game_end==0)
		{
			if(l7.getText().equals("59"))
			{
				l7.setText(""+0);
				int minute=Integer.parseInt(l5.getText());
				minute++;
				l5.setText(""+minute);
			}
			else
			{
				try
				{
					int second=Integer.parseInt(l7.getText());
					second++;
					Thread.sleep(1000);
					l7.setText(""+second);
				}
				catch(Exception e)
				{

				}
			}
		}
	}
	public class MyButton implements ActionListener
	{
		int a,j,k,p,q;
		int flag;
		public void actionPerformed(ActionEvent ae)
		{
			if(ae.getActionCommand().equals("play"))
			{
				b1.setEnabled(false);
				for(int i=0;i<4;i++)
				for(int j=0;j<4;j++)
				{
					cell[i][j].setEnabled(true);
				}
				b2.setEnabled(true);
				b3.setEnabled(true);
				t.start();
			}
			else if(ae.getActionCommand().equals("new game"))
			{
				setVisible(false);
				GamePuzzle newgame=new GamePuzzle();
				newgame.setSize(500,500);
				newgame.setVisible(true);
				newgame.setLocationRelativeTo(null);
			}
			else if(ae.getActionCommand().equals("refresh"))
			{
				for(int i=0;i<4;i++)
				for(int j=0;j<4;j++)
				{
					cell[i][j].setLabel(""+number[i][j]);
				}
				cell[3][3].setLabel("");
			}
			else
			{
				flag=0;
				System.out.println("Inside method flag="+flag);
				int mat[][]=new int[4][4];
				for(int i=0;i<4;i++)
				for(int k=0;k<4;k++)
				{
					if(cell[i][k].getLabel()=="")
						mat[i][k]=999;
					else
					mat[i][k]=Integer.parseInt(cell[i][k].getLabel());
				}
				if(ae.getActionCommand().equals(""))
					a=999;
				else
				{
					a=Integer.parseInt(ae.getActionCommand());
				
					for(j=0;j<4;j++)
					{
						for(k=0;k<4;k++)
						{
							if(mat[j][k]==a)
							{
								if(k!=3)
								{
									if(mat[j][k+1]==999)
									{
										cell[j][k+1].setLabel(""+a);
										cell[j][k].setLabel("");
										mat[j][k+1]=a;
										mat[j][k]=999;
										move=move+1;
										l2.setText(""+move);
										l4.setForeground(Color.black);
										l4.setText("valid");
										//System.out.println("if k!=3");
										break;
									}
								}
								if(k!=0)
								{
									if(mat[j][k-1]==999)
									{
										cell[j][k-1].setLabel(""+a);
										cell[j][k].setLabel("");
										mat[j][k-1]=a;
										mat[j][k]=999;
										move=move+1;
										l2.setText(""+move);
										l4.setForeground(Color.black);
										l4.setText("valid");
										//System.out.println("if k!=0");
										break;
									}
								}
								if(j!=3)
								{
									if(mat[j+1][k]==999)
									{	
										cell[j+1][k].setLabel(""+a);
										cell[j][k].setLabel("");
										mat[j+1][k]=a;
										mat[j][k]=999;
										move=move+1;
										l2.setText(""+move);
										l4.setForeground(Color.black);
										l4.setText("valid");
										//System.out.println("if j!=3");
										break;
									}
								}
								if(j!=0)
								{
									if(mat[j-1][k]==999)
									{	
										cell[j-1][k].setLabel(""+a);
										cell[j][k].setLabel("");
										mat[j-1][k]=a;
										mat[j][k]=999;
										move=move+1;
										l2.setText(""+move);
										l4.setForeground(Color.black);
										l4.setText("valid");
										//System.out.println("if j!=0");
										break;
									}
								}
								l4.setForeground(Color.red);
								l4.setText("invalid");
								break;
							}
						}
						if(k<4)
						{
							break;
						}
					}
				
					for(p=0;p<4;p++)
					{
						for(q=0;q<3;q++)
						{
							if(mat[p][q]>mat[p][q+1])
							{
								flag=1;
								System.out.println("inside first loop");
								break;
							}
						}
						if(flag==1)
							break;
						else if(p!=3)
						{
							if(mat[p][3]>mat[p+1][0])
							{

								flag=1;
								System.out.println("inside 2nd loop");
								break;
							}
						}
					}
					System.out.println("flag="+flag);
					if(flag==0)
					{
						game_end=1;
						System.out.println("game over");
						GameOver obj=new GameOver(ob);
						obj.setSize(400,300);
						obj.setVisible(true);
						obj.setLocationRelativeTo(null);
					}
				}
			}
		}
		
	}
	public class MyWindow implements WindowListener
	{
		public void windowActivated(WindowEvent we)
		{}
		public void windowClosed(WindowEvent we)
		{}
		public void windowClosing(WindowEvent we)
		{
			System.exit(0);
		}			
		public void windowDeactivated(WindowEvent we)
		{}
		public void windowDeiconified(WindowEvent we)
		{}
		public void windowOpened(WindowEvent we)
		{}
		public void windowIconified(WindowEvent we)
		{}
	}
	public static void main(String args[])
	{
		GamePuzzle game=new GamePuzzle();
		game.setSize(500,500);
		game.setVisible(true);
		game.setLocationRelativeTo(null);
	}
}