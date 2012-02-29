package frameComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JComponent;

import numberedSquare.NumberedSquare;

public class FrameComponent extends JComponent{

	private static final long serialVersionUID = 2L;

	private int n;
	private NumberedSquare[][] squares;

	public FrameComponent(int n)
	{
		this.n = n;
		squares = new NumberedSquare[n][n];
	}


	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		Random gen = new Random();

		int[] randoms = new int[n*n];

		for(int p = 0; p < n*n ; p++)
			randoms[p] = p;

		int first, last, swapFirst, swapLast;
		for(int o = 0 ; o < 1000 ; o++)
		{
			first = gen.nextInt(n*n);
			last = gen.nextInt(n*n);

			swapFirst = randoms[first];
			swapLast = randoms[last];
			
			randoms[last] = swapFirst;		
			randoms[first] = swapLast;

		}
		

		int c = 0;
		for(int i = 0 ; i < n; i++)
			for(int j = 0 ; j < n; j++)
			{

				squares[i][j] = new NumberedSquare(randoms[c]);
				squares[i][j].translate( 40 + 80*j, 40 + 80*i );
				
				g2.setPaint(new Color(224,224,224));
				g2.fill(squares[i][j]);
				g2.setPaint(Color.BLACK);
				g2.draw(squares[i][j]);
				
				g2.drawString(String.valueOf(squares[i][j].getNumber()), (int) squares[i][j].getX()+40, (int) squares[i][j].getY()+40 );
				c++;
			}
	}


}
