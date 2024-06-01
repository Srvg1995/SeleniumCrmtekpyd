package practice.datadriventesting;

public class JavaClassReadRuntimeParameter {

	public static void main(String[] args) {
		System.out.println(args.length);
		for (String var : args) {
			System.out.println(var);
		}
	}

}
