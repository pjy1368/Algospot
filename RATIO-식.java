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
		final int MAX = 2000000000;
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 플레이 횟수
			int M = Integer.parseInt(st.nextToken()); // 승리 횟수
			int Z = (int) Math.floor((double) M * 100 / N); // 승률

			if (Z >= 99) {
				bw.write("-1\n");
				continue;
			}

			Z++;
			int x = (int) Math.ceil((N * Z - 100 * M) / (double) (100 - Z));
			bw.write(x + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
