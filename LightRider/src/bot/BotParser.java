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

import java.util.Scanner;

import player.Player;

import move.Move;
import move.MoveType;

/**
 * bot.BotParser
 * 
 * Class that will keep reading output from the engine.
 * Will either update the bot state or get actions.
 * 
 * @author Jim van Eeden <jim@riddles.io>
 */

public class BotParser {

	private Scanner scan;
	private BotStarter bot;

	private BotState currentState;

	BotParser(BotStarter bot) {
		this.scan = new Scanner(System.in);
		this.bot = bot;
		this.currentState = new BotState();
	}

	/**
	 * 
	 * Run will keep reading output from the engine.
	 * Will either update the bot state or get actions.
	 */
	void run() {
		while (scan.hasNextLine()) {
			String line = scan.nextLine();

			if (line.length() == 0) continue;

			String[] parts = line.split(" ");
			switch (parts[0]) {
				case "settings":
					parseSettings(parts[1], parts[2]);
					break;
				case "update":
					if (parts[1].equals("game")) {
						parseGameData(parts[2], parts[3]);
					}
					break;
				case "action":
					if (parts[1].equals("move")) {
						Move move = this.bot.doMove(this.currentState);
						if (move != null) {
							System.out.println(move.toString());
						} else {
							System.out.println(MoveType.PASS.toString());
						}
					}
					break;
				default:
					System.err.println("Unknown command");
					break;
			}
		}
	}
	
	/**
	 * Parses all the game settings given by the engine
	 * @param key Type of setting given
	 * @param value Value
	 */
	private void parseSettings(String key, String value) {
		try {
			switch (key) {
				case "timebank":
					int time = Integer.parseInt(value);
					this.currentState.setMaxTimebank(time);
					this.currentState.setTimebank(time);
					break;
				case "time_per_move":
					this.currentState.setTimePerMove(Integer.parseInt(value));
					break;
				case "player_names":
					String[] playerNames = value.split(",");
					for (String playerName : playerNames)
						this.currentState.getPlayers().put(playerName, new Player(playerName));
					break;
				case "your_bot":
					this.currentState.setMyName(value);
					break;
				case "your_botid":
					int myId = Integer.parseInt(value);
					this.currentState.getField().setMyId(myId);
					break;
				case "field_width":
					this.currentState.getField().setWidth(Integer.parseInt(value));
					break;
				case "field_height":
					this.currentState.getField().setHeight(Integer.parseInt(value));
					break;
				default:
					System.err.println(String.format(
							"Cannot parse settings input with key '%s'", key));
			}
		} catch (Exception e) {
			System.err.println(String.format(
					"Cannot parse settings value '%s' for key '%s'", value, key));
			e.printStackTrace();
		}
	}
	
	/**
	 * Parse data about the game given by the engine
	 * @param key Type of game data given
	 * @param value Value
	 */
	private void parseGameData(String key, String value) {
		try {
			switch (key) {
				case "round":
					this.currentState.setRoundNumber(Integer.parseInt(value));
					break;
				case "field":
					this.currentState.getField().initField();
					this.currentState.getField().parseFromString(value);
					break;
				default:
					System.err.println(String.format(
							"Cannot parse game data input with key '%s'", key));
			}
		} catch (Exception e) {
			System.err.println(String.format(
					"Cannot parse game data value '%s' for key '%s'", value, key));
			e.printStackTrace();
		}
	}
}