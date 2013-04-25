package dk.aau.oose.container;

import java.util.ArrayList;
import java.util.List;

import dk.aau.oose.AGameElement;

public class ContainerElement extends AGameElement implements IContainer<ContainerElement> {
	
	private ContainerElement parent;
	private final List<ContainerElement> children = new ArrayList<ContainerElement>();
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasChildren() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int numChildren() {
		return children.size();
	}

	@Override
	public ContainerElement getChildAt(int index) {
		return children.get(index);
	}

	@Override
	public void addChild(ContainerElement child) {
		children.add(child);
		child.setParent(this);
	}

	@Override
	public void addChildAt(ContainerElement child, int index) {
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
	public boolean removeChild(ContainerElement child) {
		if(child.getParent() == this) {
			child.setParent(null);
			return children.remove(child);
		} else {
			return false;
		}
	}

	@Override
	public ContainerElement removeChildAt(int index) {
		ContainerElement ele = children.remove(index);
		ele.setParent(null);
		return ele;
	}

	@Override
	public ContainerElement getParent() {
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
	public void setParent(ContainerElement ele) {
		this.parent = ele;
	}

}
