package com.task.calculator;

import com.task.model.Tap;
import com.task.model.Trip;
import com.task.model.modelEnums.StatusType;
import com.task.model.modelEnums.StopType;
import com.task.model.modelEnums.TapType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/*
 * Performing all the calculations required such as chargeAmount and duration.
 */

@Data
@Builder
@AllArgsConstructor
public class TransactionCalculator implements CalculateFare{
    Queue<Tap> allTaps;

    public List<Trip> generateTripsFromTaps() {
        List<Trip> allTrips = new ArrayList<>();
        while (!allTaps.isEmpty()){
            Tap tap = allTaps.poll();
            if (tap.getTapType() == TapType.ON){
                allTrips.add(generateEachTrip(tap)); // looping through all the taps
            }
        }
        return allTrips;
    }

    private Trip generateEachTrip(Tap initialTap) {
        for (Tap tap : allTaps) {
            if (tap.getPan().equals(initialTap.getPan()) && tap.getTapType() == TapType.OFF) {
                return Trip.builder().started(initialTap.getTapTime()).finished(tap.getTapTime()).duration(calculateTimeTaken(initialTap.getTapTime(), tap.getTapTime()))
                        .chargeAmount(calculateTripCost(initialTap.getStop(), tap.getStop()))
                        .Fromstop(initialTap.getStop())
                        .Tostop(tap.getStop())
                        .companyId(initialTap.getCompanyId())
                        .busId(initialTap.getBusId())
                        .pan(initialTap.getPan())
                        .status(initialTap.getStop() == tap.getStop() ? StatusType.CANCELLED : StatusType.COMPLETED)
                        .build();
            }
            // only Tap ON
            if (tap.getPan().equals(initialTap.getPan()) && tap.getTapType() == TapType.ON) {
                if (tap.getPan().equals(initialTap.getPan()) && tap.getTapType() == TapType.OFF) {
                    return Trip.builder().started(initialTap.getTapTime()).finished(null).duration(null)
                            .chargeAmount(calculateTripCost(initialTap.getStop(), null))
                            .Fromstop(initialTap.getStop())
                            .Tostop(null)
                            .companyId(initialTap.getCompanyId())
                            .busId(initialTap.getBusId())
                            .pan(initialTap.getPan())
                            .status(StatusType.INCOMPLETE)
                            .build();
                }
            }
        }
        return Trip.builder().started(initialTap.getTapTime()).finished(null).duration(null)
                .chargeAmount(calculateTripCost(initialTap.getStop(), null))
                .Fromstop(initialTap.getStop())
                .Tostop(null)
                .companyId(initialTap.getCompanyId())
                .busId(initialTap.getBusId())
                .pan(initialTap.getPan())
                .status(StatusType.INCOMPLETE)
                .build();
    }


    @Override
    public double calculateTripCost(StopType initialPoint, StopType destination) {
        if (destination == null) {
            // case where client does not tap OFF
            if (initialPoint == StopType.Stop1 || initialPoint == StopType.Stop3) return 7.30;
            if (initialPoint == StopType.Stop2) return 5.50;
        } else {
            if (initialPoint == destination ) {
                // case when ON and OFF on the same stop
                return 0.00;
            }
            // the other alternative scenarios
            if (initialPoint == StopType.Stop1 && destination == StopType.Stop2 || initialPoint == StopType.Stop2 && destination == StopType.Stop1){
               // minimum cost between first two stops
                return 3.25;
            }
            if (initialPoint == StopType.Stop1 && destination == StopType.Stop3 || initialPoint == StopType.Stop3 && destination == StopType.Stop1){
                // maximum cost between the two furthest stop
                return 7.30;
            }
            if (initialPoint == StopType.Stop2 && destination == StopType.Stop3 || initialPoint == StopType.Stop3 && destination == StopType.Stop2){
                // between stop2 and stop3
                return 5.50;
            }
        }
        return Double.parseDouble(null);
    }

    @Override
    public Long calculateTimeTaken(LocalDateTime started, LocalDateTime finished){
        // calculate the duration of trip and as finishing point is unknown null is output
        Long result = (finished == null) ? null : ChronoUnit.SECONDS.between(started, finished);
        return result;
    }
}
