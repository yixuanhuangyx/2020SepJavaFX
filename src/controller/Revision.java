package controller;

import model.Croyance;
import model.etatEnum;

public class Revision {


	public static Croyance regleRevisionInterne(Croyance cryI, Croyance cryA) {
		final float a = cryI.getA()/(cryI.getA()*cryI.getN()*cryI.getI())*cryA.getI()+cryA.getA();
		final float n = cryI.getN()/(cryI.getA()*cryI.getN()*cryI.getI())*cryA.getI()+cryA.getN();
		final float i = cryI.getI() * cryA.getA();
		final float c = 1-a-n-i;
		return new Croyance(a,n,i,c);
	}
	
	public static Croyance moyenne(Croyance cry1, Croyance cry2) {
		final float a = (cry1.getA()+cry2.getA())/2;
		final float n = (cry1.getN()+cry2.getN())/2;
		final float i = (cry1.getI()+cry2.getI())/2;
		final float c = 1-a-n-i;
		return new Croyance(a,n,i,c);
	}

	
	/*
	 * Si le m¨ºme ¨¦tat alors proc¨¦dure 1: r¨¨gle de la r¨¦vision interne d¨¦taill¨¦ ds l'article
	 * Sinon proc¨¦dure 2: la moyenne de les deux croyances
	 * */
	public static boolean sameEtat(Croyance cry1, Croyance cry2) {
		return getEtat(cry1)==getEtat(cry2);
	}
	
	
	/*
	 * m(a) > 0 et m(n) =0    ¨¦tat acquise 
	 * m(a)= 0 et  m(n) > 0   ¨¦tat non acquise
	 * m(a)> 0 et m(i)> 0 ¨¦tat probablement acquise 
	 * m(n)> 0 et m(i)> 0 ¨¦tat probablement non acquise 
	 * */
	private static etatEnum getEtat(Croyance cry) {
		if(cry.getA() > 0 && cry.getN() == 0) {
			return etatEnum.acquise;
		} else if(cry.getA() == 0 && cry.getN() > 0) {
			return etatEnum.nonAcquise;
		} else if(cry.getA() > 0 && cry.getI() > 0) {
			return etatEnum.probablementAcquise;
		} else if(cry.getN() > 0 && cry.getI() > 0) {
			return etatEnum.probablementNonAcquise;
		}else 
			return etatEnum.undefined;
	}
	
}
