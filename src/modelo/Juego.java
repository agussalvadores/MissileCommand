package modelo;

import java.util.*;

/**
 * Encargada de llevar a cabo el control del juego
 * 
 * @author Agus Salvadores Fran Gonzalez
 */

public class Juego {
	private Puntaje[] puntajen;
	private Puntaje[] puntajet;
	private Administrador admin;
	private Oleada ole;
	private Contador grupos;
	private Contador regresivo;
	private Contador regresivobombardero;
	private Contador regresivoCrucero;
	private int tiempoin;
	private int tiempofin;
	private int sigo;
	private int timepausa;
	private int imprimo= 0;
	private boolean pausa = false;

	/**
	 * Constructor de la clase Juego
	 */

	public Juego() {
		this.setSigo(1);
		puntajen = new Puntaje[5];
		puntajet = new Puntaje[5];
		tiempoin = (int) System.currentTimeMillis();
		for (int i = 0; i < 5; i++) {
			puntajen[i] = new Puntaje();
			puntajet[i] = new Puntaje();
		}
		puntajen[0].setNombre("MISILES BALISTICOS INTERPLANETARIOS");
		puntajet[0].setNombre("MISILES BALISTICOS INTERPLANETARIOS");
		puntajen[1].setNombre("MISILES CRUCERO ENEMIGOS");
		puntajet[1].setNombre("MISILES CRUCERO ENEMIGOS");
		puntajen[2].setNombre("MISILES ANTIBALISTICOS SIN USAR");
		puntajet[2].setNombre("MISILES ANTIBALISTICOS SIN USAR");
		puntajen[3].setNombre("CIUDADES SALVADAS");
		puntajet[3].setNombre("CIUDADES SALVADAS");
		puntajen[4].setNombre("PUNTAJE TOTAL");
		puntajet[4].setNombre("PUNTAJE TOTAL");
	}

	/**
	 * Procesamiento de los disparos efectuados por el usuario
	 * 
	 * @param admin
	 * @param cor
	 * 
	 */

	public void procesarDisparos(Administrador admin, Coordenada cor) {
		int min = 99999;
		Silo silomin = null;
		int cantsilos = 0;
		for (Aliado a : admin.getListaAliados()) {
			if (a.esSilo()) {
				cantsilos += 1;
				double distancia = a.getPos().getX() - (cor.getX());
				if (distancia < 0) {
					distancia = -distancia;
				}
				if ((distancia < min) && (((Silo) a).getCantanti() > 0)) {
					min = (int) (distancia);
					silomin = (Silo) a;
				}
			}
		}
		if (cantsilos > 0) {
			silomin.disparar(admin, cor);
		}
	}

	/**
	 * Procesamiento de las explosiones efectuadas
	 * 
	 * @param admin
	 */

	public void procesarExplosiones(Administrador admin) {
		LinkedList<Explosion> expelim = new LinkedList<Explosion>();
		for (Explosion e : admin.getListaExplosiones()) {
			if (e.getTiempo() == 0) {
				expelim.add(e);
			} else {
				e.setTiempo(e.getTiempo() - 1);
			}
		}
		admin.getListaExplosiones().removeAll(expelim);
		System.out.println("se actualizaron las explosiones");
	}

	/**
	 * Procesamiento de los antimisiles del juego
	 * 
	 * @param admin
	 */

	public void procesarAntimisiles(Administrador admin) {
		LinkedList<Antimisil> antielim = new LinkedList<Antimisil>();
		for (Antimisil a : admin.getListaAntimisiles()) {
			if (!(a.llegoDestino(admin))) {
				a.mover();
			} else {
				a.explotar(admin);
				antielim.add(a);
			}
		}
		admin.getListaAntimisiles().removeAll(antielim);
		System.out.println("Se actualizaron los ANTImisiles");
	}

	/**
	 * Procesamiento de los bombarderos del juego
	 * 
	 * @param admin
	 * @param regresivobombardero
	 * @param ole
	 */

