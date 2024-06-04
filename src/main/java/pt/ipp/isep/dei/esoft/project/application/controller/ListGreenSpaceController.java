package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.ConfigReader;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.SortingAlgorithms;

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
