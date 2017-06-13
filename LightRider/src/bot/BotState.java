// // Copyright 2016 riddles.io (developers@riddles.io)

//    Licensed under the Apache License, Version 2.0 (the "License");
//    you may not use this file except in compliance with the License.
//    You may obtain a copy of the License at

//        http://www.apache.org/licenses/LICENSE-2.0

//    Unless required by applicable law or agreed to in writing, software
//    distributed under the License is distributed on an "AS IS" BASIS,
//    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//    See the License for the specific language governing permissions and
//    limitations under the License.
//  
//    For the full copyright and license information, please view the LICENSE
//    file that was distributed with this source code.

package bot;

import java.util.HashMap;

import move.MoveType;
import player.Player;
import field.Field;

/**
 * bot.BotState
 *
 * Keeps track of the complete game state, such as the field
 * and the players.
 *
 * @author Jim van Eeden <jim@riddles.io>
 */

public class BotState {

	private int MAX_TIMEBANK;
    private int TIME_PER_MOVE;

    private int roundNumber;
    private int timebank;
    private String myName;
    private HashMap<String, Player> players;

    private Field field;

    private MoveType lastDirection;

    BotState() {
        this.field = new Field();
        this.players = new HashMap<>();
    }   

    public void setTimebank(int value) {
        this.timebank = value;
    }

    public void setMaxTimebank(int value) {
        this.MAX_TIMEBANK = value;
    }

    public void setTimePerMove(int value) {
        this.TIME_PER_MOVE = value;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public int getTimebank() {
        return this.timebank;
    }

    public int getRoundNumber() {
        return this.roundNumber;
    }

    public HashMap<String, Player> getPlayers() {
        return this.players;
    }

    public Field getField() {
        return this.field;
    }

    public String getMyName() {
        return this.myName;
    }

    public int getMaxTimebank() {
        return this.MAX_TIMEBANK;
    }

    public int getTimePerMove() {
        return this.TIME_PER_MOVE;
    }

    public MoveType getLastDirection() {
        return lastDirection;
    }

    public void setLastDirection(MoveType lastDirection) {
        this.lastDirection = lastDirection;
    }
}