	public void procesarBombarderos(Administrador admin, Contador regresivobombardero, Oleada ole) {
		LinkedList<Bombardero> bomelim = new LinkedList<Bombardero>();
		for (Bombardero b : admin.getListaBombarderos()) {
			for (Explosion e : admin.getListaExplosiones()) {
				Coordenada cor1 = b.getPos();
				Coordenada cor2 = e.getPos();
				if ((admin.distanciaEntreDos(cor1, cor2)) < 15) {
					bomelim.add(b);
				}
			}
			if (b.llegoDestino()) {
				bomelim.add(b);
			} else {
				b.disparar(ole, admin, regresivobombardero);
				b.avanzar(ole);
			}
		}
		admin.getListaBombarderos().removeAll(bomelim);
		System.out.println("Se actualizaron los bombarderos");
	}

	/**
	 * Procesamiento de los misiles del juego
	 * 
	 * @param admin
	 * @param ole
	 */
	public void procesarAliados(Administrador admin) {
		LinkedList<Aliado> borrarAliado = new LinkedList<Aliado>();
		for (Aliado a : admin.getListaAliados()) {
			for (Explosion e : admin.getListaExplosiones()) {
				Coordenada cor1 = new Coordenada(a.getPos().getX(), a.getPos().getY());
				Coordenada cor2 = new Coordenada(e.getPos().getX(), e.getPos().getY());
				if ((admin.distanciaEntreDos(cor1, cor2)) < 5) {
					borrarAliado.add(a);
				}
			}
		}
		admin.getListaAliados().removeAll(borrarAliado);
	}

	public void procesarMisiles(Administrador admin, Oleada ole) {
		LinkedList<Misil> borrarm = new LinkedList<Misil>();
		for (Misil m : admin.getListaMisiles()) {
			for (Explosion e : admin.getListaExplosiones()) {
				Coordenada cor1 = m.getPosc();
				Coordenada cor2 = e.getPos();
				if ((admin.distanciaEntreDos(cor1, cor2)) < 15) {
					if (m.esMBITR()) {
						this.puntajen[0].setX(this.puntajen[0].getX() + 25);
						this.puntajen[4].setX(this.puntajen[4].getX() + 25);
					} else {
						this.puntajen[1].setX(this.puntajen[1].getX() + 125);
						this.puntajen[4].setX(this.puntajen[4].getX() + 125);
					}
					borrarm.add(m);
				} else {
					if (m.esInteligente()) {
						m.desvio(admin, e);
					}
					LinkedList<Coordenada> borrares = new LinkedList<Coordenada>();
					for (Coordenada es : m.getEstela()) {
						if ((admin.distanciaEntreDos(es, cor2)) < 15) {
							borrares.add(es);
						}
					}
					m.getEstela().removeAll(borrares);
				}
			}
			if (m.llegoDestino(admin)) {
				m.explotar(admin);
				borrarm.add(m);
			} else {
				try {
					m.mover();
					int ran = (60 * 5 - 0) + 1;
					int ale = (int) (Math.random() * ran) + 0;
					if (ale == 200) {
						admin.getListaMisiles().element().bifurcacion(ole, admin);	
					}
				}
					catch(RangoMisilExcepcion e) {
						borrarm.add(m);
				}
				// FALTA QUE LOS INTELIGENTES ESQUIVEN
			}
		}
		admin.getListaMisiles().removeAll(borrarm);
		System.out.println("se actualizaron los misiles");
	}

	/**
	 * Procesa la oleada
	 * 
	 * @param admin
	 * @param ole
	 * @param regresivobombardero
	 * @param regresivo
	 * @param grupos
	 * @param regresivoCrucero
	 */

	public void procesar1  (Administrador admin, Oleada ole, Contador regresivobombardero, Contador regresivo, Contador grupos, Contador regresivoCrucero) throws TerminoOleadaExcepcion {
		if(terminoOleada()) {
			throw new TerminoOleadaExcepcion();
		}
		procesarExplosiones(admin);
		if (regresivo.getX() == 0) {
			admin.grupoMisiles(ole, grupos);
			System.out.println("grupos= " + grupos);
			admin.crucero(ole, regresivoCrucero);
			admin.mandarBombardero(ole);
			System.out.println("regresivo=0");
			regresivo.setX(2 * 60);
		}
		regresivo.setX(regresivo.getX() - 1);
		procesarAntimisiles(admin);
		procesarAliados(admin);
		procesarBombarderos(admin, regresivobombardero, ole);
		procesarMisiles(admin, ole);
	}

