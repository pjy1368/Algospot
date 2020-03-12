import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int[] factors = getFactorsBrute(10000000);
		int c = Integer.parseInt(br.readLine());

		for (int i = 0; i < c; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); // 약수의 개수
			int lo = Integer.parseInt(st.nextToken()); // 최소 범위
			int hi = Integer.parseInt(st.nextToken()); // 최대 범위
			
			int cnt = 0;
			for(int j = lo; j <= hi; j++) {
				if(n == factors[j]) {
					cnt++;
				}
			}
			
			bw.write(cnt + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int[] getFactorsBrute(int n) {
		int[] factors = new int[n + 1];
		for(int i = 1; i <= n; i++) {
			for(int j = i; j <= n; j += i) {
				factors[j]++;
			}
		}
		return factors;
	}

}
