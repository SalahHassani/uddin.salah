import strings.StringImpUtils;

import java.util.Arrays;

public class Main {

  public static void printUri(String[] uriParts) {
    System.out.println(Arrays.toString(uriParts));
  }

  public static void main(String[] args) {
    StringImpUtils utils = new StringImpUtils();

    System.out.println("Task1: Convert IP address to byte array");
    int i = 0;
    byte[] ip = utils.ip2Bytes("127.0.0.255");
    System.out.println(Arrays.toString(ip));

    System.out.println("\nTask2: Convert byte array to IP address");
    System.out.println(utils.convertIp("127.0.0.1"));
    System.out.println(utils.convertIp("255.255.255.255"));


    System.out.println("\nTask3: Create alphabet string");
    System.out.println(utils.alphabet());

    System.out.println("\nTask4: Convert uri to array of uri parts");

    String uri1 = "ftp://login:password@1.2.3.4:25/pass0/pass1/pass2?a=&b=2#anchor";
    String uri2 = "ftp://1.2.3.4/pass0/pass1/pass2?a=&b=2#anchor";
    String uri3 = "ftp:///pass0/pass1/pass2?a=&b=2#anchor";
    String uri4 = "ftp:///";
    String uri5 = "ftp:///?a=1&b=2#anchor";

    printUri(utils.uri2Array(uri1));
    printUri(utils.uri2Array(uri2));
    printUri(utils.uri2Array(uri3));
    printUri(utils.uri2Array(uri4));
    printUri(utils.uri2Array(uri5));

    System.out.println("\nTask5: Convert a String to Camel Case String");
    System.out.println(utils.toCamelCase("HeLlO jAvA, WoRlD"));

    System.out.println("\nTask6: Convert a Camel Case String a Simple Case String");
    System.out.println(utils.fromCamelCase("HelloWorldIAmACoder."));
  }
}

