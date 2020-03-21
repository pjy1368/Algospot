import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.Vector;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String input = br.readLine();
		input += br.readLine();

		Vector<Integer> ret = getPrefixSuffix(input);
		Collections.sort(ret);

		bw.write(String.valueOf(ret.get(0)));
		for (int i = 1; i < ret.size(); i++) {
			bw.write(" " + ret.get(i));
		}
		bw.write("\n");
		bw.flush();
		bw.close();
		br.close();
	}

	// 실패 함수
	public static Vector<Integer> makeTable(String pattern) {
		int patternSize = pattern.length();
		Vector<Integer> table = new Vector<>(pattern.length());
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
//	public static void KMP(String parent, String pattern) {
//		Vector<Integer> table = makeTable(pattern);
//		int parentSize = parent.length();
//		int patternSize = pattern.length();
//		int j = 0;
//		for (int i = 0; i < parentSize; i++) {
//			while (j > 0 && parent.charAt(i) != pattern.charAt(j)) {
//				j = table.get(j - 1);
//			}
//
//			if (parent.charAt(i) == pattern.charAt(j)) {
//				if (j == patternSize - 1) {
//					System.out.println((i - patternSize + 1) + "번째에서 찾았습니다.");
//					j = table.get(j);
//				} else {
//					j++;
//				}
//			}
//		}
//	}

	public static Vector<Integer> getPrefixSuffix(String s) {
		Vector<Integer> ret = new Vector<>();
		Vector<Integer> pi = makeTable(s);
		int k = s.length();
		while (k > 0) {
			ret.add(k);
			k = pi.get(k - 1);
		}
		return ret;
	}

}
