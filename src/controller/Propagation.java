package controller;

import model.Croyance;

public class Propagation {
	public static Croyance propagation(int n, float d, Croyance B) {
		float factor = (float) Math.pow(1-d, n);
		Croyance res = new Croyance(
				factor * B.getA(), 
				factor * B.getN(), 
				factor * B.getI() + d, 
				factor * B.getC()
				);
		return res;
	}
}
