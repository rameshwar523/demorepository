import java.util.Scanner;
public class OddEvenNumber {
public void oddEvenCheck(int num) {
	int num1=num;
	if(num1%2==0) {
		System.out.println("Number is Even");
	}else {
		System.out.println("Number is Odd");
	}
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scannerObj=new Scanner(System.in);
		OddEvenNumber oddEvenObj=new OddEvenNumber();
		System.out.println("Enter the Number");
		int num2=scannerObj.nextInt();
		oddEvenObj.oddEvenCheck(num2);
		

	}

}
