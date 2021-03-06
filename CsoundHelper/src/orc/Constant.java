package orc;

public class Constant<T> extends Statement {
	
	T constant;

	public Constant(T constant) {
		super(constant.toString(), true, 1);
		this.constant = constant;
	}

	@Override
	public String toString() {
		defined = true;
		return alias;
	}
}
