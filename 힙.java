import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Vector;

class MaxHeap {
	Vector<Integer> heap;

	MaxHeap() {
		heap = new Vector<>();
	}

	void push_heap(int newValue) {
		// 힙의 맨 끝에 newValue를 삽입한다.
		heap.add(newValue);

		// 현재 newValue의 위치
		int idx = heap.size() - 1;

		// 루트에 도달하거나 newValue 이상의 원소를 가진 조상을 만날 때까지
		while (idx > 0 && heap.get((idx - 1) / 2) < heap.get(idx)) {
			int temp = heap.get(idx);
			heap.set(idx, heap.get((idx - 1) / 2));
			heap.set((idx - 1) / 2, temp);
		}
	}

	// 정수 원소를 갖는 최대 힙에서 최대 원소 삭제하기
	void pop_heap() {
		// 힙의 맨 끝에서 값을 가져와 루트에 덮어씌운다.
		heap.set(0, heap.lastElement());
		heap.remove(heap.size() - 1);

		int here = 0;
		while (true) {
			int left = here * 2 + 1, right = here * 2 + 2;

			// 리프에 도달한 경우
			if (left >= heap.size()) {
				break;
			}

			// heap.get(here)가 내려갈 위치를 찾는다.
			int next = here;
			if (heap.get(next) < heap.get(left)) {
				next = left;
			}

			if (right < heap.size() && heap.get(next) < heap.get(right)) {
				next = right;
			}

			if (next == here) {
				break;
			}

			int temp = heap.get(here);
			heap.set(here, heap.get(next));
			heap.set(next, temp);

			here = next;
		}
	}
}

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		MaxHeap maxHeap = new MaxHeap();
		
		maxHeap.push_heap(8);
		maxHeap.push_heap(7);
		maxHeap.push_heap(8);
		maxHeap.push_heap(6);
		maxHeap.push_heap(6);
		maxHeap.push_heap(6);
		maxHeap.push_heap(6);
		maxHeap.push_heap(5);
		
		maxHeap.pop_heap();
		bw.flush();
		bw.close();
		br.close();
	}

}
