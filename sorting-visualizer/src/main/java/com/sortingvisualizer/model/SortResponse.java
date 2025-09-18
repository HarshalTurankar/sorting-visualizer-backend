package com.sortingvisualizer.model;

import java.util.List;

public class SortResponse {
    private String algorithm;
    private String timeComplexity;
    private String spaceComplexity;
    private List<SortStep> steps;

    public SortResponse(String algorithm, String timeComplexity, String spaceComplexity, List<SortStep> steps) {
        this.algorithm = algorithm;
        this.timeComplexity = timeComplexity;
        this.spaceComplexity = spaceComplexity;
        this.steps = steps;
    }

    public String getAlgorithm() { return algorithm; }
    public String getTimeComplexity() { return timeComplexity; }
    public String getSpaceComplexity() { return spaceComplexity; }
    public List<SortStep> getSteps() { return steps; }
}
