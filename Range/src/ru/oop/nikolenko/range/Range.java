package ru.oop.nikolenko.range;

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
        if (to < from) {
            double temp = from;
            from = to;
            to = temp;
        }
    }

    public Range getIntersection(Range range) {
        double max = Math.max(from, range.from);
        double min = Math.min(to, range.to);

        if (max >= min) {
            return null;
        }

        return new Range(max, min);
    }

    public Range[] getUnion(Range range) {
        if (Math.max(from, range.from) <= Math.min(to, range.to)) {
            return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
        }

        if (from < range.from) {
            return new Range[]{new Range(from, to), new Range(range.from, range.to)};
        }

        return new Range[]{new Range(range.from, range.to), new Range(from, to)};
    }

    public Range[] getDifference(Range range) {
        if (from >= range.from && to <= range.to) {
            return new Range[]{};
        }

        if (from < range.from && to > range.to) {
            return new Range[]{new Range(from, range.from), new Range(range.to, to)};
        }

        if (from < range.from) {
            return new Range[]{new Range(from, Math.min(to, range.from))};
        }

        return new Range[]{new Range(Math.max(range.to, from), to)};
    }

    @Override
    public String toString() {
        return "(" + from + ", " + to + ')';
    }
}