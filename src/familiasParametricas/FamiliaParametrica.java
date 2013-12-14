package familiasParametricas;

public interface FamiliaParametrica {

	public  Double getMu();

	public  void setMu(Double mu);

	public  Double getValue(Double x);
	
	public double[] getOrbita(Double x0, Integer iteraciones, Integer IteracionesMax);
	

}