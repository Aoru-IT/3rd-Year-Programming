package com.example.alonzo_lectea2_lotto;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LottoCompute
{
    private Integer[] lottoNumbers;
    private Integer[] winnerNumbers;
    private int matchedQuantity;

    public LottoCompute() {
        lottoNumbers = new Integer[6];
        winnerNumbers = new Integer[6];
        matchedQuantity = 0;
    }

    public void setLottoNumbers(Integer[] numbers) {
        lottoNumbers = numbers;
    }

    public void setWinnerNumbers(){
        Set<Integer> randomNumbers = new HashSet<>();
        while (randomNumbers.size() < 6){
            int rand = (int)(Math.random() * 42) + 1;
            randomNumbers.add(rand);
        }

        winnerNumbers = randomNumbers.toArray(new Integer[0]);
    }

    public void setMatchedQuantity(){
        matchedQuantity = 0;
        Set<Integer> winners = new HashSet<>(Arrays.asList(winnerNumbers));

        for(int lottoNumber : lottoNumbers){
            if(winners.contains(lottoNumber)){
                matchedQuantity++;
            }
        }

    }

    public String getPrizeValue(){
        if(matchedQuantity == 4){
            return "20,000.00";
        }
        else if(matchedQuantity == 5){
            return "50,000.00";
        }
        else if(matchedQuantity == 6){
            return "JACKPOT YOU'VE WON 1,000,000.00";
        }
        else{
            return "BETTER LUCK NEXT TIME!";
        }
    }

    public Integer[] getWinnerNumbers(){
        return winnerNumbers;
    }

}
