package edu.team27.perfectCube.model;

/**
 * holds variables used to search
 * */
public class SearchCriteria {

    private String filterGender;
    private String filterAge;
    private String filterName;

    /**
     * creates an instance of SearchCriteria
     *
     * @param fG the gender picked in spinner
     * @param fA the age picked in spinner
     * @param fN the name entered in editText search
     * */
    public SearchCriteria(String fG, String fA, String fN) {
        filterGender = fG;
        filterAge = fA;
        filterName = fN;
    }

    /**
     * @return gender you want listed
     * */
    public String getFilterGender() {
        return filterGender;
    }

    /**
     * @param g the gender you want the shelters listed to accept
     * */
    public void setFilterGender(String g) {
        filterGender = g;
    }

    /**
     * @return age you want listed
     * */
    public String getFilterAge() {
        return filterAge;
    }

    /**
     * @param a the age you want the shelters listed to accept
     * */
    public void setFilterAge(String a) {
        filterAge = a;
    }

    /**
     * @return name of shelter you want listed
     * */
    public String getFilterName() {
        return filterName;
    }

    /**
     * @param n the name of the shelter you're looking for
     * */
    public void setFilterName(String n) {
        filterName = n;
    }
}
