import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int c = Integer.parseInt(br.readLine());
		for (int i = 0; i < c; i++) {
			int n = Integer.parseInt(br.readLine());
			int[] recipe = new int[n];
			int[] put = new int[n];

			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				recipe[j] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				put[j] = Integer.parseInt(st.nextToken());
			}

			int[] result = Solve(recipe, put);
			bw.write(String.valueOf(result[0]));
			for (int j = 1; j < n; j++) {
				bw.write(" " + result[j]);
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int Gcd(int a, int b) {
		return b == 0 ? a : Gcd(b, a % b);
	}
	
	public static int Ceil(int a, int b) {
		return (a + b - 1) / b;
	}
	
	public static int[] Solve(int[] recipe, int[] put) {
		int n = recipe.length;
		
		// 모든 recipe의 최대공약수를 구한다.
		int b = recipe[0];
		for(int i = 1; i < n; i++) {
			b = Gcd(b, recipe[i]);
		}
		
		int a = b;
		// X를 직접 구하는 대신 ceil(put[i] * b, recipe[i])의 최대값을 구한다.
		for(int i = 0; i < n; i++) {
			a = Math.max(a, Ceil(put[i] * b, recipe[i]));
		}
		// a / b 배 분량을 만들면 된다.
		int[] ret = new int[n];
		for(int i = 0; i < n; i++) {
			ret[i] = recipe[i] * a / b - put[i];
		}
		return ret;
	}

	
}
