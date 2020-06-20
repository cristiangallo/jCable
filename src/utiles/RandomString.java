package utiles;

import java.util.Random;

public class RandomString {
    String alfanumerico = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    Random random = new Random();

    public String randomAlfaString(int largo) {
        StringBuilder sb = new StringBuilder( largo );
        for( int i = 0; i < largo; i++ )
            sb.append( alfanumerico.charAt( random.nextInt(alfanumerico.length()) ));
        return sb.toString();
    }

    public static void main(String[] args) {
        RandomString randomString = new RandomString();
        System.out.println(randomString.randomAlfaString(8));

    }
}
