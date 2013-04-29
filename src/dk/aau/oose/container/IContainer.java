package dk.aau.oose.container;

public interface IContainer<ChildType extends IChild> {
	public boolean hasChildren();
	public int numChildren();
	public ChildType getChildAt(int index);
	public void addChild(ChildType child);
	public void addChildAt(ChildType child, int index);
	public boolean removeChild(ChildType child);
	public ChildType removeChildAt(int index);
	public boolean hasChild(ChildType child);
}
