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

			int[] result = solveSimulation(recipe, put);
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

	public static int[] solveSimulation(int[] recipe, int[] put) {
		int n = recipe.length;
		int[] ret = new int[n];

		// 각 재료를 최소한 recipe에 적힌 만큼은 넣어야 한다.
		for (int i = 0; i < n; i++) {
			ret[i] = Math.max(recipe[i] - put[i], 0);
			put[i] += ret[i];
		}

		// 비율이 전부 맞을 때까지 재료를 계속 추가하자.
		while (true) {
			// 냄비에 재료를 더 넣지 않아도 될 떄까지 반복한다.
			boolean ok = true;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					// i번째 자료에 의하면 모든 재료는 put[i] / recipe[i] = X배 이상은 넣어야 한다.
					// 따라서 put[i]는 recipe[i] * X 이상의 정수가 되어야 한다.
					int required = (put[i] * recipe[j] + recipe[i] - 1) / recipe[i];
					// (recipe[i] - 1)을 하는 이유는 이를 올림하기 위함인데, 위 코드 대신에 아래와 같이
					// 대신 아용해도 문제는 없다. 다만, 연산 과정에서 불필요한 추가 시간이 붙게 된다.
					// int required = (int) Math.ceil((double) put[i] * recipe[j] / recipe[i]);

					// 더 넣어야하는가?
					if (required > put[j]) {
						ret[j] += required - put[j];
						put[j] = required;
						ok = false;
					}
				}
			}
			if (ok) {
				break;
			}
		}
		return ret;
	}
}
