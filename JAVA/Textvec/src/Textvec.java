
import java.awt.Font;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Stream;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

@SuppressWarnings("serial")
public class Textvec extends JFrame{
	ChartPanel frame1; 

	@SuppressWarnings("rawtypes")
	Vector vectors = new Vector();
	@SuppressWarnings("rawtypes")
	Vector vectors2 =new Vector();
	static Map<String, Number> featureMap = new LinkedHashMap<String, Number> ();
	static Map<String, Number> featureMap2 = new LinkedHashMap<String, Number> ();	
	static Map<String, Number> idf = new LinkedHashMap<String, Number> ();	
	Map<String, Number> countEmerge = new LinkedHashMap<String, Number> ();
	double counter;
	
    public static String [] getFileName(String path)
    {
        File file = new File(path);
        String [] fileName = file.list();
        return fileName;
    }

	public static String readTxt(String name){
		File file=new File("D://corpus//"+name);
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while((s = br.readLine())!=null)
            {
                result.append(System.lineSeparator()+s);
            }
        br.close();    
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
	
	public static void countEmergeMethod(String[] filesName)
	{
		Map<String, Number> idfFlag = new LinkedHashMap<String, Number> ();	
		Iterator<Map.Entry<String, Number>> entries = featureMap.entrySet().iterator();  
		while (entries.hasNext()) {  
		    Map.Entry<String, Number>entry = entries.next();  
		    idf.put(entry.getKey(),0);
		}
		
		for(int i=0;i<filesName.length;i++) {
			Iterator<Map.Entry<String, Number>> entries1 = idf.entrySet().iterator();  
			while (entries1.hasNext()) {  
			    Map.Entry<String, Number>entry = entries1.next();  
			    idfFlag.put(entry.getKey(),1);
			}
			
	        String s = null;
			File file=new File("D://corpus//"+filesName[i]);
	        StringBuilder result = new StringBuilder();
	        try{
	            BufferedReader br = new BufferedReader(new FileReader(file));
	            while((s = br.readLine())!=null)
	            {
	                result.append(System.lineSeparator()+s);
	            }
	        br.close();    
	        }catch(Exception e){
	            e.printStackTrace();
	        }
			s=result.toString();
			
			s=s.toLowerCase();
			for(int i1=0;i1<97;i1++)
				s=s.replace((char) i1, ' ');
			for(int i1=123;i1<256;i1++)
				s=s.replace((char) i1, ' ');
			int wordStart=0;
			int wordEnd=0;
			for(int i1=0;i1<s.length();i1++) {
				if(s.charAt(i1)!=' ')
				{
					wordStart=i1;
					wordEnd=wordStart;
					while( wordEnd<s.length() && s.charAt(wordEnd)!=' ') {
						wordEnd++;
					}

					if(idfFlag.containsKey(s.substring(wordStart, wordEnd))==true && (int)idfFlag.get(s.substring(wordStart, wordEnd))==1)
					{
						idf.put(s.substring(wordStart, wordEnd), idf.get(s.substring(wordStart, wordEnd)).doubleValue()+1);
						idfFlag.put(s.substring(wordStart, wordEnd), 0);
					}
					i1=wordEnd;
				}
			}
		}
			Iterator<Map.Entry<String, Number>> entries1 = idf.entrySet().iterator();  
			while (entries1.hasNext()) {  
			    Map.Entry<String, Number>entry = entries1.next();  
			    double idfValue=(double)filesName.length/(double) entry.getValue();
			    idfValue=Math.log(idfValue);
			    idf.put(entry.getKey(), idfValue);
			}
		return;
	}	
	
	@SuppressWarnings("unchecked")
	public static void featureVectors(String text)
	{
		Map<String, Number> featureMapR = new LinkedHashMap<String, Number> ();
		text=text.toLowerCase();
		for(int i=0;i<97;i++)
			text=text.replace((char) i, ' ');
		for(int i=123;i<256;i++)
			text=text.replace((char) i, ' ');
		int wordStart=0;
		int wordEnd=0;
		for(int i=0;i<text.length();i++) {
			if(text.charAt(i)!=' ')
			{
				wordStart=i;
				wordEnd=wordStart;
				while( wordEnd<text.length() && text.charAt(wordEnd)!=' ') {
					wordEnd++;
				}
				if(featureMapR.containsKey(text.substring(wordStart, wordEnd))==false)
					featureMapR.put(text.substring(wordStart, wordEnd), 1);
				else
					featureMapR.put(text.substring(wordStart, wordEnd), featureMapR.get(text.substring(wordStart, wordEnd)).intValue()+1);
				i=wordEnd;
			}
		}
		
		Stream< Entry <String, Number>> StreamRow = featureMapR.entrySet().stream();
		//排序并倒叙
		StreamRow.sorted(Comparator.comparing(e->((Entry<String,Number>) e).getValue().doubleValue()).reversed()).forEach(e -> featureMap.put(e.getKey(), e.getValue()));
		//转而放入向量中
		return;
	}	
	
	public double countCorpusTerms(String[] fileName)
	{
		double counter=0;
		for(String name:fileName)
        {
    		String text= new String();
    		text=text.concat(readTxt(name));
			Map<String, Number> featureMapR = new LinkedHashMap<String, Number> ();
			text=text.toLowerCase();
			for(int i=0;i<97;i++)
				text=text.replace((char) i, ' ');
			for(int i=123;i<256;i++)
				text=text.replace((char) i, ' ');
			int wordStart=0;
			int wordEnd=0;
			for(int i=0;i<text.length();i++) {
				if(text.charAt(i)!=' ')
				{
					wordStart=i;
					wordEnd=wordStart;
					while( wordEnd<text.length() && text.charAt(wordEnd)!=' ') {
						wordEnd++;
					}
					if(featureMapR.containsKey(text.substring(wordStart, wordEnd))==false)
						featureMapR.put(text.substring(wordStart, wordEnd), 1);
					else
						featureMapR.put(text.substring(wordStart, wordEnd), featureMapR.get(text.substring(wordStart, wordEnd)).intValue()+1);
					i=wordEnd;
					counter++;
				}
			}
		}
		return counter;
	}	
	
	
	
	@SuppressWarnings("unchecked")
	private CategoryDataset getDataSet() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
		//当前文本向量

		vectors.addAll(featureMap.values());
		vectors2.addAll(featureMap.keySet());

		double possibility;
        for(int i=0;i<vectors.size();i++)
        {
        	possibility=Integer.valueOf(String.valueOf(vectors.get(i))).doubleValue()/counter;
        	if(i<=200)
        	{
        		dataset.addValue(possibility, vectors2.get(i).toString(),"Corpus");
        	}
        	
        }
        return dataset;
	}

	
	public Textvec() { 
		
	    this.setSize(1200,800);   
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    counter=countCorpusTerms(getFileName("D://corpus"));
	    CategoryDataset dataset = getDataSet();
        JFreeChart chart = ChartFactory.createBarChart3D("Corpus Terms Frequency","Terms","Possibility",dataset,PlotOrientation.VERTICAL,true,false,false);

        CategoryPlot plot=chart.getCategoryPlot();//获取图表区域对象
        CategoryAxis domainAxis=plot.getDomainAxis();         //水平底部列表
        domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题
        domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //垂直标题
        ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状
        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
        chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体  
        frame1=new ChartPanel(chart,true);
        this.add(frame1);
       // this.setVisible(true); 
	}
	
