package io.github.nikmang.dec1517;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: jnazario
 * Link: https://www.reddit.com/r/dailyprogrammer/comments/3ke4l6/20150909_challenge_231_intermediate_set_game/
 * Difficulty: Intermediate
 * Test Cases [Output]:
 * 1) SP3F DP3O DR2F SP3H DG3O SR1H SG2O SP1F SP3O OR3O OR3H OR2H
 * [SP3F SR1H SG2O, SP3F DG3O OR3H, SP3F SP3H SP3O, DR2F SR1H OR3O, DG3O SP1F OR2H, DG3O SP3O OR3O]
 *
 * 2) DP2H DP1F SR2F SP1O OG3F SP3H OR2O SG3O DG2H DR2H DR1O DR3O
 * [DP1F SR2F OG3F, DP2H DG2H DR2H, DP1F DG2H DR3O, SR2F OR2O DR2H, SP1O OG3F DR2H, OG3F SP3H DR3O]
 */
public class SetGame {
    /*
    Mental Note (Setup for each character):
    1) D,O,S
    2) R,P,G
    3) 1,2,3
    4) O,H,F
     */
    List<String> cards;

    public SetGame(List<String> cards) {
        assert cards != null && cards.size() >= 3;

        this.cards = cards;
    }

    public List<String[]> getCombos() {
        List<String[]> combos = new ArrayList<>();
        //String[] window = {cards.get(0), cards.get(1), cards.get(2)};

        for(int i=0; i<cards.size()-2; i++) {
            for(int j=i+1; j<cards.size()-1; j++) {
                for(int k=j+1; k<cards.size(); k++) {
                    if(areEqual(cards.get(i), cards.get(j), cards.get(k))) {
                        combos.add(new String[]{cards.get(i), cards.get(j), cards.get(k)});
                    }
                }
            }
        }

        return combos;
    }

    private boolean areEqual(String card1, String card2, String card3) {
        char[] arr1 = card1.toCharArray();
        char[] arr2 = card2.toCharArray();
        char[] arr3 = card3.toCharArray();

        for(int i=0; i<4; i++) {
            if(arr1[i] == arr2[i]) {
                if(arr1[i] != arr3[i]) {
                    return false;
                }
            } else {
                if(arr1[i] == arr3[i] || arr2[i] == arr3[i]) {
                    return false;
                }
            }
        }

        return true;
    }
}
