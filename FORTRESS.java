import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.Vector;

class TreeNode {
	Vector<TreeNode> children;

	TreeNode() {
		children = new Vector<>();
	}
}

public class Main {
	static int N;
	static int longest; // 지금까지 찾은 가장 긴 잎-잎 경로의 길이를 저장한다.
	static int[] x, y, r;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int C = Integer.parseInt(br.readLine());
		while (C-- > 0) {
			N = Integer.parseInt(br.readLine());
			x = new int[N];
			y = new int[N];
			r = new int[N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());

				x[i] = Integer.parseInt(st.nextToken());
				y[i] = Integer.parseInt(st.nextToken());
				r[i] = Integer.parseInt(st.nextToken());
			}

			TreeNode root = getTree(0);
			bw.write(solve(root) + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}
	
	// root를 루트로 하는 서브트리의 높이를 반환한다.
	public static int height(TreeNode root) {
		// 각 자식을 루트로 하는 서브트리의 높이를 계산한다.
		Vector<Integer> heights = new Vector<>();

		for (int i = 0; i < root.children.size(); i++) {
			heights.add(height(root.children.get(i)));
		}
		
		// 만약 자식이 하나도 없다면 0을 반환한다.
		if (heights.isEmpty()) {
			return 0;
		}

		Collections.sort(heights);
		
		// root를 최상위 노드로 하는 경로를 고려하자.
		if (heights.size() >= 2) {
			longest = Math.max(longest, 2 + heights.get(heights.size() - 2) + heights.get(heights.size() - 1));
		}
		
		// 트리의 높이는 서브트리 높이의 최대치에 1을 더해 계산한다.
		return heights.get(heights.size() - 1) + 1;
	}

	public static int solve(TreeNode root) {
		longest = 0;
		
		// 트리의 높이와 최대 잎-잎 경로 길이 중 큰 것을 선택한다.
		int h = height(root);
		return Math.max(longest, h);
	}

	// root 성벽을 루트로 하는 트리를 생성한다.
	public static TreeNode getTree(int root) {
		TreeNode ret = new TreeNode();
		for (int i = 0; i < N; i++) {
			// i 성벽이 root 성벽에 직접적으로 포함되어 있다면
			// 트리를 만들고 자손 목록에 추가한다.
			if (isChild(root, i)) {
				ret.children.add(getTree(i));
			}
		}
		return ret;
	}

	// x^2을 반환한다.
	public static int sqr(int x) {
		return x * x;
	}

	// 두 성벽 a, b의 중심점 간의 거리의 제곱을 반환한다.
	public static int sqrdist(int a, int b) {
		return sqr(y[a] - y[b]) + sqr(x[a] - x[b]);
	}

	// 성벽 a가 성벽 b를 포함하는지 확인한다.
	public static boolean encloses(int a, int b) {
		return r[a] > r[b] && sqrdist(a, b) < sqr(r[a] - r[b]);
	}

	// '성벽' 트리에서 parent가 child의 부모인지 확인한다.
	// parent는 child를 꼭 직접 포함해야한다.
	public static boolean isChild(int parent, int child) {
		if (!encloses(parent, child)) {
			return false;
		}

		for (int i = 0; i < N; i++) {
			if (i != parent && i != child && encloses(parent, i) && encloses(i, child)) {
				return false;
			}
		}

		return true;
	}

}