	@SuppressWarnings({ "unused" })
	public static void main(String[] args) {
		
		String s= new String();
		
		String [] fileName = getFileName("D://corpus");
        for(String name:fileName)
        {
    		s=s.concat(readTxt(name));
        }
		featureVectors(s);
		
		Iterator<Map.Entry<String, Number>> entries = featureMap.entrySet().iterator();  
		while (entries.hasNext()) {  
		    Map.Entry<String, Number>entry = entries.next();  
		    featureMap2.put(entry.getKey(),0);
		}  
		Textvec t=new Textvec();
		countEmergeMethod(fileName);
		for(int i=0;i<fileName.length;i++) {
	    	otherFrame f=new otherFrame(fileName[i], featureMap2,idf);
		}
		return;
	}
}

@SuppressWarnings("serial")
class otherFrame extends JFrame{ 
	
	String txtName;
	@SuppressWarnings("rawtypes")
	Vector vectors3 = new Vector();
	@SuppressWarnings("rawtypes")
	Vector vectors4 =new Vector();
	Map<String, Number> featureMap3 = new LinkedHashMap<String, Number> ();
	Map<String, Number> idf = new LinkedHashMap<String, Number> ();
	Map<String, Number> tfidf = new LinkedHashMap<String, Number> ();
	Map<String, Number> OrderedTDIDF = new LinkedHashMap<String, Number> ();
	double counter;
	
