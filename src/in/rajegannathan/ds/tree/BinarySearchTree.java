package in.rajegannathan.ds.tree;

public class BinarySearchTree<E extends Comparable<E>> {
	private BinaryTreeNode<E> root;

	public boolean add(E e) {
		if (e == null) {
			throw new NullPointerException();
		}
		return searchAndInsert(root, e);
	}

	private boolean searchAndInsert(BinaryTreeNode<E> node, E e) {
		if (node == null) {
			node = new BinaryTreeNode<E>();
			node.setElement(e);
			return true;
		}

		int nodeValue = node.getElement().compareTo(e);
		if (nodeValue < 0) {
			return searchAndInsert(node.getRight(), e);
		}
		if (nodeValue > 0) {
			return searchAndInsert(node.getLeft(), e);
		}
		return false;
	}

	public E search(E element) {
		BinaryTreeNode<E> node = searchInternal(root, element);
		if (node == null) {
			return null;
		}
		return node.getElement();
	}

	private BinaryTreeNode<E> searchInternal(BinaryTreeNode<E> node, E element) {
		if (node == null) {
			return null;
		}

		int nodeValue = node.getElement().compareTo(element);

		if (nodeValue < 0) {
			return searchInternal(node.getRight(), element);
		} else if (nodeValue > 0) {
			return searchInternal(node.getLeft(), element);
		}
		return node;
	}

	public boolean isHeightBalanced() {
		return (maxDepth(root) - minDepth(root) <= 1);
	}

	private int maxDepth(BinaryTreeNode<E> node) {
		if (node == null) {
			return 0;
		}
		return 1 + Math.max(maxDepth(node.getLeft()), maxDepth(node.getRight()));
	}

	private int minDepth(BinaryTreeNode<E> node) {
		if (node == null) {
			return 0;
		}
		return 1 + Math.min(minDepth(node.getLeft()), minDepth(node.getRight()));
	}

	public boolean remove(E element) {
		BinaryTreeNode<E> parent = getParent(root, element);
		BinaryTreeNode<E> node = searchInternal(parent, element);

		if (node == null) {
			return false;
		}

		switch (node.getDegree()) {
		case 0:
			if (parent == null) {
				// must be root
				root = null;
			}
			removeLinkFromParent(parent, node);
			break;
		case 1:
			if (node.getRight() != null) {
				splice(node, node.getRight());
			}
			if (node.getLeft() != null) {
				splice(node, node.getLeft());
			}
			break;
		case 2:
			BinaryTreeNode<E> tmpNode = inOrderSuccessor(node);
			// tmpNode shouldn't be null whenever order is 2. so no null check
			E newValue = tmpNode.getElement();
			remove(newValue);
			node.setElement(newValue);
			break;
		}

		return true;
	}

	private void removeLinkFromParent(BinaryTreeNode<E> parent, BinaryTreeNode<E> child) {
		if (parent.getRight() == child) {
			parent.setRight(null);
		} else {
			parent.setLeft(null);
		}
	}

	private void splice(BinaryTreeNode<E> nodeToRemove, BinaryTreeNode<E> spliceWith) {
		nodeToRemove.setElement(spliceWith.getElement());
		nodeToRemove.setLeft(spliceWith.getLeft());
		nodeToRemove.setRight(spliceWith.getRight());
	}

	public E getParent(E element) {
		BinaryTreeNode<E> parentNode = getParent(root, element);
		return parentNode.getElement();
	}

	private BinaryTreeNode<E> getParent(BinaryTreeNode<E> node, E element) {
		if (node == null) {
			return null;
		}

		if (node.getLeft() != null && node.getLeft().getElement().compareTo(element) == 0) {
			return node;
		}

		BinaryTreeNode<E> parent = getParent(node.getLeft(), element);

		if (parent == null) {
			if (node.getRight() != null && node.getRight().getElement().compareTo(element) == 0) {
				return node;
			}
			parent = getParent(node.getRight(), element);
		}

		return parent;
	}

	private BinaryTreeNode<E> inOrderSuccessor(BinaryTreeNode<E> node) {
		if (node.getRight() != null) {
			return leftMost(node.getRight());
		}

		BinaryTreeNode<E> tmp = root, successor = null;
		while (tmp != null) {
			int tmpValue = tmp.getElement().compareTo(node.getElement());
			if (tmpValue > 0) {
				successor = tmp;
				tmp = tmp.getLeft();
			}
			if (tmpValue < 0) {
				tmp = tmp.getRight();
			}
			if (tmpValue == 0) {
				break;
			}
		}
		return successor;

	}

	private BinaryTreeNode<E> leftMost(BinaryTreeNode<E> node) {
		if (node == null) {
			return null;
		}
		while (node.getLeft() != null) {
			node = node.getLeft();
		}
		return node;
	}
}

class BinaryTreeNode<E extends Comparable<E>> {
	private E element;
	private BinaryTreeNode<E> left;
	private BinaryTreeNode<E> right;

	public E getElement() {
		return element;
	}

	public int getDegree() {
		return (left == null ? 0 : 1) + (right == null ? 0 : 1);
	}

	public void setElement(E element) {
		this.element = element;
	}

	public void setLeft(BinaryTreeNode<E> left) {
		this.left = left;
	}

	public void setRight(BinaryTreeNode<E> right) {
		this.right = right;
	}

	public BinaryTreeNode<E> getLeft() {
		return left;
	}

	public BinaryTreeNode<E> getRight() {
		return right;
	}
}