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

		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			double N = Double.parseDouble(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			double P = Double.parseDouble(st.nextToken());

			bw.write(String.format("%.10f", Payment(N, M, P)) + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

	// amount원을 연 이율 rates 퍼센트로 duration월 간 한달에 monthlyPayment로 갚았을 때,
	// 남은 잔액은?
	public static double Balance(double amount, int duration, double rates, double monthlyPayment) {
		double balance = amount;
		for (int i = 0; i < duration; i++) {
			balance *= (1.0 + (rates / 12.0) / 100.0);
			balance -= monthlyPayment;
		}
		return balance;
	}

	public static double Payment(double amount, int duration, double rates) {
		double lo = 0, hi = amount * (1.0 + (rates / 12.0) / 100.0);
		// lo원씩 갚으면 duration개월 안에 갚을 수 없다.
		// hi원씩 갚으면 duration개월 안에 갚을 수 있다.
		for (int i = 0; i < 100; i++) {
			double mid = (lo + hi) / 2.0;
			if (Balance(amount, duration, rates, mid) <= 0) { // mid로 balance를 갚을 수 있다면,
				hi = mid; // hi를 mid로 초기화.
			} else { // 그렇지 않으면,
				lo = mid; // lo를 mid로 초기화.
			}
		}
		return hi;
	}

}
