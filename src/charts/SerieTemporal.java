package charts;

import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;

import familiasParametricas.FamiliaParametrica;

public class SerieTemporal {
	private double[] x;
	private double[] y;
	private FamiliaParametrica fp;
	private Integer iteraciones;
	private Integer iteracionesMax;
	private Double x0;
	
	public SerieTemporal(FamiliaParametrica fp, Double x0, Integer iteraciones, Integer iteracionesMax){
		this.fp = fp;
		this.iteraciones = iteraciones;
		this.iteracionesMax = iteracionesMax;
		this.x0 = x0;
		this.x = new double[iteracionesMax-iteraciones];
		this.y = new double[iteracionesMax-iteraciones];
	}
	
	private void construyeDatos(){
		for(int i=0; i<this.iteracionesMax-this.iteraciones; i++){
			this.x[i] = i;
		}
			this.y = this.fp.getOrbita(this.x0, this.iteraciones, this.iteracionesMax); 
	}
	
	public void muestraChart(){
		this.construyeDatos();
		Plot2DPanel plot = new Plot2DPanel();
		plot.addLinePlot("Serie Temporal", x, y);
		plot.addLegend("SOUTH");
		
		plot.getAxis(0).setVisible(true);
		plot.getAxis(1).setVisible(true);
//		plot.setFixedBounds(0, 0, this.iteraciones);
////		plot.setFixedBounds(1, 0, 100);
		
		JFrame frame = new JFrame("Serie Temporal");
		frame.setContentPane(plot);
		frame.setSize(500, 500);
//		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
