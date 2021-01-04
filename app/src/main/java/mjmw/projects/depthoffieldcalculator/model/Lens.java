package mjmw.projects.depthoffieldcalculator.model;


public class Lens {
    private String make;
    private double maximumAperture;
    private int focalLength;

    public Lens() {
    }

    public Lens(String make, double maximumAperture, int focalLength) {
        this.make = make;
        this.maximumAperture = maximumAperture;
        this.focalLength = focalLength;
    }

    public String getMake() {
        return make;
    }

    public double getMaximumAperture() {
        return maximumAperture;
    }

    public int getFocalLength() {
        return focalLength;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setMaximumAperture(double maximumAperture) {
        this.maximumAperture = maximumAperture;
    }

    public void setFocalLength(int focalLength) {
        this.focalLength = focalLength;
    }
}
