
public interface PriorityQueue<T extends Comparable<T>> {

	int len();

	boolean isEmpty();

	T poll();

	void add(T elem);

}