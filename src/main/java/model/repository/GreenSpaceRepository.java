package model.repository;

import model.GreenSpace;


import java.util.*;

public class GreenSpaceRepository {
    private Map<String,List<GreenSpace>> greenSpacesMap;

    public GreenSpaceRepository() {greenSpacesMap=new HashMap<String,List<GreenSpace>>();}

    /**
     * @param greenSpaceName name of the green space
     * @param userName name of the user
     * @return green space object with the name indicated, created by the indicated user, if found, null in other case
     */
    public GreenSpace getGreenSpaceByName(String greenSpaceName, String userName) {
        for(GreenSpace g:greenSpacesMap.get(userName)){
            if (g.getName().equals(greenSpaceName)){
                return g.clone();
            }
        }
        return null;
    }

    /**
     * in input name, type, area and address of the green space, username of the user
     * @return true if the green space is added to the list, false if the green space already exist
     */
    public boolean registerGreenSpace(String name, String type,int area,String address, String userName) {
        GreenSpace g=new GreenSpace(name,type,area,address);
        if (!greenSpacesMap.containsKey(userName)){
            List<GreenSpace> l=new ArrayList<>();
            l.add(g);
            greenSpacesMap.put(userName,l);
            return true;
        }
        if (greenSpacesMap.get(userName).contains(g)) {
            return false;
        }
        greenSpacesMap.get(userName).add(g);
        return true;
    }

    /**
     *
     * @param name of the user
     * @return the list of green spaces registered by the user
     */
    public List<GreenSpace> getGreenSpaceList(String name) {
        return greenSpacesMap.get(name);
    }
}