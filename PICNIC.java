import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			boolean[][] relation = new boolean[N][N]; // 친구 관계

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				int t = Integer.parseInt(st.nextToken());
				int f = Integer.parseInt(st.nextToken());

				relation[t][f] = relation[f][t] = true;
			}

			boolean[] check = new boolean[N];
			sb.append(recursion(check, relation, N) + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	public static int recursion(boolean[] check, boolean[][] relation, int N) {
		int firstStudent = -1; // 짝을 맺어줄 친구를 선택.

		// 가장 숫자가 작은 친구부터 시작.
		for (int i = 0; i < N; i++) {
			if (!check[i]) {
				firstStudent = i;
				break;
			}
		}

		// 모든 짝이 정해져 있을 경우, 가능한 방법을 1개 찾았음.
		if (firstStudent == -1) {
			return 1;
		}

		int result = 0;
		for (int i = firstStudent + 1; i < N; i++) {
			if (!check[i] && relation[firstStudent][i]) {
				check[firstStudent] = check[i] = true;
				result += recursion(check, relation, N);
				check[firstStudent] = check[i] = false;
			}
		}

		return result;
	}

}