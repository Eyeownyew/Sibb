package com.sibb;

import com.sibb.world.Entity;
import com.sibb.world.Position;

/**
 * @author Eyeownyew
 * @version $Revision: 1.0 $
 */
public class Camera extends Position {

    /**
     * Method getFollowing.
     *
     * @return Entity
     */
    public Entity getFollowing() {
        return following;
    }

    /**
     * Method setFollowing.
     *
     * @param following Entity
     */
    public void setFollowing(Entity following) {
        this.following = following;
    }

    private Entity following = null;

    public void update() {
        if (following != null)
            setPos((following.getX() + (following.getWidth() / 2)) - (Window.getContainer().getWidth() / 2),
                    (following.getY() + (following.getHeight() / 2)) - (Window.getContainer().getHeight() / 2));
    }
}
