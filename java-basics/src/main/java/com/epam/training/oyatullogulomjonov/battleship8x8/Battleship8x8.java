package com.epam.training.oyatullogulomjonov.battleship8x8;

import java.math.BigInteger;

public class Battleship8x8 {
    private final long ships;
    private long shots = 0L;

    public Battleship8x8(final long ships) {
        this.ships = ships;
    }

    public boolean shoot(String shot) {
        String shotsMap = Long.toBinaryString(shots);
        String shotsMapEmptyCells = "0".repeat(64 - shotsMap.length());
        shotsMap = shotsMapEmptyCells + shotsMap;

        //find index of shot
        int shotRowIndex = 8 * (shot.charAt(1)-'0' - 1);
        int shotColumnIndex = 0;
        switch (shot.charAt(0)) {
            case 'A':
                shotColumnIndex = 0; break;
            case 'B':
                shotColumnIndex = 1; break;
            case 'C':
                shotColumnIndex = 2; break;
            case 'D':
                shotColumnIndex = 3; break;
            case 'E':
                shotColumnIndex = 4; break;
            case 'F':
                shotColumnIndex = 5; break;                
            case 'G':
                shotColumnIndex = 6; break;
            case 'H':
                shotColumnIndex = 7;                
        }
        int shotIndex = shotRowIndex + shotColumnIndex;
        
        //update shots map
        if (shotIndex == 0) {
            shotsMap = '1' + shotsMap.substring(1, 64);
        } else if (shotIndex == 63) {
            shotsMap = shotsMap.substring(0, 63) + '1';
        } else {
            shotsMap = shotsMap.substring(0, shotIndex) + '1' + shotsMap.substring(shotIndex + 1);            
        }
        shots = new BigInteger(shotsMap, 2).longValue();
        
        String shipsMap = Long.toBinaryString(ships);
        String shipsMapEmptyCells = "0".repeat(64 - shipsMap.length());
        shipsMap = shipsMapEmptyCells + shipsMap;
        return shipsMap.charAt(shotIndex) == '1' ? true : false;
    }

    public String state() {
        String map = "................................................................";        

        //add ships to battleship map
        String shipsMap = Long.toBinaryString(ships);
        String shipsMapEmptyCells = "0".repeat(64 - shipsMap.length());
        shipsMap = shipsMapEmptyCells + shipsMap;
        for (int i = 0; i < 64; i++) {
            if ('1' == shipsMap.charAt(i)) {
                map = map.substring(0, i) + '☐' + map.substring(i + 1);
            }
        }        

        //add shots to battleship map
        String shotsMap = Long.toBinaryString(shots);
        String shotsMapEmptyCells = "0".repeat(64 - shotsMap.length());
        shotsMap = shotsMapEmptyCells + shotsMap;
        for (int i = 0; i < 64; i++) {
            if ('1' == shotsMap.charAt(i)) {
                if (map.charAt(i) == '.') { //empty cell is shot
                    map = map.substring(0, i) + '×' + map.substring(i + 1);
                } else {                    //ship is shot
                    map = map.substring(0, i) + '☒' + map.substring(i + 1);
                }
            }
        }
        
        //insert new lines after each 8 characters
        for (int i = 8; i < 72; i += 9) {
            map = map.substring(0, i) + '\n' + map.substring(i);
        }
        return map;
    }
}
