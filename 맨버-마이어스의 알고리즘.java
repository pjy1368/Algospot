import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
	static int t, n;
	static int[] tg, g;
	static Integer[] SA;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str = "banana";

		g = new int[str.length() + 1];
		tg = new int[str.length() + 1];
		SA = new Integer[str.length()];

		getSA(str);

		for (int i = 0; i < n; i++) {
			System.out.println(str.substring(SA[i], str.length()));
		}

		for (int i = 0; i < n; i++) {
			System.out.print(SA[i] + " ");
		}
		System.out.println();
		bw.flush();
		bw.close();
		br.close();
	}

	public static void getSA(String str) {
		t = 1;
		n = str.length(); // 글자의 수 배정
		
		// 첫 글자에 의한 그룹을 정해주는 전처리
		for (int i = 0; i < n; i++) {
			SA[i] = i;
			g[i] = str.charAt(i) - 'a';
		}
		// 예를 들어 str = "banana"에 경우
		// g[0] :: banana :: 1
		// g[1] :: anana :: 0
		// g[2] :: nana :: 13
		// g[3] :: ana :: 0
		// g[4] :: na :: 13
		// g[5] :: a :: 0
		
		// 1, 2, 4, 8, ... 씩 단어의 길이보다 작은 경우를 탐색
		while (t <= n) {
			g[n] = -1;
			// g[n]에 -1을 넣는 이유
			// g[n]은 길이가 0인 접미사를 뜻하는데, 이를 정렬하게 되면
			// 항상 g의 맨앞에 오므로 원래 g의 가장 작은 값인 0보다 더 작은 음수를 넣어준다.
			
			// 그룹에 의한 정렬
			Arrays.sort(SA, new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					// 그룹 번호가 같으면 
					if (g[o1] == g[o2]) {
						return g[o1 + t] - g[o2 + t];
					}
					// 그룹 번호가 다르면
					return g[o1] - g[o2];
				}

			});
			// str = "banana"에 경우,
			// SA[] = {0, 1, 2, 3, 4, 5}였던 것이
			// SA[] = {5, 1, 3, 0, 2, 4}로 바뀌게 된다.
			// 즉, a/anana/ana/banana/nana/na 순서로 바뀌었다.
			
			tg[SA[0]] = 0; // 다음 그룹을 할당하기 위하여 teamgroup의 첫 번째 번호 배정
			// str = "banana"에 경우
			// tg[5] = 0이 된다.
			
			
			// 다음 그룹 배정 - t번째 글자 기준으로 그룹이 될 대상을 만든다는 의미.
			for (int i = 1; i < n; i++) {
				// 그룹이 다를 경우 다음 그룹 번호 할당
				if (cmp(SA[i - 1], SA[i])) {
					tg[SA[i]] = tg[SA[i - 1]] + 1;
				} else { // 그룹이 같을 경우 같은 그룹 번호 할당
					tg[SA[i]] = tg[SA[i - 1]];
				}
			}
			// str = "banana"에 경우
			// tg[] = {2, 1, 3, 1, 3, 0}이 된다.
			
			
			// 새로운 그룹 할당
			for (int i = 0; i < n; i++) {
				g[i] = tg[i];
			}

			t <<= 1; // t *= 2
		}
	}

	public static boolean cmp(int x, int y) {
		// 그룹 번호가 같으면
		if (g[x] == g[y]) {
			return g[x + t] < g[y + t]; // 각각 t만큼의 다음 문자를 비교한다.
		}
		// 그룹 번호가 다르면
		return g[x] < g[y];
	}

}
