package dk.aau.oose.container;

public interface IChild {
	public IContainer<?> getParent();
	void setParent(IContainer<?> parent);
}
