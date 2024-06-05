package controller;

import model.GreenSpace;
import model.repository.AuthenticationRepository;
import model.repository.GreenSpaceRepository;
import model.repository.Repositories;
import ui.utils.ConfigReader;
import ui.utils.SortingAlgorithms;

import java.util.List;

public class ListGreenSpaceController {
    private final GreenSpaceRepository greenSpaceRepository;
    private final AuthenticationRepository authenticationRepository;
    private final String sortingAlgorithm;

    public ListGreenSpaceController() {
        Repositories repositories = Repositories.getInstance();
        this.greenSpaceRepository = repositories.getGreenSpaceRepository();
        this.authenticationRepository = repositories.getAuthenticationRepository();
        this.sortingAlgorithm = ConfigReader.getInstance().getProperty("sorting.algorithm", "default");
    }

    public List<GreenSpace> listGreenSpaces() {
        String username = authenticationRepository.getCurrentUserSession().getUserId().toString();
        List<GreenSpace> greenSpaces = greenSpaceRepository.getGreenSpaceList(username);
        if (greenSpaces == null) {
            throw new IllegalStateException("Green spaces list is null");
        }
        applySortingAlgorithm(greenSpaces);
        return greenSpaces;
    }

    private void applySortingAlgorithm(List<GreenSpace> greenSpaces) {
        if (greenSpaces != null) {
            switch (sortingAlgorithm) {
                case "alternative":
                    SortingAlgorithms.sortBySizeDescendingAlternative(greenSpaces);
                    break;
                case "default":
                default:
                    SortingAlgorithms.sortBySizeDescending(greenSpaces);
                    break;
            }
        }
    }
}
