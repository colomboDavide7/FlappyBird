/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.players;

import flappybird.players.Bird;
import flappybird.animationTool.IAnimation;
import flappybird.resources.LoadException;
import java.util.List;

/**
 *
 * @author davidecolombo
 */
public class SimplePlayerFactory {
    
    public static IPlayer createPlayerByType(
            AvailablePlayer requestedType, List<IAnimation> animations
    ) throws LoadException{
    
        IPlayer player = null;
        switch(requestedType){
            case bird:
                player = new Bird(animations);
                break;
            default:
                throw new LoadException(LoadException.ErrorCode.INVALID_PLAYER_TYPE, requestedType.name());
        }
        return player;
    }
    
}
