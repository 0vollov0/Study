package decorator;

public class Whip extends CondimentDecorator {
	Beverage beverage;
	public Whip(Beverage beverage) {
		this.beverage = beverage;
	}
	
	@Override
	public String getDescription() {
		return beverage.getDescription() + ", ���� ũ��";
	}
	
	@Override
	public double cost() {
		return 0.10 + beverage.cost();
	}

}
