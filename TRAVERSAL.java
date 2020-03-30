import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int C = Integer.parseInt(br.readLine());
		while (C-- > 0) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());

			int[] preorder = new int[N]; // 전위
			for (int i = 0; i < N; i++) {
				preorder[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			int[] inorder = new int[N]; // 중위
			for (int i = 0; i < N; i++) {
				inorder[i] = Integer.parseInt(st.nextToken());
			}

			traversal(preorder, inorder);
			bw.write(sb.toString() + "\n");
			sb.setLength(0);
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	// 후순위를 출력하는 함수.
	public static void traversal(int[] preorder, int[] inorder) {
		if (preorder.length == 0) { // preorder가 empty 상태가 되면 종료.
			return;
		}

		int root = preorder[0]; // 전위 순서에서 첫 번째 인덱스 값이 루트임.
		int lsize = 0;
		// 중위 순서에서 위에서 구한 root가 나오는 인덱스를 탐색함.
		for (int i = 0; i < inorder.length; i++) {
			if (root == inorder[i]) {
				lsize = i;
				break;
			}
		}
		
		// lsize 인덱스 기점을 왼쪽은 왼쪽서브트리, 오른쪽은 오른쪽서브트리가 된다.
		// 따라서 각각에 대해서 재귀함수를 이용하여 계속 쪼갠다.
		traversal(Arrays.copyOfRange(preorder, 1, lsize + 1), Arrays.copyOfRange(inorder, 0, lsize));
		traversal(Arrays.copyOfRange(preorder, lsize + 1, preorder.length),
				Arrays.copyOfRange(inorder, lsize + 1, inorder.length));
		
		sb.append(root + " "); // 후위 순위는 root가 가장 마지막에 출력이 됨.
	}

}
