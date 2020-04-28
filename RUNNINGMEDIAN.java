import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static final int MOD = 20090711;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int C = Integer.parseInt(br.readLine());

		long[] A = new long[200001];
		A[0] = 1983;

		while (C-- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			for (int i = 1; i < N; i++) {
				A[i] = (A[i - 1] * a + b) % MOD;
			}

			bw.write((runningMedian(N, A) % MOD) + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	public static long runningMedian(int N, long[] A) {
		PriorityQueue<Long> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Long> minHeap = new PriorityQueue<>();
		long ret = 0;
		
		// 반복문 불변식
		// 1. maxHeap의 크기는 minHeap의 크기와 같거나 1 더 크다.
		// 2. minHeap.peek() <= minHeap.peek();

		for (int cnt = 1; cnt <= N; cnt++) {
			// 우선 1번 불변식부터 만족시킨다.
			if (maxHeap.size() == minHeap.size()) {
				maxHeap.offer(A[cnt - 1]);
			} else {
				minHeap.offer(A[cnt - 1]);
			}

			// 2번 불변식이 깨졌을 경우 복구하자.
			if (!minHeap.isEmpty() && !maxHeap.isEmpty() && minHeap.peek() < maxHeap.peek()) {
				long a = maxHeap.peek(), b = minHeap.peek();
				maxHeap.poll();
				minHeap.poll();

				maxHeap.add(b);
				minHeap.add(a);
			}
			ret = (ret + maxHeap.peek()) % MOD;
		}

		return ret;
	}

}
