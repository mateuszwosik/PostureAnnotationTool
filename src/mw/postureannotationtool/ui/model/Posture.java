package mw.postureannotationtool.ui.model;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Posture {

    /* ========== FRONT POINTS ========== */
    public static final List<String> FRONT_POINTS_ORDER = List.of(
            "RIGHT_EYE",
            "LEFT_EYE",
            "CHIN",
            "MANUBRIUM",
            "RIGHT_SHOULDER",
            "LEFT_SHOULDER",
            "RIGHT_ASIS",
            "LEFT_ASIS",
            "RIGHT_ANKLE",
            "LEFT_ANKLE");
    public static final Map<String, String> FRONT_POINTS_IMAGES = Map.of(
            "RIGHT_EYE","res/images/front_posture.jpg",
            "LEFT_EYE","res/images/front_posture.jpg",
            "CHIN","res/images/front_posture.jpg",
            "MANUBRIUM","res/images/front_posture.jpg",
            "RIGHT_SHOULDER","res/images/front_posture.jpg",
            "LEFT_SHOULDER","res/images/front_posture.jpg",
            "RIGHT_ASIS","res/images/front_posture.jpg",
            "LEFT_ASIS","res/images/front_posture.jpg",
            "RIGHT_ANKLE","res/images/front_posture.jpg",
            "LEFT_ANKLE","res/images/front_posture.jpg");
    public static final Map<String, String> FRONT_POINTS_NAMES = Map.of(
            "RIGHT_EYE","Prawe oko",
            "LEFT_EYE","Lewe oko",
            "CHIN","Broda",
            "MANUBRIUM","Wcięcie szyjne mostka",
            "RIGHT_SHOULDER","Wyrostek barkowy prawy",
            "LEFT_SHOULDER","Wyrostek barkowy lewy",
            "RIGHT_ASIS","Kolec biodrowy przedni górny prawy (ASIS prawy)",
            "LEFT_ASIS","Kolec biodrowy przedni górny lewy (ASIS lewy)",
            "RIGHT_ANKLE","Punkt między kostką boczną i przyśrodkową prawy",
            "LEFT_ANKLE","Punkt między kostką boczną i przyśrodkową lewy");

    /* ========== BACK POINTS ========== */
    public static final List<String> BACK_POINTS_ORDER = Arrays.asList(
            "C7",
            "TH12",
            "S1",
            "LEFT_SHOULDER_BLADE",
            "LEFT_LOWER_ANGLE_SHOULDER_BLADE",
            "RIGHT_SHOULDER_BLADE",
            "RIGHT_LOWER_ANGLE_SHOULDER_BLADE",
            "LEFT_PSIS",
            "RIGHT_PSIS",
            "LEFT_HEEL",
            "RIGHT_HEEL");
    public static final Map<String, String> BACK_POINTS_IMAGES = Stream.of(new String[][]{
            {"C7","res/images/back_posture.jpg"},
            {"TH12","res/images/back_posture.jpg"},
            {"S1","res/images/back_posture.jpg"},
            {"LEFT_SHOULDER_BLADE","res/images/back_posture.jpg"},
            {"LEFT_LOWER_ANGLE_SHOULDER_BLADE","res/images/back_posture.jpg"},
            {"RIGHT_SHOULDER_BLADE","res/images/back_posture.jpg"},
            {"RIGHT_LOWER_ANGLE_SHOULDER_BLADE","res/images/back_posture.jpg"},
            {"LEFT_PSIS","res/images/back_posture.jpg"},
            {"RIGHT_PSIS","res/images/back_posture.jpg"},
            {"LEFT_HEEL","res/images/back_posture.jpg"},
            {"RIGHT_HEEL","res/images/back_posture.jpg"}
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
    public static final Map<String, String> BACK_POINTS_NAMES = Stream.of(new String[][]{
            {"C7","Wyrostek kolczysty C7"},
            {"TH12","Wyrostek kolczysty Th12"},
            {"S1","Podstawy kości krzyżowej (S1)"},
            {"LEFT_SHOULDER_BLADE","Grzebień łopatki lewej"},
            {"LEFT_LOWER_ANGLE_SHOULDER_BLADE","Kąt dolny łopatki lewej"},
            {"RIGHT_SHOULDER_BLADE","Grzebień łopatki prawej"},
            {"RIGHT_LOWER_ANGLE_SHOULDER_BLADE","Kąt dolny łopatki prawej"},
            {"LEFT_PSIS","Kolec biodrowy tylny górny lewy (PSIS lewy)"},
            {"RIGHT_PSIS","Kolec biodrowy tylny górny prawy (PSIS prawy)"},
            {"LEFT_HEEL","Środek pięty lewej"},
            {"RIGHT_HEEL","Środek pięty prawej"}
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    /* ========== SIDE POINTS ========== */
    public static final List<String> SIDE_POINTS_ORDER = Arrays.asList(
            "EAR",
            "SHOULDER",
            "C7",
            "TH12",
            "TH10",
            "TH4",
            "S1",
            "ASIS",
            "PSIS",
            "TROCHLEADER",
            "KNEE",
            "ANKLE");
    public static final Map<String, String> SIDE_POINTS_IMAGES = Stream.of(new String[][]{
            {"EAR","res/images/side_posture.jpg"},
            {"SHOULDER","res/images/side_posture.jpg"},
            {"C7","res/images/side_posture.jpg"},
            {"TH12","res/images/side_posture.jpg"},
            {"TH10","res/images/side_posture.jpg"},
            {"TH4","res/images/side_posture.jpg"},
            {"S1","res/images/side_posture.jpg"},
            {"ASIS","res/images/side_posture.jpg"},
            {"PSIS","res/images/side_posture.jpg"},
            {"TROCHLEADER","res/images/side_posture.jpg"},
            {"KNEE","res/images/side_posture.jpg"},
            {"ANKLE","res/images/side_posture.jpg"}
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
    public static final Map<String, String> SIDE_POINTS_NAMES = Stream.of(new String[][]{
            {"EAR","Środek ucha (kanał słuchowy)"},
            {"SHOULDER","Wyrostek barkowy"},
            {"C7","Wyrostek kolczysty C7"},
            {"TH12","Wyrostek kolczysty Th12"},
            {"TH10","Wyrostek kolczysty Th10"},
            {"TH4","Wyrostek kolczysty Th4"},
            {"S1","Podstawa kości krzyżowej (S1)"},
            {"ASIS","Kolec biodrowy przedni górny (ASIS)"},
            {"PSIS","Kolec biodrowy tylny górny (PSIS)"},
            {"TROCHLEADER","Szczyt krętarza większego"},
            {"KNEE","Nadkłykieć boczny kości udowej"},
            {"ANKLE","Kostka boczna"}
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    public enum PostureType {
        FRONT("Front"),
        BACK("Back"),
        RIGHT("Right"),
        LEFT("Left");

        private String name;

        public String getName(){
            return this.name;
        }

        PostureType(String name) {
            this.name = name;
        }
    }

    public class Point {

        private String name;
        private double x;
        private double y;

        public Point(String name, double x, double y) {
            this.name = name;
            this.x = x;
            this.y = y;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return Double.compare(point.x, x) == 0 &&
                    Double.compare(point.y, y) == 0 &&
                    name.equals(point.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, x, y);
        }

    }



}