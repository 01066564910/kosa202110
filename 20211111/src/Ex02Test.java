
public class Ex02Test {
	public static void main(String[] args) {
		// final������ �����ڸ� ���ؼ� �ʱ�ȭ�� ���� 
		Ex02 ex02 = new Ex02(10, 20,"dltndan");
		// �ʱ�ȭ�� ���� �������� ���Ѵ�. 
		// ex02.first = 20;
		Ex02 ex021 = new Ex02(30, 40,"abanbsbj");
		System.out.println(ex02.first); // 10
		System.out.println(ex021.first); // 30
		// ��ü���� final������ ���� ������ ���� �� �ִ�.
	}
}