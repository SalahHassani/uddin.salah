package strings;
import com.nix.jtc.strings.StringUtils;

public class StringImpUtils implements StringUtils {
  /**
   * Checks that {@code ip} complies with NNN.NNN.NNN.NNN format, i.e. that
   * {@code ip} contains four numbers separated by dots. Each field should be
   * in the range from 0 to 255. There might be leading and/or trailing
   * spaces in {@code ip}.
   * <p>IP field values from 0 through 127 (inclusive) are converted to byte
   * values between 0 and 127.<br/>
   * IP field values from 128 through 255 (inclusive) are converted to byte
   * values between -128 and -1.
   * <p>For example "127.0.0.255" -> {127, 0, 0, -1}
   *
   * @param ip IP address
   * @return {@code ip} as {@code byte} array or {@code null} in case of
   * an error
   */
  @Override
  public byte[] ip2Bytes(String ip) {

    ip = ip.trim();

    String[] parts = checkIpFormat(ip);
    if(parts == null) {
      return null;
    }

    byte[] result = new byte[4];

    try {
      for (int i = 0; i < 4; i++) {

        int value = Integer.parseInt(parts[i]);
        if (value < 0 || value > 255) {
          return null;
        }
        result[i] = convertToByte(value);

      }
    } catch (NumberFormatException e) {
      return null;
    }

    return result;
  }

  /**
   * Converts {@code ip} into NNN.NNN.NNN.NNN format where each field
   * separated by a dot has three digits.
   * <p>Before converting the string make sure that IP address passed to
   * the method is formatted properly:<br/>
   * {@code ip} contains four numbers separated by dots.<br/>
   * Each number is in the range from 0 to 255 (inclusive).<br/>
   * <p>There might be leading and/or trailing spaces in {@code ip}.
   * <p>For example "127.0.0.1" -> "127.000.000.001"
   *
   * @param ip IP address
   * @return {@code ip} as a string in NNN.NNN.NNN.NNN format <br/>
   * {@code null} in case IP address is not formatted properly
   */
  @Override
  public String convertIp(String ip) {

    String[] parts = checkIpFormat(ip);

    if(parts == null) {
      return null;
    }

    StringBuilder convertedIp = new StringBuilder();

    try {
      for (int i = 0; i < parts.length; i++) {
        int value = Integer.parseInt(parts[i]);
        if (value < 0 || value > 255) {
          return null;
        }

        convertedIp.append(String.format("%03d", value));
        if (i < parts.length - 1) {
          convertedIp.append(".");
        }
      }
    } catch (NumberFormatException e) {
      return null;
    }

    return convertedIp.toString();
  }


  /**
   * Creates a string of English alphabet letters "A...Z" with letters on even
   * positions in upper case and letters on odd positions in lower case.
   * 0 is even number
   * String starts from 0 position
   *
   * @return StringBuilder containing the alphabet string
   */
  @Override
  public StringBuilder alphabet() {
    StringBuilder stringBuilder = new StringBuilder();

    for (int i = 0; i < 26; i++) {
      char letter = (char) ('A' + i);
      if (i % 2 == 0) {
        stringBuilder.append(letter);
      } else {
        stringBuilder.append(Character.toLowerCase(letter));
      }
    }

    return stringBuilder;
  }


  /**
   * Converts uri to an array of its components<br/>
   * <p>&lt;schema&gt;://[[&lt;login&gt;:&lt;password&gt;@]&lt;host&gt;[:&lt;port&gt;]]/[&lt;path&gt;][?&lt;parameters&gt;][#&lt;anchor&gt;]<br>
   * <p>Some uri components might be absent in the input string. In this case
   * the corresponding element in the resulting array should be {@code null}
   * <p> For example "ftp://1.2.3.4:25/pass0/pass1/pass2?a=&b=2#anchor"
   * -> ["ftp", null, null, "1.2.3.4", "25", "pass0/pass1/pass2", "a=1&b=2", "anchor"]
   * <p>Use only methods of {@code String} class to parse the input string.</p>
   *
   * @param uri string that contains resource identifier
   * @return array of strings where<br/>
   * [0] - schema<br/>
   * [1] - login<br/>
   * [2] - password<br/>
   * [3] - host<br/>
   * [4] - port<br/>
   * [5] - path<br/>
   * [6] - parameters<br/>
   * [7] - anchor<br/>
   */
  @Override
  public String[] uri2Array(String uri) {
    if(uri == null || uri.trim().isEmpty()) {
      return null;
    }

    String[] uriParts = new String[8];
    int currIndex = 0;

    String[] uriSplitKeys = {"://", "/", "@", ":", "?", "#"};



    // For Schema...
    uri = separateUriParts(uri, uriSplitKeys[currIndex], uriParts, currIndex, 3);
    currIndex++;

    // Splitting the Uri in 2 parts. 1): log_pass_host_port, 2): path_params_anchor
    int slashIndex = uri.indexOf(uriSplitKeys[1]);
    String log_pass_host_port = uri.substring(0, slashIndex);
    String path_params_anchor = uri.substring(slashIndex + 1);

    // For login and password
    // we can also put this on separateUriParts function, but it will use more resources that way...
    int endIndexOfLogAndPass = log_pass_host_port.indexOf(uriSplitKeys[2]);
    if(endIndexOfLogAndPass == -1) {
      uriParts[currIndex] = null;
      uriParts[currIndex + 1] = null;
    }
    else {
      String logAndPass = log_pass_host_port.substring(0, endIndexOfLogAndPass);
      int indexOfColonInLogAndPass = logAndPass.indexOf(":");

      if(indexOfColonInLogAndPass == -1) {
        uriParts[currIndex + 1] = null;
        indexOfColonInLogAndPass = endIndexOfLogAndPass;
      }
      else {
        uriParts[currIndex + 1] = logAndPass.substring(indexOfColonInLogAndPass + 1);
      }

      uriParts[currIndex] = logAndPass.substring(0, indexOfColonInLogAndPass);
      log_pass_host_port = log_pass_host_port.substring(endIndexOfLogAndPass + 1);
    }
    currIndex += 2;


    // For Host and Port
    String port = separateUriParts(log_pass_host_port, uriSplitKeys[currIndex], uriParts, currIndex, 1);
    currIndex++;

    if(port == null || port.trim().isEmpty()) {
      uriParts[currIndex] = null;
    }
    else {
      uriParts[currIndex] = port;
    }
    currIndex++;

    // For path and parameters...
    for(int i = 0; i < 2; i++) {
      path_params_anchor = separateUriParts(path_params_anchor, uriSplitKeys[4 + i], uriParts, currIndex, 1);
      currIndex++;
    }

    // For anchor...
    if(path_params_anchor.isEmpty() || path_params_anchor.endsWith("/")) {
      uriParts[currIndex] = null;
    }
    else {
      uriParts[currIndex] = path_params_anchor;
    }

    return uriParts;
  }

