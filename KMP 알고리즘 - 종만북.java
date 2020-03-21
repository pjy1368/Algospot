import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Vector;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String H = "aabaabac";
		String N = "aabaa";

//		Vector<Integer> ret = naiveSearch(H, N);
//		for (int i = 0; i < ret.size(); i++) {
//			bw.write(ret.get(i) + "\n");
//		}

		Vector<Integer> ret = kmpSearch(H, N);
		for (int i = 0; i < ret.size(); i++) {
			bw.write(ret.get(i) + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	// 모든 시작 위치를 비교하는 단순한 알고리즘
	// '짚더미' H의 부분 문자열로 '바늘' N이 출현하는 시작 위치들을 모두 반환한다.
	public static Vector<Integer> naiveSearch(String H, String N) {
		Vector<Integer> ret = new Vector<>();
		// 모든 시작 위치를 다 시도해 본다.
		for (int begin = 0; begin + N.length() <= H.length(); begin++) {
			boolean matched = true;
			for (int i = 0; i < N.length(); i++) {
				if (H.charAt(begin + i) != N.charAt(i)) {
					matched = false;
					break;
				}
			}
			if (matched) {
				ret.add(begin);
			}
		}
		return ret;
	}

	// '짚더미' H의 부분 문자열로 '바늘' N이 출현하는 시작 위치들을 모두 반환하다.
	public static Vector<Integer> kmpSearch(String H, String N) {
		int n = H.length(), m = N.length();
		Vector<Integer> ret = new Vector<>();

		// pi[i] = N[..i]의 접미사도 되고 접두사도 되는 문자열의 최대 길이
		Vector<Integer> pi = getPartialMatch(N);

		// begin = matched = 0에서부터 시작하자.
		int begin = 0, matched = 0;
		while (begin <= n - m) {
			// 만약 짚더미의 해당 글자가 바늘의 해당 글자와 같다면
			if (matched < m && H.charAt(begin + matched) == N.charAt(matched)) {
				matched++;
				// 결과적으로 m 글자가 모두 일치했다면 답에 추가한다.
				if (matched == m) {
					ret.add(begin);
				}
			} else {
				// 예외 : matched가 0인 경우에는 다음 칸에서부터 계속
				if (matched == 0) {
					begin++;
				} else {
					begin += matched - pi.get(matched - 1);
					// begin을 옮겼다고 처음부터 다시 비교할 필요가 없다.
					// 옮긴 후에도 pi.get(matched - 1)만큼은 항상 일치하기 때문이다.

					matched = pi.get(matched - 1);
				}
			}
		}
		return ret;
	}

	// N에서 자기 자신을 찾으면서 나타나는 부분 일치를 이용해
	// pi[]를 계산한다.
	// pi[i]=N[..i]의 접미사도 되고 접두사도 되는 문자열의 최대 길이
	public static Vector<Integer> getPartialMatchNaive(String N) {
		int m = N.length();
		Vector<Integer> pi = new Vector<>();
		for (int i = 0; i < m; i++) {
			pi.add(0);
		}

		// 단순한 문자열 검색 알고리즘을 구현한다.
		for (int begin = 1; begin < m; begin++) {
			for (int i = 0; i + begin < m; i++) {
				if (N.charAt(begin + i) != N.charAt(i)) {
					break;
				}
				// i + 1 글자가 서로 대응되었다.
				pi.setElementAt(Math.max(pi.get(begin + i), i + 1), begin + i);
			}
		}
		return pi;
	}

	// N에서 자기 자신을 찾으면서 나타나는 부분 일치를 이용해
	// pi[]를 계산한다.
	// pi[i]=N[..i]의 접미사도 되고 접두사도 되는 문자열의 최대 길이
	public static Vector<Integer> getPartialMatch(String N) {
		int m = N.length();
		Vector<Integer> pi = new Vector<>();
		for (int i = 0; i < m; i++) {
			pi.add(0);
		}

		// KMP로 자기 자신을 찾는다.
		// N을 N에서 찾는다. begin=0이면 자기 자신을 찾아버리니까 안 됨!
		int begin = 1, matched = 0;
		// 비교할 문자가 N의 끝에 도달할 때까지 찾으면서 부분 일치를 모두 기록한다.
		while (begin + matched < m) {
			if (N.charAt(begin + matched) == N.charAt(matched)) {
				matched++;
				pi.set(begin + matched - 1, matched);
			} else {
				if (matched == 0) {
					begin++;
				} else {
					begin += matched - pi.get(matched - 1);
					matched = pi.get(matched - 1);
				}
			}
		}
		return pi;
	}

}
