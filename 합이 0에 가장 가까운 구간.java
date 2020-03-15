import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int[] a = { -14, 7, 2, 3, -8, 4, -6, 8, 9, 11 };

		int[] psum = new int[a.length];
		psum[0] = a[0];
		for (int i = 1; i < a.length; i++) {
			psum[i] = psum[i - 1] + a[i];
		}
		
		// 부분 합을 정렬
		Arrays.sort(psum);
		
		int result = Integer.MAX_VALUE;
		// 0에 가깝다는 말은 psum[]의 두 값의 차이가 가장 작다는 뜻.
		for(int i = 1; i < psum.length; i++) {
			result = Math.min(result, psum[i] - psum[i - 1]);
		}
		
		bw.write(result + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

}
