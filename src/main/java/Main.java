import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Point point1 = new Point(1, 5);
        Point point2 = new Point(2, 8);
        Point point3 = new Point(5, 3);
        Point point4 = new Point(8, 9);

        //Создается массив нужных точек для создания ломаной линии
        ArrayList<Point> arrayPoints1 = new ArrayList<>();
        arrayPoints1.add(point1);
        arrayPoints1.add(point2);
        arrayPoints1.add(point3);
        arrayPoints1.add(point4);

        //Создается массив нужных точек для создания замкнутой ломаной линии
        ArrayList<Point> arrayPoints2 = new ArrayList<>();
        arrayPoints2.add(point1);
        arrayPoints2.add(point2);
        arrayPoints2.add(point3);
        arrayPoints2.add(point4);
        arrayPoints2.add(point1);

        //Создать Ломаную, проходящую через точки
        PolyLine polyLine1 = new PolyLine(arrayPoints1);
        PolyLine polyLine2 = new PolyLine(arrayPoints2);

        //Создать список ломанных
        ArrayList<PolyLine> arrayPolyline = new ArrayList<>();
        arrayPolyline.add(polyLine1);
        arrayPolyline.add(polyLine2);

        //Создать экземпляр класса тест, где лежит интерфейсный метод
        Test test = new Test();
        test.executeMeasure(arrayPolyline);
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "{" + x + ";" + y + "}";
    }
}

class Line implements Measurable {
    Point start;
    Point end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Line(int x1, int y1, int x2, int y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    public String toString() {
        return "Линия от " + start + " до " + end;
    }

    public double getLength() {
        int segment1 = start.x - end.x;
        int segment2 = start.y - end.y;
        return Math.sqrt(segment1 * segment1 + segment2 * segment2);
    }

    public void measure() {
        System.out.println(this);
    }

    ;
}

class PolyLine implements Measurable {
    ArrayList<Point> listPoint = new ArrayList<>();

    //конструктор с массивом точек
    public PolyLine(ArrayList<Point> listPoint) {
        this.listPoint = listPoint;
    }

    //конструктор без параметров
    public PolyLine() {
    }

    //расчитать длину ломаной
    public double getLength(ArrayList<Point> listPoint) {
        this.listPoint = listPoint;
        double polyLineLength = 0;
        for (int i = 0; i < listPoint.size() - 1; i++) {
            int katet1 = listPoint.get(i + 1).x - listPoint.get(i).x;
            int katet2 = listPoint.get(i + 1).y - listPoint.get(i).y;
            double segment = Math.sqrt(katet1 * katet1 + katet2 * katet2);
            polyLineLength += segment;
        }
        System.out.println("Длина итоговая ломаной " + polyLineLength);
        return polyLineLength;
    }

    public void measure() {
        System.out.println(this);
    }

    ;

    public String toString() {
        return "Линия " + listPoint.toString();
    }
}

interface Measurable {
    void measure();
}

class Test {
    public void executeMeasure(ArrayList<PolyLine> listPolyline) {
        ArrayList<Measurable> measurable = new ArrayList<>();
        measurable.addAll(listPolyline);
        for (Measurable element : measurable) {
            System.out.println(element);
        }
    }
}
