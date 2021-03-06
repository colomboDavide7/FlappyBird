/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.powerUp;

import flappybird.generalInterfaces.ICloneable;
import flappybird.generalInterfaces.IConfigurable;
import flappybird.generalInterfaces.IMovable;
import flappybird.players.IPlayer;
import flappybird.generalInterfaces.IRenderable;
import flappybird.generalInterfaces.ISearchable;
import flappybird.generalInterfaces.IUpdatable;

/**
 *
 * @author davidecolombo
 */
public interface IPowerUp extends ICloneable, IUpdatable, IRenderable, IConfigurable, IMovable, ISearchable {
    
    public abstract void powerUp(IPlayer player);
    
}
