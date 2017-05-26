import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by admin on 26.05.2017.
 */
public class Square {
    private static final Logger LOGGER= LoggerFactory.getLogger(Square.class);

    public static void main(String[] args) {
        LOGGER.info("Hello world!");

    }

    public static double[] solve (double a,double b,double c){
        double d = b*b-4*a*c;
        if (d<0)
            return new double[0];
        if (d==0)
            return new double[]{-b/(2*a)};
        return new double[]{(-b-Math.sqrt(d))/(2*a),(-b+Math.sqrt(d))/(2*a)};
    }
}
