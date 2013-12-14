package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JTree;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;

import charts.Red;
import charts.SerieTemporal;
import familiasParametricas.FamiliaCuadraticaImpl;
import familiasParametricas.FamiliaParametrica;

public class GUI {

	private JFrame frmVisualizadorFamiliaCuadrtica;
	private JCheckBox chckbxSerieTemporal;
	private JCheckBox chckbxRed;
	private JSpinner spinner;
	private JSpinner spinner_1;
	private JTextField x0;
	private JTextField mu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmVisualizadorFamiliaCuadrtica.setVisible(true);
					window.frmVisualizadorFamiliaCuadrtica.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVisualizadorFamiliaCuadrtica = new JFrame();
		frmVisualizadorFamiliaCuadrtica.setTitle("Visualizador Familia Cuadr\u00E1tica");
		frmVisualizadorFamiliaCuadrtica.setBounds(100, 100, 439, 276);
		frmVisualizadorFamiliaCuadrtica.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVisualizadorFamiliaCuadrtica.getContentPane().setLayout(null);
		
		chckbxSerieTemporal = new JCheckBox("Serie Temporal");
		chckbxSerieTemporal.setBounds(271, 120, 145, 23);
		frmVisualizadorFamiliaCuadrtica.getContentPane().add(chckbxSerieTemporal);
		
		chckbxRed = new JCheckBox("Red");
		chckbxRed.setBounds(271, 151, 145, 23);
		frmVisualizadorFamiliaCuadrtica.getContentPane().add(chckbxRed);
		
		SpinnerModel sm = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1); //default value,lower bound,upper bound,increment by
		spinner = new JSpinner(sm);
		spinner.setBounds(123, 79, 105, 20);
		frmVisualizadorFamiliaCuadrtica.getContentPane().add(spinner);
		
		JLabel lblNIteraciones = new JLabel("N\u00BA Iteraciones de:");
		lblNIteraciones.setBounds(10, 82, 104, 14);
		frmVisualizadorFamiliaCuadrtica.getContentPane().add(lblNIteraciones);
		
		JLabel lblparametro = new JLabel(" Mu:");
		lblparametro.setBounds(75, 124, 39, 14);
		frmVisualizadorFamiliaCuadrtica.getContentPane().add(lblparametro);
		
		mu = new JTextField();
		mu.setBounds(123, 121, 29, 20);
		frmVisualizadorFamiliaCuadrtica.getContentPane().add(mu);
		
		SpinnerModel sm1 = new SpinnerNumberModel(100, 0, Integer.MAX_VALUE, 1); //default value,lower bound,upper bound,increment by

		spinner_1 = new JSpinner(sm1);
		spinner_1.setBounds(270, 79, 105, 20);
		frmVisualizadorFamiliaCuadrtica.getContentPane().add(spinner_1);
		
		JLabel lblX = new JLabel("  x0:");
		lblX.setBounds(75, 155, 28, 14);
		frmVisualizadorFamiliaCuadrtica.getContentPane().add(lblX);
		
		x0 = new JTextField();
		x0.setBounds(123, 152, 29, 20);
		frmVisualizadorFamiliaCuadrtica.getContentPane().add(x0);
		
		JLabel lblA = new JLabel("   a");
		lblA.setBounds(238, 82, 27, 14);
		frmVisualizadorFamiliaCuadrtica.getContentPane().add(lblA);
		
		JLabel lblNewLabel = new JLabel("<html><b>f(x) = x<sup>2</sup> + mu</html>");
		lblNewLabel.setBounds(174, 22, 105, 14);
		frmVisualizadorFamiliaCuadrtica.getContentPane().add(lblNewLabel);
		
		JButton button = new JButton("Visualizar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxSerieTemporal.isSelected()==false && chckbxRed.isSelected()==false){
					JOptionPane.showMessageDialog(frmVisualizadorFamiliaCuadrtica, "Seleccione un gráfico a dibujar");
				}else{
					Integer min = (Integer)spinner.getValue();
					Integer max = (Integer)spinner_1.getValue();
					if((min<max) && (min>=0)){
						if(x0.getText().equals("")){
							JOptionPane.showMessageDialog(frmVisualizadorFamiliaCuadrtica, "Introduzca correctamente el x0");
						}else if(mu.getText().equals("")){
							JOptionPane.showMessageDialog(frmVisualizadorFamiliaCuadrtica, "Introduzca correctamente el parámetro mu");
						}else{
							double x = Double.parseDouble(x0.getText());
							double m = Double.parseDouble(mu.getText());
							FamiliaParametrica fp= new FamiliaCuadraticaImpl(m);
							if(chckbxSerieTemporal.isSelected()){
								SerieTemporal st = new SerieTemporal(fp, x, min, max);
								st.muestraChart();
							}
							if(chckbxRed.isSelected()){
								Red r = new Red(fp, x, min, max);
								r.muestraChart();
							}
						}
					}else{
						JOptionPane.showMessageDialog(frmVisualizadorFamiliaCuadrtica, "Introduzca correctamente el rango de iteraciones");
					}
				}
			}
		});
		button.setBounds(163, 192, 93, 23);
		frmVisualizadorFamiliaCuadrtica.getContentPane().add(button);
	}
}
