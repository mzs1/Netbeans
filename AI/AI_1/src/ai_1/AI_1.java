/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_1;

import java.util.Random;

/**
 *
 * @author PC
 */
public class AI_1 {

    static char[] rooms = {'A', 'B', 'C', 'D'};

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        action();
    }

    public static int getRBool() {
        Random rand = new Random();
        int randBoolean = rand.nextInt(2);
        return randBoolean;
    }

    public static void action() {
        int i, j;
        while (true) {
            for (i = 0; i < rooms.length;) {
                if (getRBool() == 1) {
                    if (i == rooms.length - 1) {
                        j = 0;
                        System.out.println("Room " + rooms[i] + " is clean skip go to " + rooms[j]);
                        i++;
                    } else {
                        j = i;
                        System.out.println("Room " + rooms[i] + " is clean skip go to " + rooms[++j]);
                        i++;
                    }
                } else if (getRBool() == 0) {
                    System.out.println("Room " + rooms[i] + " is dirty cleaning now ");
                }
            }
        }
    }

}
