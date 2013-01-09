package in.rajegannathan.ds.tree;

public enum TreeTraversal {
	INORDER("inorder",1),PREORDER("preorder",2),POSTORDER("postorder",3);
	
	private String method;
	private int value;
	
	TreeTraversal(String method, int value){
		this.method = method;
		this.value = value;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
