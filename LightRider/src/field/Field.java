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

package field;

import java.awt.Point;

/**
 * field.Field
 *
 * Handles everything that has to do with the field, such 
 * as storing the current state and performing calculations
 * on the field.
 *
 * @author Jim van Eeden <jim@riddles.io>
 */

public class Field {

    private int myId;
    private int width;
    private int height;
    private String[][] field;
    private Point myPosition;

    /**
     * Initializes and clears field
     * @throws Exception exception
     */
    public void initField() throws Exception {
        try {
            this.field = new String[this.width][this.height];
        } catch (Exception e) {
            throw new Exception("Error: trying to initialize field while field "
                    + "settings have not been parsed yet.");
        }

        clearField();
    }

    /**
     * Clears the field
     */
    private void clearField() {
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                this.field[x][y] = ".";
            }
        }

        this.myPosition = null;
    }

    /**
     * Parse field from comma separated String
     * @param s input from engine
     */
    public void parseFromString(String s) {
        clearField();
        
        String[] split = s.split(",");
        int x = 0;
        int y = 0;

        for (String value : split) {
            this.field[x][y] = value;

            if (this.field[x][y].equals(this.myId + "")) {
                this.myPosition = new Point(x, y);
            }
                            
            if (++x == this.width) {
                x = 0;
                y++;
            }
        }
    }

    public void setMyId(int id) {
        this.myId = id;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Point getMyPosition() {
        return this.myPosition;
    }
}