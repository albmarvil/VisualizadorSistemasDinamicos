package familiasParametricas;

public class FamiliaCuadraticaImpl extends FamiliaParametricaImpl {
	
	
	public FamiliaCuadraticaImpl(){
		super();
	}
	
	public FamiliaCuadraticaImpl(Double mu){
		super(mu);
	}
	

	public Double getValue(Double x){
		return x*x + this.getMu();
	}

}
