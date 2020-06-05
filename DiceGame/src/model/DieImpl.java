package model;

import model.interfaces.DicePair;
import model.interfaces.Die;

public class DieImpl implements Die {
	
	private int number;
	private int value;
	private int numFaces;

    public DieImpl(int number, int value, int numFaces) throws IllegalArgumentException 
    {
    	if ((number < 1 || number > 2) || value < 1 || value > numFaces || numFaces < 1)
    		throw new IllegalArgumentException("Please check the input number."); 
    	
    	this.number = number;
    	this.value = value;
    	this.numFaces = numFaces;
    }


	@Override
	public int getNumber() {
		return number;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public int getNumFaces() {
		return numFaces;
	}

	@Override
	public boolean equals(Die die) {
		return (die == null) ? false : numFaces == die.getNumFaces() && value ==die.getValue();
	}
	
	@Override
	public boolean equals(Object die) {
		return !(die instanceof Die) ? false : this.equals((Die) die);
	}

	@Override
	public int hashCode() { 
		return new Integer(numFaces).hashCode() + new Integer(value).hashCode();
		
	};
	   
	 @Override
	 public String toString() {
		 String[] list = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight","Nine", "Ten",
					"Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
			return list[getValue()];
		 
	 };

}
