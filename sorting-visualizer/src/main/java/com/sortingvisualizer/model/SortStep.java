package com.sortingvisualizer.model;

import java.util.List;

public class SortStep {
    private int stepNumber;
    private int[] array;
    private List<Integer> highlight; // indices being compared
    private List<Integer> sorted;    // sorted elements
    private Integer pivot;           // pivot element index (Quick Sort)
    private List<Integer> merged;    // merged section (Merge Sort)

    public SortStep(int stepNumber,int[] array, List<Integer> highlight, List<Integer> sorted,
                    Integer pivot, List<Integer> merged) {
        this.stepNumber=stepNumber;
        this.array = array;
        this.highlight = highlight;
        this.sorted = sorted;
        this.pivot = pivot;
        this.merged = merged;
    }

    public int getStepNumber(){
        return stepNumber;
    }
    public int[] getArray() { return array; }
    public List<Integer> getHighlight() { return highlight; }
    public List<Integer> getSorted() { return sorted; }
    public Integer getPivot() { return pivot; }
    public List<Integer> getMerged() { return merged; }
}
