package ui.utils;

import model.GreenSpace;

import java.util.Comparator;
import java.util.List;

public class SortingAlgorithms {

    public static void sortBySizeDescending(List<GreenSpace> greenSpaces) {
        greenSpaces.sort(new Comparator<GreenSpace>() {
            @Override
            public int compare(GreenSpace gs1, GreenSpace gs2) {
                return Integer.compare(gs2.getArea(), gs1.getArea());
            }
        });
    }

    public static void sortBySizeDescendingAlternative(List<GreenSpace> greenSpaces) {
        greenSpaces.sort((gs1, gs2) -> Integer.compare(gs2.getArea(), gs1.getArea()));
    }
}
