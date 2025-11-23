package interfaces;

import logic.compute.ProcessUnit;

public interface Parallelizable {
    double parallelCompute(ProcessUnit processUnit);
}