	public otherFrame(String txtName,Map<String, Number> featureMap,Map<String, Number> idf)  
    {  
    	this.txtName=txtName;
    	this.featureMap3=featureMap;
    	this.counter=countTerms(readTxt(txtName));
    	this.idf=idf;
    	featureVector(readTxt(txtName));
    	
        CategoryDataset dataSet=getDataSet(1);
        tfIdfFrame f1=new tfIdfFrame(dataSet,txtName);
    	//CategoryDataset dataSet=getDataSet(0);
        //possibilityFrame f2=new  possibilityFrame(dataSet,txtName);
    }
	
	public String readTxt(String name){
		File file=new File("D://corpus//"+name);
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while((s = br.readLine())!=null)
            {
                result.append(System.lineSeparator()+s);
            }
        br.close();    
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
	
	public  void featureVector(String text)
	{
		
		text=text.toLowerCase();
		for(int i=0;i<97;i++)
			text=text.replace((char) i, ' ');
		for(int i=123;i<256;i++)
			text=text.replace((char) i, ' ');
		int wordStart=0;
		int wordEnd=0;
		for(int i=0;i<text.length();i++) {
			if(text.charAt(i)!=' ')
			{
				wordStart=i;
				wordEnd=wordStart;
				while( wordEnd<text.length() && text.charAt(wordEnd)!=' ') {
					wordEnd++;
				}
				if(featureMap3.containsKey(text.substring(wordStart, wordEnd))==false)
				{
					featureMap3.put(text.substring(wordStart, wordEnd), 1);
					System.out.println("ERROR:LOST ELEMENTS");
				}
				else
				{
					featureMap3.put(text.substring(wordStart, wordEnd), featureMap3.get(text.substring(wordStart, wordEnd)).intValue()+1);
				}
				
				i=wordEnd;
			}
		}
		return;
	}
	
	
	public  double countTerms(String text)
	{
		double counter=0;
		Map<String, Number> featureMapR = new LinkedHashMap<String, Number> ();
		text=text.toLowerCase();
		for(int i=0;i<97;i++)
			text=text.replace((char) i, ' ');
		for(int i=123;i<256;i++)
			text=text.replace((char) i, ' ');
		int wordStart=0;
		int wordEnd=0;
		for(int i=0;i<text.length();i++) {
			if(text.charAt(i)!=' ')
			{
				wordStart=i;
				wordEnd=wordStart;
				while( wordEnd<text.length() && text.charAt(wordEnd)!=' ') {
					wordEnd++;
				}
				if(featureMapR.containsKey(text.substring(wordStart, wordEnd))==false)
					featureMapR.put(text.substring(wordStart, wordEnd), 1);
				else
					featureMapR.put(text.substring(wordStart, wordEnd), featureMapR.get(text.substring(wordStart, wordEnd)).intValue()+1);
				i=wordEnd;
				counter++;
			}
		}
		return counter;
	}	

	@SuppressWarnings("unchecked")
	private  CategoryDataset getDataSet(int function) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
		vectors3.addAll(featureMap3.values());
		vectors4.addAll(featureMap3.keySet());
		
		double possibility;
		double tfidfValue;

        	if(function==0) {
                for(int i=0;i<vectors3.size();i++)
                {
	        		possibility=Integer.valueOf(String.valueOf(vectors3.get(i))).doubleValue()/counter;
	             	if(i<=200)
	            	{
	            		dataset.addValue(possibility, vectors4.get(i).toString(),"Possible");
	            	}
                }
        	}
        	else if(function==1) {
                for(int i=0;i<vectors3.size();i++)
                {
	        		possibility=Integer.valueOf(String.valueOf(vectors3.get(i))).doubleValue()/counter;
	        		tfidfValue= possibility * idf.get(vectors4.get(i).toString()).doubleValue();
	        		tfidf.put( vectors4.get(i).toString(), tfidfValue);
	            	dataset.addValue(tfidfValue, vectors4.get(i).toString(),"TF-IDF");
                }
        	}
        	else if(function==2){
                for(int i=0;i<vectors3.size();i++)
                {
	        		possibility=Integer.valueOf(String.valueOf(vectors3.get(i))).doubleValue()/counter;
	        		tfidfValue= possibility * idf.get(vectors4.get(i).toString()).doubleValue();
	        		tfidf.put( vectors4.get(i).toString(), tfidfValue);
                }
	        	Stream< Entry <String, Number>> StreamRow = tfidf.entrySet().stream();
	    		//排序并倒叙
	        	StreamRow.sorted(Comparator.comparing(e->((Entry<String,Number>) e).getValue().doubleValue()).reversed()).forEach(e -> OrderedTDIDF.put(e.getKey(), e.getValue()));
	    		//转而放入向量中
	        	Iterator<Map.Entry<String, Number>> entries1 = OrderedTDIDF.entrySet().iterator();  
	    		while (entries1.hasNext()) { 
	    		    Map.Entry<String, Number>entry = entries1.next();  
	    		    dataset.addValue(entry.getValue(),entry.getKey(),"Order-TF IDF");
	    		}
        	}
        return dataset;
	}
 } 

