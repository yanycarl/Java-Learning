package graphics;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


class MyPanel2 extends JPanel
{
	public void paint(Graphics g)
	{
		/*super.paint(g);
		//画圆  
        g.drawOval(10, 10, 30, 30);  
        //画直线  
        g.drawLine(20, 30, 20, 80);  
        //画出矩形边框  
        g.drawRect(50, 50, 100, 50);  
        //画填充矩形  
        g.setColor(Color.BLUE);     //设置颜色  
        g.fillRect(80,60,40,60); */
		//Image im = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("1.png"));  
        //g.drawImage(im, 0, 0, 240, 280, this); 
		
		/*int length=200;
		g.setColor(Color.ORANGE);  
		g.setFont(new Font("微软雅黑",Font.BOLD,30));  
		g.drawString("社会主义好", this.getWidth()/2-75,length);    //第一个参数你要画出什么东西，后面两个是位置  */
	    /*Graphics2D d2;
	    d2=(Graphics2D)g;//转换d为2D对象
	    d2.setColor(Color.red);
	    Line2D line=new Line2D.Double(100,10,100,300);//创建line对象
	    d2.draw(line);//画出2D直线
	    d2.setColor(Color.blue);
	    Line2D line1=new Line2D.Double(0,300,100,300);//创建line对象
	    d2.draw(line1);//画出2D直线*/
	    /*Graphics2D d2;
	    d2=(Graphics2D)g;//转换g为2D对象
	    d2.setColor(Color.red);
	    QuadCurve2D draw_c1=new QuadCurve2D.Double(10,50,100,100,20,40);//创建对象
	    d2.draw(draw_c1);//画出
	    d2.setColor(Color.blue);
	    QuadCurve2D draw_c2=new QuadCurve2D.Double(20,60,100,100,40,80);//创建对象
	    d2.draw(draw_c2);//画出
	    d2.setColor(Color.green);
	    QuadCurve2D draw_c3=new QuadCurve2D.Double(30,70,100,100,80,160);//创建对象
	    d2.draw(draw_c3);//画出  */
	}
}



@SuppressWarnings("serial")
public class graphics2 extends JFrame {
	
	MyPanel2 mp=null;
	public graphics2() {
		mp= new MyPanel2();
		this.add(mp);  
	    this.setSize(400,300);  
	    this.setVisible(true);  
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public static void main(String []args) {
		graphics2 g=new graphics2();  
		return;
	}

}