import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int C = Integer.parseInt(br.readLine());

		outer: while (C-- > 0) {
			String input = br.readLine();

			Stack<Character> stack = new Stack<>();
			for (int i = 0; i < input.length(); i++) {
				char c = input.charAt(i);

				if (c == '(' || c == '{' || c == '[') {
					stack.push(c);
				} else {
					if (stack.isEmpty()) {
						bw.write("NO\n");
						continue outer;
					}
					switch (c) {
					case ')':
						char temp = stack.pop();
						if (temp != '(') {
							bw.write("NO\n");
							continue outer;
						}
						break;
					case '}':
						temp = stack.pop();
						if (temp != '{') {
							bw.write("NO\n");
							continue outer;
						}
						break;
					case ']':
						temp = stack.pop();
						if (temp != '[') {
							bw.write("NO\n");
							continue outer;
						}
						break;
					}
				}

			}

			if (!stack.isEmpty()) {
				bw.write("NO\n");
				continue outer;
			}

			bw.write("YES\n");

		}

		bw.flush();
		bw.close();
		br.close();
	}

}
