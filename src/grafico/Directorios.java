package grafico;

import java.awt.image.BufferedImage;

public class Directorios {
	public static BufferedImage avion;
	public static BufferedImage avion2;
	public static BufferedImage satelite;
	public static BufferedImage ciudad;
	public static BufferedImage MBITR;
	public static BufferedImage crucero;
	public static BufferedImage antimisil;
	public static BufferedImage silo;
	public static BufferedImage explosion;
	public static BufferedImage ciuextra;
	public static void init()
	{
		avion = Loader.ImageLoader("/grafica/imagenes/avion.png");
		avion2 = Loader.ImageLoader("/grafica/imagenes/avion2.png");
		satelite=Loader.ImageLoader("/grafica/imagenes/satelite.png");
		ciudad=Loader.ImageLoader("/grafica/imagenes/ciudad.png");
		crucero=Loader.ImageLoader("/grafica/imagenes/crucero.png");
		MBITR=Loader.ImageLoader("/grafica/imagenes/MBITR.png");
		silo=Loader.ImageLoader("/grafica/imagenes/base.png");
		explosion=Loader.ImageLoader("/grafica/imagenes/explosion.png");
		antimisil=Loader.ImageLoader("/grafica/imagenes/antimisil.png");
		ciuextra=Loader.ImageLoader("/grafica/imagenes/ciudadextra.png");
	}
}
