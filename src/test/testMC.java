package test;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;

public class testMC {
	
	public static double FamiliaCuadratica(double x, double mu){
		return x*x + mu;
	}

	public static void main(String[] args) {
		List<Double> xs = new ArrayList<Double>();
		List<Double> ys = new ArrayList<Double>();
		for(double i=-5; i<=5; i = i + 0.01){
			xs.add(i);
			ys.add(FamiliaCuadratica(i,0));
		}
		
		double[] x = new double[xs.size()];
		double[] y = new double[ys.size()];
		
		for(int i=0; i<xs.size();i++){
			x[i] = xs.get(i);
			y[i] = ys.get(i);
		}
		
		double x0 = 0.5;
		
		double[][] x1 = {{x0,0},{x0,FamiliaCuadratica(x0,0)}};
		double[][] x2 = {{x0,FamiliaCuadratica(x0,0)},{FamiliaCuadratica(x0,0),FamiliaCuadratica(x0,0)}};
		x0 = FamiliaCuadratica(x0,0);
				 
				  // create your PlotPanel (you can use it as a JPanel)
		Plot2DPanel plot = new Plot2DPanel();
				 
				  // add a line plot to the PlotPanel
		plot.addLinePlot("my plot", x, y);
		plot.addLinePlot("otro", x1);
		plot.addLinePlot("MOAR", x2);
		for(int i = 2; i<=5; i++){
			double[][] xp = {{x0,x0},{x0,FamiliaCuadratica(x0,0)}};
			double[][] xn = {{x0,FamiliaCuadratica(x0,0)},{FamiliaCuadratica(x0,0),FamiliaCuadratica(x0,0)}};
			x0 = FamiliaCuadratica(x0,0);
			plot.addLinePlot(""+i, xp);
			plot.addLinePlot(""+i, xn);
		}
		
				 
				  // put the PlotPanel in a JFrame, as a JPanel
		JFrame frame = new JFrame("a plot panel");
		frame.setContentPane(plot);
		frame.setVisible(true);

	}

}
