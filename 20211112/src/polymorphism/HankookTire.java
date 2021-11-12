package polymorphism;
 //              B                 A  ; a = b
public class HankookTire extends Tire { // tire = HankookTire
	public HankookTire(String location, int maxRotation) {
		super(location, maxRotation);
	}
	@Override
	public boolean roll() { // �������̵�
		++accumulatedRotation;
		if (accumulatedRotation < maxRotation) {
			System.out.println(location + "HankookTire ���� : " +
						(maxRotation - accumulatedRotation) + "ȸ");
			return true;
		}else {
			System.out.println("*** " + location + 
					" HankookTire ��ũ ***");
			return false;
		}
	}
}
