package dk.aau.oose.container;

/**
 * Interface for specifying a child-parent functionality
 * @author Adam
 *
 */
public interface IChild {
	/**
	 * 
	 * @return The parent IContainer<?> object
	 */
	public IContainer<?> getParent();
	/**
	 * Set the parent
	 * @param parent
	 */
	void setParent(IContainer<?> parent);
}
