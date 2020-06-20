package utiles;

import java.util.Random;

public class RandomString {

    public static String randomAlfaString(int largo) {
        String alfanumerico = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();

        StringBuilder sb = new StringBuilder( largo );
        for( int i = 0; i < largo; i++ )
            sb.append( alfanumerico.charAt( random.nextInt(alfanumerico.length()) ));
        return sb.toString();
    }

    public static void main(String[] args) {
        // RandomString randomString = new RandomString();
        System.out.println(RandomString.randomAlfaString(8));

    }
}
