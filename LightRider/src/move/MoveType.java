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

package move;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * move.MoveType
 *
 * Enumerator for all move types
 *
 * @author Jim van Eeden <jim@riddles.io>
 */
public enum MoveType {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    PASS;

    private static final List<MoveType> values = Collections.unmodifiableList(Arrays.asList(values()));
    private static final Random rand = new Random();

    public static MoveType getRandomMoveType() {
        return values.get(rand.nextInt(values.size()));
    }

    public static MoveType getRandomExcluding(MoveType moveType) {
        List<MoveType> valuesExcluding = new ArrayList<>(values);
        valuesExcluding.remove(moveType);

        return valuesExcluding.get(rand.nextInt(valuesExcluding.size()));
    }

    public MoveType getOpposite() {
        switch(this) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            default:
                return this;
        }
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
