package model;

public class Croyance {
	float a;
	float n;
	float i;
	float c;
	
	
	
	public Croyance(float a, float n, float i, float c) {
//		super();
		this.a = a;
		this.n = n;
		this.i = i;
		this.c = c;
	}

	public Croyance(Croyance c) {
		this.a = c.getA();
		this.n = c.getN();
		this.i = c.getI();
		this.c = c.getC();
	}


	@Override
	public String toString() {
		return "Croyance [ m(a)=" + a + ", m(non a)=" + n + ", m(i)=" + i + ", m(c)=" + c + " ]";
	}
	
	
	public float getA() {
		return a;
	}
	public void setA(float a) {
		this.a = a;
	}
	public float getN() {
		return n;
	}
	public void setN(float n) {
		this.n = n;
	}
	public float getI() {
		return i;
	}
	public void setI(float i) {
		this.i = i;
	}
	public float getC() {
		return c;
	}
	public void setC(float c) {
		this.c = c;
	}
	
	
	
}
