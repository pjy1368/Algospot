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

		int T = Integer.parseInt(br.readLine());
		final int MAX = 2000000000;
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 플레이 횟수
			int M = Integer.parseInt(st.nextToken()); // 승리 횟수
			int Z = (int) Math.floor((double) M * 100 / N); // 승률
			// M / N * 100과 같은 형식을 하면, 부동소수점 연산에 의해서 오차값이 날 수 있음.
			
			if (N == M || !Odds(N, M, Z, MAX)) {
				bw.write("-1\n");
				continue;
			}

			long lo = 1, hi = MAX;
			for (int j = 0; j < 100; j++) {
				long mid = (lo + hi) / 2;
				if (!Odds(N, M, Z, mid)) {
					lo = mid;
				} else {
					hi = mid;
				}
			}
			bw.write(hi + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

	public static boolean Odds(long N, long M, long Z, long win) {
		double odd = (double) (M + win) * 100 / (N + win);
		if (odd - Z < 1) {
			return false;
		}
		return true;
	}

}
