package modelo;

/** Define el estado de un silo
 * @author Agus Salvadores Fran Gonzalez
*/


public enum EstadoSilo {
	OK(" "), LOW("LOW"), OUT("OUT");
	private String s;
	
	/** Constructor del estado del silo
	 * @param s
	*/
	
	
	EstadoSilo(String s) {
		this.s=s;
	}
	public String verEstado() {
		return s;
	}
}
