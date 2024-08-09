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

        //добавить 2 разных инстанса разных классов Line и PolyLine
        Line line1 = new Line(point1, point2);
        PolyLine polyLine1 = new PolyLine(arrayPoints1);

        //вызывать метод подсчета длины у разных инстансов
        System.out.println(line1.getLength());
        System.out.println(polyLine1.getLength());

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
}


class PolyLine implements Measurable {
    ArrayList<Point> listPoint = new ArrayList<>();

    public PolyLine(ArrayList<Point> listPoint) {
        this.listPoint = listPoint;
    }

    public double getLength() {
        double sum = 0, len1, len2;
        for (int i = 0; i < listPoint.size() - 1; i++) {
            len1 = listPoint.get(i).x - listPoint.get(i + 1).x;
            len2 = listPoint.get(i).y - listPoint.get(i + 1).y;
            sum += Math.sqrt(len1 * len1 + len2 * len2);
        }
        return sum;
    }
}

interface Measurable {
    double getLength();
}
