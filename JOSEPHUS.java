import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int C = Integer.parseInt(br.readLine());
		
		while(C-- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 포위당한 사람의 수
			int K = Integer.parseInt(st.nextToken()); // 죽은 사람으로부터 떨어져 있는 거리
			
			Vector<Integer> v = new Vector<>();
			
			for(int i = 0; i < N; i++) {
				v.add(i + 1);
			}
			
			v.remove(0);
			
			int index = -1;
			while(v.size() > 2) {
				index = (index + K) % v.size(); 
				v.remove(index);
				index--;
			}
			
			bw.write(String.valueOf(v.get(0)));
			for(int i = 1; i < v.size(); i++) {
				bw.write(" " + v.get(i));
			}
			bw.write("\n");
		}
	
		
		bw.flush();
		bw.close();
		br.close();
	}

}