@SuppressWarnings("serial") 
class tfIdfFrame extends JFrame{ 
	static ChartPanel frame1;
	@SuppressWarnings("deprecation")
	public tfIdfFrame(CategoryDataset dataSet, String txtName)  
    {  
    	this.setSize(1200,800);   
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    CategoryDataset dataset = dataSet;
        JFreeChart chart = ChartFactory.createBarChart3D(txtName+" Terms TF-IDF Values","Terms","Value",dataset,PlotOrientation.VERTICAL,false,false,false);
        CategoryPlot plot=chart.getCategoryPlot();
        CategoryAxis domainAxis=plot.getDomainAxis();         
        domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));       
        domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  
        ValueAxis rangeAxis=plot.getRangeAxis();
        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,14));
        //chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 0));//图例
        chart.getTitle().setFont(new Font("宋体",Font.BOLD,15));
        frame1=new ChartPanel(chart,true);
        this.add(frame1);
        this.setVisible(true);
        /*try {
			Thread.sleep(500);
			//this.hide();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  */
    }
}

@SuppressWarnings("serial")
class possibilityFrame extends JFrame{ 
	public possibilityFrame(CategoryDataset dataSet, String txtName)  
    {  
		ChartPanel frame1;
    	this.setSize(1200,800);   
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    CategoryDataset dataset = dataSet;
        JFreeChart chart = ChartFactory.createBarChart3D(txtName+" Terms Possibility Values","Terms","Possibility",dataset,PlotOrientation.VERTICAL,true,false,false);
        CategoryPlot plot=chart.getCategoryPlot();
        CategoryAxis domainAxis=plot.getDomainAxis();         
        domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));       
        domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  
        ValueAxis rangeAxis=plot.getRangeAxis();
        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
        chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));
        frame1=new ChartPanel(chart,true);
        this.add(frame1);
        this.setVisible(true);
        try {
			Thread.sleep(1000);
			this.hide();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