	/**
	 * Calculo del punataje obtenido durante el juego
	 * 
	 * @param admin
	 * @param ole
	 */
	public void limpiarSilos() {
		LinkedList<Aliado> borrarAliado = new LinkedList<Aliado>();
		for (Aliado a : admin.getListaAliados()) {
			if(a.esSilo()) {
					borrarAliado.add(a);
				}
			}
		admin.getListaAliados().removeAll(borrarAliado);
	}
	public void calcularPuntaje(Administrador admin, Oleada ole) {
		int ciusalvadas = 0;
		int cantantitot = 0;
		for (Aliado a : admin.getListaAliados()) {
			if (a.esSilo()) {
				cantantitot += (((Silo) a).getCantanti()); // me fijo cuantos antimisiles me quedaron sin usar
			} else {
				if (a.esCiudad()) {
					ciusalvadas += 1;
				}
			}
		}
		this.puntajen[2].setX(this.puntajen[2].getX() + (cantantitot * 5));
		this.puntajen[4].setX(this.puntajen[4].getX() + (cantantitot * 5));
		this.puntajen[3].setX(this.puntajen[3].getX() + (ciusalvadas * 100));
		this.puntajen[4].setX(this.puntajen[4].getX() + (ciusalvadas * 100));
		this.puntajet[0]
				.setX((int) (this.puntajet[0].getX() + (this.puntajen[0].getX() * (int) (ole.getNum() / 2) + 0.6)));
		this.puntajet[1]
				.setX((int) (this.puntajet[1].getX() + (this.puntajen[1].getX() * (int) (ole.getNum() / 2) + 0.6)));
		this.puntajet[2]
				.setX((int) (this.puntajet[2].getX() + (this.puntajen[2].getX() * (int) (ole.getNum() / 2) + 0.6)));
		this.puntajet[3]
				.setX((int) (this.puntajet[3].getX() + (this.puntajen[3].getX() * (int) (ole.getNum() / 2) + 0.6)));
		this.puntajet[4]
				.setX((int) (this.puntajet[4].getX() + (this.puntajen[4].getX() * (int) (ole.getNum() / 2) + 0.6)));

	}

	/**
	 * Resetear el puntaje
	 */

	public void resetearPuntaje() {
		for (int i = 0; i < 5; i++) {
			this.puntajen[i].setX(0);
		}
	}

	/**
	 * Se da por finalizada la ejecucion del juego
	 * 
	 * @param lista
	 */

