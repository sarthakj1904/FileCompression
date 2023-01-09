import java.util.*;

public class MinPriorityQueue<T extends Comparable<T>> implements PriorityQueue<T> {
	
	private int size = 0;
    private List<T> list;

    MinPriorityQueue() {
        list = new ArrayList<>();
    }

    @Override
	public int len() { 
    	return size; 
    }
    
    @Override
	public boolean isEmpty() { 
    	if(size == 0)
    		return true;
    	return false;
    }
    
    @Override
	public T poll() { 
    	return poll(0); 
    }

    @Override
	public void add(T elem) {
        list.add(elem);
        siftUp(size);
        size++;
    }

    private T poll(int i) {
        if(size == 1) {
            size--;
            T data = list.get(0);
            list.clear();
            return data;
        }
        size--;
        T res = list.get(i);
        swap(i, size);
        list.remove(list.get(size));
        siftDown(i);
        return res;
    }

    private void siftDown(int i) {
    	
        while(true) {
            int leftChild = (2 * i) + 1;
            int rightChild = (2 * i) + 2;
            int smallest = leftChild;
            
            if(rightChild < size) {
                if (isLess(rightChild, leftChild)) smallest = rightChild;
            }
            
            if(smallest < size) {
                if (isLess(i, smallest)) break;
                swap(i, smallest);
                i = smallest;
            } else 
            	break;
            
        }
    }

    private void siftUp(int i) {
        int parentIdx = (i - 1) / 2;
        while(i > 0 && isLess(i, parentIdx)) {
            swap(i, parentIdx);
            i = parentIdx;
            parentIdx = (i - 1) / 2;
        }
    }

    private boolean isLess(int i, int j) {
        T elem1 = list.get(i);
        T elem2 = list.get(j);

        return elem1.compareTo(elem2) <= 0;
    }

    private void swap(int i, int j) {
        T elem1 = list.get(i);
        T elem2 = list.get(j);

        list.set(i, elem2);
        list.set(j, elem1);
    }

    public String toString() {
        return list.toString();
    }

}
