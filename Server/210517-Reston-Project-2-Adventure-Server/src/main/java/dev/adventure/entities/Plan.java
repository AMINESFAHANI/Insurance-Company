package dev.adventure.entities;

public class Plan implements Comparable<Plan>{

    private int planID;
    private String name;
    private String type;
    private float deductible;
    private float premium;

    public Plan(){}

    public Plan(int planID, String name, String type, float deductible, float premium) {
        this.planID = planID;
        this.name = name;
        this.type = type;
        this.deductible = deductible;
        this.premium = premium;
    }

    public int getPlanID() {
        return planID;
    }

    public void setPlanID(int planID) {
        this.planID = planID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getDeductible() {
        return deductible;
    }

    public void setDeductible(float deductible) {
        this.deductible = deductible;
    }

    public float getPremium() {
        return premium;
    }

    public void setPremium(float premium) {
        this.premium = premium;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "planID=" + planID +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", deductible=" + deductible +
                ", premium=" + premium +
                '}';
    }

    @Override
    public int compareTo(Plan o) {
        if (this.planID > o.planID){
            return 1;
        } else if (this.planID < o.planID){
            return -1;
        } else {
            return 0;
        }
    }
}
