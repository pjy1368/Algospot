import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int n = 100;
	static final int MAX_N = 100;
	static char[] sieve = new char[(MAX_N + 7) / 8];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		Eratos();
		for (int i = 0; i <= n; i++) {
			System.out.print(i + "=" + isPrime(i) + " ");
			if (i % 10 == 0) {
				System.out.println();
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}

	// k가 소수인지 확인한다.
	public static boolean isPrime(int k) {
		if ((sieve[k >> 3] & (1 << (k & 7))) > 0) {
			// k를 3으로 나눈 몫에 해당하는 sieve 인덱스값
			// k & 7 -> k를 8로 나눈 나머지
			// 2의 (k & 7) 제곱
			// 0 이외의 값이 나오면 소수
			return true;
		} else { // 그렇지 않으면 합성수
			return false;
		}
	}

	// k가 소수가 아니라고 표시한다.
	public static void setComposition(int k) {
		sieve[k >> 3] &= ~(1 << (k & 7)); // sieve 배열에 소수가 아닌 부분은 0으로 바뀐다.
	}

	public static void Eratos() {
		for (int i = 0; i < sieve.length; i++) {
			sieve[i] = 255; // 처음에는 다 11111111로 초기화한다.
		}

		setComposition(0);
		setComposition(1);

		for (int i = 2; i * i <= n; i++) {
			if (isPrime(i)) {
				for (int j = i * i; j <= n; j += i) {
					setComposition(j);
				}
			}
		}

	}

}
