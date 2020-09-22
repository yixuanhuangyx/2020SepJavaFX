package controller;

import model.Croyance;

public class Transformation {
	
	public static Croyance fonctionsTransformation(float note, float x, float r) {
		Croyance croyance = new Croyance(0, 1, 0, 0);
		if (note <= (x - r)) {
			
		} else if (note <= x && note > (x - r)) {
			croyance = new Croyance(0, calculateZone1N(note, x, r), calculateZone1I(note, x, r), 0);
		} else if (note <= (x + r) && note > x) {
			croyance = new Croyance(calculateZone2A(note, x, r), 0, calculateZone2I(note, x, r), 0);
		} else if (note > (x + r)) {
			croyance = new Croyance(1, 0, 0, 0);
		}
		return croyance;
	}
	
	private static float calculateZone2A(float note, float x, float r) {
		float res = note - x;
		res /= r;
		return res;
	}

	private static float calculateZone2I(float note, float x, float r) {
		float res = -1 * note + x;
		res /= r;
		res += 1;
		return res;
	}

	private static float calculateZone1I(float note, float x, float r) {
		float res = note - x;
		res /= r;
		res += 1;
		return res;
	}

	private static float calculateZone1N(float note, float x, float r) {
		float res = -1 * note + x;
		res /= r;
		return res;
	}
}
