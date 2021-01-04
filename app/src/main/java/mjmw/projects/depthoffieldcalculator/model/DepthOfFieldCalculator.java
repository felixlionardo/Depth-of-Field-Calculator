package mjmw.projects.depthoffieldcalculator.model;

public class DepthOfFieldCalculator {
    private double hyperFocalDistance;
    private double nearFocalPoint;
    private double farFocalPoint;
    private double depthOfField;

    public DepthOfFieldCalculator(int focalLength,double distanceValue,double apertureValue,double COC) {
        distanceValue = distanceValue*1000; // Convert from m to mm
        double hyperFocalDistance = Math.pow(focalLength,2)/(apertureValue*COC);
        this.hyperFocalDistance = hyperFocalDistance;
        double nearFocalPoint = (hyperFocalDistance*distanceValue)/(hyperFocalDistance+(distanceValue-focalLength));
        this.nearFocalPoint = nearFocalPoint;
        double farFocalPoint = (hyperFocalDistance*distanceValue)/(hyperFocalDistance-(distanceValue-focalLength));
        if(farFocalPoint<0){
            farFocalPoint = Double.POSITIVE_INFINITY;
        }
        this.farFocalPoint = farFocalPoint;
        this.depthOfField = farFocalPoint-nearFocalPoint;
    }

    public double getHyperFocalDistance() {
        return (hyperFocalDistance/1000);
    }

    public double getNearFocalPoint() {
        return (nearFocalPoint/1000);
    }

    public double getFarFocalPoint() {
        return (farFocalPoint/1000);
    }

    public double getDepthOfField() {
        return (depthOfField/1000);
    }
}
