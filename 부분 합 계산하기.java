import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int[] a = {1, 2, 3, 4, 5, 6, 7, 8};
		
		int[] psum = partialSum(a);
		int[] sqpnum = partialSqpSum(a);
		
		double ret = variance(sqpnum, psum, 0, 7);
		
		bw.write(ret + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	// 주어진 벡터 a의 부분합을 계산한다.
	public static int[] partialSum(int[] a) {
		int[] ret = new int[a.length];
		ret[0] = a[0];
		for (int i = 1; i < a.length; i++) {
			ret[i] = ret[i - 1] + a[i];
		}
		return ret;
	}
	
	public static int[] partialSqpSum(int[] a) {
		int[] ret = new int[a.length];
		ret[0] = a[0] * a[0];
		for(int i = 1; i < a.length; i++) {
			ret[i] = ret[i - 1] + a[i] * a[i];
		}
		return ret;
	}
	
	// 어떤 배열의 부분합 psum[]이 주어질 때, 원래 배열의 a부터 b까지의 합을 구한다.
	public static int rangeSum(int[] psum, int a, int b) {
		if(a == 0) {
			return psum[b];
		}
		return psum[b] - psum[a - 1];
	}
	
	public static double variance(int[] sqpsum, int[] psum, int a, int b) {
		// 우선 해당 구간의 평균을 계산한다.
		double mean = rangeSum(psum, a, b) / (double) (b - a + 1);
		double ret = rangeSum(sqpsum, a, b) - 2 * mean * rangeSum(psum,a, b) + (b - a + 1) * mean * mean;
		return ret / (b - a + 1);
	}

}
