package modelo;

import java.io.Serializable;

public class Ranking implements Serializable{
	private int puntos;
	private String nombre;
	private int pos;
	private int tiempo;
	private static final long serialVersionUID = 1L; 
	
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Ranking(int puntos,String nombre,int lugar,int tiempo) {
		this.setPuntos(puntos);
		this.setNombre(nombre);
		this.setPos(lugar);
		this.setTiempo(tiempo);
	}
	public void setTiempo(int tiempo) {
		this.tiempo=tiempo;
		
	}
	public int getTiempo () {
		return tiempo;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
}
