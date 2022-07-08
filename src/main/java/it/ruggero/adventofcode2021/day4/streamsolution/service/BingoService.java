package it.ruggero.adventofcode2021.day4.streamsolution.service;

import it.ruggero.adventofcode2021.day4.streamsolution.entity.BingoSession;
import it.ruggero.adventofcode2021.day4.streamsolution.newsolution.Board;

import java.util.ArrayList;
import java.util.List;

public class BingoService {

    private final BingoSession bingoSession;

    public BingoService(BingoSession bingoSession) {
        this.bingoSession = bingoSession;
    }


    public void drawOneNumber() {
        int currentStepIndex = bingoSession.getSessionHistory().size();
        int currentlyDrawnNumber = bingoSession.getDrawnNumbers().get(currentStepIndex);
        List<Board> lastStepBoardList = bingoSession.getSessionHistory().get(bingoSession.getSessionHistory().size() - 1 );
        List<Board> list = new ArrayList<>(lastStepBoardList);

        bingoSession.getSessionHistory().put(bingoSession.getSessionHistory().size(), list);


    }




    public void checkIfThisBoardHasWon(Board board) {

    }












}
