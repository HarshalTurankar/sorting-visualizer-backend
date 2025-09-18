package com.sortingvisualizer.service;

import com.sortingvisualizer.model.SortResponse;
import com.sortingvisualizer.model.SortStep;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SortService {

    private int stepCounter; // ✅ Global counter for steps

    public SortResponse sort(String algorithm, int[] arr) {
        List<SortStep> steps = new ArrayList<>();
        int[] copy = Arrays.copyOf(arr, arr.length);
        stepCounter = 0; // reset each sort

        String time = "";
        String space = "";

        switch (algorithm.toLowerCase()) {
            case "bubble":
                bubbleSort(copy, steps);
                time = "O(n²)";
                space = "O(1)";
                break;
            case "selection":
                selectionSort(copy, steps);
                time = "O(n²)";
                space = "O(1)";
                break;
            case "insertion":
                insertionSort(copy, steps);
                time = "O(n²)";
                space = "O(1)";
                break;
            case "merge":
                mergeSort(copy, 0, copy.length - 1, steps);
                time = "O(n log n)";
                space = "O(n)";
                break;
            case "quick":
                quickSort(copy, 0, copy.length - 1, steps);
                time = "O(n log n) (avg), O(n²) (worst)";
                space = "O(log n)";
                break;
            default:
                return new SortResponse("invalid", "-", "-", steps);
        }

        // ✅ Mark final array as fully sorted
        steps.add(new SortStep(
                ++stepCounter,
                Arrays.copyOf(copy, copy.length),
                List.of(),
                rangeList(0, copy.length - 1),
                null,
                List.of()
        ));

        return new SortResponse(algorithm, time, space, steps);
    }

    // ---------------- Bubble Sort ----------------
    private void bubbleSort(int[] arr, List<SortStep> steps) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                List<Integer> highlight = List.of(j, j + 1);
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
                steps.add(new SortStep(++stepCounter,
                        Arrays.copyOf(arr, arr.length),
                        highlight,
                        List.of(),
                        null,
                        List.of()
                ));
            }
        }
    }

    // ---------------- Selection Sort ----------------
    private void selectionSort(int[] arr, List<SortStep> steps) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;

            steps.add(new SortStep(++stepCounter,
                    Arrays.copyOf(arr, arr.length),
                    List.of(i, minIdx),
                    List.of(),
                    null,
                    List.of()
            ));
        }
    }

    // ---------------- Insertion Sort ----------------
    private void insertionSort(int[] arr, List<SortStep> steps) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                steps.add(new SortStep(++stepCounter,
                        Arrays.copyOf(arr, arr.length),
                        List.of(j, j + 1),
                        List.of(),
                        null,
                        List.of()
                ));
                j--;
            }
            arr[j + 1] = key;
            steps.add(new SortStep(++stepCounter,
                    Arrays.copyOf(arr, arr.length),
                    List.of(j + 1),
                    List.of(),
                    null,
                    List.of()
            ));
        }
    }

    // ---------------- Merge Sort ----------------
    private void mergeSort(int[] arr, int l, int r, List<SortStep> steps) {
        if (l < r) {
            int mid = (l + r) / 2;
            mergeSort(arr, l, mid, steps);
            mergeSort(arr, mid + 1, r, steps);
            merge(arr, l, mid, r, steps);
        }
    }

    private void merge(int[] arr, int l, int m, int r, List<SortStep> steps) {
        int n1 = m - l + 1, n2 = r - m;
        int[] L = Arrays.copyOfRange(arr, l, m + 1);
        int[] R = Arrays.copyOfRange(arr, m + 1, r + 1);

        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            arr[k] = (L[i] <= R[j]) ? L[i++] : R[j++];
            steps.add(new SortStep(++stepCounter,
                    Arrays.copyOf(arr, arr.length),
                    List.of(k),
                    List.of(),
                    null,
                    rangeList(l, r)
            ));
            k++;
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];

        steps.add(new SortStep(++stepCounter,
                Arrays.copyOf(arr, arr.length),
                List.of(),
                List.of(),
                null,
                rangeList(l, r)
        ));
    }

    // ---------------- Quick Sort ----------------
    private void quickSort(int[] arr, int low, int high, List<SortStep> steps) {
        if (low < high) {
            int pi = partition(arr, low, high, steps);
            quickSort(arr, low, pi - 1, steps);
            quickSort(arr, pi + 1, high, steps);
        }
    }

    private int partition(int[] arr, int low, int high, List<SortStep> steps) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            steps.add(new SortStep(++stepCounter,
                    Arrays.copyOf(arr, arr.length),
                    List.of(j),
                    List.of(),
                    high,
                    List.of()
            ));
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                steps.add(new SortStep(++stepCounter,
                        Arrays.copyOf(arr, arr.length),
                        List.of(i, j),
                        List.of(),
                        high,
                        List.of()
                ));
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        steps.add(new SortStep(++stepCounter,
                Arrays.copyOf(arr, arr.length),
                List.of(i + 1, high),
                List.of(),
                high,
                List.of()
        ));
        return i + 1;
    }

    // ---------------- Helper ----------------
    private List<Integer> rangeList(int start, int end) {
        List<Integer> list = new ArrayList<>();
        for (int i = start; i <= end; i++) list.add(i);
        return list;
    }
}
