import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int[][] a = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		int[][] psum = partialSum(a);
		
		int ret = gridSum(psum, 0, 0, 1, 1);
		System.out.println(ret);
		bw.flush();
		bw.close();
		br.close();
	}
	
	// 2차원 배열의 부분합을 계산한다.
	public static int[][] partialSum(int[][] a){
		int[][] ret = new int[a.length][a[0].length];
		
		// 처음에는 가로합을 구한다.
		for(int i = 0; i < a.length; i++) {
			ret[i][0] = a[i][0];
			for(int j = 1; j < a[0].length; j++) {
				ret[i][j] = ret[i][j - 1] + a[i][j];
			}
		}
		
		// 그리고 누적된 상태에서 세로합을 구한다.
		for(int i = 0; i < a.length; i++) {
			for(int j = 1; j < a[0].length; j++) {
				ret[j][i] += ret[j - 1][i];
			}
		}
		
		return ret;
	}
	
	// 2차원 배열의 구간합을 구한다.
	public static int gridSum(int[][] psum, int y1, int x1, int y2, int x2) {
		int ret = psum[y2][x2];
		if(y1 > 0) {
			ret -= psum[y1 - 1][x2];
		}
		if(x1 > 0) {
			ret -= psum[y2][x1 - 1];
		}
		if(y1 > 0 && x1 > 0) {
			ret += psum[y1 - 1][x1 - 1];
		}
		return ret;
	}

}
