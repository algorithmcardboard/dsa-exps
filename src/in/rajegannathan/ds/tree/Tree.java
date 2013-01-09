package in.rajegannathan.ds.tree;

public interface Tree<E extends Comparable<E>> {
	public void add(E e);
	public boolean contains(E e);
	public void remove(E e);
	public void print(TreeTraversal t);
}
