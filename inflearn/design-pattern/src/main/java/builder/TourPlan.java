package builder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TourPlan {
    private String title;
    private int nights;
    private int days;
    private LocalDate startAt;
    private String whereToStay;
    private List<DetailPlan> plans = new ArrayList<>();

    public TourPlan() {

    }

    public TourPlan(String title, int nights, int days, LocalDate startAt, String whereToStay) {
        this.title = title;
        this.nights = nights;
        this.days = days;
        this.startAt = startAt;
        this.whereToStay = whereToStay;
    }

    public void addPlan(int day, String plan) {
        this.plans.add(new DetailPlan(day, plan));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNights() {
        return nights;
    }

    public void setNights(int nights) {
        this.nights = nights;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public LocalDate getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalDate startAt) {
        this.startAt = startAt;
    }

    public String getWhereToStay() {
        return whereToStay;
    }

    public void setWhereToStay(String whereToStay) {
        this.whereToStay = whereToStay;
    }

    public List<DetailPlan> getPlans() {
        return plans;
    }

    public void setPlans(List<DetailPlan> plans) {
        this.plans = plans;
    }
}
