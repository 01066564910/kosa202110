
public class Ex061 {
	String name;
	int age;
	double height;
	/// �ڹٿ��� �����ڴ� Ŭ������� ���ƾ� �Ѵ�.
	// ������
//  ���������� Ŭ������(�Ű�����,...)
	public Ex061(String name, int age, double height) {
		this.name = name;
		this.age = age;
		this.height = height;
		System.out.println("������ ����");
	}
	// �޼��� : 
//  ���������� ��ȯ�� �Լ��� (�Ű�����)
	// setter
	     //��ȯ��(void:��ȯ�Ұ� ����)
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	// getter
	public int getAge() {
		// ������ ��ȯ
		return this.age;
	}
	public double getHeight() {
		return this.height;
	}
	public String getName() {
		return this.name;
	}
}
