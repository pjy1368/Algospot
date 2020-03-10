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
			double C = (N * (P / 1200) * Math.pow(1 + (P / 1200), M)) / (Math.pow(1 + (P / 1200), M) - 1);
			bw.write(String.format("%.10f", C) + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
