package familiasParametricas;

public class FamiliaCuadraticaImpl implements FamiliaParametrica {
	
	private Double mu;
	
	public FamiliaCuadraticaImpl(){
		this.mu = 0.0;
	}
	
	public FamiliaCuadraticaImpl(Double mu){
		this.mu = mu;
	}
	
	

	public Double getMu(){
		return this.mu;
	}
	
	

	public void setMu(Double mu){
		this.mu = mu;
	}
	
	

	public Double getValue(Double x){
		return x*x + this.mu;
	}


	public double[] getOrbita(Double x0, Integer iteraciones, Integer iteracionesMax) {
		Integer n = iteracionesMax-iteraciones;
		double[] res = new double[n];
		
		Double x = x0;
		Integer j= 0;
		for(int i=0; i<iteracionesMax; i++){
			if(i<iteracionesMax && i>=iteraciones){
				res[j] = x;
				j++;
			}
			x = this.getValue(x);
		}
		return res;
	}
	
	public String toString(String s){
		return "x^2+"+this.getMu();
	}

}