  /**
   * Converts a combination of words to camel case regardless of their original
   * case. The only exception is the first letter which should keep its case
   * unchanged.
   * <p>Words are separated by spaces and/or commas.
   * <p>There might be leading and/or trailing spaces in {@code str}.
   * <p>For example "HeLlO jAvA, WoRlD" -> "HelloJavaWorld"
   *
   * @param str usual string
   * @return string in camel case<br/>
   * or an empty string if {@code null} or an empty string is passed as an argument
   */
  @Override
  public String toCamelCase(String str) {
    if (str == null || str.trim().isEmpty()) {
      return "";
    }

    // this will split the string by ',' and space....
    String[] words = str.trim().split("[ ,]+");
    StringBuilder camelCaseString = new StringBuilder();


    if (words.length != 0) {
      // to keep the 1st letter in original case...
      camelCaseString.append(words[0].charAt(0)).append((words[0].substring(1).toLowerCase()));
    }

    for (int i = 1; i < words.length; i++) {
      String word = words[i].toLowerCase();
      camelCaseString.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1));
    }

    return camelCaseString.toString();
  }


  /**
   * Converts a string in camel case into a combination of words in lower
   * case. The only exception is the first letter which should keep its case
   * unchanged.
   * <p>For example "HelloJavaWorld" -> "Hello java world"
   *
   * @param camelStr string in camel case
   * @return usual string<br/>
   * or an empty string if {@code null} or an empty string is passed as an argument
   */
  @Override
  public String fromCamelCase(String camelStr) {
    if (camelStr == null || camelStr.isEmpty()) {
      return "";
    }

    StringBuilder stringBuilder = new StringBuilder();

    for (int i = 0; i < camelStr.length(); i++) {
      char ch = camelStr.charAt(i);
      // i != 0 ensure that it skip the 1st letter, or we can use i = 1...
      if (i != 0 && Character.isUpperCase(ch)) {
        stringBuilder.append(" ").append(Character.toLowerCase(ch));
      } else {
        stringBuilder.append(ch);
      }
    }

    return stringBuilder.toString();
  }

  private String[] checkIpFormat(String ip) {

    if (ip == null || ip.isEmpty()) {
      return null;
    }

    String[] parts = ip.split("\\.");

    if (parts.length != 4) {
      return null;
    }

    // extra check for inputs like 127.0..1
    for(int i = 0; i < parts.length; i++) {
      parts[i] = parts[i].trim();
      if(parts[i].isEmpty()) {
        return null;
      }
    }

    return parts;
  }

  private String separateUriParts(String uri, String uriSplitKey, String[] uriParts, int currIndex, int moves) {
    int index = uri.indexOf(uriSplitKey);

    if(index != -1) {
      String currentUriPart = uri.substring(0, index).trim();
      if (!currentUriPart.isEmpty()) {
        uriParts[currIndex] = currentUriPart;
      } else {
        uriParts[currIndex] = null;
      }
      uri = uri.substring(index + moves);
    }
    else {
      // Extra check it the host is available but port is missing
      if(!uri.trim().isEmpty() && uriSplitKey.equals(":")) {
        uriParts[currIndex] = uri;
        return null;
      }
      uriParts[currIndex] = null;
    }

    return uri;
  }

  private byte convertToByte(int value) {
    return (byte) value;
  }
}
