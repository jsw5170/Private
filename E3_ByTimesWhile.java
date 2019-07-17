
public class E3_ByTimesWhile {

	public static void main(String[] args) {
		int i=2,j=1;
		while(i<10) {
			System.out.println("------------------");
			while(j<10) {
				System.out.printf("%d * %d = %d \n",i,j,i*j);
				j++;
			}
			j=1;
			i++;
		}
	}
}
