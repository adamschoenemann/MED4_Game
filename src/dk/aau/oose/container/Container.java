package dk.aau.oose.container;

import java.util.ArrayList;
import java.util.List;

public class Container<ChildType extends IChild> implements IContainer<ChildType>, IChild {
	
	private IContainer<?> parent;
	private final List<ChildType> children = new ArrayList<ChildType>();
	
	@Override
	public boolean hasChildren() {
		return (children.size() > 0);
	}

	@Override
	public int numChildren() {
		return children.size();
	}

	@Override
	public ChildType getChildAt(int index) {
		return children.get(index);
	}
	
	public boolean hasChild(ChildType child){
		for(int i = 0; i < children.size(); i++){
			if(children.get(i) == child){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void addChild(ChildType child) {
		children.add(child);
		child.setParent(this);
	}

	@Override
	public void addChildAt(ChildType child, int index) {
		if(index > children.size()){
			throw new IllegalArgumentException();
		}
		child.setParent(this);
		for(int i = index; i < children.size(); i++){
			child = children.set(i, child);
		}
		children.add(child);
		
	}

	@Override
	public boolean removeChild(ChildType child) {
		if(child.getParent() == this) {
			child.setParent(null);
			return children.remove(child);
		} else {
			return false;
		}
	}

	@Override
	public ChildType removeChildAt(int index) {
		ChildType ele = children.remove(index);
		ele.setParent(null);
		return ele;
	}

	@Override
	public IContainer<?> getParent() {
		return parent;
	}
	
	public static void main(String[] args){
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < 10; i++){
			list.add(i);
		}
		// Print list
		for(int i = 0; i < list.size(); i++){
			System.out.println(list.get(i));
		}
		System.out.println();
		
		Integer newInt = 200;
		int insertIndex = 11;
		for(int i = insertIndex; i < list.size(); i++){
			newInt = list.set(i, newInt);
		}
		list.add(newInt);
		
		// Print list
		for(int i = 0; i < list.size(); i++){
			System.out.println(list.get(i));
		}
		
	}

	@Override
	public void setParent(IContainer<?> ele) {
		this.parent = ele;
	}

}
