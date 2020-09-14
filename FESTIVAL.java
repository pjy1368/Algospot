import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());

			int[] arr = new int[N + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			int[] psum = new int[N + 1]; // 부분 합
			psum[0] = 0;

			for (int i = 1; i <= N; i++) {
				psum[i] = psum[i - 1] + arr[i];
			}

			double ans = Double.MAX_VALUE;

			// 1일 ~ (N - L + 1)일을 시작점으로 잡음.
			// 시작점과 (i + L - 1)일 까지의 평균 비용을 구함.
			for (int i = 1; i <= N - L + 1; i++) {
				double res = Double.MAX_VALUE;
				for (int j = i + L - 1; j <= N; j++) {
					double temp = (psum[j] - psum[i - 1]) / (double) (j - i + 1);
					res = Math.min(res, temp);
				}
				ans = Math.min(ans, res);
			}
			sb.append(ans + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}