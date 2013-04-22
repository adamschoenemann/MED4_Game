package dk.aau.oose;

import dk.aau.oose.core.GameWorld;

public abstract class AGameElement implements IGameElement {
	
	@Override
	public void destroy(){
		GameWorld.remove(this);
	}
}
