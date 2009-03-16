/**
 * 
 */
package ch.fhzh.file;

/**
 * @author bseelige
 *
 */
public class Person {

    enum SEX {
        MALE,
        FEMALE,
        UNKNOWN;
    }
    
    private String name;
    private String prename;
    private SEX sex;
    
    
    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }
    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return Returns the prename.
     */
    public String getPrename() {
        return prename;
    }
    /**
     * @param prename The prename to set.
     */
    public void setPrename(String prename) {
        this.prename = prename;
    }
    /**
     * @return Returns the sex.
     */
    public SEX getSex() {
        return sex;
    }
    /**
     * @param sex The sex to set.
     */
    public void setSex(SEX sex) {
        this.sex = sex;
    }
    
    
    

}
