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
		
		final long MOD = (long) Math.pow(2, 32);
		int C = Integer.parseInt(br.readLine());

		while (C-- > 0) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken()); // 합
			int N = Integer.parseInt(st.nextToken()); // 개수
			
			long seed = 1983;
			int sum = 0;
			int cnt = 0;
			
			Vector<Integer> v = new Vector<>();
			for(int i = 1; i <= N; i++) {
				int num = (int)(seed % 10000 + 1);
				sum += num;
				v.add(num);
				
				while(sum >= K && !v.isEmpty()) {
					if(sum == K) {
						cnt++;
					}
					sum -= v.remove(0);
				}
				seed = (seed * 214013 + 2531011) % MOD;
			}
			
			bw.write(cnt + "\n");

		}

		bw.flush();
		bw.close();
		br.close();
	}

}
