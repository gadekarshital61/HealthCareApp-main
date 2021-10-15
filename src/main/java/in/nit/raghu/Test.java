package in.nit.raghu;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//import java.util.stream.Collectors;

public class Test {
	public static void main(String[] args) {
		/*//JDK 9
		List<Object[]> list = List.of(
				new Object[] {10,"AA"},
				new Object[] {11,"BB"},
				new Object[] {12,"CC"},
				new Object[] {13,"DD"},
				new Object[] {14,"EE"}
				);

		//Java 8 Streams
		Map<Integer,String> map =
				list
				.stream()
				.collect(Collectors.toMap(
						ob->Integer.valueOf(
								ob[0].toString()), 
						ob->ob[1].toString()));
		//for Each
		Map<Integer,String> map2 = new HashMap<>();
		for(Object[] ob:list) {
			map2.put(
					Integer.valueOf(ob[0].toString()), 
					ob[1].toString());
		}
	}
	*/
	//Universally Unique IDentifier  : UUID (String)
	/*
	 * String pwd = UUID.randomUUID() .toString() .replace("-","") .substring(0, 8);
	 * System.out.println(pwd);
	 */
		
        String pwd = "SAM";
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encPwd = encoder.encode(pwd);
		
		System.out.println(encPwd);
		}
}