	public boolean terminoElJuego(LinkedList<Aliado> lista) {
		int cont = 0;
		for (Aliado a : lista) {
			if (a.esCiudad()) {
				cont += 1;
			}
		}
		if ((cont == 0) || (ole.getNum() > 15)) {
			if(this.puntajet[4].getX()>10000*this.sigo) { //"ME FIJO SI GANO CIUDAD EXTRA POR EL PUNTAJE"
				this.crearCiudad();
				this.sigo+=1;
				this.imprimo=120; //imprime "CIUDAD EXTRA EN EL PANEL"
			}
			    System.out.print("TERMINO EL JUEGO " + cont + ole.getNum());
				calcularPuntaje(admin, ole);
				setTiempofin((int) ((int) System.currentTimeMillis() - tiempoin) / 1000);
				return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Se da por finalizada la oleada
	 */
	public void crearCiudad() {
		Coordenada corde = new Coordenada(30 + 58, 1);
		Ciudad ciu = new Ciudad(corde);
		admin.getListaAliados().add(ciu);
	}

	public boolean terminoOleada() {
		if (((admin.getListaMisiles().size() == 0) && (this.grupos.getX() == 0)
				&& (admin.getListaBombarderos().size() == 0)) || (this.terminoElJuego(admin.getListaAliados()))) {
			calcularPuntaje(admin, ole);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Inicializa los atributos del juego
	 * 
	 * @param numole
	 */

	public void inicializar(int numole) {
		System.out.println("METODO START");
		admin = new Administrador();
		ole = new Oleada(numole);
		admin.inicializarCiudades();
	}

	/**
	 * Inicializa la Oleada
	 */

	public void inicializarOleada() {
		admin.getListaExplosiones().clear();
		admin.getListaAntimisiles().clear();
		admin.getListaBombarderos().clear();
		limpiarSilos();
		this.resetearPuntaje();
		grupos = new Contador(4); // CANTIDAD DE GRUPOS DE MISILES QUE VAN A LLOVER
		regresivo = new Contador(0); // CUANDO REGRESIVO LLEGUE A 0 VA A LLOVER EL GRUPO DE MISILES
		regresivobombardero = new Contador(60 * 3); // CUANDO REGRESIVOBOMBARDERO LLEGUE A 0 VA A PASAR UN BOMBARDERO
		regresivoCrucero = new Contador(4);
		admin.inicializarSilos();
		admin.grupoMisiles(ole, grupos); // SE MANDA UN GRUPO DE MISILES
		regresivo.setX(3 * 60);
		admin.crucero(ole, regresivoCrucero);
		admin.mandarBombardero(ole);
	}

	/**
	 * Efectua el procesamiento del juego
	 */

	public void procesar() {
		if (!this.pausa) {
			if (!(terminoElJuego(admin.getListaAliados()))) { // MIENTRAS NO TERMINE EL JUEGO
				try { // MIENTRAS NO TERMINE LA OLEADA
					// ENTRADA DEL USUARIO
					procesar1(admin, ole, regresivobombardero, regresivo, grupos, regresivoCrucero);
				}
				catch(TerminoOleadaExcepcion e) {
					this.pausa=true;
					this.timepausa=0;
				}
			}
		} else {
			this.timepausa += 1;
			if (this.timepausa == 60 * 2) {
				ole.setNum(ole.getNum() + 1);
				inicializarOleada();
				this.pausa = false;
			}
		}
	}

	public Puntaje[] getPuntajet() {
		return this.puntajet;
	}

	public Puntaje[] getPuntajen() {
		return this.puntajen;
	}

	public Administrador getAdmin() {
		return this.admin;
	}

	public void setAdmin(Administrador admin) {
		this.admin = admin;
	}

	public Oleada getOle() {
		return ole;
	}

	public void setOle(Oleada ole) {
		this.ole = ole;
	}

	public Contador getGrupos() {
		return grupos;
	}

	public void setGrupos(Contador grupos) {
		this.grupos = grupos;
	}

	public Contador getRegresivo() {
		return regresivo;
	}

	public void setRegresivo(Contador regresivo) {
		this.regresivo = regresivo;
	}

	public Contador getRegresivobombardero() {
		return regresivobombardero;
	}

	public void setRegresivobombardero(Contador regresivobombardero) {
		this.regresivobombardero = regresivobombardero;
	}

	public Contador getRegresivoCrucero() {
		return regresivoCrucero;
	}

	public void setRegresivoCrucero(Contador regresivoCrucero) {
		this.regresivoCrucero = regresivoCrucero;
	}

	public void setPuntajen(Puntaje[] puntajen) {
		this.puntajen = puntajen;
	}

	public void setPuntajet(Puntaje[] puntajet) {
		this.puntajet = puntajet;
	}

	public void setPausa(boolean pausa) {
		this.pausa = pausa;
	}

	public boolean getPausa() {
		return this.pausa;
	}

	public int getTiempofin() {
		return tiempofin;
	}

	public void setTiempofin(int tiempofin) {
		this.tiempofin = tiempofin;
	}

	public int getSigo() {
		return sigo;
	}

	public void setSigo(int sigo) {
		this.sigo = sigo;
	}

	public int getImprimo() {
		return imprimo;
	}

	public void setImprimo(int imprimo) {
		this.imprimo = imprimo;
	}
}
