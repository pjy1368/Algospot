import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static final int MOD = 20091101;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 인형 상자의 개수
			int K = Integer.parseInt(st.nextToken()); // 어린이의 수

			int[] box = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				box[i] = Integer.parseInt(st.nextToken());
			}

			// 부분 합을 구한다.
			int[] psum = partialSum(box, K);

			long cnt = waysToBuy(psum, K);
			bw.write(cnt + " ");

			cnt = maxBuys(psum, K);
			bw.write(cnt + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	public static int[] partialSum(int[] box, int K) {
		int[] psum = new int[box.length + 1];

		psum[0] = 0; // 첫 번째 상자부터 주문하는 경우가 있으므로 0으로 따로 초기화.
		for (int i = 1; i <= box.length; i++) {
			psum[i] = (psum[i - 1] + box[i - 1]) % K; // 부분 합이 모두 K의 배수가 되어야 함.
		}

		return psum;
	}

	public static long waysToBuy(int[] psum, int K) {
		long cnt = 0;
		long[] count = new long[K]; // psum[]의 각 값을 몇 번이나 본 적 있는지 기록한다.
		for (int i = 0; i < psum.length; i++) {
			count[psum[i]]++;
		}

		// 두 번 이상 본 적 있다면 이 값 중 두 개를 선택하는 방법의 수를 더한다.
		for (int i = 0; i < K; i++) {
			if (count[i] >= 2) {
				cnt = (cnt + ((count[i] * (count[i] - 1)) / 2)) % MOD; // 2개를 뽑는 조합의 경우의 수를 나타낸 식.
			}
		}

		return cnt;
	}

	// 부분 합 배열 psum[]과 K가 주어질 때, 겹치지 않게 몇 번이나 살수 있는지 반환한다.
	// psum[]의 첫 번째 원소 전에 0을 삽입했다고 가정한다.
	public static long maxBuys(int[] psum, int K) {
		int[] dp = new int[psum.length]; // 첫 번째 상자부터 i번째 상자까지 고려했을 떄 살 수 있는 최대 횟수
		int[] prev = new int[K]; // prev[s] = psum[]이 s였던 마지막 위치.
		Arrays.fill(prev, -1);

		for (int i = 0; i < psum.length; i++) {
			// i 번째 상자를 아예 고려하지 않는 경우
			if (i > 0) {
				dp[i] = dp[i - 1];
			} else {
				dp[i] = 0;
			}

			// psum[i]를 전에도 본 적이 있으면, prev[psum[i]] + 1부터 여기까지 쭉 사 본다.
			int loc = prev[psum[i]];
			if (loc != -1) {
				dp[i] = Math.max(dp[i], dp[loc] + 1);
			}
			prev[psum[i]] = i;
		}

		return dp[psum.length - 1];
	}

}
