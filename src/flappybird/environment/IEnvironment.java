/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.environment;

import flappybird.generalInterfaces.IRenderable;
import flappybird.generalInterfaces.ISearchable;
import flappybird.generalInterfaces.IUpdatable;
import flappybird.players.IPlayer;

/**
 *
 * @author davidecolombo
 */
public interface IEnvironment extends IUpdatable, IRenderable, ISearchable {
    
    public abstract void checkCollision(IPlayer player);
    
}
