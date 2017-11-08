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

        CategoryPlot plot=chart.getCategoryPlot();//获取图表区域对象
        CategoryAxis domainAxis=plot.getDomainAxis();         //水平底部列表
        domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题
        domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //垂直标题
        ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状
        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
        chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体  */
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
           dataset.addValue(100, "北京", "苹果");
           dataset.addValue(100, "上海", "苹果");
           dataset.addValue(100, "广州", "苹果");
           dataset.addValue(200, "北京", "梨子");
           dataset.addValue(200, "上海", "梨子");
           dataset.addValue(200, "广州", "梨子");
           dataset.addValue(300, "北京", "葡萄");
           dataset.addValue(300, "上海", "葡萄");
           dataset.addValue(300, "广州", "葡萄");
           dataset.addValue(400, "北京", "香蕉");
           dataset.addValue(400, "上海", "香蕉");
           dataset.addValue(400, "广州", "香蕉");
           dataset.addValue(500, "北京", "荔枝");
           dataset.addValue(500, "上海", "荔枝");
           dataset.addValue(500, "广州", "荔枝");
           return dataset;
	   }
}

