package ru.oop.nikolenko.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public Range() {
        this.from = 0;
        this.to = 0;
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
        if (to < from) {
            double temp = from;
            from = to;
            to = temp;
        }
    }

    public Range getIntersection(Range range) {
        Range intersection = new Range();

        if (from < range.from) {
            if (to < range.from) {
                return null;
            }

            intersection.from = range.from;
            intersection.to = Math.min(to, range.to);
        } else {
            if (from > range.to) {
                return null;
            }

            intersection.from = from;
            intersection.to = Math.min(range.to, to);
        }

        if (intersection.from == intersection.to) {
            return null;
        }

        return intersection;
    }

    public Range[] getUnion(Range range) {
        if (from < range.from) {
            if (from < range.from) {
                if (to < range.from) {
                    return new Range[]{new Range(from, to), new Range(range.from, range.to)};
                } else if (to < range.to) {
                    return new Range[]{new Range(from, range.to)};
                } else {
                    return new Range[]{new Range(from, to)};
                }
            }
        } else {
            if (range.to < from) {
                return new Range[]{new Range(range.from, range.to), new Range(from, to)};
            } else if (range.to < to) {
                return new Range[]{new Range(range.from, to)};
            }
        }

        return new Range[]{new Range(range.from, range.to)};
    }

    public Range[] getDifference(Range range) {
        if (from >= range.from && to <= range.to) {
            return new Range[]{};
        }

        Range intersection = getIntersection(range);

        if (intersection == null) {
            return new Range[]{new Range(from, to)};
        }

        if (from == intersection.from) {
            return new Range[]{new Range(intersection.to, to)};
        } else if (to == intersection.to) {
            return new Range[]{new Range(from, intersection.from)};
        }

        return new Range[]{new Range(from, intersection.from), new Range(intersection.to, to)};
    }

    @Override
    public String toString() {
        return "(" + from + ", " + to + ')';
    }
}