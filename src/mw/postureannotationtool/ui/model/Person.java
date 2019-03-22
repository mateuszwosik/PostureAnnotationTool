package mw.postureannotationtool.ui.model;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private int id;
    private Posture.PostureType postureType;
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private List<Posture.Point> points;

    public Person(int id) {
        this.id = id;
        points = new ArrayList<>(10);
    }

    public int getId() {
        return id;
    }

    public Posture.PostureType getPostureType() {
        return postureType;
    }

    public void setPostureType(Posture.PostureType postureType) {
        this.postureType = postureType;
    }

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public List<Posture.Point> getPoints() {
        return points;
    }

    public void addPoint(Posture.Point point) {
        points.add(point);
    }

    public void setPersonRect(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

}
