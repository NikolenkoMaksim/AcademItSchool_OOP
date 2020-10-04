package ClassRange;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public void formatRangeEnds() {
        double epsilon = 1e-10;

        if (to - from < -epsilon) {
            double temp = from;
            setFrom(to);
            setTo(temp);
        }
    }

    public void findIntersection(Range range1, Range range2) {

        double from1 = range1.getFrom();
        double to1 = range1.getTo();
        double from2 = range2.getFrom();
        double to2 = range2.getTo();

        if (from1 < from2) {
            if (from2 > to1) {
                setFrom(1);
                setTo(-1);
            } else if (to1 > to2) {
                setFrom(from2);
                setTo(to2);
            } else {
                setFrom(from2);
                setTo(to1);
            }
        } else {
            if (from1 > to2) {
                setFrom(1);
                setTo(-1);
            } else if (to2 > to1) {
                setFrom(from1);
                setTo(to1);
            } else {
                setFrom(from1);
                setTo(to2);
            }
        }
    }
}
