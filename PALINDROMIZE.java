import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Vector;

public class Main {
	static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int C = Integer.parseInt(br.readLine());
		while (C-- > 0) {
			String parent = br.readLine(); // 기존에 있던 문자열
			StringBuilder sb = new StringBuilder(parent);
			String pattern = sb.reverse().toString(); // 역순으로 한 문자열
			
			// 기존의 문자열 길이의 2배를 한 값에 겹친 부분을 빼면 가장 짧은 팰린드롬의 길이가 된다.
			int ans = parent.length() * 2 - maxOverlap(parent, pattern);
			bw.write(ans + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

	public static Vector<Integer> makeTable(String pattern) {
		Vector<Integer> table = new Vector<>();
		for (int i = 0; i < pattern.length(); i++) {
			table.add(0);
		}

		int j = 0;
		for (int i = 1; i < pattern.length(); i++) {
			while (j > 0 && pattern.charAt(j) != pattern.charAt(i)) {
				j = table.get(j - 1);
			}

			if (pattern.charAt(j) == pattern.charAt(i)) {
				table.set(i, ++j);
			}

		}
		return table;
	}
	
	// 겹친 문자열의 길이를 반환.
	public static int maxOverlap(String parent, String pattern) {
		Vector<Integer> pi = makeTable(pattern);

		int j = 0;
		for (int i = 0; i < parent.length(); i++) {
			while (j > 0 && parent.charAt(i) != pattern.charAt(j)) {
				j = pi.get(j - 1);
			}

			if (parent.charAt(i) == pattern.charAt(j)) {
				if (j == parent.length() - 1) {
					return parent.length();
				} else {
					j++;
				}
			}
		}
		return j;
	}

}
