import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class C5_ConvertCollection {
	public static void main(String[] args) {
		// 중복을 허용 하는 List
		List<String> lst = Arrays.asList("Toy", "Box", "Box", "Toy");
		ArrayList<String> list = new ArrayList<>(lst);
		
		for(String s : list)
			System.out.print(s.toString() + '\t');
		System.out.println();
		
		// 중복을 허용 않는 Set
		HashSet<String> set = new HashSet<>(list);
		list = new ArrayList<>(set);
		
		for(String s : list)
			System.out.print(s.toString() + '\t');
		System.out.println();
	}
}