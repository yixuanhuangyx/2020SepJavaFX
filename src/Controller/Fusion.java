package Controller;

import Model.Croyance;

public class Fusion {

	/*
	 * la fusion de la croyance locale et la croyance propag¨¦e
	 * */
	public static Croyance fusion(Croyance cry1, Croyance cry2) {
		final float a = cry1.getA()*cry2.getA() + cry1.getI()*cry2.getA() + cry2.getI()*cry1.getA();
		final float n = cry1.getN()*cry2.getN() + cry1.getI()*cry2.getN() + cry2.getI()*cry1.getN();
		final float i = cry1.getI()*cry2.getI();
		final float c = 1-a-n-i;
		return new Croyance(a,n,i,c);
	}
	
}
