package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Heap<T extends Comparable<T>> implements IHeap<T> {

	private int size = 0;
	private ArrayList<INode<T>> h;

	public Heap() {
		h = new ArrayList<INode<T>>();
	}

	@Override
	public INode<T> getRoot() {
		if (size == 0)
			return null;
		return h.get(0);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public void heapify(INode<T> node) {
		if (node == null)
			return;
		int flag = 0;
		INode lch = node.getLeftChild();
		INode rch = node.getRightChild();
		if (lch != null && node.getValue().compareTo((T) lch.getValue()) < 0) {
			flag = 1;
		}
		if (rch != null && lch.getValue().compareTo(rch.getValue()) < 0
				&& node.getValue().compareTo((T) rch.getValue()) < 0) {
			flag = 2;
		}
		if (flag != 0) {
			if (flag == 1) {
				swap(node, node.getLeftChild());
				heapify(node.getLeftChild());
			}
			if (flag == 2) {
				swap(node, node.getRightChild());
				heapify(node.getRightChild());
			}
		}

	}

	@Override
	public T extract() {
		if (size == 0)
			return null;
		T temp = (T) h.get(0).getValue();
		swap(getRoot(), h.get(size - 1));
		h.remove(size - 1);
		size--;
		heapify(getRoot());
		return temp;
	}

	@Override
	public void insert(T element) {
		if (element == null)
			return;
		h.add(new Node<T>(size, element));
		size++;
		INode n = h.get(h.size() - 1);
		heapifyUp(n);
//	while( n.getParent() != null && n.getParent().getValue().compareTo(n.getValue()) < 0 )
//		swap(h.indexOf(n),h.indexOf(n.getParent()) );
//	   n=n.getParent();
	}

	public void heapifyUp(INode<T> node) {
		if (node.getParent() != null && node.getParent().getValue().compareTo(node.getValue()) < 0) {
			swap((node), (node.getParent()));
			heapifyUp(node.getParent());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void build(Collection<T> unordered) {
		if (unordered == null || unordered.size() == 0)
			return;
		this.h = new ArrayList();
		Iterator it = unordered.iterator();
		int ind = 0;
		while (it.hasNext()) {

			h.add(new Node(ind, (Comparable) it.next()));
			ind++;
		}
		size = unordered.size();
		for (int i = (size - 1) / 2; i >= 0; i--) {
			heapify(h.get(i));
		}

	}
 
	private void swap(INode x, INode y) {
		T t = (T) x.getValue();
		x.setValue(y.getValue());
		y.setValue(t);
	}

	void RemoveforSort(int i) {
		swap(h.get(i), getRoot());
		size--;
	}

	/*
	 * void reverse() { for(int i=0;i<(size-1)/2;i++) { swap(i,size-i-1); } }
	 */
	void setsize() {
		size = h.size();
	}


  class Node<T extends Comparable<T>> implements INode<T> {
	private int ind;
	private T val;

	public Node(int ind, T val) {
		this.ind = ind;
		this.val = val;
	}

	@Override
	public INode<T> getLeftChild() {
		if ((ind + 1) * 2 - 1 < size())
			return (INode<T>) h.get((ind + 1) * 2 - 1);
		return null;
	}

	@Override
	public INode<T> getRightChild() {
		if ((ind + 1) * 2 < size())
			return (INode<T>) h.get((ind + 1) * 2);
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public INode<T> getParent() {
		if (size > 1)
			return (INode<T>) h.get((ind - 1) / 2);
		return null;
	}

	@Override
	public T getValue() {

		return val;
	}

	@Override
	public void setValue(T value) {
		val = value;
	}
  }
  }