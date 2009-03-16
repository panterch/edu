/**
 * 
 */
package ch.fhzh.file;

/**
 * @author bseelige
 *
 */
public class CommaSeperatedPersonSerializer implements IPersonSerializer {

    public static char SEPARATOR = ',';

    public String serialize(Person p) {
        StringBuilder ret = new StringBuilder();
        ret.append(p.getName());
        ret.append(SEPARATOR);
        ret.append(p.getPrename());
        ret.append(SEPARATOR);
        ret.append(p.getSex().name());
        return ret.toString();
    }

}
