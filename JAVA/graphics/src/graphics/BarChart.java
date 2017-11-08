package graphics;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

@SuppressWarnings("serial")
public class BarChart extends JFrame {
	ChartPanel frame1; 
	public BarChart() { 
	    this.setSize(1200,800);   
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    CategoryDataset dataset = getDataSet();
        JFreeChart chart = ChartFactory.createBarChart3D("Corpus Terms Frequency","Terms","Times",dataset,PlotOrientation.VERTICAL,true,false,false);

        CategoryPlot plot=chart.getCategoryPlot();//��ȡͼ���������
        CategoryAxis domainAxis=plot.getDomainAxis();         //ˮƽ�ײ��б�
        domainAxis.setLabelFont(new Font("����",Font.BOLD,14));         //ˮƽ�ײ�����
        domainAxis.setTickLabelFont(new Font("����",Font.BOLD,12));  //��ֱ����
        ValueAxis rangeAxis=plot.getRangeAxis();//��ȡ��״
        rangeAxis.setLabelFont(new Font("����",Font.BOLD,15));
        chart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));
        chart.getTitle().setFont(new Font("����",Font.BOLD,20));//���ñ�������  */
        frame1=new ChartPanel(chart,true);
        this.add(frame1);
        this.setVisible(true); 
	}
	
	public static void main(String []args) {
		BarChart g=new BarChart(); 
		return;
	}
	   private static CategoryDataset getDataSet() {
           DefaultCategoryDataset dataset = new DefaultCategoryDataset();
           dataset.addValue(100, "����", "ƻ��");
           dataset.addValue(100, "�Ϻ�", "ƻ��");
           dataset.addValue(100, "����", "ƻ��");
           dataset.addValue(200, "����", "����");
           dataset.addValue(200, "�Ϻ�", "����");
           dataset.addValue(200, "����", "����");
           dataset.addValue(300, "����", "����");
           dataset.addValue(300, "�Ϻ�", "����");
           dataset.addValue(300, "����", "����");
           dataset.addValue(400, "����", "�㽶");
           dataset.addValue(400, "�Ϻ�", "�㽶");
           dataset.addValue(400, "����", "�㽶");
           dataset.addValue(500, "����", "��֦");
           dataset.addValue(500, "�Ϻ�", "��֦");
           dataset.addValue(500, "����", "��֦");
           return dataset;
	   }
}

