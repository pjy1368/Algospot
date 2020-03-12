import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Vector;

class Prime {
	int num;
	int cnt;

	public Prime(int num, int cnt) {
		this.num = num;
		this.cnt = cnt;
	}
}

public class Main {
	static int[] minFactor;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int c = Integer.parseInt(br.readLine());

		minFactor = new int[10000001];
		Eratos(10000000);
		
		for (int i = 0; i < c; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); // 약수의 개수
			int lo = Integer.parseInt(st.nextToken()); // 최소 범위
			int hi = Integer.parseInt(st.nextToken()); // 최대 범위

			int cnt = 0;
			for (int j = lo; j <= hi; j++) {
				if (Factor(n, j, minFactor)) {
					cnt++;
				}
			}

			bw.write(cnt + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	public static int[] Eratos(int hi) {
		minFactor[0] = minFactor[1] = -1;

		// 처음에는 소수라고 가정.
		for (int i = 2; i <= hi; i++) {
			minFactor[i] = i;
		}

		for (int i = 2; i * i <= hi; i++) {
			if (minFactor[i] == i) { // 소수일 경우,
				for (int j = i * i; j <= hi; j += i) {
					if (minFactor[j] == j) { // 아직 약수를 본 적 없는 숫자인 경우 i를 써 둔다.
						minFactor[j] = i;
					}
				}
			}
		}

		return minFactor;
	}

	public static boolean Factor(int n, int hi, int[] minFactor) {
		Vector<Prime> v = new Vector<>();

		while (hi > 1) {
			int temp = minFactor[hi];
			if (v.isEmpty()) {
				v.add(new Prime(temp, 1));
			} else {
				if (v.lastElement().num == temp) {
					v.lastElement().cnt++;
				} else {
					v.add(new Prime(temp, 1));
				}
			}

			hi /= temp;
		}

		int total = 1;

		for (int i = 0; i < v.size(); i++) {
			total *= (v.get(i).cnt + 1);
		}

		return n == total;
	}

}
