package ru.geekbrains.lesson5.presenters;

import ru.geekbrains.lesson5.models.Table;

import java.util.Collection;
import java.util.Date;

public class BookingPresenter implements ViewObserver {
    private final Model model;
    private final View view;

    public BookingPresenter(Model model, View view) {
        this.model = model;
        this.view = view;
        view.setObserver(this);
    }

    private Collection<Table> loadTables() {
        return model.loadTables();
    }

    public void updateUIShowTables() {
        view.showTables(loadTables());
    }

    private void updateUIShowReservationTableResult(int reservationNo) {
        view.showReservationTableResult(reservationNo);
    }

    @Override
    public void onReservationTable(Date orderDate, int tableNo, String name) {
        try {
            int reservationNo = model.reservationTable(orderDate, tableNo, name);
            updateUIShowReservationTableResult(reservationNo);
        } catch (RuntimeException e) {
            updateUIShowReservationTableResult(-1);
        }
    }

    public void onChangeReservation(int oldReservation, Date reservationDate, int tableNo, String name) {
        try {
            int reservationNo = model.changeReservation(oldReservation, reservationDate, tableNo, name);
            updateUIShowReservationTableResult(reservationNo);
        } catch (RuntimeException e) {
            updateUIShowReservationTableResult(-1);
        }
    }
}

