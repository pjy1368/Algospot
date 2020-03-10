import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {
	static final double L = 10;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int C = Integer.parseInt(br.readLine());

		for (int i = 0; i < C; i++) {
			int N = Integer.parseInt(br.readLine());

			Vector<Double> poly = new Vector<>();
			st = new StringTokenizer(br.readLine());

			while (st.hasMoreTokens()) {
				double temp = Double.parseDouble(st.nextToken());
				poly.add(temp);
			}

			Vector<Double> result = Solve(poly);
			bw.write(String.format("%.12f", result.get(0)));
			for (int j = 1; j < result.size(); j++) {
				bw.write(" " + String.format("%.12f", result.get(j)));
			}
			bw.write("\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	// 미분
	public static Vector<Double> Differentiate(Vector<Double> poly) {
		int n = poly.size() - 1;
		Vector<Double> result = new Vector<>();

		for (int i = 0; i < n; i++) {
			result.add((n - i) * poly.get(i));
		}
		return result;
	}

	// 1차 or 2차 방정식을 품.
	public static Vector<Double> SolveEquation(Vector<Double> poly) {
		int n = poly.size() - 1;
		Vector<Double> result = new Vector<>();

		if (n == 1) {
			result.add((-1 * poly.get(1)) / poly.get(0));
		} else {
			double a = poly.get(0), b = poly.get(1), c = poly.get(2);
			result.add(((-1 * b) + Math.sqrt(b * b - 4 * a * c)) / (2 * a));
			result.add(((-1 * b) - Math.sqrt(b * b - 4 * a * c)) / (2 * a));
		}

		Collections.sort(result);
		return result;
	}

	// 어떠한 x에 대응하는 y를 구함.
	public static double Evaluate(Vector<Double> poly, double x) {
		int n = poly.size() - 1;
		double result = 0;

		for (int i = 0; i <= n; i++) {
			result += poly.get(i) * Math.pow(x, (n - i));
		}
		return result;
	}
	
	// 고차방정식을 품.
	public static Vector<Double> Solve(Vector<Double> poly) {
		int n = poly.size() - 1;
		if (n <= 2) {
			return SolveEquation(poly);
		}

		Vector<Double> derivative = Differentiate(poly);
		Vector<Double> sols = Solve(derivative);
		
		sols.add(0, -1 * L - 1); // 첫 번째 극값과 비교할 대상을 만들어주어야 함.
		sols.add(sols.size(), L + 1); // 마지막 극값과 비교할 대상을 만들어주어야 함.
		
		Vector<Double> result = new Vector<>();
		for (int i = 0; i < sols.size() - 1; i++) {
			double x1 = sols.get(i), x2 = sols.get(i + 1);
			double y1 = Evaluate(poly, x1), y2 = Evaluate(poly, x2);

			if (y1 * y2 > 0) { // y의 부호가 같으면 근이 없음.
				continue;
			}

			if (y1 > y2) { // 이분법을 사용하려면, 처음에 오는 값이 나중에 오는 값보다 작아야 함.
				double temp = y1;
				y1 = y2;
				y2 = temp;

				temp = x1;
				x1 = x2;
				x2 = temp;
			}

			for (int j = 0; j < 100; j++) { // 100번을 돌리면 오차 범위가 10의 -7제곱으로 작아짐.
				double midX = (x1 + x2) / 2;
				double midY = Evaluate(poly, midX);

				if (y1 * midY > 0) {
					y1 = midY;
					x1 = midX;
				} else {
					y2 = midY;
					x2 = midX;
				}
			}
			result.add((x1 + x2) / 2);
		}
		Collections.sort(result);
		return result;
	}

}
