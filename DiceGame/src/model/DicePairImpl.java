package model;

import java.util.Random;

import model.interfaces.DicePair;
import model.interfaces.Die;

public class DicePairImpl implements DicePair {
	private Die die1;
	private Die die2;

	public DicePairImpl() {
		Random random = new Random();
		die1 = new DieImpl(1, random.nextInt(Die.NUM_FACES) + 1, Die.NUM_FACES);
		die2 = new DieImpl(2, random.nextInt(Die.NUM_FACES) + 1, Die.NUM_FACES);
	}

	public DicePairImpl(Die die1, Die die2) {
		this.die1 = die1;
		this.die2 = die2;

	}

	@Override
	public Die getDie1() {
		return die1;
	}

	@Override
	public Die getDie2() {
		return die2;
	}

	@Override
	public int getTotal() {
		return die1.getValue() + die2.getValue();
	}

	@Override
	public boolean equals(DicePair dicePair) {

		return (dicePair == null) ? false : die1.equals(dicePair.getDie1()) && die2.equals(dicePair.getDie2());
	}

	@Override
	public boolean equals(Object dicePair) {
		return !(dicePair instanceof DicePair) ? false : this.equals((DicePair) dicePair);
	}

	@Override
	public int hashCode() {
		return die1.hashCode() + die2.hashCode();

	}

	@Override
	public String toString() {
		return String.format("%Dice 1: %s, Dice 2: %s ..Total: %d", die1.toString(), die2.toString(), this.getTotal());
	}

	@Override
	public int compareTo(DicePair dicePair) {
		return getTotal() - dicePair.getTotal();
	}

}
