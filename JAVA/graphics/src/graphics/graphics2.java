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
		//��Բ  
        g.drawOval(10, 10, 30, 30);  
        //��ֱ��  
        g.drawLine(20, 30, 20, 80);  
        //�������α߿�  
        g.drawRect(50, 50, 100, 50);  
        //��������  
        g.setColor(Color.BLUE);     //������ɫ  
        g.fillRect(80,60,40,60); */
		//Image im = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("1.png"));  
        //g.drawImage(im, 0, 0, 240, 280, this); 
		
		/*int length=200;
		g.setColor(Color.ORANGE);  
		g.setFont(new Font("΢���ź�",Font.BOLD,30));  
		g.drawString("��������", this.getWidth()/2-75,length);    //��һ��������Ҫ����ʲô����������������λ��  */
	    /*Graphics2D d2;
	    d2=(Graphics2D)g;//ת��dΪ2D����
	    d2.setColor(Color.red);
	    Line2D line=new Line2D.Double(100,10,100,300);//����line����
	    d2.draw(line);//����2Dֱ��
	    d2.setColor(Color.blue);
	    Line2D line1=new Line2D.Double(0,300,100,300);//����line����
	    d2.draw(line1);//����2Dֱ��*/
	    /*Graphics2D d2;
	    d2=(Graphics2D)g;//ת��gΪ2D����
	    d2.setColor(Color.red);
	    QuadCurve2D draw_c1=new QuadCurve2D.Double(10,50,100,100,20,40);//��������
	    d2.draw(draw_c1);//����
	    d2.setColor(Color.blue);
	    QuadCurve2D draw_c2=new QuadCurve2D.Double(20,60,100,100,40,80);//��������
	    d2.draw(draw_c2);//����
	    d2.setColor(Color.green);
	    QuadCurve2D draw_c3=new QuadCurve2D.Double(30,70,100,100,80,160);//��������
	    d2.draw(draw_c3);//����  */
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