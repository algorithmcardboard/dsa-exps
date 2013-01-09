package in.rajegannathan.ds.tree;

import java.util.Iterator;


public class TernarySearchTree<E extends Comparable<E>> {
	private TernaryNode<E> root = new TernaryNode<E>();

	public TernaryNode<E> addToTree(Iterator<E> iter) {
		
		TernaryNode<E> tmp = root;
		
		while(iter.hasNext()){
			E next = iter.next();
			if(tmp == null){
				tmp = new TernaryNode<E>();
			}
			if(tmp.getElement() == null){
				tmp.setElement(next);
			}
			tmp = getNextNode(tmp, next);
		}
		return root;
	}

	private TernaryNode<E> getNextNode(TernaryNode<E> node, E element) {
		int nodeValue = node.getElement().compareTo(element);
		if(nodeValue ==0){
			return node.getMiddle();
		}else if(nodeValue > 0 ){
			return node.getLeft();
		}else{
			return node.getRight();
		}
	}
	
}

class TernaryNode<E extends Comparable<E>> {
	private E element;
	private TernaryNode<E> left = null, right = null, middle = null;

	public E getElement() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public TernaryNode<E> getLeft() {
		return left;
	}

	public void setLeft(TernaryNode<E> left) {
		this.left = left;
	}

	public TernaryNode<E> getRight() {
		return right;
	}

	public void setRight(TernaryNode<E> right) {
		this.right = right;
	}

	public TernaryNode<E> getMiddle() {
		return middle;
	}

	public void setMiddle(TernaryNode<E> middle) {
		this.middle = middle;
	}
}