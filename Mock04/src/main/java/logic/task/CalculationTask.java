package logic.task;

import interfaces.Computable;
import interfaces.Modifiable;
import logic.compute.ProcessUnit;

public class CalculationTask extends Task implements Computable, Modifiable {
    public CalculationTask(String name, double workload){
        super(name, workload);
    }

    @Override
    public double compute(ProcessUnit processUnit){
        return processUnit.compute(this);
    }

    public void changeWorkload(int workload){
        setWorkload(getWorkload() + workload);
    }

    @Override
    public void modify(int modifyValue) {
        changeWorkload(modifyValue);
    }

    @Override
    public String fullTaskName() {
        return "Calculation Task";
    }
}
