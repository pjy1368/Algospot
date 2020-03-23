import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Vector;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String pattern = "abacaaba";
		Vector<Integer> table = makeTable(pattern);
		for (int i = 0; i < table.size(); i++) {
			System.out.print(table.get(i) + " ");
		}
		System.out.println();

		String parent = "ababacabacaabacaaba";
		KMP(parent, pattern);

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
	public static void KMP(String parent, String pattern) {
		Vector<Integer> table = makeTable(pattern);
		int parentSize = parent.length();
		int patternSize = pattern.length();
		int j = 0;
		for (int i = 0; i < parentSize; i++) {
			while (j > 0 && parent.charAt(i) != pattern.charAt(j)) {
				j = table.get(j - 1);
			}

			if (parent.charAt(i) == pattern.charAt(j)) {
				if (j == patternSize - 1) {
					System.out.println((i - patternSize + 1) + "번째에서 찾았습니다.");
					j = table.get(j);
				} else {
					j++;
				}
			}
		}
	}

}
