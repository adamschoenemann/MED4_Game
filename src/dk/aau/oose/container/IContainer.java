package dk.aau.oose.container;

public interface IContainer<ChildType> {
	public boolean hasChildren();
	public int numChildren();
	public ChildType getChildAt(int index);
	public void addChild(ChildType child);
	public void addChildAt(ChildType child, int index);
	public boolean removeChild(ChildType child);
	public ChildType removeChildAt(int index);
	public ChildType getParent();
	void setParent(ChildType ele);
}
