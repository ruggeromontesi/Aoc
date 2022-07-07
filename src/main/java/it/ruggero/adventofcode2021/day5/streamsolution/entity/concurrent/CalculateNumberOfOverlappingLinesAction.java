package it.ruggero.adventofcode2021.day5.streamsolution.entity.concurrent;

import it.ruggero.adventofcode2021.day5.streamsolution.entity.HydrotermalVentureMap;

import java.util.concurrent.RecursiveAction;

public class CalculateNumberOfOverlappingLinesAction extends RecursiveAction {

    private HydrotermalVentureMap hydrotermalVentureMap;

    private int start;

    private int stop;

    public CalculateNumberOfOverlappingLinesAction(HydrotermalVentureMap hydrotermalVentureMap, int start, int stop) {
        this.hydrotermalVentureMap = hydrotermalVentureMap;
        this.start = start;
        this.stop = stop;
    }

    @Override
    public void compute() {
        if (stop - start < 995) {
            for (int i = start; i < stop; i++) {
                hydrotermalVentureMap.createMapWitNumberOfOverlappingLinesAtIndex(i);
            }
        } else {
            int middle = start + (stop - start)/2;

            invokeAll(new CalculateNumberOfOverlappingLinesAction(hydrotermalVentureMap, start, middle),
                    new CalculateNumberOfOverlappingLinesAction(hydrotermalVentureMap, middle, stop)
            );

        }

    }
}
