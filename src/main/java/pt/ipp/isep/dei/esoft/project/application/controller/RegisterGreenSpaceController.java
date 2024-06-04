package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.repository.*;


public class RegisterGreenSpaceController {
    private Repositories repositories;
    private GreenSpaceRepository greenSpaceRepository;
    private AuthenticationRepository authenticationRepository;

    public RegisterGreenSpaceController() {
        Repositories repositories = Repositories.getInstance();
        greenSpaceRepository = repositories.getGreenSpaceRepository();
        authenticationRepository = repositories.getAuthenticationRepository();
}
    /**
     *
     * *@param name, type, area and address of the green space
     * @return true if the green space has been created, false in other case
     */
    public boolean registerGreenSpace(String name,String type,int area,String address){
        String userName=authenticationRepository.getCurrentUserSession().getUserId().toString();
        return greenSpaceRepository.registerGreenSpace(name,type,area,address,userName);
    }
}