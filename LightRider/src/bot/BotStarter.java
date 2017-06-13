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

import move.Move;
import move.MoveType;

/**
 * bot.BotStarter
 * 
 * Magic happens here. You should edit this file, or more specifically
 * the makeTurn() method to make your bot do more than random moves.
 * 
 * @author Jim van Eeden <jim@riddles.io>
 */

public class BotStarter {

	/**
     * Edit this method to make your bot smarter.
	 * Currently returns a random, but valid move.
     * @return a Move object
     */
    public Move doMove(BotState state) {
		MoveType moveType;
		MoveType lastMoveType = state.getLastDirection();

		if (lastMoveType == null) { // first move
			moveType = MoveType.getRandomExcluding(MoveType.PASS);
		} else {
			MoveType opposite = lastMoveType.getOpposite();
			moveType = MoveType.getRandomExcluding(opposite);
		}

		if (moveType != MoveType.PASS) {
            state.setLastDirection(moveType);
        }

		return new Move(moveType);
    }
    
	/**
     * Main method for the bot. Creates a parser and runs it.
     */
 	public static void main(String[] args) {
 		BotParser parser = new BotParser(new BotStarter());
 		parser.run();
 	}
 }
