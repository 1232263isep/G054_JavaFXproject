package pt.ipp.isep.dei.esoft.project.ui.console.utils;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;

import java.util.Collections;
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
