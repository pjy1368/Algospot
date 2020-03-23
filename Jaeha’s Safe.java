import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Vector;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());

			String[] dial = new String[N + 1];
			for (int i = 0; i <= N; i++) {
				dial[i] = br.readLine();
			}

			int result = 0;
			for (int i = 0; i < N; i++) {
				if (i % 2 == 1) {
					result += shift(dial[i], dial[i + 1]); // 시계 방향 (그림은 반시계 방향으로 돌리지만, 문자열 자체는 시계 방향으로 돌아감.)
				} else {
					result += shift(dial[i + 1], dial[i]); // 반시계 방향 (그림은 시계 방향으로 돌리지만, 문자열 자체는 반시계 방향으로 돌아감.)
				}
			}
			bw.write(result + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

	// 실패 함수
	public static Vector<Integer> makeTable(String pattern) {
		int patternSize = pattern.length();
		Vector<Integer> table = new Vector<>();
		for (int i = 0; i < patternSize; i++) {
			table.add(0);
		}
		int j = 0;
		for (int i = 1; i < patternSize; i++) {
			while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = table.get(j - 1);
			}
			if (pattern.charAt(i) == pattern.charAt(j)) {
				table.set(i, ++j);
			}
		}
		return table;
	}

	// KMP 알고리즘
	public static int KMP(String parent, String pattern) {
		Vector<Integer> table = makeTable(pattern);
		Vector<Integer> result = new Vector<>();

		int parentSize = parent.length();
		int patternSize = pattern.length();
		int j = 0;
		for (int i = 0; i < parentSize; i++) {
			while (j > 0 && parent.charAt(i) != pattern.charAt(j)) {
				j = table.get(j - 1);
			}

			if (parent.charAt(i) == pattern.charAt(j)) {
				if (j == patternSize - 1) {
					return i - patternSize + 1;
				} else {
					j++;
				}
			}
		}
		return -1;
	}

	public static int shift(String original, String target) {
		return KMP(original + original, target);
	}

}
