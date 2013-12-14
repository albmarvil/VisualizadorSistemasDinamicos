package charts;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.math.plot.Plot2DPanel;

import familiasParametricas.FamiliaCuadraticaImpl;
import familiasParametricas.FamiliaParametrica;

public class Red {
	private Map<double[][],double[][]> red;
	private double[] xp;
	private double[] yp;
	private FamiliaParametrica fp;
	private Integer iteraciones;
	private Integer iteracionesMax;
	private Double x0;
	
	public Red(FamiliaParametrica fp, Double x0, Integer iteraciones, Integer iteracionesMax){
		this.fp = fp;
		this.iteraciones = iteraciones;
		this.iteracionesMax = iteracionesMax;
		this.x0 = x0;
		this.red = new LinkedHashMap<double[][],double[][]>();
	}
	
	private void construyeDatos(){
		
		List<Double> xs = new ArrayList<Double>();
		List<Double> ys = new ArrayList<Double>();
		for(double i=-2; i<=2; i = i + 0.001){
			xs.add(i);
			ys.add(fp.getValue(i));
		}
		
		xp = new double[xs.size()];
		yp = new double[ys.size()];
		
		for(int i=0; i<xs.size();i++){
			xp[i] = xs.get(i);
			yp[i] = ys.get(i);
		}
		double[] orbita = fp.getOrbita(x0, iteraciones, iteracionesMax);
		
		if(iteraciones==0){//En este caso pintamos el principio de forma distinta
			double[][] v = {{orbita[0],0},{orbita[0],orbita[1]}};
			double[][] h = {{orbita[0],orbita[1]},{orbita[1],orbita[1]}};
			red.put(v, h);
			for(int i=1; i<orbita.length-1;i++){
				double[][] vn = {{orbita[i],orbita[i]},{orbita[i],orbita[i+1]}};
				double[][] hn = {{orbita[i],orbita[i+1]},{orbita[i+1],orbita[i+1]}};
				red.put(vn, hn);
			}
		}else{//Caso general
			for(int i=0; i<orbita.length-1;i++){
				double[][] vn = {{orbita[i],orbita[i]},{orbita[i],orbita[i+1]}};
				double[][] hn = {{orbita[i],orbita[i+1]},{orbita[i+1],orbita[i+1]}};
				red.put(vn, hn);
			}
		}
		
	}
	
	public void muestraChart(){
		this.construyeDatos();
		double[][] recta = {{-2,-2},{0,0},{2,2}};
		double[][] ejeX = {{-2,0},{2,0}};
		double[][] ejeY = {{0,-2},{0,2}};
		Plot2DPanel plot = new Plot2DPanel();
		plot.addLinePlot("FamiliaCuadratica", xp, yp);
		plot.addLinePlot("y=x", recta);
		plot.addLinePlot("ejeX", ejeX);
		plot.addLinePlot("ejeY", ejeY);
//		plot.addLegend("SOUTH");
		
		plot.setFixedBounds(0, -2, 2);
		
		Integer i = 1;
		for(double[][] key : this.red.keySet()){
			plot.addLinePlot(""+i, key);
			plot.addLinePlot(""+i, this.red.get(key));
			i++;
		}
		Integer nLines = this.red.size();
		plot.changePlotColor(0, Color.BLUE);
		plot.changePlotColor(1, Color.BLACK);
		plot.changePlotColor(2, Color.BLACK);
		plot.changePlotColor(3, Color.BLACK);
		
		for(int j=4; j<=nLines*2+2; j=j+2){
			plot.changePlotColor(j, Color.RED);
			plot.changePlotColor(j+1, Color.RED);			
		}
		
		JFrame frame = new JFrame("Red");
		frame.setContentPane(plot);
		frame.setSize(500, 500);
//		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
//	public static void main(String[] args) {
//		FamiliaParametrica fp = new FamiliaCuadraticaImpl(-1.);
//		Double x0 = 1.;
//		Integer iteracionesMax = 10;
//		Integer iteraciones = 0;
//		
//		Red st= new Red(fp, x0, iteraciones, iteracionesMax);
//		
//		st.muestraChart();
//	}
}
