package de.matt3o.bruchrechner;

public class Fraction {
	/** Deutsch: Zähler */
	private int numerator;
	
	/** Deutsch: Nenner */
	private int denominator;
	
	/**
	 * Erzeugt eine neue Fraction Instance
	 * 
	 * @param numerator Der Zähler
	 * @param denominator Der Nenner
	 * @throws FractionException Wird ausgelöst, wenn der Nenner oder Zähler eine 0 enthält.
	 */
	public Fraction( int numerator, int denominator ) throws FractionException {
		if ( numerator == 0 || denominator == 0 ){
			throw new FractionException();
		}
		
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	/**
	 * Gibt den numerator (Zähler) zurück.
	 * 
	 * @return numerator (Zähler)
	 */
	public int getNumerator(){
		return numerator;
	}
	
	/**
	 * Gibt den denominator (Nenner) zurück.
	 * @return
	 */
	public int getDenominator(){
		return denominator;
	}
	
	/**
	 * Gibt das GGF zurück.
	 * 
	 * @author http://de.wikipedia.org/wiki/Euklidischer_Algorithmus#Rekursive_Variante
	 * @return
	 */
	private int getGGF( int a, int b ){
		if ( b == 0 )
			return a;
		else
			return getGGF( b, a % b );
	}
	
	public int getGGF(){
		return getGGF( getNumerator(), getDenominator() );
	}
	
	public Fraction trim(){
		int ggf = getGGF();
		Fraction resul;
		try {
			resul = new Fraction( getNumerator()/ggf , getDenominator()/ggf );
		} catch (FractionException e) {
			e.printStackTrace();
			return null;
		}
		
		return resul;
	}
	
	public int getGGT( Fraction fraction2 ){		
		int zahl1 = this.getDenominator();
		int zahl2 = fraction2.getDenominator();
		
		while ( zahl2 != 0 ) {
		   if ( zahl1 > zahl2 ) {
			   zahl1 = zahl1 - zahl2;
		   } else {
			   zahl2 = zahl2 - zahl1;
		   }
		}
		
		return zahl1;
	}
	
	public Fraction addition( Fraction summand2 ) throws MathException{
		int numerator;
		int denominator;
		
		if ( summand2.getDenominator() == this.getDenominator() ){
			numerator = summand2.getNumerator() + this.getNumerator();
			denominator = this.getDenominator();
			return new Fraction( numerator, denominator ).trim();
		}
		
		throw new MathException( "Die beiden Nenner müssen gleich sein!" );
	}
	
	public Fraction multiplicat( Fraction multiplier ) throws FractionException{
		int numerator = multiplier.getNumerator() * this.getNumerator();
		int denominator = multiplier.getDenominator() * this.getDenominator();
		
		return new Fraction( numerator, denominator ).trim();
	}
	
	public Fraction division( Fraction divisor ) throws FractionException{
		Fraction reciprocalValue = new Fraction( divisor.getDenominator(), divisor.getNumerator() );
		
		return multiplicat( reciprocalValue ).trim();
	}
		
	@Override
	public String toString(){
		return "Fraction: [" + numerator + "/" + denominator + "]";
	}
}
