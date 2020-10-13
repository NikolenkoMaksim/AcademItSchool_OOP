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

    public void getIntersection(Range range1, Range range2) {
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

    public Range[] getUnion(Range range2) {
        Range range1 = new Range(from, to);
        double from1 = from;
        double to1 = to;
        double from2 = range2.getFrom();
        double to2 = range2.getTo();

        Range[] union = {null, null};

        if (from1 < from2) {
            if (to1 < from2) {
                union[1] = range2;
            } else if (to1 < to2) {
                range1.setTo(to2);
            }

            union[0] = range1;
        } else {
            if (to2 < from1) {
                union[0] = range2;
                union[1] = range1;
            } else if (to2 > to1) {
                union[0] = range2;
            } else {
                range1.setFrom(from2);
                union[0] = range1;
            }
        }

        return union;
    }

    public Range[] getDifference(Range range) {
        Range range1 = new Range(from, to);
        double from1 = from;
        double to1 = to;
        Range range2 = new Range(range.getFrom(), range.getTo());
        double from2 = range.getFrom();
        double to2 = range.getTo();

        Range[] difference = {null, null};

        if (from1 < from2) {
            if (to1 > from2) {
                range1.setTo(from2);

                if (to1 > to2) {
                    range2.setFrom(to2);
                    range2.setTo(to);
                    difference[1] = range2;
                }
            }

            difference[0] = range1;

        } else {
            if (from1 > to2) {
                difference[0] = range1;
            } else if (to2 < to1) {
                range1.setFrom(to2);
                difference[0] = range1;
            }
        }

        return difference;
    }
}
