package mw.postureannotationtool.ui.model;

import java.util.*;
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
            "RIGHT_EYE", "/images/front/right_eye.jpg",
            "LEFT_EYE", "/images/front/left_eye.jpg",
            "CHIN", "/images/front/chin.jpg",
            "MANUBRIUM", "/images/front/manubrium.jpg",
            "RIGHT_SHOULDER", "/images/front/right_shoulder.jpg",
            "LEFT_SHOULDER", "/images/front/left_shoulder.jpg",
            "RIGHT_ASIS", "/images/front/right_asis.jpg",
            "LEFT_ASIS", "/images/front/left_asis.jpg",
            "RIGHT_ANKLE", "/images/front/right_ankle.jpg",
            "LEFT_ANKLE", "/images/front/left_ankle.jpg");
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
            {"C7", "/images/back/c7.jpg"},
            {"TH12", "/images/back/th12.jpg"},
            {"S1", "/images/back/s1.jpg"},
            {"LEFT_SHOULDER_BLADE", "/images/back/left_shoulder_blade.jpg"},
            {"LEFT_LOWER_ANGLE_SHOULDER_BLADE", "/images/back/left_lower_angle_shoulder_blade.jpg"},
            {"RIGHT_SHOULDER_BLADE", "/images/back/right_shoulder_blade.jpg"},
            {"RIGHT_LOWER_ANGLE_SHOULDER_BLADE", "/images/back/right_lower_angle_shoulder_blade.jpg"},
            {"LEFT_PSIS", "/images/back/left_psis.jpg"},
            {"RIGHT_PSIS", "/images/back/right_psis.jpg"},
            {"LEFT_HEEL", "/images/back/left_heel.jpg"},
            {"RIGHT_HEEL", "/images/back/right_heel.jpg"}
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
    public static final Map<String, String> SIDE_LEFT_POINTS_IMAGES = Stream.of(new String[][]{
            {"EAR", "/images/left/ear.jpg"},
            {"SHOULDER", "/images/left/shoulder.jpg"},
            {"C7", "/images/left/c7.jpg"},
            {"TH12", "/images/left/th12.jpg"},
            {"TH10", "/images/left/th10.jpg"},
            {"TH4", "/images/left/th4.jpg"},
            {"S1", "/images/left/s1.jpg"},
            {"ASIS", "/images/left/asis.jpg"},
            {"PSIS", "/images/left/psis.jpg"},
            {"TROCHLEADER", "/images/left/trochleader.jpg"},
            {"KNEE", "/images/left/knee.jpg"},
            {"ANKLE", "/images/left/ankle.jpg"}
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
    public static final Map<String, String> SIDE_RIGHT_POINTS_IMAGES = Stream.of(new String[][]{
            {"EAR", "/images/right/ear.jpg"},
            {"SHOULDER", "/images/right/shoulder.jpg"},
            {"C7", "/images/right/c7.jpg"},
            {"TH12", "/images/right/th12.jpg"},
            {"TH10", "/images/right/th10.jpg"},
            {"TH4", "/images/right/th4.jpg"},
            {"S1", "/images/right/s1.jpg"},
            {"ASIS", "/images/right/asis.jpg"},
            {"PSIS", "/images/right/psis.jpg"},
            {"TROCHLEADER", "/images/right/trochleader.jpg"},
            {"KNEE", "/images/right/knee.jpg"},
            {"ANKLE", "/images/right/ankle.jpg"}
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
        FRONT("front"),
        BACK("back"),
        RIGHT("right"),
        LEFT("left");

        private String name;

        public String getName(){
            return this.name;
        }

        PostureType(String name) {
            this.name = name;
        }
    }

    public static class Point {

        private String name;
        private double x;
        private double y;

        public Point(String name, double x, double y) {
            this.name = name;
            this.x = x;
            this.y = y;
        }

        private String getName() {
            return name;
        }

        private double getX() {
            return x;
        }

        private double getY() {
            return y;
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

        @Override
        public String toString(){
            return this.getName() +
                    ";" +
                    this.getX() +
                    ";" +
                    this.getY();
        }

    }



}
