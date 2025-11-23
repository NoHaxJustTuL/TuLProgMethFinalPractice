package logic.task;

import interfaces.Computable;
import interfaces.Modifiable;
import interfaces.Parallelizable;
import logic.compute.ProcessUnit;

public class SortTask extends Task implements Computable, Modifiable, Parallelizable{

    public SortTask(String name, double workload){
        super(name, workload);
    }

    @Override
    public double compute(ProcessUnit processUnit) {
        return processUnit.compute(this);
    }

    @Override
    public double parallelCompute(ProcessUnit processUnit) {
        return processUnit.parallelCompute(this);
    }

    public void mergeTask(int index){
        Task selectTask = TaskList.getTask(index);
        if (selectTask instanceof SortTask){
            setWorkload(getWorkload() + selectTask.getWorkload());
            TaskList.removeTask(index);
        }
    }

    @Override
    public void modify(int modifyValue) {
        mergeTask(modifyValue);
    }

    @Override
    public String fullTaskName() {
        return "Sorting Task";
    }
}
